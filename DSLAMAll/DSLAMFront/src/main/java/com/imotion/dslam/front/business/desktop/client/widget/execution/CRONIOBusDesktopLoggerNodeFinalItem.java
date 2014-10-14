package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopLoggerNodeFinalItem extends AEGWTBootstrapTreeMenuFinalItem {
	
	public static final String NAME  = "CRONIOBusDesktopLoggerNodeFinalItem";

	public CRONIOBusDesktopLoggerNodeFinalItem(String nodeName, AEGWTICompositePanel parentWidget, boolean machineExist) {
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
