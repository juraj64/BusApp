package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.exception.NoneFreeBusOrDriver;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.WrongKey;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import com.jurajlazovy.bus.serviceapi.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	// Kedze fomular nepouzivam, tak tam tato metoda, ani make.jsp nemusia vobec byt
    @RequestMapping(value = "/seat/form/reserve", method = RequestMethod.GET)
    public String reserveForm(ModelMap modelMap) {
        modelMap.addAttribute("entity", new Seat());
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


}
