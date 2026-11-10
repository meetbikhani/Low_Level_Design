package notificationService.src;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class retryHandler{
    Integer seconds;
    Integer retries;
    ScheduledExecutorService scheduler;

    public retryHandler(
            int maxRetries,
            int retryDelay,
            ScheduledExecutorService scheduler) {

        this.retries = maxRetries;
        this.seconds = retryDelay;
        this.scheduler = scheduler;
    }

    public void execute(Runnable task, int attempt){
        try {
            task.run();
            System.out.println("Notification delivered successfully!");
        } catch (Exception e) {
            if (attempt >= retries) {
                System.out.println("Maximum retries reached.");
                return;
            }
            System.out.println("Faced an error while running - retrying");
            scheduler.schedule(() -> execute(task, attempt + 1),
                seconds,
                TimeUnit.MILLISECONDS);
        }
    }
}