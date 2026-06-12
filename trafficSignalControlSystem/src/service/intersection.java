package service;

public class intersection {
    private String id;
    private String name;
    private trafficLight signalController;

    public intersection(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public trafficLight createTrafficLight() {
        signalController = trafficLight.getInstance();
        return signalController;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}