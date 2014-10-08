package com.imotion.dslam.front.business.desktop.client.widget.authentication;

import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.common.AEGWTCheckBox;
import com.selene.arch.exe.gwt.client.ui.widget.textbox.AEGWTPasswordBox;
import com.selene.arch.exe.gwt.client.ui.widget.textbox.AEGWTTextBox;

public class CRONIOBusDesktopPasswordTextBox extends AEGWTPasswordBox {

	public CRONIOBusDesktopPasswordTextBox() {
		super();
		addStyleName(CRONIOBusDesktopIStyleConstants.PASSWORD_TEXTBOX);
		getTextBoxZone().addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		getCheckBoxZone().addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
	}

	@Override
	protected AEGWTCheckBox createCheckBox(String labelText) {
		return new CRONIOBusDesktopPasswordCheckBox(labelText);
	}
	
	public AEGWTTextBox getTextBox() {
		return getPasswordTextBox();
	}	
}
