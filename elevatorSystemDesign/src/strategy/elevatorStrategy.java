package strategy;
import entities.request;
import service.elevatorSystem;
import entities.elevator;

public interface elevatorStrategy {
    public elevator getElevator(request request, elevatorSystem elevatorSystem);
}