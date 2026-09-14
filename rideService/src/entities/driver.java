package rideService.src.entities;
import rideService.src.enums.driverStatus;
import java.util.UUID;

public class driver {
    private String name;
    private String phoneNumber;
    private driverStatus status;
    private vehicle vehicle;
    private String id;

    public driver(String name, String phoneNumber, vehicle vehicle) {
        this.vehicle = vehicle;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = driverStatus.ONLINE;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public driverStatus getStatus() {
        return status;
    }

    public void setStatus(driverStatus status) {
        this.status = status;
    }
    public vehicle getVehicle() {
        return vehicle;
    }

    public String getId() {
        return id;
    }
}