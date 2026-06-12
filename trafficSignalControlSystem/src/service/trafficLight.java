package service;

import enums.direction;
import enums.light;
import lightHandler.greenLight;
import lightHandler.signalLight;

import java.util.*;

public class trafficLight {
    public Map<light, Integer> signalDuration;
    List<direction> directionList;
    private static trafficLight instance;
    enums.direction currentDirection;
    private signalLight currentLight;
    public Boolean isManualRequested;

    public static trafficLight getInstance() {
        if (instance == null) {
            instance = new trafficLight();
        }
        return instance;
    }

    public trafficLight() {
        signalDuration = new HashMap<light, Integer>();
        signalDuration.put(light.RED, 0);
        signalDuration.put(light.YELLOW, 0);
        signalDuration.put(light.GREEN, 0);
        directionList = new ArrayList<direction>();
        directionList.add(direction.NORTH);
        directionList.add(direction.SOUTH);
        directionList.add(direction.EAST);
        directionList.add(direction.WEST);
        currentDirection = direction.NORTH;
        currentLight = new greenLight();
        isManualRequested = false;
        System.out.println("Traffic Light Controller Created");
    }
    

    public void setRedLightDuration(int duration) {
        signalDuration.put(light.RED, duration);
        System.out.println("Red Light Duration Set to " + duration);
    }

    public void setYellowLightDuration(int duration) {
        signalDuration.put(light.YELLOW, duration);
        System.out.println("Yellow Light Duration Set to " + duration);
    }

    public void setGreenLightDuration(int duration) {
        signalDuration.put(light.GREEN, duration);
        System.out.println("Green Light Duration Set to " + duration);
    }

    public void start() {
        while (true) {
            System.out.println("Current Direction: " + currentDirection);
            System.out.println("Going to start the light");
            currentLight.handleLight();
        }
    }

    public void handleLightChange(signalLight light) {
        currentLight = light;
        System.out.println("Light Changed to " + light.getLightName());
    }

    public void handleDirectionChange() {
        Integer currIndex = directionList.indexOf(currentDirection);
        direction nextDirection =
        directionList.get(
                (currIndex + 1) % directionList.size()
        );
        currentDirection = nextDirection;
        currentLight = new greenLight();
    }

    public void manualChange(direction direction){
        System.out.println("Manual Change Requested");
        isManualRequested = true;
        currentDirection = direction;
        currentLight = new greenLight();
    }
}