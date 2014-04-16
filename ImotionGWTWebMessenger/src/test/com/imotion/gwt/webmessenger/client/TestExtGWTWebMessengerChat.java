package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.imotion.gwt.webmessenger.client.ExtGWTWebMessengerCommCS;
import com.imotion.gwt.webmessenger.client.ExtGWTWebMessengerHasCommHandler;

public class TestExtGWTWebMessengerChat extends Composite implements ExtGWTWebMessengerHasCommHandler {

	private final static TestExtGwtWebMessengerTexts TEXTS = GWT.create(TestExtGwtWebMessengerTexts.class);

	private TextArea 	areaMessage;
	private ListBox 	connectionsList;
	private TextBox		textMessage;
	private TextBox		textNickName;
	private TextBox		textRoomName;
	private ExtGWTWebMessengerCommCS commCS;

	public TestExtGWTWebMessengerChat() {
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatContainer");

		// Title
		SimplePanel titlePanel = new SimplePanel();
		titlePanel.addStyleName("extgwt-webMessengerChatTitle");
		contentPanel.add(titlePanel);
		Label title = new Label();
		titlePanel.setWidget(title);
		title.setText(TEXTS.chat_messenger_title_text());

		// North zone
		HorizontalPanel northZone = new HorizontalPanel();
		northZone.addStyleName("extgwt-webMessengerChatNorthZone");
		contentPanel.add(northZone);

		// Message panel
		SimplePanel messagePanel = new SimplePanel();
		messagePanel.addStyleName("extgwt-webMessegerChatMessagePanel");
		northZone.add(messagePanel);
		northZone.setCellWidth(messagePanel, "70%");
		areaMessage = new TextArea();
		areaMessage.setReadOnly(true);
		messagePanel.setWidget(areaMessage);

		// Conections panel
		SimplePanel conectionsPanel = new SimplePanel();
		conectionsPanel.addStyleName("extgwt-webMessegerChatConnectionsPanel");
		northZone.add(conectionsPanel);
		northZone.setCellWidth(conectionsPanel, "30%");
		connectionsList = new ListBox(true);
		for (int i = 0; i < 10; i++) {
			connectionsList.addItem("Item: " + i);
		}
		conectionsPanel.setWidget(connectionsList);

		/// South zone
		HorizontalPanel southZone = new HorizontalPanel();
		southZone.addStyleName("extgwt-webMessengerChatSouthZone");
		southZone.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		contentPanel.add(southZone);

		//// Nick name panel
		FlowPanel nickNamePanel = new FlowPanel();
		nickNamePanel.addStyleName("extgwt-webMessengerChatNickNamePanel");
		southZone.add(nickNamePanel);

		///// Label nick name
		Label lblNickName = new Label(TEXTS.nick_name_label_text());
		nickNamePanel.add(lblNickName);

		///// Text nick name
		textNickName = new TextBox();
		textNickName.setText(TEXTS.nick_name_default_value_text());
		nickNamePanel.add(textNickName);

		//// Room name panel
		FlowPanel roomNamePanel = new FlowPanel();
		roomNamePanel.addStyleName("extgwt-webMessengerChatRoomNamePanel");
		southZone.add(roomNamePanel);

		///// Label room name
		Label lblRoomName = new Label(TEXTS.room_name_label_text());
		roomNamePanel.add(lblRoomName);

		///// Text room name
		textRoomName = new TextBox();
		textRoomName.setText(TEXTS.room_name_default_value_text());
		roomNamePanel.add(textRoomName);

//		//// Change nick name button
//		Button buttonChangeNickName = new Button(TEXTS.button_reconect_text());
//		southZone.add(buttonChangeNickName);
//		southZone.setCellHorizontalAlignment(buttonChangeNickName, HasHorizontalAlignment.ALIGN_RIGHT);
//		buttonChangeNickName.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				String userId = textNickName.getText();
//				String roomId = textRoomName.getText();
//				ExtGWTWebMessengerCommCS comm = getCommCS(userId, roomId);
//				if (commCS != null) {
//					comm.reconnect(userId, roomId);
//				} else {
//					Window.alert("No se ha podido obtener comunicación con los parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
//				}
//			}
//		});

		//// Button connect
		Button buttonConnect = new Button(TEXTS.button_conect_text());
		southZone.add(buttonConnect);
		southZone.setCellHorizontalAlignment(buttonConnect, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonConnect.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userId = textNickName.getText();
				String roomId = textRoomName.getText();
				ExtGWTWebMessengerCommCS comm = getCommCS(userId, roomId);
				if (comm != null) {
					textMessage.setEnabled(true);
				} else {
					Window.alert("No se ha podido iniciar comunicación con los parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
				}
			}
		});

