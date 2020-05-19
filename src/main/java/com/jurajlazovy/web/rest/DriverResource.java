package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of DriverResource.
 */
@Controller
public class DriverResource extends DriverResourceBase {

	public DriverResource() {
	}

	@RequestMapping(value = "/driver/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		Driver entity = new Driver();
		modelMap.addAttribute("entity", entity);
		return "driver/create";
	}

	// Doplnena overwritnuta metoda pre vytvorenie noveho drivera, aby zaroven bola schopna nacitat connections
	// Bez tejto metody nefunguje input Driver cez curl
	@RequestMapping(value = "/driver", method = RequestMethod.POST)
	public String create(@RequestBody Driver entity) {
		entity.getConnections().forEach(e -> e.setDriver(entity));
		return super.create(entity);
	}

}
