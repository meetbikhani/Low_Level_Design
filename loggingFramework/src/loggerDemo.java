import service.*;
import textAppender.*;
import textFormatter.*;
import java.io.File;
import java.time.LocalDateTime;
import enums.*;

public class loggerDemo {
	public static void main(String[] args) {
		logger logger = service.logger.getInstance();
		appender appender1 = new consoleAppender(new stringFormatter());
        appender appender2 = new consoleAppender(new jsonFormatter());
        appender appender3 = new fileAppender(new File("log1.txt"), new jsonFormatter());
        appender appender4 = new fileAppender(new File("log2.txt"), new stringFormatter());

        logger.addErrorAppender(appender1);
        logger.addWarnAppender(appender2);
        logger.addInfoAppender(appender3);
        logger.addDebugAppender(appender4);

        logger.getHandler().handle("Hello World!", logLevel.INFO, LocalDateTime.now());
        logger.getHandler().handle("Hello World!", logLevel.WARN, LocalDateTime.now());
        logger.getHandler().handle("Hello World!", logLevel.ERROR, LocalDateTime.now());
        logger.getHandler().handle("Hello World!", logLevel.DEBUG, LocalDateTime.now());
	}
}