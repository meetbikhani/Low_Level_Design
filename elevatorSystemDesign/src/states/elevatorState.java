package states;
import entities.request;

import entities.elevator;

public interface elevatorState {
	void move(elevator elevator);
    void addRequest(elevator elevator, request request);
    String getState();
}