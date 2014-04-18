package com.imotion.gwt.webmessenger.client;

import com.google.gwt.i18n.client.Messages;

public interface ExtGWTWMMessageTexts extends Messages {

	@DefaultMessage("Exception error: \nAction: {0} \nUserId: {1} \nroomId: {2} \nException stacktrace: {3}")
	public String error_common_exception_message_text(String action, String userId, String roomId, String messageException);
	
}
