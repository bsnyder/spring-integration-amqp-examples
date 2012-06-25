package org.bsnyder.spring.amqp.domain;

import java.io.Serializable;

public class Frisbee implements Serializable {
	
	private static final long serialVersionUID = 7687983586901789706L;
	
	private long messageNumber;
	
	private StringBuilder message = new StringBuilder();
	
	public Frisbee() {}
	
	public Frisbee(long messageNumber, String message) {
		this.messageNumber = messageNumber; 
		this.message = new StringBuilder(message);
	}

	public long getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(long messageNumber) {
		this.messageNumber = messageNumber;
	}

	public String getMessage() {
		return message.toString();
	}

	public void appendToMessage(String message) {
		this.message.append(message);
	}
	
	public void setMessage(String message) {
		this.message = new StringBuilder(message);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("{ \"frisbee\": [\n")
			.append("\"messageNumber\": ").append(getMessageNumber()).append("\n")
			.append("\"message\": ").append(getMessage()).append("\n")
			.append("]\n")
			.append("}");
		return str.toString();
	}

}