		//// Button Disconnect
		Button buttonDisconnect = new Button(TEXTS.button_disconect_text());
		southZone.add(buttonDisconnect);
		southZone.setCellHorizontalAlignment(buttonDisconnect, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonDisconnect.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (commCS != null) {
					commCS.disconnect();
					commCS = null;
					textMessage.setEnabled(false);
				}
			}
		});


		/// Send Message
		HorizontalPanel sendMessagePanel = new HorizontalPanel();
		sendMessagePanel.addStyleName("extgwt-webMessengerChatSendMessagePanel");
		contentPanel.add(sendMessagePanel);

		//// Message text
		textMessage = new TextBox();
		sendMessagePanel.add(textMessage);
		sendMessagePanel.setCellWidth(textMessage, "80%");
		textMessage.setEnabled(false);
		textMessage.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String userId = textNickName.getText();
					String roomId = textRoomName.getText();
					ExtGWTWebMessengerCommCS comm = getCommCS(userId, roomId);
					if (comm != null) {
						comm.sendMessage(textMessage.getText());
						textMessage.setText("");
					} else {
						Window.alert("No se ha podido enviar el mensaje. Parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
					}
				}
			}
		});

		//// Button send
		Button buttonSend = new Button(TEXTS.button_send_text());
		sendMessagePanel.add(buttonSend);
		sendMessagePanel.setCellWidth(buttonSend, "20%");
		sendMessagePanel.setCellHorizontalAlignment(buttonSend, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonSend.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userId = textNickName.getText();
				String roomId = textRoomName.getText();
				ExtGWTWebMessengerCommCS comm = getCommCS(userId, roomId);
				if (comm != null) {
					comm.sendMessage(textMessage.getText());
					textMessage.setText("");
				} else {
					Window.alert("No se ha podido enviar el mensaje. Parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
				}
			}
		});
	}

	/**********************************************************************
	 *                   IExtGWTWebMessengerWidgetDisplay				  *
	 **********************************************************************/

	@Override
	public void handleReceivedMessage(String text, long date, String sender) {
		String newMessage = sender	+ ": " + text;
		writeMessage(newMessage);	
	}

	@Override
	public void handleConnectionOpened() {		
		writeMessage(TEXTS.connection_opened_text());
		textMessage.setEnabled(true);
	}

	@Override
	public void handleConnectionClosed() {		
		writeMessage(TEXTS.connection_closed_text());
		textMessage.setText("");
		textMessage.setEnabled(false);
	}



	/**********************************************************************
	 *                		   PRIVATE FUNCTIONS						  *
	 **********************************************************************/
		
	private ExtGWTWebMessengerCommCS getCommCS(String nickname, String roomname) {
		if (nickname == null || nickname.length() == 0 || roomname == null || roomname.length() == 0) {
			Window.alert("Debes informar: 'nickname' y 'roomname'");
			return null;
		} else  {
			if (commCS == null) {
				commCS = GWT.create(ExtGWTWebMessengerCommCS.class);
				commCS.initConnection(nickname, roomname);
				commCS.addCommHandler(this);
			} else {
				if (!nickname.equals(commCS.getSessionData().getUserId()) || !roomname.equals(commCS.getSessionData().getRoomId())) {
					commCS.disconnect();
					commCS.initConnection(nickname, roomname);
					commCS.addCommHandler(this);
				}
			}
			return commCS;
		}
	}
	
	private void writeMessage(String text) {
		String finalText = new StringBuilder()
			.append(areaMessage.getText())
			.append(text)
			.append("\n")
			.toString();
		areaMessage.setText(finalText);
	}
}
