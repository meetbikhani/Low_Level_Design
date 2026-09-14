package rideService.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import rideService.src.enums.*;
import rideService.src.strategy.feeStrategy;
import rideService.src.entities.*;

public class rideService {
    public static rideService instance;
    Map<String, driver> drivers = new ConcurrentHashMap<>();
    Map<String, rider> riders = new ConcurrentHashMap<>();
    Map<String, trip> trips = new ConcurrentHashMap<>();
    private feeStrategy feeStrategy;

    private rideService() {
}

    public static rideService getInstance() {
        if (instance == null) {
            instance = new rideService();
        }
        return instance;
    }

    public void setFeeStraregy(feeStrategy feeStrategy){
        this.feeStrategy =feeStrategy;
    }

    public void registerRider(rider rider) {
        riders.put(rider.getId(), rider);
    }

    public void registerDriver(driver driver) {
        drivers.put(driver.getId(), driver);
    }

    private List<driver> getAvailableDrivers(vehicleType vehicleType) {
        List<driver> drivers = new ArrayList<>();
        for (driver driver : this.drivers.values()) {
            if (driver.getStatus() == driverStatus.ONLINE && driver.getVehicle().getVehicleType() == vehicleType) {
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public trip requestTrip(rider rider, double start, double end, vehicleType vehicleType) {
        List<driver> availableDrivers = getAvailableDrivers(vehicleType);
        if (availableDrivers.size() == 0) {
            System.out.println("No drivers available");
            return null;
        }

        double fare = feeStrategy.calculateFee(start, end);
        System.out.println("Fare: " + fare);

        for (driver driver : availableDrivers) {
            System.out.println("Notifying driver: " + driver.getName() + "at location: " + driver.getVehicle().getLocation());
        }

        trip trip = new trip.tripBuilder()
                .setRider(rider)
                .setStart(start)
                .setEnd(end)
                .setFare(fare)
                .build();
        
        trips.put(trip.getId(), trip);
        return trip;
    }

    synchronized public void acceptTrip(trip trip, driver driver) {
        if(trip.getDriver() != null){
            System.out.println("already assigned trip to a driver");
            return;
        }
        System.out.println("Accepting trip");
        System.out.println("Driver: " + driver.getName());
        trip.assignDriver(driver);
    }

    public void startTrip(trip trip) {
        System.out.println("Starting trip");
        trip.startTrip();
    }

    public void endTrip(trip trip) {
        System.out.println("Ending trip");
        trip.endTrip();
    }
}