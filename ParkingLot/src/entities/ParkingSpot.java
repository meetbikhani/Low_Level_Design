package entities;

public class ParkingSpot {
    private String id;
    private Boolean isOccupied;
    private Vehicle.VehicleType SpotSize;
    private Vehicle vehicle;

    public ParkingSpot(String id, Boolean isOccupied, Vehicle.VehicleType SpotSize) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.SpotSize = SpotSize;
        this.vehicle = null;
    }

    public String getId() {
        return id;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public Vehicle.VehicleType getSpotSize() {
        return SpotSize;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}