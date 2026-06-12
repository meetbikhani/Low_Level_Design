import service.*;

public class trafficSignalControlSystemDemo {
	public static void main(String[] args) throws InterruptedException {
		intersection intersection1 = new intersection("1", "Intersection 1");

        System.out.println("Intersection 1 ID: " + intersection1.getId());
        System.out.println("Intersection 1 Name: " + intersection1.getName());

        trafficLight signalController = intersection1.createTrafficLight();
        signalController.setRedLightDuration(10);
        signalController.setYellowLightDuration(15);
        signalController.setGreenLightDuration(20);

        new Thread(() -> {
            signalController.start();
        }).start();

        Thread.sleep(5000);

        signalController.manualChange(enums.direction.EAST);
	}
}