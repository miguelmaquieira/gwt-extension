package com.imotion.gwt.webmessenger.client;

import com.google.gwt.i18n.client.Messages;

public interface ExtGWTWMMessageTexts extends Messages {

	@DefaultMessage("Exception error: \nAction: {0} \nroomId: {1} \nuserId: {2} \nException stacktrace: {3}")
	public String error_common_exception_message_text(String action, String roomId, String userId, String messageException);
	
	@DefaultMessage("Connection already opened with params: roomId: {0} userId: {1}")
	public String error_open_connection_message_text(String roomId, String userId);
	
	@DefaultMessage("Connection attemp with wrong parameter. You must provide an 'userId' and a 'roomId params. \n\troomId: {0} \n\tuserId: {1}") 
	public String error_get_connection_param_not_informed_message_text(String roomId, String userId);
	
}
