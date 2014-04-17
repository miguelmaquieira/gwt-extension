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

	
	@DefaultStringValue("Connection opened")
	String connection_opened_text();
	
	@DefaultStringValue("Connection closed")
	String connection_closed_text();
	
}
