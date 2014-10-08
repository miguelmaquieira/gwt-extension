package com.imotion.dslam.front.business.client;

import com.google.gwt.core.shared.GWT;
import com.selene.arch.exe.gwt.client.common.AEGWTMessageWrapper;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;

public class CRONIOBusMessageWrapper extends AEGWTMessageWrapper {

	private CRONIOBusI18NCommonMessages messages = GWT.create(CRONIOBusI18NCommonMessages.class);

	@Override
	public String getMessageText(String messageId) {
		String message = messages.getString(messageId);
		if (AEGWTStringUtils.isEmptyString(message)) {
			message = getDefaultMessageText();
		}
		return message;
	}

	@Override
	public String getDefaultMessageText() {
		return messages.SAILOR_COMMON_MESSAGE_NOT_FOUND_MESSAGE();
	}
}
