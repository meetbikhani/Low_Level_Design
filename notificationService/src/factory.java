package notificationService.src;


public class factory{
    public static notificationSender notificationFactory(notificationEnum channel){
        if(channel != notificationEnum.Email && channel != notificationEnum.SMS && channel != notificationEnum.Push){
            System.out.println("wrong channel");
            return null;
        }
        if(channel == notificationEnum.Email)return new email();
        else if(channel == notificationEnum.SMS)return new sms();
        return new push();
    }
}