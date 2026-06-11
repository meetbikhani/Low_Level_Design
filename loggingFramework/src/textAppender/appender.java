package textAppender;
import enums.*;
import java.time.LocalDateTime;
import textFormatter.*;

public abstract class appender {
    protected formatter formatter;

    public appender(formatter formatter) {
        this.formatter = formatter;
    }

    public abstract void append(String message, logLevel type, LocalDateTime time);

    public formatter getFormatter() {
        return formatter;
    }
}