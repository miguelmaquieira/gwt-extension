package test.com.imotion.gwt.webmessenger.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.imotion.gwt.webmessenger.client.ExtGWTWMFactory;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCS;
import com.imotion.gwt.webmessenger.client.comm.ExtGWTWMCommCSConnection;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError;
import com.imotion.gwt.webmessenger.client.common.ExtGWTWMError.TYPE;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasErrorHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasOpenCommHandler;
import com.imotion.gwt.webmessenger.client.handler.ExtGWTWMHasReceiveCommHandler;

public class TestExtGWTWMChatRoom extends Composite implements ExtGWTWMHasReceiveCommHandler {

	private final TestExtGwtWMTexts 	TEXTS 	= GWT.create(TestExtGwtWMTexts.class);
	private final DateTimeFormat 		format 	= DateTimeFormat.getFormat("HH:mm:ss");
	
	private Label 		titleRoom;
	private TextArea 	areaMessage;
	private ListBox 	connectionsList;
	private TextBox		textMessage;
	private TextBox		textNickName;
	private TextBox		textRoomName;
	
	private TestExtGWTWMChatStatusPanel 	statusPanel;
	private TestExtGWTWMChatMessagePanel	chatMessagePanel;
	
	private ExtGWTWMCommCSConnection connectionCS;

