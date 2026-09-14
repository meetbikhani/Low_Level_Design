package rideService.src.state;

import rideService.src.entities.trip;
import rideService.src.entities.driver;

public interface tripState {
	public void requested(trip trip);
	public void accepted(trip trip, driver driver);
	public void started(trip trip);
	public void ended(trip trip);

}