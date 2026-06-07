import entities.*;
import service.*;
public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        ParkingSpot parkingSpot1 = new ParkingSpot("1", false, Vehicle.VehicleType.Two_Wheeler);
        ParkingSpot parkingSpot2 = new ParkingSpot("2", false, Vehicle.VehicleType.Two_Wheeler);
        ParkingSpot parkingSpot3 = new ParkingSpot("3", false, Vehicle.VehicleType.Small_Vehicle);
        ParkingSpot parkingSpot4 = new ParkingSpot("4", false, Vehicle.VehicleType.Small_Vehicle);
        ParkingSpot parkingSpot5 = new ParkingSpot("5", false, Vehicle.VehicleType.Large_Vehicle);
        ParkingSpot parkingSpot6 = new ParkingSpot("6", false, Vehicle.VehicleType.Large_Vehicle);

        floor1.addParkingSpot(parkingSpot1);
        floor1.addParkingSpot(parkingSpot2);
        floor2.addParkingSpot(parkingSpot3);
        floor2.addParkingSpot(parkingSpot4);
        floor2.addParkingSpot(parkingSpot5);
        floor2.addParkingSpot(parkingSpot6);

        Vehicle vehicle1 = new Small_Vehicle("1");
        Vehicle vehicle2 = new Large_Vehicle("2");
        Vehicle vehicle3 = new TwoWheeler("3");

        parkingLot.parkVehicle(vehicle1);
        parkingLot.parkVehicle(vehicle2);
        parkingLot.parkVehicle(vehicle3);

        parkingLot.removeVehicle("1");
        Vehicle vehicle4 = new Small_Vehicle("4");
        parkingLot.parkVehicle(vehicle4);
    }
}
