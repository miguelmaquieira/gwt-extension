package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommHandler;

public class TestExtGWTWMChatStatusPanel extends Composite implements ExtGWTWMCommHandler {
	
	private final TestExtGwtWMTexts 	TEXTS 	= GWT.create(TestExtGwtWMTexts.class);
	
	private Image connectionLed;
	private Label statusMessageLabel;
	
	public TestExtGWTWMChatStatusPanel() {
		HorizontalPanel contentPanel = new HorizontalPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatStatusPanel");
		contentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		contentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		//// label status
		Label labelStatus = new Label(TEXTS.status_label_text());
		labelStatus.getElement().setId("labelStatus");
		contentPanel.add(labelStatus);
		contentPanel.setCellHorizontalAlignment(labelStatus, HasHorizontalAlignment.ALIGN_LEFT);
		contentPanel.setCellWidth(labelStatus, "120px");
		
		//// Connection icon
		connectionLed = new Image("images/connection_off.png");
		contentPanel.add(connectionLed);
		connectionLed.getElement().setId("connectionLed");
		contentPanel.setCellHorizontalAlignment(connectionLed, HasHorizontalAlignment.ALIGN_LEFT);
		
		//// Dashboard message label
		statusMessageLabel = new Label(TEXTS.status_message_label_waiting_connection_text());
		contentPanel.add(statusMessageLabel);
		statusMessageLabel.getElement().setId("messageStatus");
		contentPanel.setCellHorizontalAlignment(statusMessageLabel, HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	public void setStatusMessage(String message) {
		statusMessageLabel.setText(message);
	}

	@Override
	public void handleConnectionOpened() {
		connectionLed.setUrl("images/connection_on.png");
		statusMessageLabel.setText(TEXTS.status_message_label_connection_open_text());
		Timer timerText = new Timer() {
			public void run() {
				statusMessageLabel.setText(TEXTS.status_message_label_waiting_messages_text());
				statusMessageLabel.removeStyleName("extgwt-textTransicion");
			}
		};
		timerText.schedule(5000);
		
		Timer timerTransition = new Timer() {
			public void run() {
				statusMessageLabel.addStyleName("extgwt-textTransicion");
			}
		};
		timerTransition.schedule(2000);
	}

	@Override
	public void handleConnectionClosed() {
		connectionLed.setUrl("images/connection_off.png");
		statusMessageLabel.setText(TEXTS.status_message_label_connection_closed_text());
		Timer timerText = new Timer() {
			public void run() {
				statusMessageLabel.setText(TEXTS.status_message_label_waiting_connection_text());
				statusMessageLabel.removeStyleName("extgwt-textTransicion");
			}
		};
		timerText.schedule(5000);
		
		Timer timerTransition = new Timer() {
			public void run() {
				statusMessageLabel.addStyleName("extgwt-textTransicion");
			}
		};
		timerTransition.schedule(2000);
	}

	@Override
	public void handleSendMessage(String message, long timestamp, String sender) {
		connectionLed.setUrl("images/ajax-loader.gif");
		statusMessageLabel.setText(TEXTS.status_message_label_message_sent_text() + message);
	}

	@Override
	public void handleReceivedMessage(String message, long timstamp, String sender) {
		connectionLed.setUrl("images/connection_on.png");
		statusMessageLabel.setText(TEXTS.status_message_label_waiting_messages_text());
	}
}
