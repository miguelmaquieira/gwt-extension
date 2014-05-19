package com.imotion.transit.front.business.client;

import com.google.gwt.core.shared.GWT;
import com.imotion.transit.front.business.desktop.client.common.TRANSBusI18NCommonMessages;
import com.selene.arch.exe.gwt.client.common.AEGWTMessageWrapper;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;

public class TRANSBusMessageWrapper extends AEGWTMessageWrapper {

	private TRANSBusI18NCommonMessages messages = GWT.create(TRANSBusI18NCommonMessages.class);

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