	public TestExtGWTWMChatRoom() {
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("extgwt-webMessengerChatRoomContainer");

		// Title
		SimplePanel titlePanel = new SimplePanel();
		titlePanel.addStyleName("extgwt-webMessengerChatRoomTitle");
		contentPanel.add(titlePanel);
		titleRoom = new Label();
		titlePanel.setWidget(titleRoom);
		titleRoom.setText(TEXTS.chat_messenger_title_text());
		
		/// Message panel
		chatMessagePanel = new TestExtGWTWMChatMessagePanel();
		contentPanel.add(chatMessagePanel);
		chatMessagePanel.setEnabledButton(false);
		chatMessagePanel.addButtonClickhandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (connectionCS != null) {
					if (chatMessagePanel.isListeningCommEvents()) {
						chatMessagePanel.setMessage(TEXTS.chat_message_panel_not_listening_label_text());
						connectionCS.getCommHandlerWrapper().removeCommReceiveHandler(chatMessagePanel);
					} else {
						connectionCS.getCommHandlerWrapper().addCommReceiveHandler(chatMessagePanel);
						chatMessagePanel.setMessage(TEXTS.chat_message_panel_listening_new_messages_label_text());
					}
				}
			}
		});

		// North zone
		HorizontalPanel northZone = new HorizontalPanel();
		northZone.addStyleName("extgwt-webMessengerChatRoomNorthZone");
		contentPanel.add(northZone);

		// Message panel
		SimplePanel messagePanel = new SimplePanel();
		messagePanel.addStyleName("extgwt-webMessegerChatRoomMessagePanel");
		northZone.add(messagePanel);
		northZone.setCellWidth(messagePanel, "70%");
		areaMessage = new TextArea();
		areaMessage.setReadOnly(true);
		messagePanel.setWidget(areaMessage);

		// Conections panel
		SimplePanel conectionsPanel = new SimplePanel();
		conectionsPanel.addStyleName("extgwt-webMessegerChatRoomConnectionsPanel");
		northZone.add(conectionsPanel);
		northZone.setCellWidth(conectionsPanel, "30%");
		connectionsList = new ListBox(true);
		conectionsPanel.setWidget(connectionsList);

		/// South zone
		HorizontalPanel southZone = new HorizontalPanel();
		southZone.addStyleName("extgwt-webMessengerChatRoomSouthZone");
		southZone.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		contentPanel.add(southZone);

		//// Nick name panel
		FlowPanel nickNamePanel = new FlowPanel();
		nickNamePanel.addStyleName("extgwt-webMessengerChatRoomNickNamePanel");
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
		roomNamePanel.addStyleName("extgwt-webMessengerChatRoomRoomNamePanel");
		southZone.add(roomNamePanel);

		///// Label room name
		Label lblRoomName = new Label(TEXTS.room_name_label_text());
		roomNamePanel.add(lblRoomName);

		///// Text room name
		textRoomName = new TextBox();
		textRoomName.setText(TEXTS.room_name_default_value_text());
		roomNamePanel.add(textRoomName);

		//// Button connect
		Button buttonConnect = new Button(TEXTS.button_conect_text());
		southZone.add(buttonConnect);
		southZone.setCellHorizontalAlignment(buttonConnect, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonConnect.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String userId = textNickName.getText();
				String roomId = textRoomName.getText();
				ExtGWTWMCommCSConnection connection = getCommCS(userId, roomId);
				if (connection != null) {
					connection.connect();
					textMessage.setEnabled(true);
					chatMessagePanel.setEnabledButton(true);
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
				if (connectionCS != null) {
					connectionCS.disconnect();
					connectionCS = null;
					textMessage.setEnabled(false);
					chatMessagePanel.setEnabledButton(false);
				}
			}
		});


		/// Send Message panel
		HorizontalPanel sendMessagePanel = new HorizontalPanel();
		sendMessagePanel.addStyleName("extgwt-webMessengerChatRoomSendMessagePanel");
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
					ExtGWTWMCommCSConnection connection = getCommCS(userId, roomId);
					if (connection != null) {
						connection.sendMessage(textMessage.getText());
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
				ExtGWTWMCommCSConnection connection = getCommCS(userId, roomId);
				if (connection != null) {
					connection.sendMessage(textMessage.getText());
					textMessage.setText("");
				} else {
					Window.alert("No se ha podido enviar el mensaje. Parámetros: 'userId': " + userId + " ' roomId: '" + roomId);
				}
			}
		});
		
		/// status panel
		statusPanel = new TestExtGWTWMChatStatusPanel();
		contentPanel.add(statusPanel);
	}

	/**********************************************************************
	 *                   IExtGWTWebMessengerWidgetDisplay				  *
	 **********************************************************************/

	@Override
	public void handleReceivedMessage(String message, long timestamp, String sender) {
		String time = format.format(new Date(timestamp));
		String newMessage = sender	+ " (" + time + ")" + ": " + message;
		writeMessage(newMessage);
		String me = connectionCS.getSessionData().getUserId();
		if (!me.equals(sender)) {
			int elementCount = connectionsList.getItemCount();
			boolean contains = false;
			for (int index = 0; index < elementCount; index++) {
				String item = connectionsList.getItemText(index);
				if (item.equals(sender)) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				connectionsList.addItem(sender);
			}
		}
	}
	
	/**********************************************************************
	 *                		   PUBLIC FUNCTIONS						      *
	 **********************************************************************/
	
	public void setChatRoomId(String roomId) {
		textRoomName.setText(roomId);
		titleRoom.setText(TEXTS.chat_messenger_title_text() + ": " + roomId);
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
				ExtGWTWMCommCS commCS = ExtGWTWMFactory.getDefaultStandaloneCommCS();
				connectionCS = commCS.getConnection(roomname, nickname);
				connectionCS.getCommHandlerWrapper().addCommReceiveHandler(this);
				connectionCS.getCommHandlerWrapper().addCommHandler(statusPanel);
				connectionCS.getErrorHandlerWrapper().addErrorHandler(statusPanel);
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
	
	private void testCase1() {
		
		// Instanciones el gestor de conexiones
		ExtGWTWMCommCS commCS = ExtGWTWMFactory.getDefaultStandaloneCommCS();
		
		// Pedimos una conexión para un determinado roomId y userId
		ExtGWTWMCommCSConnection connection = commCS.getConnection("roomId", "userId");
		
		// Añadimos un handler de errores
		connection.getErrorHandlerWrapper().addErrorHandler(new ExtGWTWMHasErrorHandler() {
			
			@Override
			public void onError(ExtGWTWMError error) {
				// tratar error
			}
			
			@Override
			public TYPE[] getErrorType() {
				return new TYPE[] { TYPE.EXCEPTION };
			}
		});
		
		// Añadimos un handler de eventos de apertura de la conexión
		connection.getCommHandlerWrapper().addCommReceiveHandler(this);
		
		// Abrimos la conexión
		connection.connect();		
	}
	
	private void testCase2() {
		
		// Instanciones el gestor de conexiones
		ExtGWTWMCommCS commCS = ExtGWTWMFactory.getDefaultStandaloneCommCS();
		
		// Pedimos una conexión para un determinado roomId y userId
		final ExtGWTWMCommCSConnection connection = commCS.getConnection("roomId", "userId");
		
		// Añadimos el handler de apertura de la conexión
		connection.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {
			
			@Override
			public void handleConnectionOpened() {
				connection.sendMessage("message");
			}
		});
		
		// Añadimos un handler de eventos de apertura de la conexión
		connection.getCommHandlerWrapper().addCommReceiveHandler(this);
		
		// Abrimos la conexión
		connection.connect();	
		
		
	}
	
	private void testCase3() {
		
		// Instanciones el gestor de conexiones
		final ExtGWTWMCommCS commCS = ExtGWTWMFactory.getDefaultStandaloneCommCS();
		
		// Pedimos una conexión para un determinado roomId y userId
		final ExtGWTWMCommCSConnection connection = commCS.getConnection("roomId", "userId");
		
		// Añadimos el handler de apertura de la conexión
		connection.getCommHandlerWrapper().addCommOpenHandler(new ExtGWTWMHasOpenCommHandler() {
			
			@Override
			public void handleConnectionOpened() {
				connection.sendMessage("message");
			}
		});
		
		// Añadimos un handler de eventos de apertura de la conexión
		connection.getCommHandlerWrapper().addCommReceiveHandler(new ExtGWTWMHasReceiveCommHandler() {
			
			@Override
			public void handleReceivedMessage(String message, long timstamp, String sender) {
				commCS.releaseConnection(connection);
			}
		});
		
		// Abrimos la conexión
		connection.connect();	
		
		
	}
}
