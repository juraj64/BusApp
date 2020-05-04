package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.*;
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
	public void makeConnection(ServiceContext ctx, String destination, int minSeats, int durationMinutes) {
		System.out.println("Make new connection for destination " + destination);
		// najdenie volneho busu a drivera
		List<Bus> freeBuses = busRepository.findAll(); // vsetky busy
		List<Driver> freeDrivers = driverRepository.findAll(); // vsetci driveri
		List<BusConnection> allConnections = busConnectionRepository.findAll();
		for (BusConnection connection : allConnections) {
			Bus reservedBus = connection.getBus();
			freeBuses.remove(reservedBus);
			Driver reservedDriver = connection.getDriver();
			freeDrivers.remove(reservedDriver);
			}
		System.out.println("There are " + freeDrivers.size() + " vacant drivers.");
		for(Driver driver : freeDrivers) {
			System.out.println("   Driver`s name: " + driver.getName());
		}

		System.out.println("There are " + freeBuses.size() + " non-occupied buses.");
		for(Bus bus : freeBuses) {
			System.out.println("   Bus`s ID number: " + bus.getBusSpz());
		}

		Bus myBus = freeBuses.get(0); // vyberieme prvy dostupny bus. Alternat�vne ho vyberie user
		Driver myDriver = freeDrivers.get(0);  // a drivera

		BusConnection newConnection = new BusConnection(); // vytvorim new connection
		newConnection.setDestination(destination);
		newConnection.setMinSeats(minSeats);
		newConnection.setDurationMinutes(durationMinutes);
		newConnection.setBus(myBus);
		newConnection.setDriver(myDriver);
		for (int i = 0; i < myBus.getNumberOfSeats(); i++) {
			Seat seat = new Seat();
			seat.setSeatNo(i+1);
			seat.setSeatStatus(SeatStatus.Free);
			newConnection.addSeat(seat);
		}
		System.out.println("-----------------------");
		System.out.println("We created new connection ");
		System.out.println("-----------------------");
		System.out.println("   Destination: " + newConnection.getDestination());
		System.out.println("   Driver`s name: " + newConnection.getDriver().getName());
		System.out.println("   Bus`s ID number: " + newConnection.getBus().getBusSpz());
		System.out.println("   Number of seats: " + newConnection.getBus().getNumberOfSeats());
	}


	// Prescanuje vsetky sedadla vsetkych autobusov a ak je rezervacia starsia ako 10 minut tak ju zrusi
	// Kedze mi z tabulky berie iba d�tum a nie cas, dal som rezerv�ciu starsiu ako 24 hod.
	public void freeReservedSeats(ServiceContext ctx) {
		Date actualTime = new Date(); //aktualny cas
		//LocalDateTime actualTime = LocalDateTime.now(); //alternat�va s LocalDateTime vyhadzuje chyby

		List<BusConnection> allConnections = busConnectionRepository.findAll();
		for(BusConnection connection : allConnections) {
			for(int i=0; i< connection.getBus().getNumberOfSeats(); i++) {
				if(connection.getSeats().get(i).getSeatStatus() == SeatStatus.Reserved) {
					Date reservationDate = connection.getSeats().get(i).getReservationDate(); // nac�ta iba d�tum, cas nevie
					long diffMillies = Math.abs(actualTime.getTime() - reservationDate.getTime());
					long diffHours = TimeUnit.HOURS.convert(diffMillies, TimeUnit.MILLISECONDS);
					System.out.println("DiffHours = " + diffHours);
//					LocalDateTime ldt = LocalDateTime.ofInstant(reservationDate.toInstant(), ZoneId.systemDefault());
//					Duration duration = Duration.between(actualTime, ldt);
//					long diffMinutes = Math.abs(duration.toMinutes());
					if(diffHours > 24) {
						connection.getSeats().get(i).setSeatStatus(SeatStatus.Free);
					}
				}
			}
		}
		// v�stup
		for(BusConnection connection : allConnections) {
			System.out.println("BusConnection: " + connection.getDestination());
			for(int i=0; i< connection.getBus().getNumberOfSeats(); i++) {
				System.out.println("  seatNo: " + connection.getSeats().get(i).getSeatNo() +
						" seatStatus: " + connection.getSeats().get(i).getSeatStatus());
			}
		}
	}

}
