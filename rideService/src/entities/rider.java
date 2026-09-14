package rideService.src.entities;

import java.util.UUID;

public class rider {
    private String name;
    private String phoneNumber;
    private String id;

    public rider(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

}