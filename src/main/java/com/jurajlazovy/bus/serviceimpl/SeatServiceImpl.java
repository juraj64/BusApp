package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.WrongKey;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // Rezervuj sedadlo,
    // ak je obsadene (Paid) alebo bolo rezervovane pred menej ako 10 minutami, vyhod exception SeatAlreadyReserved
    // To znamena ak je sice rezervovane ale dlhsie ako 10 minut vygeneruj novy kluc a urob novu rezervaciu
    // Ak je mozne rezervovat sedadlo vygeneruj 8 miestne cislo ktore nemoze zacinat 0 a vrat ako navratovu hodnotu - key
    public String reserveSeat(ServiceContext ctx, BusConnection direction, int seatNum) throws SeatAlreadyReserved {

        // toto je potrebne urobit takto, aby Hibernate nacital seats do direction
        List<Seat> allSeats = new ArrayList<>();
        Long directionId = direction.getId();
        if (directionId != null) {
            try {
                BusConnection busConnection = busConnectionRepository.findById(directionId);
                allSeats = busConnection.getSeats(); // takto dostanem seats pre direction
            } catch (Exception e) {
                System.out.println("There is no bus connection with id = " + directionId);
                e.printStackTrace();
                return "null";
            }
        }
        // hladanie seat v direction
        Seat mySeat = new Seat();
        try {
            mySeat = findSeatBySeatNo(ctx, allSeats, seatNum);
        } catch (Exception exception) {
            System.out.println("There is no seat with seat number = " + seatNum);
            return "null";
        }

        // rezervacia, ak nie je paid, alebo rezervovane kratsie ako 10 min.
        Date actualTime = new Date(); //aktualny cas
        long diffMillies = Math.abs(actualTime.getTime() - mySeat.getReservationDate().getTime());
        long diffMinutes = TimeUnit.MINUTES.convert(diffMillies, TimeUnit.MILLISECONDS);

        if ((mySeat.getSeatStatus() == SeatStatus.Paid) || // ak paid alebo reserved v ramci platneho intervalu
                (mySeat.getSeatStatus() == SeatStatus.Reserved && diffMinutes < 10)) {
            System.out.println("This seat is already reserved (not relevant for confirmation)");
            throw new SeatAlreadyReserved("SeatAlreadyReserved");
        } else { // ak free, alebo rezervacia vyprsala
            mySeat.setSeatStatus(SeatStatus.Reserved);
            mySeat.setReservationDate(actualTime);
            mySeat.setReservationKey(reservationKeyGenerator());
            System.out.println("Seat is now reserved. " + "Need to be confirmed and paid " +
                    "within 10 minutes with reservationKey = " + mySeat.getReservationKey());
            return mySeat.getReservationKey();
        }
    }

    // Potvrd rezervaciu po zaplateni
    // Ak je sedadlo Free tak rezervuj rovno bez kontroly kluca
    // Ak je rezervovane skontroluj kluc, ak sedi kluc, daj do stavu Paid, inac vrat chybu WrongKey
    public String confirmSeat(ServiceContext ctx, BusConnection direction, int seatNum, String reservationKey) throws WrongKey {

        // toto je potrebne urobit takto, aby Hibernate nacital seats do direction
        List<Seat> allSeats = new ArrayList<>();
        Long directionId = direction.getId();
        if (directionId != null) {
            try {
                BusConnection busConnection = busConnectionRepository.findById(directionId);
                allSeats = busConnection.getSeats(); // takto dostanem seats pre direction
            } catch (Exception e) {
                System.out.println("There is no bus connection with id = " + directionId);
                e.printStackTrace();
                return "null";
            }
        }
        // hladanie seat v direction
        Seat mySeat = new Seat();
        try {
            mySeat = findSeatBySeatNo(ctx, allSeats, seatNum);
        } catch (Exception exception) {
            System.out.println("There is no seat with seat number = " + seatNum);
            return "null";
        }

        String myKey = mySeat.getReservationKey();
        //System.out.println("My reservation key = " + myKey);

        if ((mySeat.getSeatStatus() == SeatStatus.Free) || // ak je free, alebo reserved so správnym reservationKey
                (mySeat.getSeatStatus() == SeatStatus.Reserved && myKey.equals(reservationKey))) {
            mySeat.setSeatStatus(SeatStatus.Paid);
            System.out.println("Your seat reservation is confirmed.");
            return "confirmed";
        } else {
            System.out.println("Seat is already reserved or wrong reservation key. Try again.");
            throw new WrongKey("Seat is already reserved or wrong reservation key. Try again.");
        }
    }

    public Seat findSeatBySeatNo(ServiceContext ctx, List<Seat> allSeats, int seatNum) throws Exception {
        for (Seat seat : allSeats) {
            if (seat.getSeatNo() == seatNum) {
                return seat;
            }
        }
        throw new Exception("seatNum does not exist");
    }

    public String reservationKeyGenerator() {
        Random random = new Random();
        int max = 99999999;
        int min = 9999999;
        int int_key = random.nextInt(max - min) + min;
        return String.valueOf(int_key);
    }

    // Najdi Seats podla zadanych kriterii (alternativa SELECT)
    public List<Seat> findSeatsByCondition(ServiceContext ctx, int seatNo, String reservationKey) {

        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Seat.class)
                .withProperty(SeatProperties.seatNo()).eq(seatNo) // rovna sa zadanemu seatNo
                .withProperty(SeatProperties.reservationKey()).lessThan(reservationKey) // mensie ako zadane key
                .withProperty(SeatProperties.seatStatus()).eq(SeatStatus.Paid) // je paid
                .orderBy(SeatProperties.reservationDate()).build(); // zorad podla reservation date

        return seatRepository.findByCondition(criteria);
    }

    // Najdi Seats podla zadanych kriterii (alternativa SELECT) - druhy variant
    // seats, ktore nie su free a zorad ich podla datumu rezervacie zostupne
    public List<Seat> findSeatsByConditionTwo(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Seat.class)
                .withProperty(SeatProperties.seatStatus()).in(SeatStatus.Reserved, SeatStatus.Paid)
                .orderBy(SeatProperties.reservationDate()).descending().build();

        return seatRepository.findByCondition(criteria);
    }

    // Najdi Seats podla zadanych kriterii (alternativny JOIN SELECT)
    public List<Seat> findSeatsJoinByCondition(ServiceContext ctx, int seatNo) {

        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Seat.class)
                .withProperty(SeatProperties.seatNo()).greaterThanOrEqual(seatNo) // pre s.seatNo vacsie ako
                .orderBy(SeatProperties.direction().destination()) // zorad podla bc.destination
                .orderBy(SeatProperties.seatNo()).build(); // a tiez podla s.seatNo

        return seatRepository.findByCondition(criteria);
    }


}


