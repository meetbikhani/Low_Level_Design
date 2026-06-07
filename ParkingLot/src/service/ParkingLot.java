package service;
import entities.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private static ParkingLot instance;
    private Map<Integer, Floor> floors;
    private Map<String, Ticket> tickets;
    private ParkingStrategy parkingStrategy;
    private ParkingFee feeStrategy;

    private ParkingLot() {
        this.floors = new java.util.HashMap<>();
        this.tickets = new java.util.HashMap<>();
        this.parkingStrategy = new BestSpotStrategy();
        this.feeStrategy = new VehicleBasedFee();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(Floor floor) {
        this.floors.put(floor.getFloorNumber(), floor);
    }

    public void parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot = parkingStrategy.getParkingSpot(vehicle, floors);
        if(parkingSpot == null){
            System.out.println("No parking spot available");
        }
        else{
            parkingSpot.parkVehicle(vehicle);
            UUID uuid = UUID.randomUUID();
            Ticket ticket = new Ticket(uuid.toString(), vehicle.getVehicleType(), LocalDateTime.now(), parkingSpot);
            this.tickets.put(vehicle.getVehicleNumber(), ticket);
            System.out.println("Vehicle parked successfully at " + parkingSpot.getId());
            System.out.println("Your ticket number is: " + ticket.getId());
        }   
    }

    public void removeVehicle(String vehicleNumber){
        Ticket ticket = this.tickets.get(vehicleNumber);
        if(ticket == null){
            System.out.println("Vehicle not found");
        }
        else{
            ticket.getParkingSpot().removeVehicle();
            this.tickets.remove(vehicleNumber);
            int fee = feeStrategy.getFee(ticket);
            System.out.println("Vehicle removed successfully and your fee is: " + fee);
        }
    }
}