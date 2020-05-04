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
        Seat mySeat = findSeatBySeatNo(ctx, direction, seatNum);

		// rezervacia, ak nie je paid, alebo rezervovane kratsie ako 24 h
		Date actualTime = new Date(); //aktualny cas
		long diffMillies = Math.abs(actualTime.getTime() - mySeat.getReservationDate().getTime());
		long diffHours = TimeUnit.HOURS.convert(diffMillies, TimeUnit.MILLISECONDS);

		if ((mySeat.getSeatStatus() == SeatStatus.Paid) || // ak paid alebo reserved v ramci platneho intervalu
				(mySeat.getSeatStatus() == SeatStatus.Reserved && diffHours < 24)) {
			System.out.println("This seat is already reserved (not relevant for confirmation)");
			// neviem ako s tymto exceptionom, Palo
			//throw new SeatAlreadyReserved("SeatAlreadyReserved with ReservationKey = " + mySeat.getReservationKey());
		} else { // ak free, alebo rezervacia vyprsala
			mySeat.setSeatStatus(SeatStatus.Reserved);
			mySeat.setReservationDate(actualTime);
			mySeat.setReservationKey(reservationKeyGenerator());
			System.out.println("Seat is now reserved. " +
					"Need to be paid and confirmed with reservationKey = " + mySeat.getReservationKey());
		}
		return mySeat.getReservationKey();
	}

	public Seat findSeatBySeatNo(ServiceContext ctx, BusConnection direction, int seatNum) {
        Seat mySeat = new Seat();
        List<Seat> allSeats = direction.getSeats();
        for(Seat seat : allSeats) {
            if(seat.getSeatNo() == seatNum) {
                mySeat = seat;
                break;
            }
        }
        return mySeat;
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

	// Nefunguje to zatial tak, ako by som si predstavoval, lebo to nevie nacitat data z db, ale len z testovacej
	// tabulky. Preto pri konfimacii musim vzdy najprv urobit rezervaciu, co je vlastne blbost
    public String confirmSeat(ServiceContext ctx, BusConnection direction, int seatNum, String reservationKey) throws WrongKey {
		Seat mySeat = findSeatBySeatNo(ctx, direction, seatNum);
		String myKey;
//		String myKey = mySeat.getReservationKey(); // takto to nejde zatial, lebo berie data len z testovacej tabulky
//		System.out.println("My reservation key = " + myKey);

		try { // takze to robim takto, co je vsak v praxi asi blbost
			myKey = reserveSeat(ctx,direction,seatNum);
			//System.out.println("My reservation key = " + myKey);
		} catch (SeatAlreadyReserved sar) {
			myKey = null;
		}

		if((mySeat.getSeatStatus() == SeatStatus.Free) || // ak je free, alebo reserved so správnym reservationKey
				((mySeat.getSeatStatus() == SeatStatus.Reserved && myKey.length() == 8) && myKey.equals(reservationKey))) {
			mySeat.setSeatStatus(SeatStatus.Paid);
			System.out.println("Your seat reservation is confirmed.");
		} else {
			// neviem ako s tymto exceptionom, Palo
			//throw new WrongKey("Seat is already reserved or wrong reservation key. Try again.");
			System.out.println("Seat is already reserved or wrong reservation key. Try again.");
		}
		return reservationKey;
    }
}
