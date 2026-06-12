package lightHandler;
import service.*;

public class yellowLight implements signalLight {

	@Override
	public void handleLight() {
		System.out.println("Yellow light is on");

        int time  = trafficLight.getInstance().signalDuration.get(enums.light.YELLOW);

        for (int i = 0; i < time; i++) {
            System.out.println("Waiting for yellow light");
            if(trafficLight.getInstance().isManualRequested) {
                trafficLight.getInstance().isManualRequested = false;
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        trafficLight.getInstance().handleLightChange(new redLight());
	}

    @Override
    public enums.light getLightName() {
        return enums.light.YELLOW;
    }
}