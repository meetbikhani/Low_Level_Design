package service;
import entities.*;
import observer.elevatorDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import strategy.elevatorStrategy;
import strategy.nearestElevator;
import enums.*;

public class elevatorSystem {
    private List<elevator> elevatorsList;
    private ExecutorService executorService;
    private elevatorStrategy elevatorStrategy = new nearestElevator();

    public elevatorSystem(Integer numElevators) {
        elevatorsList = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(numElevators);
        elevatorDisplay elevatorDisplay = new elevatorDisplay();

        for (int i = 1; i <= numElevators; i++) {
            elevator elevator = new elevator("elevator " + i);
            elevator.addObserver(elevatorDisplay);
            elevatorsList.add(elevator);
        }
    }

    public void start() {
        for (elevator elevator : elevatorsList) {
            executorService.execute(elevator);
        }
    }

    public List<elevator> getElevatorsList() {
        return elevatorsList;
    }

    public void addRequest(request request) {
        //get the elevator from strategy
        elevator elevator = elevatorStrategy.getElevator(request, this);
        if (elevator != null) {
            System.out.println("Elevator " + elevator.getId() + " processing: " + request.getTargetFloor());
            elevator.addRequest(request);
        } else {
            System.out.println("No elevator found for request.");
        }
    }

    public void requestElevator(int floor, direction direction){
        System.out.println("\n>> EXTERNAL Request: User requested elevator to go to floor " + floor);
        request request = new request(floor, direction, requestType.EXTERNAL);

        addRequest(request);
    }

    public void selectFloor(String elevatorId, int floor){
        System.out.println("\n>> INTERNAL Request: User in Elevator " + elevatorId + " selected floor " + floor);
        request request = new request(floor, direction.IDLE, requestType.INTERNAL);

        elevator elevator = elevatorsList.stream().filter(e -> e.getId().equals(elevatorId)).findFirst().orElse(null);
        if (elevator != null) {
            System.out.println("Elevator " + elevator.getId() + " processing: " + request);
            elevator.addRequest(request);
        } else {
            System.err.println("Invalid elevator ID.");
        }
    }

}