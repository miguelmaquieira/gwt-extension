package com.imotion.gwt.webmessenger.client.common;

import java.util.Date;

public class ExtGWTWMSession {
	
	private String 	userId;
	private String 	roomId;
	private long 	creationTimestamp;
	
	public ExtGWTWMSession() {
		this.creationTimestamp = new Date().getTime();
	}
	
	public ExtGWTWMSession(String roomId, String userId) {
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
	
	public boolean equals(ExtGWTWMSession obj) {
		return obj != null &&
				obj.getRoomId() == getRoomId() &&
				obj.getUserId() == getUserId();
	}
}
