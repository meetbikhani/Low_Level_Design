package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Floor {
    private int floorNumber;
    private Map<String, ParkingSpot> parkingSpots;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new java.util.HashMap<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.put(parkingSpot.getId(), parkingSpot);
    }

    public void removeParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.remove(parkingSpot.getId());
    }

    public List<ParkingSpot> getAvailableParkingSpots() {
        List<ParkingSpot> availableParkingSpots = new ArrayList<>();
        for (ParkingSpot parkingSpot : this.parkingSpots.values()) {
            if (!parkingSpot.getIsOccupied()) {
                availableParkingSpots.add(parkingSpot);
            }
        }
        return availableParkingSpots;
    }
}