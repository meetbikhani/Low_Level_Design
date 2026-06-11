package handler;
// import textAppender.*;
import enums.*;

public class info extends logHandler {
	public info() {
		super();
	}
	
	@Override
	public boolean canHandle(logLevel type) {
		return type == logLevel.INFO;
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