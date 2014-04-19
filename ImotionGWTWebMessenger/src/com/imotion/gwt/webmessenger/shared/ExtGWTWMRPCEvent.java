package com.imotion.gwt.webmessenger.shared;

import java.io.Serializable;
import java.util.Date;

public class ExtGWTWMRPCEvent implements Serializable {
	
	private static final long serialVersionUID = 5168205444928677997L;
	
	private String 	message;
	private long 	timestamp;
	private String 	senderId;
    private String 	roomId;
    
    public ExtGWTWMRPCEvent() {
    	this.timestamp = new Date().getTime();
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String toString() {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("roomId: ")
					.append(getRoomId())
					.append("\n\t")
					.append("userId: ")
					.append(getSenderId())
					.append("\n\t")
					.append("timestamp: ")
					.append(getTimestamp())
					.append("\n\t")
					.append("message: ")
					.append(getMessage());
		return sbBuilder.toString();
	}
}
