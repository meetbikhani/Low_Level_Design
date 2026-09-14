package rideService.src.state;

import rideService.src.entities.driver;
import rideService.src.entities.trip;
import rideService.src.enums.driverStatus;

public class requestedState implements tripState {
	@Override
	public void requested(trip trip) {
		System.out.println("Requested State");
	}

	@Override
	public void accepted(trip trip, driver driver) {
		System.out.println("Accepted State");
		driver.setStatus(driverStatus.IN_TRIP);
		trip.setDriver(driver);
		trip.setCurrentState(new assignedState());
	}

	@Override
	public void started(trip trip) {
		System.out.println("Ride is not started");
	}

	@Override
	public void ended(trip trip) {	
		System.out.println("Ride is not started");
	}
}