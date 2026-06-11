package textAppender;
import enums.*;
import java.time.LocalDateTime;
import textFormatter.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileAppender extends appender {
	private File file;
	
	public fileAppender(File file, formatter formatter) {
		super(formatter);
		this.file = file;
	}
	
	@Override
	public synchronized void append(String message, logLevel type, LocalDateTime time) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(formatter.format(message, type, time));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} 