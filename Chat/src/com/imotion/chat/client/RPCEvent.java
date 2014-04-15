package com.imotion.chat.client;

import java.io.Serializable;

public class RPCEvent implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String data;
    private String broadcastId;
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}
    
}
