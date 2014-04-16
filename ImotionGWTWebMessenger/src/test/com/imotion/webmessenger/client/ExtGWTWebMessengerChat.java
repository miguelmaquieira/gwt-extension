package test.com.imotion.webmessenger.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class ExtGWTWebMessengerChat extends Composite {
	
	private final static ExtGwtWebMessengerTexts TEXTS = GWT.create(ExtGwtWebMessengerTexts.class);
	
	private TextArea 	areaMessage;
	private ListBox 	connectionsList;
	private TextBox		textMessage;
	private TextBox		textNickName;
	private TextBox		textRoomName;
	
	public ExtGWTWebMessengerChat() {
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

		//// Change nick name button
		Button buttonChangeNickName = new Button(TEXTS.button_reconect_text());
		southZone.add(buttonChangeNickName);
		southZone.setCellHorizontalAlignment(buttonChangeNickName, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonChangeNickName.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO be sure if we have to enconding the message
				Window.alert("Change nickname: " + textNickName.getText());
			}
		});
		
		//// Button connect
		Button buttonConnect = new Button(TEXTS.button_conect_text());
		southZone.add(buttonConnect);
		southZone.setCellHorizontalAlignment(buttonConnect, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonConnect.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO be sure if we have to enconding the message
				Window.alert("Connect. Nick name: " + textNickName.getText());
			}
		});
		
		//// Button connect
		Button buttonDisconnect = new Button(TEXTS.button_disconect_text());
		southZone.add(buttonDisconnect);
		southZone.setCellHorizontalAlignment(buttonDisconnect, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonDisconnect.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO be sure if we have to enconding the message
				Window.alert("Disconnect. Nick name: " + textNickName.getText());
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
		
		//// Button send
		Button buttonSend = new Button(TEXTS.button_send_text());
		sendMessagePanel.add(buttonSend);
		sendMessagePanel.setCellWidth(buttonSend, "20%");
		sendMessagePanel.setCellHorizontalAlignment(buttonSend, HasHorizontalAlignment.ALIGN_RIGHT);
		buttonSend.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO be sure if we have to enconding the message
				Window.alert("Sending: " + textMessage.getText());
			}
		});
	}
}
