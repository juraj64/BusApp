package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private BusConnectionService busConnectionService;

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

	// Snaha o zapis noveho drivera cez curl tak, ze busConnection len Id. Vyhadzuje chybu. Neviem preco.
//	@RequestMapping(value = "/driver", method = RequestMethod.POST)
//	public String create(@RequestBody Driver entity) {
//
//		for (BusConnection connection: entity.getConnections()) {
//			Long connId = connection.getId();
//			if (connId != null) {
//				try {
//					BusConnection direction = busConnectionService.findById(serviceContext(),connId);
//					direction.setDriver(entity);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return super.create(entity);
//	}

}
