package lightHandler;
import service.*;

public class redLight implements signalLight {

    Boolean overrideHandled = false;

	@Override
	public void handleLight() {
		System.out.println("Red light is on");

        int time  = trafficLight.getInstance().signalDuration.get(enums.light.RED);

        for (int i = 0; i < time; i++) {
            System.out.println("Waiting for red light");
            if(trafficLight.getInstance().isManualRequested) {
                trafficLight.getInstance().isManualRequested = false;
                overrideHandled = true;
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!overrideHandled){
            trafficLight.getInstance().handleDirectionChange();
        }
	}

    @Override
    public enums.light getLightName() {
        return enums.light.RED;
    }
}