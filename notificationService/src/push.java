package notificationService.src;

public class push implements notificationSender{
    @Override 
    public void send(notification notification){
        System.out.println("push msg send " + notification.getMessage());
    }
}