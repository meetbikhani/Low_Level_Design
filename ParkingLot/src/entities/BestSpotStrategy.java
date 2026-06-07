package entities;
import java.util.Map;

public class BestSpotStrategy implements ParkingStrategy {
    @Deprecated
    public ParkingSpot getParkingSpot(Vehicle vehicle, Map<Integer, Floor> floors) {

        ParkingSpot bestSpot = null;
        Vehicle.VehicleType requiredType = vehicle.getVehicleType();

        for (Floor floor : floors.values()) {
            for (ParkingSpot spot : floor.getAvailableParkingSpots()) {
                Vehicle.VehicleType spotType = spot.getSpotSize();
                if (spotType == requiredType) {
                    return spot;
                }
            }
        }

        return bestSpot;
    }
}
