package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopProcessNodeFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopProcessNodeFinalItem";

	public CRONIOBusDesktopProcessNodeFinalItem(String nodeName, AEGWTICompositePanel parentWidget, boolean machineExist) {
		super(null, nodeName, nodeName, parentWidget, machineExist);
		
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * AEGWTBootstrapTreeMenuFinalItem
	 */
	
}
