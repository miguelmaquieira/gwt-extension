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
		
		FlowPanel leftZone 	= new FlowPanel();
		root.add(leftZone);
		leftZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LEFT_ZONE);
		leftZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		FlowPanel rightZone = new FlowPanel();
		root.add(rightZone);
		rightZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_RIGHT_ZONE);
		rightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		nodeListZone = new CRONIOBusDesktopProcessNodeList();
		leftZone.add(nodeListZone);
		nodeListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_LIST_ZONE);
		nodeListZone.builder();
		
		nodeInfoZone = new CRONIOBusDesktopProcessConfigureNodesInfo();
		rightZone.add(nodeInfoZone);
		nodeInfoZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_INFO_ZONE);
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
