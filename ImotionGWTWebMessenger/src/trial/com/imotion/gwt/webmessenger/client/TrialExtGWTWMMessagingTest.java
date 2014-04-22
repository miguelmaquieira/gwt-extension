package trial.com.imotion.gwt.webmessenger.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public class TrialExtGWTWMMessagingTest extends Composite {

	private final TrialExtGwtWMTexts 	TEXTS 	= GWT.create(TrialExtGwtWMTexts.class);
	private final DateTimeFormat 		format 	= DateTimeFormat.getFormat("HH:mm:ss");

	private ExtGWTWMCommCSConnection connectionCS;
	
	private TextBox 	textRoomName;
	private TextBox 	textNickName;
	private TextBox		textMessage;
	private TextArea 	areaMessage;
	private Button 		buttonSend;


	public TrialExtGWTWMMessagingTest() {

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
		sendMessagePanel.setCellWidth(textMessage, "80%");

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
					connection.connect();
				} else {
					Window.alert("No se ha podido iniciar comunicación con los parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
				}
			}
		});


		// Message panel
		SimplePanel messagePanel = new SimplePanel();
		messagePanel.addStyleName("extgwt-webMessegerMessagingTestMessagePanel");
		contentPanel.add(messagePanel);
		areaMessage = new TextArea();
		areaMessage.setReadOnly(true);
		messagePanel.setWidget(areaMessage);

	}



	/**********************************************************************
	 *                		   PRIVATE FUNCTIONS						  *
	 **********************************************************************/

	private ExtGWTWMCommCSConnection getCommCS(String nickname, String roomname) {
		if (nickname == null || nickname.length() == 0 || roomname == null || roomname.length() == 0) {
			Window.alert("Debes informar: 'nickname' y 'roomname'");
			return null;
		} else  {
			if (connectionCS == null) {
				connectionCS = ExtGWTWMFactory.getDefaultStandaloneCommCS().getConnection(roomname, nickname);			
				
				connectionCS.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {	
					@Override
					public void handleConnectionOpened() {
						String text ="Status: Conection open. userId: " + textNickName.getText() + ", roomId: " + textRoomName.getText();						
						writeMessage(text);		
						textMessage.setEnabled(true);
						buttonSend.setEnabled(false);						
						connectionCS.sendMessage(textMessage.getText());						
					}
				});	
				
								
				connectionCS.getCommHandlerWrapper().addCommReceiveHandler(new ExtGWTWMHasReceiveCommHandler() {				
					@Override
					public void handleReceivedMessage(String message, long timstamp, String sender) {
						String time = format.format(new Date(timstamp));
						String newMessage = sender	+ " (" + time + ")" + ": " + message;
						writeMessage(newMessage);	
						buttonSend.setEnabled(false);
						
					}
				});
			}
			return connectionCS;
		}
	}

	private void writeMessage(String text) {
		String finalText = new StringBuilder()
		.append(areaMessage.getText())
		.append(text)
		.append("\n")
		.toString();
		areaMessage.setText(finalText);
		areaMessage.setCursorPos(finalText.length());
	}


}
