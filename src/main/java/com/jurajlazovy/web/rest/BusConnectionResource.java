package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.BusConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of BusConnectionResource.
 */
@Controller
public class BusConnectionResource extends BusConnectionResourceBase {

	public BusConnectionResource() {
	}

	@RequestMapping(value = "/busConnection/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		BusConnection entity = new BusConnection();
		modelMap.addAttribute("entity", entity);
		return "busConnection/create";
	}

	// Doplnena overwritnuta metoda pre vytvorenie novy busConnections, aby zaroven bola schopna nacitat seats
	@RequestMapping(value = "/busConnection", method = RequestMethod.POST)
	public String create(@RequestBody BusConnection entity) {
		entity.getSeats().forEach(e->e.setDirection(entity));
		return super.create(entity);
	}


}
