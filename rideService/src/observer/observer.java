package rideService.src.observer;

import rideService.src.entities.trip;

public interface observer {
	public void update(trip trip, String message);
}