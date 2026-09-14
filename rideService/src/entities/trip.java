package rideService.src.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rideService.src.observer.observer;
import rideService.src.state.*;

public class trip {
    private String id;
    private rider rider;
    private driver driver;
    private double start;
    private double end;
    private tripState currentState;
    private double fare;
    private List<observer> observers = new ArrayList<>();

    public trip(tripBuilder tripBuilder) {
        this.id = tripBuilder.getId();
        this.rider = tripBuilder.getRider();
        this.start = tripBuilder.getStart();
        this.end = tripBuilder.getEnd();
        this.currentState = new requestedState();
        this.fare = tripBuilder.getFare();
    }

    public void addObserver(observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (observer observer : observers) {
            observer.update(this, message);
        }
    }

    // Getters
    public rider getRider() {
        return rider;
    }

    public driver getDriver() {
        return driver;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public double getFare() {
        return fare;
    }

    public tripState getCurrentState() {
        return currentState;
    }

    public String getId() {
        return id;
    }

    // Setters
    public void setCurrentState(tripState currentState) {
        this.currentState = currentState;
    }

    public void setDriver(driver driver) {
        this.driver = driver;
    }

    public void assignDriver(driver driver) {
        currentState.accepted(this, driver);
        notifyObservers("Driver assigned");
    }

    public void startTrip() {
        currentState.started(this);
        notifyObservers("Trip started");
    }

    public void endTrip() {
        currentState.ended(this);
        notifyObservers("Trip ended");
    }

    public static class tripBuilder {
        private rider rider;
        private double start;
        private double end;
        private double fare;
        private String id = UUID.randomUUID().toString();

        public tripBuilder setRider(rider rider) {
            this.rider = rider;
            return this;
        }

        public tripBuilder setStart(double start) {
            this.start = start;
            return this;
        }

        public tripBuilder setEnd(double end) {
            this.end = end;
            return this;
        }

        public tripBuilder setFare(double fare) {
            this.fare = fare;
            return this;
        }

        public rider getRider() {
            return rider;
        }

        public double getStart() {
            return start;
        }

        public double getEnd() {
            return end;
        }

        public double getFare() {
            return fare;
        }

        public String getId() {
            return id;
        }

        public trip build() {
            return new trip(this);
        }
    }
}