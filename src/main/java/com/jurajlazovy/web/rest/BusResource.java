package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of BusResource.
 */
@Controller
public class BusResource extends BusResourceBase {
	int i = 100;

	public BusResource() {
	}

	@RequestMapping(value = "/bus/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		Bus entity = new Bus(i++);
		//Bus entity = new Bus();
		modelMap.addAttribute("entity", entity);
		return "bus/create";
	}

}
