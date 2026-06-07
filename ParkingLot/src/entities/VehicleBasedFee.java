package entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class VehicleBasedFee implements ParkingFee {
    
    // private Ticket ticket;
    private Map<Vehicle.VehicleType, Integer> feeMap = Map.of(
        Vehicle.VehicleType.Two_Wheeler, 100,
        Vehicle.VehicleType.Small_Vehicle, 200,
        Vehicle.VehicleType.Large_Vehicle, 300
    );
    

    // public VehicleBasedFee(Ticket ticket) {
    //     this.ticket = ticket;
    // }

    public int getFee(Ticket ticket) {
        long time = Duration.between(
            ticket.getTime(),
            LocalDateTime.now()
        ).toHours();
        Integer fee = 0;

        if(time<2){
            fee = 10;
        }
        else if(time<4){
            fee = 20;
        }
        else if(time<6){
            fee = 30;
        }
        else fee = 40;

        return fee * feeMap.get(ticket.getVehicleType());
    }
}
