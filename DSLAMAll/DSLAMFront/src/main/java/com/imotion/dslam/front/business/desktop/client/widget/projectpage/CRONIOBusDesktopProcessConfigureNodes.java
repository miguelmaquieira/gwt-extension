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
	private CRONIOBusDesktopProcessConfigureNodesList	NodeListZone;
	private CRONIOBusDesktopProcessConfigureNodesInfo	NodeInfoZone;
	

	public CRONIOBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initWidget(root);

		NodeListZone = new CRONIOBusDesktopProcessConfigureNodesList();
		root.add(NodeListZone);
		NodeListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LIST_ZONE);
		NodeListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		NodeInfoZone = new CRONIOBusDesktopProcessConfigureNodesInfo();
		root.add(NodeInfoZone);
		NodeInfoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_INFO_ZONE);
		NodeInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
}
