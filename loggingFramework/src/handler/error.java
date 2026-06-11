package handler;
// import textAppender.*;
import enums.*;

public class error extends logHandler {
	public error() {
		super();
	}
	
	@Override
	public boolean canHandle(logLevel type) {
		return type == logLevel.ERROR;
	}

    // @Override
    // public void addAppender(appender appender) {
    //     this.appenders.add(appender);
    // }

	@Override
	public void setNext(logHandler next) {
		this.next = next;
	}
}