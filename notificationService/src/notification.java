package notificationService.src;

public class notification {
    private String message;
    private String to;
    private notificationEnum channel;

    public notification(String message, String to, notificationEnum channel){
        this.message=message;
        this.to=to;
        this.channel=channel;
    }

    public String getTo(){
        return this.to;
    }

    public String getMessage(){
        return this.message;
    }

    public notificationEnum getChannel(){
        return this.channel;
    }
}
