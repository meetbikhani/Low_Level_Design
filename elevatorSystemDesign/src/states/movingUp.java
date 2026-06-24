package states;
import entities.*;

public class movingUp implements elevatorState {

    @Override
    public void move(elevator elevator) {
        if(elevator.getUpQueue().size()>0) {
            elevator.setFloor(elevator.getUpQueue().poll());
        }else{
            elevator.setState(new idleState());
        }
    }

    @Override
    public void addRequest(elevator elevator, request request) {
        if(elevator.getFloor() < request.getTargetFloor()) {
            elevator.getUpQueue().add(request.getTargetFloor());
        } else if(elevator.getFloor() > request.getTargetFloor()) {
            elevator.getDownQueue().add(request.getTargetFloor());
        }

        System.out.println("Elevator " + elevator.getId() + "up queue is" + elevator.getUpQueue());
        System.out.println("Elevator " + elevator.getId() + "down queue is" + elevator.getDownQueue());
    }

    @Override
    public String getState() {
        return "moving up state";
    }
}