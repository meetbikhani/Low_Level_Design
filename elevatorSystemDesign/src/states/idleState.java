package states;
import entities.*;
import enums.*;

public class idleState implements elevatorState {

    @Override
    public void move(elevator elevator) {
        if(elevator.getDownQueue().size()>0) {
            elevator.setState(new movingDown());
            elevator.setDirection(direction.DOWN);
        }
        else if(elevator.getUpQueue().size()>0) {
            elevator.setState(new movingUp());
            elevator.setDirection(direction.UP);
        }
    }

    @Override
    public void addRequest(elevator elevator, request request) {
        Integer currentFloor = elevator.getFloor();

        if(currentFloor>request.getTargetFloor()) {
            elevator.getDownQueue().add(request.getTargetFloor());
        }
        else if(currentFloor<request.getTargetFloor()) {
            elevator.getUpQueue().add(request.getTargetFloor());
        }
        System.out.println("Elevator " + elevator.getId() + "up queue is" + elevator.getUpQueue());
        System.out.println("Elevator " + elevator.getId() + "down queue is" + elevator.getDownQueue());

    }

    @Override
    public String getState() {
        return "idle";
    }
}