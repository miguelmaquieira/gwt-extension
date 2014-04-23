package test.com.imotion.gwt.webmessenger.testcase.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasCloseCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public class TestExtGWTWMTestCaseMessagePanel extends Composite implements ExtGWTWMHasCloseCommHandler, ExtGWTWMHasReceiveCommHandler,ExtGWTWMHasErrorHandler{
	
	private final TestExtGwtWMTestCaseTexts 	TEXTS 	= GWT.create(TestExtGwtWMTestCaseTexts.class);
	private final DateTimeFormat 		format 	= DateTimeFormat.getFormat("HH:mm:ss");
	
	private TextArea areaMessage;
	
	public TestExtGWTWMTestCaseMessagePanel() {
		
		// Message panel
		SimplePanel messagePanel = new SimplePanel();
		initWidget(messagePanel);
		messagePanel.addStyleName("extgwt-testCaseConnectionErrorMessagePanel");
		areaMessage = new TextArea();
		areaMessage.setReadOnly(true);
		messagePanel.setWidget(areaMessage);
		
	}


	/**********************************************************************
	 *                		     HANDLERS						   		  *
	 **********************************************************************/
	
	@Override
	public void handleConnectionClosed() {
		writeMessage(TEXTS.status_message_label_connection_fallen_network_error_text());
	}

	@Override
	public void handleReceivedMessage(String message, long timstamp, String sender) {
		String time = format.format(new Date(timstamp));
		String newMessage = sender	+ " (" + time + ")" + ": " + message;
		writeMessage(newMessage);
	}

	@Override
	public void onError(ExtGWTWMError error) {
		
		String message = "Error:";
		
		if (error.getErrorType() == TYPE.TRANSPORT) {
			message = TEXTS.status_message_label_connection_transportation_error_text() + error.getMessage();
		} else if (error.getErrorType() == TYPE.CONNECTION_ERROR) {
			message = TEXTS.status_message_label_connection_connect_error_text();
		}  else if (error.getErrorType() == TYPE.SEND_MESSAGE) {
			message = TEXTS.status_message_label_connection_send_message_error_text();			
		} else {
			message += error.getErrorType().toString();
		}
		writeMessage(message);
	}

	@Override
	public TYPE[] getErrorType() {
		return new TYPE[]{ TYPE.ALL };
	}

	
	/**********************************************************************
	 *                		   PUBLIC FUNCTIONS						      *
	 **********************************************************************/
	
	
	public void writeMessage(String text) {
		String finalText = new StringBuilder()
		.append(areaMessage.getText())
		.append(text)
		.append("\n")
		.toString();
		areaMessage.setText(finalText);
		areaMessage.setCursorPos(finalText.length());
	}


}
