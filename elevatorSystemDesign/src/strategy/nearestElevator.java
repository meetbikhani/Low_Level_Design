package strategy;
import entities.request;
import enums.*;
import service.elevatorSystem;
import java.util.List;
import entities.elevator;

public class nearestElevator implements elevatorStrategy {

    @Override
    public elevator getElevator(request request, elevatorSystem elevatorSystem) {
        //logic to get elevator
        elevator nearestElevator = null;
        if(request.getRequestType() == requestType.EXTERNAL) {
            int mini = Integer.MAX_VALUE;
            List<elevator> elevatorsList = elevatorSystem.getElevatorsList();
            for(elevator elevator : elevatorsList) {
                System.out.println("Elevator: " + elevator.getId() + " is at floor " + elevator.getFloor() + " and direction " + elevator.getDirection());
                if(elevator.getDirection()==request.getDirection() || elevator.getDirection()==direction.IDLE) {
                    Integer distance = Math.abs(elevator.getFloor() - request.getTargetFloor());
                    if(distance<mini) {
                        nearestElevator = elevator;
                        mini = distance;
                    }
                }
            }
        }
        return nearestElevator;
    }
}