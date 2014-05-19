package com.imotion.dslam.front.business.client;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.front.business.desktop.client.common.DSLAMBusI18NCommonMessages;
import com.selene.arch.exe.gwt.client.common.AEGWTMessageWrapper;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;

public class DSLAMBusMessageWrapper extends AEGWTMessageWrapper {

	private DSLAMBusI18NCommonMessages messages = GWT.create(DSLAMBusI18NCommonMessages.class);

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
