package com.imotion.gwt.webmessenger.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public class TestExtGWTWMChatMessagePanel extends Composite implements ExtGWTWMHasReceiveCommHandler {
	
	private final TestExtGwtWMTexts TEXTS = GWT.create(TestExtGwtWMTexts.class);
	private ToggleButton 	activateButton;
	private Label 			messageLabel;
	private Timer 			timerText;
	
	public TestExtGWTWMChatMessagePanel() {
		HorizontalPanel contentPanel = new HorizontalPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatRoomMessagePanel");
		contentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		// Message label
		messageLabel = new Label(TEXTS.chat_message_panel_waiting_label_text());
		contentPanel.add(messageLabel);
		
		// Button
		activateButton = new ToggleButton(new Image("images/switch-off.png"), 
											new Image("images/switch-on.png"));
		activateButton.setValue(true);
		contentPanel.add(activateButton);
		contentPanel.setCellHorizontalAlignment(activateButton, HasHorizontalAlignment.ALIGN_RIGHT);
		contentPanel.setCellWidth(activateButton, "32px");
	}

	@Override
	public void handleReceivedMessage(String message, long timstamp, String sender) {
		setMessage(sender + ": " + message);
		messageLabel.addStyleName("extgwt-textTransition");
		if (timerText != null && timerText.isRunning()) {
			timerText.cancel();
		}
		timerText = new Timer() {
			public void run() {
				setMessage(TEXTS.chat_message_panel_listening_new_messages_label_text());
				messageLabel.removeStyleName("extgwt-textTransition");
			}
		};
		timerText.schedule(3000);
	}
	
	public void setMessage(String message) {
		messageLabel.setText(message);
	}
	
	public boolean isListeningCommEvents() {
		return activateButton.getValue();
	}
	
	public void addButtonClickhandler(ClickHandler handler) {
		activateButton.addClickHandler(handler);
	}
	
	public void setEnabledButton(boolean value) {
		activateButton.setEnabled(value);
	}
}
