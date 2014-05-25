package com.imotion.gwt.webmessenger.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class TestExtGWTWMChatRoomTab extends Composite {
	
	private Image 	closeAction;
	private Anchor 	tabAction;
	
	public TestExtGWTWMChatRoomTab(String chatName) {
		
		// Content panel
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatRoomTab");
		
		tabAction = new Anchor(chatName);
		contentPanel.add(tabAction);
		
		closeAction = new Image("/images/close-round.png");
		contentPanel.add(closeAction);
	}
	
	public void addCloseAction(ClickHandler handler) {
		closeAction.addClickHandler(handler);
	}
	
	public void addClickAction(ClickHandler handler) {
		tabAction.addClickHandler(handler);
	}
	
	public void addAnchorStylename(String stylename) {
		tabAction.addStyleName(stylename);
	}
	
	public void removeAnchorStylename(String stylename) {
		tabAction.removeStyleName(stylename);
	}
}
