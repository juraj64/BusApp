package com.jurajlazovy.web.rest;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.DriverRepository;
import com.jurajlazovy.bus.exception.DriverNotFoundException;
import com.jurajlazovy.bus.exception.NoneFreeBusOrDriver;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import com.jurajlazovy.bus.serviceapi.BusService;
import com.jurajlazovy.bus.serviceapi.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of BusConnectionResource.
 */
@Controller
public class BusConnectionResource extends BusConnectionResourceBase {

    private BusConnection entity;

    public BusConnectionResource() {
    }

    @Autowired
    private BusConnectionService busConnectionService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private BusService busService;

    @RequestMapping(value = "/busConnection/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        BusConnection entity = new BusConnection();
        modelMap.addAttribute("entity", entity);
        return "busConnection/create";
    }

    // Doplnena overwritnuta metoda pre vytvorenie novej busConnections
    @RequestMapping(value = "/busConnection", method = RequestMethod.POST)
    public String create(@RequestBody BusConnection entity) {

        Long driverId = entity.getDriver().getId(); // doplnenie moznosti nacitat drivera podla id
        if (driverId != null) {
            try {
                Driver driver = driverService.findById(serviceContext(), driverId);
                entity.setDriver(driver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Long busId = entity.getBus().getId(); // doplnenie moznosti nacitat bus podla id
        if (busId != null) {
            try {
                Bus bus = busService.findById(serviceContext(), busId);
                entity.setBus(bus);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Doplnene a rozsirene su tiez show.jsp a showlist.jsp o driverName a busSpz. Umoznuje tak nacitat
        // busConnection (nie je vsak mozne nacitat driverId a busId, preto je treba pouzit curl prikaz)

        entity.getSeats().forEach(e -> e.setDirection(entity)); // aby sme vedeli nacitat seats
        return super.create(entity);
    }

    // snaha o spustenie metody (spolu s metodou make). Vytvorenie formulara
    @RequestMapping(value = "/busConnection/form/make", method = RequestMethod.GET)
    public String makeForm(ModelMap modelMap) {
        modelMap.addAttribute("entity", new BusConnection());
        return "busConnection/make";
    }
    // spustenie vlastnej metody makeConnection. Funguje pomocou curl prikazu
    @RequestMapping(value = "/busConnection/make", method = RequestMethod.POST)
    public String make(@RequestBody BusConnection entity) throws NoneFreeBusOrDriver {
        entity.getSeats().forEach(e -> e.setDirection(entity)); // aby sme vedeli nacitat seats

        String destination = entity.getDestination();
        int minSeats = entity.getMinSeats();
        int startHours = entity.getStartHours();
        int startMinutes = entity.getStartMinutes();
        int durationMinutes = entity.getDurationMinutes();
        BusConnection result = busConnectionService.makeConnection(serviceContext(), destination, minSeats,
                startHours, startMinutes, durationMinutes);

        return String.format("redirect:/rest/busConnection/%s", result.getId());
    }
}
