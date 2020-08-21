package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.NoneFreeBusOrDriver;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of BusConnectionService.
 */
@Service("busConnectionService")
public class BusConnectionServiceImpl extends BusConnectionServiceImplBase {

	@Autowired
	private BusConnectionRepository busConnectionRepository;
	@Autowired
	private BusRepository busRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private SeatRepository seatRepository;

	public BusConnectionServiceImpl() {
	}

	// Najdi volny autobus a najdi volneho vodica tak ze
	// - Autobus nemoze byt na inej linke
	// - Vodic moze robit max 12 hodin potom musi 12 hodin oddychovat
	// - Vodic musi mat oddych od predchadzajuceho spoja minimalne 10 minut
	// - Treba vyrobit aj Seats v pocte Bus.numberOfSeats
	public BusConnection makeConnection(ServiceContext ctx, String destination, int minSeats, int startHours,
										int startMinutes, int durationMinutes) throws NoneFreeBusOrDriver {
		System.out.println("Make new connection for destination " + destination);
		List<BusConnection> allConnections = busConnectionRepository.findAll();

		// najdenie volneho busu
		List<Bus> freeBuses = busRepository.findAll(); // vsetky busy

		// vyradenie busov, ktore nie su dostatocne velke
		freeBuses.removeIf(smallBus -> smallBus.getNumberOfSeats() < minSeats);

		// vyradenie obsadenych busov
		for (BusConnection connection : allConnections) {
			Bus reservedBus = connection.getBus();
			freeBuses.remove(reservedBus);
		}

		if (freeBuses.isEmpty()) {
			System.out.println("There is no free bus to make new connection");
			throw new NoneFreeBusOrDriver("None Free Bus");
		}

		System.out.println("There are " + freeBuses.size() + " non-occupied bus(es).");
		for(Bus bus : freeBuses) {
			System.out.println("   Bus`s ID number: " + bus.getBusSpz());
		}

		// najdenie volneho drivera
		int startTime = startHours * 60 + startMinutes;
		int finishTime = startTime + durationMinutes;
		List<Driver> freeDrivers = driverRepository.findAll(); // vsetci driveri
		// treba ist takto cez connections, lebo cez driverov to vyhadzuje chyby
		for (BusConnection connection : allConnections) {
			Driver driver = connection.getDriver();
			boolean driverCurrentOccupation = true;
			int connectionStart = connection.getStartHours() * 60 + connection.getStartMinutes();
			int connectionFinish =  connectionStart + connection.getDurationMinutes();
			if ((connectionFinish+10) < startTime || finishTime < (connectionStart-10)) { // 10 min. pred a po volno
				driverCurrentOccupation = false; // driver je volny
			}

			if (driverDailyOccupation(driver) >= 720 || driverCurrentOccupation) { // ak plati jedno alebo druhe, driver nie je volny
				freeDrivers.remove(driver);
			}
		}

		if (freeDrivers.isEmpty()) {
			System.out.println("There is no free driver to make new connection");
			throw new NoneFreeBusOrDriver("None Free Driver");
		}

		System.out.println("There are " + freeDrivers.size() + " vacant driver(s).");
		for(Driver driver : freeDrivers) {
			System.out.println("   Driver`s name: " + driver.getName());
		}

		Bus myBus = freeBuses.get(0); // vyberieme prvy dostupny bus
		//a drivera co ma najmenej najazdene
		int myDriverOccupation = 1440;
		Driver myDriver = new Driver();

		for (Driver driver : freeDrivers) {
			int currentDriverOccupation = driverDailyOccupation(driver);
			if (currentDriverOccupation < myDriverOccupation) {
				myDriverOccupation = currentDriverOccupation;
				myDriver = driver;
			}
		}

		Date actualTime = new Date();
		BusConnection connection = new BusConnection(); // vytvorim new connection
		connection.setDestination(destination);
		connection.setMinSeats(minSeats);
		connection.setStartHours(startHours);
		connection.setStartMinutes(startMinutes);
		connection.setDurationMinutes(durationMinutes);
		connection.setBus(myBus);
		connection.setDriver(myDriver);
		for (int i = 0; i < myBus.getNumberOfSeats(); i++) {
			Seat seat = new Seat();
			seat.setSeatNo(i+1);
			seat.setSeatStatus(SeatStatus.Free);
			seat.setReservationDate(actualTime);
			seat.setReservationKey("null");
			connection.addSeat(seat);
		}
		busConnectionRepository.save(connection);

		System.out.println("-----------------------");
		System.out.println("We created new connection ");
		System.out.println("-----------------------");
		System.out.println("   Destination: " + connection.getDestination());
		System.out.println("   Driver`s name: " + connection.getDriver().getName());
		System.out.println("   Bus`s ID number: " + connection.getBus().getBusSpz());
		System.out.println("   Number of seats: " + connection.getBus().getNumberOfSeats());

		return connection;
	}

	// celkovy pocet minut, ktory denne odjazdi konkretny driver
	public int driverDailyOccupation (Driver driver) {
		int totalDuration = 0;

		for (int i=0; i < driver.getConnections().size(); i++) {
			BusConnection connection = driver.getConnections().get(i);
			totalDuration += connection.getDurationMinutes();
		}
		return totalDuration;
	}

	// Prescanuje vsetky sedadla vsetkych autobusov a ak je rezervacia starsia ako 10 minut tak ju zrusi
	public void freeReservedSeats(ServiceContext ctx) {
		Date actualTime = new Date(); //aktualny cas
		List<BusConnection> allConnections = busConnectionRepository.findAll();

		for(BusConnection connection : allConnections) {
			for(int i=0; i< connection.getBus().getNumberOfSeats(); i++) {

				if(connection.getSeats().get(i).getSeatStatus() == SeatStatus.Reserved) {
					Date reservationDate = connection.getSeats().get(i).getReservationDate();
					long diffMillies = Math.abs(actualTime.getTime() - reservationDate.getTime());
					long diffMinutes = TimeUnit.MINUTES.convert(diffMillies, TimeUnit.MILLISECONDS);
					System.out.println("DiffMinutes = " + diffMinutes); // standardne to tam nemusi byt

					if(diffMinutes > 10) {
						connection.getSeats().get(i).setSeatStatus(SeatStatus.Free);
						connection.getSeats().get(i).setReservationKey("null");
					}
				}
			}
		}
		// výstup (standardne to tam nemusi byt)
		for(BusConnection connection : allConnections) {
			System.out.println("BusConnection: " + connection.getDestination());
			for(int i=0; i< connection.getBus().getNumberOfSeats(); i++) {
				System.out.println("  seatNo: " + connection.getSeats().get(i).getSeatNo() +
						" seatStatus: " + connection.getSeats().get(i).getSeatStatus());
			}
		}
	}

}
