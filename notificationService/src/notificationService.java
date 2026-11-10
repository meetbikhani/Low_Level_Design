package notificationService.src;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class notificationService{
    private ExecutorService executor;
    private ScheduledExecutorService scheduler;
    private retryHandler retryHandler;

    public notificationService(){
        this.executor = Executors.newFixedThreadPool(5);
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.retryHandler = new retryHandler(3, 2000, scheduler);
    }

    public void sendNotification(notification notification) {

        notificationSender sender = factory.notificationFactory(notification.getChannel());

        executor.submit(() -> {

            retryHandler.execute(
                () -> {
                    sender.send(notification);
                },
                1
            );

        });
    }


    public void shutdown() {

        executor.shutdown();
        scheduler.shutdown();
    }

}