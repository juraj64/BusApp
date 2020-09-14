package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.serviceapi.BusService;
import com.jurajlazovy.bus.serviceapi.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Implementation of BusResource.
 */
@Controller
public class BusResource extends BusResourceBase {
	int i = 100;

	public BusResource() {
	}

	@Autowired
	private BusService busService;

	@RequestMapping(value = "/bus/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		//Bus entity = new Bus(i++);
		Bus entity = new Bus();
		modelMap.addAttribute("entity", entity);
		return "bus/create";
	}

	// spustenie metody findSeatsByCondition. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/bus/find", method = RequestMethod.POST)
	public String find(@RequestBody Bus entity) {

		int numberOfSeats = entity.getNumberOfSeats();
		List<Bus> result = busService.findBusesByCondition(serviceContext(), numberOfSeats);

		System.out.println("size = " + result.size());

		for (Bus bus : result) {
			System.out.println("Bus ECV = " + bus.getBusSpz() +
					", number of seats = " + bus.getNumberOfSeats());
		}
		return String.format("redirect:/rest/bus/%s", result);
	}

}
