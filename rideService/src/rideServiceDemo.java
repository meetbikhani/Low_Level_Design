package rideService.src;

import rideService.src.entities.*;
import rideService.src.enums.vehicleType;
import rideService.src.observer.*;
import rideService.src.strategy.*;

public class rideServiceDemo {
    public static void main(String[] args) {
        rideService service = rideService.getInstance();
        service.setFeeStraregy(new feeBasedOnDistance());

        rider rider = new rider("John", "1234567890");
        service.registerRider(rider);

        driver driver1 = new driver("Alice", "552563256325", new vehicle("mp 04 5566566", vehicleType.CAR, 1));
        service.registerDriver(driver1);

        driver driver2 = new driver("Peter", "23658548585", new vehicle("br 04 5566566", vehicleType.CAR, 2));
        service.registerDriver(driver2);

        driver driver3 = new driver("George", "45658655585", new vehicle("gm 04 5566566", vehicleType.BIKE, 3));
        service.registerDriver(driver3);

        trip trip = service.requestTrip(rider, 0.5, 4, vehicleType.CAR);
        trip.addObserver(new rideDetailsObserver());

        Thread driverA = new Thread(() -> service.acceptTrip(trip, driver1));
        Thread driverB = new Thread(() -> service.acceptTrip(trip, driver2));
        Thread driverC = new Thread(() -> service.acceptTrip(trip, driver3));

        driverA.start();
        driverB.start();
        driverC.start();

        try {
            driverA.join();
            driverB.join();
            driverC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.startTrip(trip);
        service.endTrip(trip);
    }
}