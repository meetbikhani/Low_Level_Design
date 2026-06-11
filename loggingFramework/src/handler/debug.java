package handler;
// import textAppender.*;
import enums.*;

public class debug extends logHandler {
	public debug() {
		super();
	}
	
	@Override
	public boolean canHandle(logLevel type) {
		return type == logLevel.DEBUG;
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