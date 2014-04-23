package test.com.imotion.gwt.webmessenger.testcase.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.webmessenger.client.ExtGWTWMException;
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;

public class TestExtGWTWMTestCaseConnectionError extends Composite  {

	private final TestExtGwtWMTestCaseTexts 	TEXTS 	= GWT.create(TestExtGwtWMTestCaseTexts.class);
	private ExtGWTWMCommCSConnection connectionCS;

	private TextBox 	textRoomName;
	private TextBox 	textNickName;
	private TextBox		textMessage;
	private Button 		buttonSend;
	private Button 		buttonConnect;
	private TestExtGWTWMTestCaseMessagePanel messagePanel;

	public TestExtGWTWMTestCaseConnectionError() {
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);

		//// Nick name panel
		FlowPanel nickNamePanel = new FlowPanel();
		contentPanel.add(nickNamePanel);

		///// Label nick name
		Label lblNickName = new Label(TEXTS.nick_name_label_text());
		nickNamePanel.add(lblNickName);

		///// Text nick name
		textNickName = new TextBox();
		textNickName.setText(TEXTS.nick_name_default_value_text());
		nickNamePanel.add(textNickName);

		//// Room name panel
		FlowPanel roomNamePanel = new FlowPanel();
		contentPanel.add(roomNamePanel);

		///// Label room name
		Label lblRoomName = new Label(TEXTS.room_name_label_text());
		roomNamePanel.add(lblRoomName);

		///// Text room name
		textRoomName = new TextBox();
		textRoomName.setText(TEXTS.room_name_default_value_text());
		roomNamePanel.add(textRoomName);

		/// Send Message panel
		HorizontalPanel sendMessagePanel = new HorizontalPanel();
		sendMessagePanel.addStyleName("extgwt-webMessengerMessagingTestSendMessagePanel");
		contentPanel.add(sendMessagePanel);

		//// Message text
		textMessage = new TextBox();
		sendMessagePanel.add(textMessage);
		textMessage.setEnabled(false);
		sendMessagePanel.setCellWidth(textMessage, "60%");

		buttonConnect = new Button(TEXTS.button_conect_text());
		sendMessagePanel.add(buttonConnect);
		sendMessagePanel.setCellWidth(buttonConnect, "20%");

		buttonConnect.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userId = textNickName.getText();
				String roomId = textRoomName.getText();
				ExtGWTWMCommCSConnection connection = getCommCS(userId, roomId);
				if (connection != null) {
					connection.connect();
				} 
			}
		});

		buttonSend = new Button(TEXTS.button_send_text());
		sendMessagePanel.add(buttonSend);
		sendMessagePanel.setCellWidth(buttonSend, "20%");

		buttonSend.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userId = textNickName.getText();
				String roomId = textRoomName.getText();
				ExtGWTWMCommCSConnection connection = getCommCS(userId, roomId);
				if (connection != null) {
					connection.sendMessage(textMessage.getText());;
				} 
			}
		});


		// Message panel		
		messagePanel = new TestExtGWTWMTestCaseMessagePanel();
		contentPanel.add(messagePanel);

	}


	/**********************************************************************
	 *                		   PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private ExtGWTWMCommCSConnection getCommCS(String nickname, String roomname) {

		if (connectionCS == null) {
			try {
				connectionCS = ExtGWTWMFactory.getDefaultStandaloneCommCS().getConnection(roomname, nickname);


				connectionCS.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {	
					@Override
					public void handleConnectionOpened() {
						String text = "Status: Conection open. userId: " + textNickName.getText() + ", roomId: " + textRoomName.getText();						
						messagePanel.writeMessage(text);		
						textMessage.setEnabled(true);
						buttonConnect.setEnabled(false);											
					}
				});	

				connectionCS.getCommHandlerWrapper().addCommReceiveHandler(messagePanel);
				connectionCS.getCommHandlerWrapper().addCommCloseHandler(messagePanel);
				connectionCS.getErrorHandlerWrapper().addErrorHandler(messagePanel);
			} catch (ExtGWTWMException e) {
				Window.alert(e.getMessage());
			}		


		}	
		return connectionCS;

	}

}
