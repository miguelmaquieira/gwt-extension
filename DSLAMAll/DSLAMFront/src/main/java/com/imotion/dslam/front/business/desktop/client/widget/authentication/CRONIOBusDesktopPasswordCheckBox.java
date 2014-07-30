package com.imotion.dslam.front.business.desktop.client.widget.authentication;

import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.common.AEGWTCheckBox;

public class CRONIOBusDesktopPasswordCheckBox extends AEGWTCheckBox {

	public CRONIOBusDesktopPasswordCheckBox(String labelText) {
		super(labelText);
//		addStyleName(BusinessDesktopIStylesConstants.PASSWORD_CHECKBOX);
	}

	@Override
	protected String getCheckBoxZoneStyleName() {
		return AEGWTIBoostrapConstants.COL_XS_4;
	}

	@Override
	protected String getLabelZoneStyleName() {
		return AEGWTIBoostrapConstants.COL_XS_7;
	}
}
