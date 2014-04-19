package com.imotion.gwt.webmessenger.shared;

import java.io.Serializable;

public class ExtGWTWebMessengerRPCEvent implements Serializable{

	
	private static final long serialVersionUID = 5168205444928677997L;
	
	private String 	message;
	private long 	timestamp;
	private String 	senderId;
    private String 	roomId;
    
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
}
