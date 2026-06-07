package entities;

import java.time.LocalDateTime;

public class Ticket {
    String id;
    Vehicle.VehicleType vehicleType;
    LocalDateTime EntryTime;
    ParkingSpot parkingSpot;

    public Ticket(String id, Vehicle.VehicleType vehicleType, LocalDateTime EntryTime, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.EntryTime = EntryTime;
        this.parkingSpot = parkingSpot;
    }

    public String getId() {
        return id;
    }

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalDateTime getTime() {
        return EntryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}