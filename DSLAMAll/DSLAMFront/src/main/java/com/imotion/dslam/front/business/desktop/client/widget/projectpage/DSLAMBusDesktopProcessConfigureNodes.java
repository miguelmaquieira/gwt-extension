package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class DSLAMBusDesktopProcessConfigureNodes extends AEGWTCompositePanel {
	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 											root;
	private DSLAMBusDesktopProcessConfigureNodesProperties		propertiesNodeZone;
	private DSLAMBusDesktopProcessConfigureNodesSelection		selectionNodeZone;

	public DSLAMBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initWidget(root);

		selectionNodeZone = new DSLAMBusDesktopProcessConfigureNodesSelection();
		root.add(selectionNodeZone);
		selectionNodeZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_SELECTION_ZONE);
		selectionNodeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		
		propertiesNodeZone = new DSLAMBusDesktopProcessConfigureNodesProperties();
		root.add(propertiesNodeZone);
		propertiesNodeZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_PROPERTIES_ZONE);
		propertiesNodeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
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
