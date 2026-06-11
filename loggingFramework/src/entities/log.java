package entities;
import enums.*;
import java.time.LocalDateTime;


public class log {
	private String message;
	private logLevel type;
	private LocalDateTime time;
	
	public log(String message, logLevel type, LocalDateTime time) {
		this.message = message;
		this.type = type;
		this.time = time;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public logLevel getType() {
		return type;
	}
	public LocalDateTime getTime() {
		return time;
	}
}