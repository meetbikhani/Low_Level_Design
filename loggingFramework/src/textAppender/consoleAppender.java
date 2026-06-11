package textAppender;
import enums.*;
import java.time.LocalDateTime;
import textFormatter.*;

public class consoleAppender extends appender {
	public consoleAppender(formatter formatter) {
		super(formatter);
	}
	
	@Override
	public synchronized void append(String message, logLevel type, LocalDateTime time) {
		System.out.println(formatter.format(message, type, time));
	}
}