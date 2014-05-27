package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

public class DSLAMBusDesktopProcessConfigureNodes extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {
	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 											root;
	private DSLAMBusDesktopProcessConfigureNodesProperties		propertiesNodeZone;
	private DSLAMBusDesktopProcessConfigureNodesSelection		selectionNodeZone;

	public DSLAMBusDesktopProcessConfigureNodes() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES);

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
