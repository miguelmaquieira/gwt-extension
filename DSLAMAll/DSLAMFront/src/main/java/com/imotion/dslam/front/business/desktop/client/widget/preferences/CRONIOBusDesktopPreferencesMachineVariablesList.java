package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopVariablesList;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class CRONIOBusDesktopPreferencesMachineVariablesList extends DSLAMBusDesktopVariablesList {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariablesList";

	public CRONIOBusDesktopPreferencesMachineVariablesList(AEGWTButton deleteButton) {
		super(deleteButton);
	}

	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}

}
