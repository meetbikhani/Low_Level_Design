package service;
import handler.*;
import textAppender.*;

public class logger {

    private static logger instance;

    private debug debugHandler;
    private info infoHandler;
    private warn warnHandler;
    private error errorHandler;

    private logHandler handler;

    private logger() {
        createChain();
    }

    public static logger getInstance() {
        if(instance == null) {
            instance = new logger();
        }
        return instance;
    }

    private void createChain() {

        errorHandler = new error();
        warnHandler = new warn();
        infoHandler = new info();
        debugHandler = new debug();

        debugHandler.setNext(infoHandler);
        infoHandler.setNext(warnHandler);
        warnHandler.setNext(errorHandler);

        handler = debugHandler;
    }

    public void addErrorAppender(appender appender) {
        errorHandler.addAppender(appender);
    }

    public void addWarnAppender(appender appender) {
        warnHandler.addAppender(appender);
    }

    public void addInfoAppender(appender appender) {
        infoHandler.addAppender(appender);
    }

    public void addDebugAppender(appender appender) {
        debugHandler.addAppender(appender);
    }

    public logHandler getHandler() {
        return handler;
    }
}