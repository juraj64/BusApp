package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.domain.SeatStatus;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.SeatNotFoundException;
import com.jurajlazovy.bus.exception.WrongKey;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class SeatServiceTest extends AbstractDbUnitJpaTests implements SeatServiceTestBase {

	@Autowired
	protected SeatService seatService;
	@Autowired
	protected BusConnectionService busConnectionService;

	@Test
	public void testFindById() throws Exception {
		Seat seat = seatService.findById(getServiceContext(),3L);
		assertEquals(5, seat.getSeatNo());
	}

	@Test
	public void testFindAll() throws Exception {
		List<Seat> seats = seatService.findAll(getServiceContext());
		assertEquals(SeatStatus.Paid, seats.get(2).getSeatStatus());
	}

	@Test
	public void testSave() throws Exception {
		BusConnection direction = busConnectionService.findById(getServiceContext(), 1L);
		Seat seat = new Seat();
		seat.setSeatNo(10);
		seat.setSeatStatus(SeatStatus.Free);
		LocalDateTime localDate = LocalDateTime.now(); //aktualny cas
		Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()); // konverzia z LocalDateTime do Date
		seat.setReservationDate(date);
		seat.setReservationKey("free");
		seat.setDirection(direction);
		Seat newSeat = seatService.save(getServiceContext(), seat);
		assertEquals(10, newSeat.getSeatNo());
		assertEquals(10, newSeat.getDirection().getMinSeats());

		// Otestovanie Duration v LocalDateTime
		Seat otherSeat = new Seat();
		otherSeat.setSeatNo(12);
		otherSeat.setSeatStatus(SeatStatus.Reserved);
		LocalDateTime otherLocalDate = localDate.minusHours(1L);
		Date otherDate = Date.from(otherLocalDate.atZone(ZoneId.systemDefault()).toInstant());
		otherSeat.setReservationDate(otherDate);
		System.out.println("First seat reservation date: " + date);
		System.out.println("Other seat reservation date: " + otherDate);
		Duration duration = Duration.between(localDate, otherLocalDate);
		long diff = Math.abs(duration.toMinutes());
		assertEquals(diff, 60);

		// Otestovanie časového rozdielu vo formáte Data
		Seat seat1 = seatService.findById(getServiceContext(), 1L);
		Date date1 = seat1.getReservationDate();
		Seat seat2 = seatService.findById(getServiceContext(), 2L);
		Date date2 = seat2.getReservationDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
		System.out.println("Date1: " + date1); // Nacita iba date a nie time. A neviem to nijako vyriešiť
		System.out.println("Date2: " + sdf.format(date2)); // v hore nastavenom formáte, date ok, time divno
		long diffMillies = Math.abs(date1.getTime() - date2.getTime()); // počíta iba rozdiel v dňoch (hodiny ignoruje)
		long diffHours = TimeUnit.HOURS.convert(diffMillies, TimeUnit.MILLISECONDS); // rozdiel v hodinách (3 x 24)
		System.out.println("diffHours: " + diffHours);

		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss", Locale.ENGLISH); // iný formáte
		Date firstDate = sdf2.parse("06/24/2017 00:00:00");
		Date secondDate = sdf2.parse("06/30/2017 04:30:00");
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		long diffInMinutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		System.out.println("firstDate: " + firstDate);
		System.out.println("secondDate: " + sdf2.format(secondDate));
		System.out.println("diffInMillies: " + diffInMillies);
		System.out.println("diffInDays: " + diffInDays);
		System.out.println("diffInMinutes: " + diffInMinutes);

		// converting java.util.Date to java.time.LocalDateTime.
		// Toto funguje, lebo načítam aktuálny PC čas a nie čas z testovacej tabuľky
		Date now = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
		LocalDateTime ldtPlus = localDate.plusMinutes(145L);
		Duration dr = Duration.between(ldtPlus, ldt);
		long plus = Math.abs(dr.toMinutes());
		System.out.println("value of Date: " + now);
		System.out.println("value of LocalDateTime: " + ldt);
		System.out.println("value of Duration in Minutes: " + plus);
		// converting java 8 LocalDateTime to java.util.Date
		Date output = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		Date outputPlus = Date.from(ldtPlus.atZone(ZoneId.systemDefault()).toInstant());
		System.out.println("value of LocalDateTimePlus: " + ldtPlus);
		System.out.println("value of DatePlus: " + outputPlus);
	}

	@Test
	public void testDelete() throws Exception {
		Seat seat = seatService.findById(getServiceContext(),3L);
		seatService.delete(getServiceContext(),seat);
		boolean exception = false;

		try {
			Seat seatDel = seatService.findById(getServiceContext(),3L);
			fail();
		} catch (SeatNotFoundException snfe) {
			exception = true;
		}
		assertTrue(exception);
	}

	@Test
	public void testReserveSeat() throws Exception {
		// free seat, vygeneruje reservation key
		BusConnection direction1 = busConnectionService.findById(getServiceContext(), 1L);
		String reservationKey1 = seatService.reserveSeat(getServiceContext(), direction1, 10);
		assertNotNull(reservationKey1);

		// reserved seat, rezervácia vypršala, vygeneruje novy reservation key
		BusConnection direction2 = busConnectionService.findById(getServiceContext(), 3L);
		String reservationKey2 = seatService.reserveSeat(getServiceContext(), direction2, 12);
		//System.out.println("reservationKey2 = " + reservationKey2);
		assertNotEquals("12121212", reservationKey2);

		// paid seat, negeneruje kluc
		BusConnection direction3 = busConnectionService.findById(getServiceContext(), 2L);
		try {
			seatService.reserveSeat(getServiceContext(), direction3, 5);
			fail();
		} catch (SeatAlreadyReserved sr) {
			assertNotNull(sr);
		}

		 //reserved seat, negeneruje kluc (pri testovani aktualizovat cas rezervacie, aby bol v limite 10 min.)
		BusConnection direction4 = busConnectionService.findById(getServiceContext(), 3L);
		try {
			seatService.reserveSeat(getServiceContext(), direction4, 11);
			fail();
		} catch (SeatAlreadyReserved sr) {
			assertNotNull(sr);
		}

	}


	@Test
	public void testConfirmSeat() throws Exception {
		// free seat, po zaplateni do stavu paid a vrati confirmed
		BusConnection direction1 = busConnectionService.findById(getServiceContext(), 1L);
		String result1 = seatService.confirmSeat(getServiceContext(), direction1, 10, "null");
		assertEquals("confirmed", result1);

		// reserved seat, po zaplateni do stavu paid a vrati confirmed
		BusConnection direction2 = busConnectionService.findById(getServiceContext(), 3L);
		String result2 = seatService.confirmSeat(getServiceContext(), direction2, 12, "12121212");
		assertEquals("confirmed", result2);

		// reserved seat, zly reservationKey
		BusConnection direction3 = busConnectionService.findById(getServiceContext(), 3L);
		try {
			seatService.confirmSeat(getServiceContext(), direction3, 12, "1212");
			fail();
		} catch (WrongKey wk) {
			assertNotNull(wk);
		}

		// paid uz rezervovany seat
		BusConnection direction4 = busConnectionService.findById(getServiceContext(), 2L);
		try {
			seatService.confirmSeat(getServiceContext(), direction4, 5,"55555555");
			fail();
		} catch (WrongKey wk) {
			assertNotNull(wk);
		}

	}
}
