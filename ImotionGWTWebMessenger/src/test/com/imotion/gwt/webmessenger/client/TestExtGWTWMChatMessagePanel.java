package test.com.imotion.gwt.webmessenger.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMHasReceiveCommHandler;

public class TestExtGWTWMChatMessagePanel extends Composite implements ExtGWTWMHasReceiveCommHandler {
	
	private final TestExtGwtWMTexts TEXTS = GWT.create(TestExtGwtWMTexts.class);
	private ToggleButton 	activateButton;
	private Label 			messageLabel;
	
	public TestExtGWTWMChatMessagePanel() {
		HorizontalPanel contentPanel = new HorizontalPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatMessagePanel");
		contentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		// Message label
		messageLabel = new Label(TEXTS.chat_message_panel_waiting_label_text());
		contentPanel.add(messageLabel);
		
		// Button
		activateButton = new ToggleButton(new Image("images/switch-off.png"), 
											new Image("images/switch-on.png"));
		contentPanel.add(activateButton);
		contentPanel.setCellHorizontalAlignment(activateButton, HasHorizontalAlignment.ALIGN_RIGHT);
		contentPanel.setCellWidth(activateButton, "32px");
	}

	@Override
	public void handleReceivedMessage(String message, long timstamp, String sender) {
		setMessage(sender + ": " + message);
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
