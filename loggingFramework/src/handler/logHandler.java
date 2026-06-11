package handler;
import java.time.LocalDateTime;
import java.util.List;
import textAppender.*;
import enums.*;

public abstract class logHandler {
	logHandler next;
    List<appender> appenders;

    public logHandler(){
        this.appenders = new java.util.ArrayList<appender>();
    }

    public void notify(String message, logLevel type, LocalDateTime time){
        for(appender appender : appenders){
            appender.append(message, type, time);
        }
    }

    public void handle(String message, logLevel type, LocalDateTime time){
        if(canHandle(type)){
            notify(message, type, time);
        }
        if(next != null){
            System.out.println("next");
            next.handle(message, type, time);
        }
    }

    public void addAppender(appender appender){
        this.appenders.add(appender);
    }

    public abstract boolean canHandle(logLevel type);
    // public abstract void addAppender(appender appender);
    public abstract void setNext(logHandler next);
}