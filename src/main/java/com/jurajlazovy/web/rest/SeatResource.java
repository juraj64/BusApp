package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of SeatResource.
 */
@Controller
public class SeatResource extends SeatResourceBase {

	public SeatResource() {
	}

	@RequestMapping(value = "/seat/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		Seat entity = new Seat();
		modelMap.addAttribute("entity", entity);
		return "seat/create";
	}

}
