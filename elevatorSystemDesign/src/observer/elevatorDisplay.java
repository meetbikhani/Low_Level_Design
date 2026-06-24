package observer;
import entities.elevator;

public class elevatorDisplay implements elevatorObserver {

	@Override
	public void update(elevator elevator) {
		System.out.println("Elevator: " + elevator.getId() + " is at floor " + elevator.getFloor());
	}
}