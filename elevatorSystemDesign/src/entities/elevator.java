package entities;
import observer.elevatorObserver;
import enums.*;

import java.util.*;
import states.*;

public class elevator implements Runnable {
    private String id;
    private int currentFloor;
    private direction currentDirection;
    private Boolean isRunning=true;
    private List<elevatorObserver> observers = new ArrayList<>();
    private PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<>(Comparator.reverseOrder());
    private elevatorState state;

    public void setState(elevatorState state) {
        this.state = state;
    }

    public elevatorState getState() {
        return state;
    }

    public elevator(String id) {
        this.id = id;
        this.state = new idleState();
        this.currentFloor = 0;
        this.currentDirection = direction.IDLE;
    }

    public void setFloor(int floor) {
        this.currentFloor = floor;
        notifyObservers();
    }

    public int getFloor() {
        return currentFloor;
    }

    public void setDirection(direction direction) {
        this.currentDirection = direction;
    }

    public direction getDirection() {
        return currentDirection;
    }

    public String getId() {
        return id;
    }

    public PriorityQueue<Integer> getUpQueue() {
        return upQueue;
    }

    public PriorityQueue<Integer> getDownQueue() {
        return downQueue;
    }

    public void notifyObservers() {
        for (elevatorObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addObserver(elevatorObserver observer) {
        observers.add(observer);
    }

    private void move() {
        state.move(this);
    }

    public synchronized void addRequest(request request) {
        System.out.println("Elevator " + id + " processing: " + request);
        System.out.println("Current floor: " + currentFloor);
        System.out.println("Current direction: " + currentDirection);
        System.out.println("Current state: " + state.getState());
        state.addRequest(this, request);
    }
}