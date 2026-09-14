package rideService.src.state;

import rideService.src.entities.driver;
import rideService.src.entities.trip;

public class completedState implements tripState {
	@Override
	public void requested(trip trip) {
		System.out.println("Ride already assigned");
	}

	@Override
	public void accepted(trip trip, driver driver) {
		System.out.println("Ride already assigned");
	}

	@Override
	public void started(trip trip) {
		System.out.println("Ride is already started");
	}

	@Override
	public void ended(trip trip) {
		System.out.println("Ride is completed");
	}
}