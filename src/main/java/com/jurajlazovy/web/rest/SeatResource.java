package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.NoneFreeBusOrDriver;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.WrongKey;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import com.jurajlazovy.bus.serviceapi.SeatService;
import org.omg.IOP.ServiceContext;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of SeatResource.
 */
@Controller
public class SeatResource extends SeatResourceBase {

    public SeatResource() {
    }

    @Autowired
    private SeatService seatService;

    @Autowired
    private BusConnectionService busConnectionService;

    @RequestMapping(value = "/seat/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        Seat entity = new Seat();
        modelMap.addAttribute("entity", entity);
        return "seat/create";
    }

    // Doplnena overwritnuta metoda pre vytvorenie noveho seatu o BusConnection ID.
    @RequestMapping(value = "/seat", method = RequestMethod.POST)
    public String create(@RequestBody Seat entity) {

        // doplnenie moznosti nacitat BusConnection podla id
        Long directionId = entity.getDirection().getId();
        if (directionId != null) {
            try {
                BusConnection busConnection = busConnectionService.findById(serviceContext(), directionId);
                entity.setDirection(busConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.create(entity);
    }

    // Vytvorenie formulara pre metodu reserveSeat.
    // Nefunguje to, pouzivam curl prikaz. Toto ani reserve.jsp tu v principe nemusia vobec byt
    @RequestMapping(value = "/seat/form/reserve", method = RequestMethod.GET)
    public String reserveForm(ModelMap modelMap, Seat entity) {
        modelMap.addAttribute("entity", entity);
        return "seat/reserve";
    }

    // spustenie metody reserveSeat. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/seat/reserve", method = RequestMethod.POST)
    public String reserve(@RequestBody Seat entity) throws SeatAlreadyReserved {

        int seatNum = entity.getSeatNo();

        Long directionId = entity.getDirection().getId();
        if (directionId != null) {
            try {
                BusConnection busConnection = busConnectionService.findById(serviceContext(), directionId);
                entity.setDirection(busConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        BusConnection direction = entity.getDirection();
        String result = seatService.reserveSeat(serviceContext(), direction, seatNum);
        return String.format("redirect:/rest/seat/%s", result);
    }

    // spustenie metody confirmSeat. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/seat/confirm", method = RequestMethod.POST)
    public String confirm(@RequestBody Seat entity) throws WrongKey {

        int seatNum = entity.getSeatNo();
        String reservationKey = entity.getReservationKey();

        Long directionId = entity.getDirection().getId();
        if (directionId != null) {
            try {
                BusConnection busConnection = busConnectionService.findById(serviceContext(), directionId);
                entity.setDirection(busConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        BusConnection direction = entity.getDirection();
        String result = seatService.confirmSeat(serviceContext(), direction, seatNum, reservationKey);
        return String.format("redirect:/rest/seat/%s", result);
    }

    // Vytvorenie formulara pre metodu findSeatsByCondition.
    // Zatial to nefunguje. Pouzivam preto len curl prikaz.
    @RequestMapping(value = "/seat/form/find", method = RequestMethod.GET)
    public String findForm(ModelMap modelMap, Seat entity) {
        modelMap.addAttribute("entity", entity);
        return "seat/find";
    }

    // spustenie metody findSeatsByCondition. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/seat/find", method = RequestMethod.POST)
    public String find(@RequestBody Seat entity) {

        int seatNo = entity.getSeatNo();
        String reservationKey = entity.getReservationKey();
        List<Seat> result = seatService.findSeatsByCondition(serviceContext(), seatNo, reservationKey);

        System.out.println("size = " + result.size());

        for(Seat seat : result) {
            System.out.println("seat number = " + seat.getSeatNo() +
                    ", reservation key = " + seat.getReservationKey() +
                    ", reservation date = " + seat.getReservationDate() +
                    ", destination = " + seat.getDirection().getDestination());
        }

        return String.format("redirect:/rest/seat/%s", result);
    }

    // spustenie metody findSeatsByConditionTwo. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/seat/findtwo", method = RequestMethod.POST)
    public String findTwo(@RequestBody Seat entity) {
        List<Seat> result = seatService.findSeatsByConditionTwo(serviceContext());
        System.out.println("size = " + result.size());

        for(Seat seat : result) {
            System.out.println("seat number = " + seat.getSeatNo() +
                    ", reservation date = " + seat.getReservationDate() +
                    ", destination = " + seat.getDirection().getDestination());
        }

        return String.format("redirect:/rest/seat/%s", result);
    }

    // spustenie metody findSeatsJoinsByConditionTwo. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/seat/findjoin", method = RequestMethod.POST)
    public String findjoin(@RequestBody Seat entity) {

        int seatNo = entity.getSeatNo();
        List<Seat> result = seatService.findSeatsJoinByCondition(serviceContext(), seatNo);
        System.out.println("size = " + result.size());

        for(Seat seat : result) {
            System.out.println("seat number = " + seat.getSeatNo() +
                    ", destination = " + seat.getDirection().getDestination());
        }

        return String.format("redirect:/rest/seat/%s", result);
    }


}
