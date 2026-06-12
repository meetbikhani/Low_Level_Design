package lightHandler;
import service.*;

public class greenLight implements signalLight {

	@Override
	public void handleLight() {
		System.out.println("Green light is on");

        int time  = trafficLight.getInstance().signalDuration.get(enums.light.GREEN);

        for (int i = 0; i < time; i++) {
            System.out.println("Waiting for green light");
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

        trafficLight.getInstance().handleLightChange(new yellowLight());
	}

    @Override
    public enums.light getLightName() {
        return enums.light.GREEN;
    }
}