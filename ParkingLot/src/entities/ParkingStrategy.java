package entities;

import java.util.Map;

public interface ParkingStrategy {
    ParkingSpot getParkingSpot(Vehicle vehicle, Map<Integer, Floor> floors);
}