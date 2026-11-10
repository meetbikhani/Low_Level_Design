package notificationService.src;

public class email implements notificationSender{
    @Override 
    public void send(notification notification){
        try {
            System.out.println("Email sending started...");
            Thread.sleep(5000);

            System.out.println("Email sent: " 
                + notification.getMessage() 
                + ", to - " + notification.getTo());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}