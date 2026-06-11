package textFormatter;
import enums.*;
import java.time.LocalDateTime;

public interface formatter {
	String format(String message, logLevel type, LocalDateTime time);
}