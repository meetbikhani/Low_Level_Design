import service.elevatorSystem;
import enums.direction;

public class elevatorSystemDemo {
    public static void main(String[] args) throws InterruptedException {
        elevatorSystem elevatorSystem = new elevatorSystem(2);
        elevatorSystem.start();

        
        elevatorSystem.requestElevator(3, direction.UP);
        Thread.sleep(1000);
        elevatorSystem.selectFloor("elevator 1", 5);
        Thread.sleep(2000);
        elevatorSystem.requestElevator(2, direction.DOWN);
        Thread.sleep(3000);
        elevatorSystem.selectFloor("elevator 2", 1);
    }
}