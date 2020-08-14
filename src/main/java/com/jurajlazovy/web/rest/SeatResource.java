package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
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

}
