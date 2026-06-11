package textFormatter;
import enums.*;
import java.time.LocalDateTime;

public class stringFormatter implements formatter {
	@Override
	public String format(String message, logLevel type, LocalDateTime time) {
        return "message: " + message + ", type: " + type + ", time: " + time;
	}
}