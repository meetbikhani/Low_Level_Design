package notificationService.src;

public class sms implements notificationSender{
    @Override 
    public void send(notification notification){
        System.out.println("sms send " + notification.getMessage() + ", to - " + notification.getTo());
    }
}