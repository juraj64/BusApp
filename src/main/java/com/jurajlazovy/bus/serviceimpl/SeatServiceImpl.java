package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.WrongKey;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of SeatService.
 */
@Service("seatService")
public class SeatServiceImpl extends SeatServiceImplBase {

	@Autowired
	private BusConnectionRepository busConnectionRepository;
	@Autowired
	private BusRepository busRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private SeatRepository seatRepository;

	public SeatServiceImpl() {
	}

	// Rezervuj sedadlo, ak je obsadene (Paid) alebo bolo rezervovane pred menej ako 10 minutami
	// vyhod exception SeatAlreadyReserved
	// To znamena ak je sice rezervovane ale dlhsie ako 10 minut vygeneruj novy kluc a urob novu rezervaciu
	// Ak je mozne rezervovat sedadlo vygeneruj 8 miestne cislo ktore nemoze zacinat 0
	// a vrat ako navratovu hodnotu - key
	public String reserveSeat(ServiceContext ctx, BusConnection direction, int seatNum) throws SeatAlreadyReserved {
		// find Seat by seatNum
		Seat mySeat = new Seat();
		List<Seat> allSeats = direction.getSeats();
		for(Seat seat : allSeats) {
			if(seat.getSeatNo() == seatNum) {
				mySeat = seat;
				break;
			}
		}
		// rezervacia, ak nie je paid, alebo rezervovane kratsie ako 24 h
		Date actualTime = new Date(); //aktualny cas
		long diffMillies = Math.abs(actualTime.getTime() - mySeat.getReservationDate().getTime());
		long diffHours = TimeUnit.HOURS.convert(diffMillies, TimeUnit.MILLISECONDS);

		if ((mySeat.getSeatStatus() == SeatStatus.Paid) || // ak paid alebo reserved v ramci platneho intervalu
				(mySeat.getSeatStatus() == SeatStatus.Reserved && diffHours < 24)) {
			System.out.println("SeatAlreadyReserved with ReservationKey = " + mySeat.getReservationKey());
			// neviem ako s tymto exceptionom, Palo
			//throw new SeatAlreadyReserved("SeatAlreadyReserved with ReservationKey = " + mySeat.getReservationKey());
		} else { // ak free, alebo rezervacia vyprsala
			mySeat.setSeatStatus(SeatStatus.Reserved);
			mySeat.setReservationDate(actualTime);
			mySeat.setReservationKey(reservationKeyGenerator());
			System.out.println("Seat now reserved with new ReservationKey = " + mySeat.getReservationKey());
		}
		return mySeat.getReservationKey();
	}

	public String reservationKeyGenerator() {
		Random random = new Random();
		int max = 99999999;
		int min = 9999999;
		int int_key = random.nextInt(max - min) + min;
		return String.valueOf(int_key);
	}

	// Potvrd rezervaciu po zaplateni
	// Ak je sedadlo Free tak rezervuj rovno bez kontroly kluca
	// Ak je rezervovane skontroluj kluc, ak sedi kluc daj do stavu Paid inac vrat chybu WrongKey
	public String confirmSeat(ServiceContext ctx, int seatNum, String reservationKey) throws WrongKey {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("confirmSeat not implemented");
	}

}
