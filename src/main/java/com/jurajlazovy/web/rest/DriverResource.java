package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.domain.SeatStatus;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import com.jurajlazovy.bus.serviceapi.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Implementation of DriverResource.
 */
@Controller
public class DriverResource extends DriverResourceBase {

	public DriverResource() {
	}

	@Autowired
	private BusConnectionService busConnectionService;

	@Autowired
	private DriverService driverService;

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

	// Vytvorenie formulara pre metodu findSeatsByCondition.
	// Zatial to nefunguje. Pouzivam preto len curl prikaz.
	@RequestMapping(value = "/driver/form/find", method = RequestMethod.GET)
	public String findForm(ModelMap modelMap) {
		Driver entity = new Driver();
		modelMap.addAttribute("entity", entity);
		return "driver/find";
	}

	// spustenie metody findDriversByCondition. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/driver/find", method = RequestMethod.POST)
	public String find(@RequestBody Driver entity) {

		int age = entity.getAge();

		List<Driver> result = driverService.findDriversByCondition(serviceContext());

		System.out.println("size = " + result.size());
		for (Driver driver : result) {
			System.out.println("name = " + driver.getName() + ", age = " + driver.getAge());
		}

		return String.format("redirect:/rest/driver/%s", result);
	}

	// spustenie metody findDriversByConditionTwo. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/driver/findtwo", method = RequestMethod.POST)
	public String findTwo(@RequestBody Driver entity) {
		int age = entity.getAge();
		List<Driver> result = driverService.findDriversByConditionTwo(serviceContext(), age);

		System.out.println("size = " + result.size());
		for (Driver driver : result) {
			System.out.println("name = " + driver.getName() + ", age = " + driver.getAge());
		}

		return String.format("redirect:/rest/driver/%s", result);
	}

}
