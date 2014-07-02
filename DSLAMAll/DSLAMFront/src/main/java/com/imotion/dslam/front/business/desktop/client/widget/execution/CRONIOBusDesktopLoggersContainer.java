package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLoggersContainer extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopLoggersContainer";
	
	private TabLayoutPanel tabsContainer;
	
	public CRONIOBusDesktopLoggersContainer() {
		tabsContainer = new TabLayoutPanel(3, Unit.PX);
		initWidget(tabsContainer);
		tabsContainer.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS);
		
	}
	
	public void addLogger(String nodeName, String loggerId) {
		CRONIOBusDesktopProjectNodeLog nodeLog = new CRONIOBusDesktopProjectNodeLog(loggerId);
		tabsContainer.add(nodeLog, nodeName);
		nodeLog.postDisplay();
	}
	
	public void clear() {
		tabsContainer.clear();
	}

	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}

}
