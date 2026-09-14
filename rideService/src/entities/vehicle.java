package rideService.src.entities;
import rideService.src.enums.vehicleType;

public class vehicle {
    private String vehicleNumber;
    private vehicleType vehicleType;
    private double location;

    public vehicle(String vehicleNumber, vehicleType vehicleType, double location) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.location = location;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public vehicleType getVehicleType() {
        return vehicleType;
    }

    public double getLocation() {
        return location;
    }
}