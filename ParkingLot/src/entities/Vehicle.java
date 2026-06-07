package entities;

public abstract class Vehicle {
    public enum VehicleType {
        Two_Wheeler, 
        Small_Vehicle, 
        Large_Vehicle
    }

    public VehicleType vehicleType;
    public String vehicleNumber;


    public Vehicle(VehicleType vehicleType, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}