package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.domain.SeatStatus;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.SeatNotFoundException;
import org.junit.Assert;
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

	@Override
	public void testReserveSeat() throws Exception {

	}

	@Override
	public void testConfirmSeat() throws Exception {

	}

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

		// Toto nižšie nacitat nechce. Zrejme kvôli formátu v tabuľke SeatServiceTest.xml. Skúšal som aj iné formáty
		//  pre Date Tue Apr 28 18:13:35 CEST 2020, resp. pre LocalDateTime: 2020-04-28T18:13:35.648,
		//  ale to zas potom vyhadzuje inú chybu. Zdá sa, že to proste nevie načítať
//		LocalDateTime ldt1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
//		System.out.println("LocalDateTime1: " + ldt1);

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
	public void reserveSeat() throws Exception {
		List<BusConnection> myConnections = busConnectionService.findAll(getServiceContext());
		for(BusConnection connection : myConnections) {
            String key = seatService.reserveSeat(getServiceContext(), connection, connection.getSeats().get(0).getSeatNo());
            Assert.assertTrue(key != null);
            try {
                seatService.reserveSeat(getServiceContext(), connection, connection.getSeats().get(1).getSeatNo());
                Assert.fail();
            } catch (SeatAlreadyReserved sr) {
                Assert.assertTrue(sr != null);
            }
		}

		//		BusConnection direction = busConnectionService.findById(getServiceContext(), 3L);
//		seatService.reserveSeat(getServiceContext(), direction, 11);

	}


	@Test
	public void confirmSeat() throws Exception {
        BusConnection direction = busConnectionService.findById(getServiceContext(), 2L);
        String myKey = seatService.reserveSeat(getServiceContext(),direction, 5);
		System.out.println("Confirmation after reservation");
		seatService.confirmSeat(getServiceContext(), direction, 5, myKey);

	}
}
