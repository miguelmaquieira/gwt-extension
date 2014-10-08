package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class CRONIOBusDesktopProcessNodesInfoVariablesList extends CRONIOBusDesktopVariablesList {

	public static final String NAME = "CRONIOBusDesktopProcessNodesInfoVariablesList";

	public CRONIOBusDesktopProcessNodesInfoVariablesList(AEGWTButton deleteButton) {
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
