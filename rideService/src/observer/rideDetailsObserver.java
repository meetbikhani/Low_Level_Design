package rideService.src.observer;

import rideService.src.entities.trip;

public class rideDetailsObserver implements observer {
	@Override
	public void update(trip trip, String message) {
		System.out.println("--- Observer Notification: " + message + " ---");
		System.out.println("Rider: " + (trip.getRider() != null ? trip.getRider().getName() : "N/A"));
		System.out.println("Driver: " + (trip.getDriver() != null ? trip.getDriver().getName() : "Unassigned"));
		System.out.println("Start: " + trip.getStart());
		System.out.println("End: " + trip.getEnd());
		System.out.println("Fare: " + trip.getFare());
		System.out.println("State: " + (trip.getCurrentState() != null ? trip.getCurrentState().getClass().getSimpleName() : "N/A"));
	}
}