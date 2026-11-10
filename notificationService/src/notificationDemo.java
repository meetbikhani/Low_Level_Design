package notificationService.src;

public class notificationDemo{
    public static void main(String[] args) {
        notificationService service = new notificationService();
        notification emailNotification = new notification("hiiii", "meet", notificationEnum.Email);
        notification smsNotification = new notification("helooo jiii", "meetansh", notificationEnum.SMS);

        service.sendNotification(emailNotification);
        service.sendNotification(smsNotification);

        service.shutdown();
    }
}