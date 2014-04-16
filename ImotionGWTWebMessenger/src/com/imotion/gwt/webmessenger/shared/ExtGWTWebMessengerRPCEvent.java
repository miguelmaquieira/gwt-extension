package com.imotion.gwt.webmessenger.shared;

import java.io.Serializable;

public class ExtGWTWebMessengerRPCEvent implements Serializable{

	
	private static final long serialVersionUID = 5168205444928677997L;
	
	private String text;
	private long hour;
	private String senderId;
    private String broadcastId;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
	public long getHour() {
		return hour;
	}

	public void setHour(long hour) {
		this.hour = hour;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}


}
