package com.imotion.gwt.webmessenger.client.session;

import java.util.Date;

public class ExtGWTWebMessengerSession {
	
	private String 	userId;
	private String 	roomId;
	private long 	creationTimestamp;
	
	public ExtGWTWebMessengerSession() {
		this.creationTimestamp = new Date().getTime();
	}
	
	public ExtGWTWebMessengerSession(String userId, String roomId) {
		this.userId = userId;
		this.roomId = roomId;
		this.creationTimestamp = new Date().getTime();
	}

	// userId
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	// roomId
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	// creationTimestamp
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public boolean equals(ExtGWTWebMessengerSession obj) {
		return obj != null &&
				obj.getRoomId() == getRoomId() &&
				obj.getUserId() == getUserId();
	}
}
