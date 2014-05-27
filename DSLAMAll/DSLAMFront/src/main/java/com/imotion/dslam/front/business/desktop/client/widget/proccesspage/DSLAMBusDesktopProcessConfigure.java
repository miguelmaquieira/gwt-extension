package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;


public class DSLAMBusDesktopProcessConfigure extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessConfigure";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private DSLAMBusDesktopProcessConfigureOptions		optionsZone;
	private DSLAMBusDesktopProcessConfigureNodes		nodeZone;

	public DSLAMBusDesktopProcessConfigure() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE);

		optionsZone = new DSLAMBusDesktopProcessConfigureOptions();
		root.add(optionsZone);
		optionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_ZONE);
		optionsZone.addStyleName(AEGWTIBoostrapConstants.ROW);

		nodeZone = new DSLAMBusDesktopProcessConfigureNodes();
		root.add(nodeZone);
		nodeZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_ZONE);
		nodeZone.addStyleName(AEGWTIBoostrapConstants.ROW);
	}
	
	public void postDisplay() {
		super.postDisplay();
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