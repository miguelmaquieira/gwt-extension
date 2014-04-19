package test.com.imotion.gwt.webmessenger.client;

import com.google.gwt.i18n.client.Constants;

public interface TestExtGwtWMTexts extends Constants {
	
	@DefaultStringValue("Cliente messenger")
	String chat_messenger_title_text();
	
	@DefaultStringValue("Send message")
	String button_send_text();
	
	@DefaultStringValue("Reconect")
	String button_reconect_text();
	
	@DefaultStringValue("userNickName")
	String nick_name_default_value_text();
	
	@DefaultStringValue("roomDefault")
	String room_name_default_value_text();
	
	@DefaultStringValue("Nick name")
	String nick_name_label_text();
	
	@DefaultStringValue("Room name")
	String room_name_label_text();
	
	@DefaultStringValue("Conect")
	String button_conect_text();
	
	@DefaultStringValue("Disconect")
	String button_disconect_text();
	
	@DefaultStringValue("Connection status:")
	String status_label_text();
	
	@DefaultStringValue("Waiting connection...")
	String status_message_label_waiting_connection_text();
	
	@DefaultStringValue("Waiting messages...")
	String status_message_label_waiting_messages_text();
	
	@DefaultStringValue("Connection closed...")
	String status_message_label_connection_closed_text();
	
	@DefaultStringValue("Message send: ")
	String status_message_label_message_sent_text();
	
	@DefaultStringValue("Connection open...")
	String status_message_label_connection_open_text();
	
	@DefaultStringValue("Transportation failure: ")
	String status_message_label_connection_transportation_error_text();
	
	@DefaultStringValue("Waiting orders...")
	String chat_message_panel_waiting_label_text();
	
	@DefaultStringValue("Listening activated. Waiting new messages...")
	String chat_message_panel_listening_new_messages_label_text();
	
	@DefaultStringValue("Listener deactivated...")
	String chat_message_panel_not_listening_label_text();
	
}
