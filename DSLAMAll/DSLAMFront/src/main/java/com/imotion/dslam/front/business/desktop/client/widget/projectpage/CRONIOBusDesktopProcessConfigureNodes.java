package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopProcessConfigureNodes extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodes";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessNodeList				nodeListZone;
	private CRONIOBusDesktopProcessConfigureNodesInfo	nodeInfoZone;
	

	public CRONIOBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initWidget(root);

		nodeListZone = new CRONIOBusDesktopProcessNodeList();
		root.add(nodeListZone);
		nodeListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LIST_ZONE);
		nodeListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		
		nodeInfoZone = new CRONIOBusDesktopProcessConfigureNodesInfo();
		root.add(nodeInfoZone);
		nodeInfoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_INFO_ZONE);
		nodeInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		//nodeListZone.builder();
	}
}
