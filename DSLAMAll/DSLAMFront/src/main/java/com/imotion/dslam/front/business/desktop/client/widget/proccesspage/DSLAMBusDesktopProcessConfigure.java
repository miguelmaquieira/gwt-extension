package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;


public class DSLAMBusDesktopProcessConfigure extends AEGWTCompositePanel  {

	public static final String NAME = "DSLAMBusDesktopProcessConfigure";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private DSLAMBusDesktopProcessConfigureOptions		optionsZone;
	private DSLAMBusDesktopProcessConfigureNodes		nodeZone;

	public DSLAMBusDesktopProcessConfigure() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE);

		optionsZone = new DSLAMBusDesktopProcessConfigureOptions();
		root.add(optionsZone);
		optionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_ZONE);

		nodeZone = new DSLAMBusDesktopProcessConfigureNodes();
		root.add(nodeZone);
		nodeZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_ZONE);
		nodeZone.addStyleName(AEGWTIBoostrapConstants.ROW);
	}
	
	public void reset() {
		optionsZone.reset();
		this.setVisibility(Visibility.HIDDEN);
	}
	
	/**
	 * AEGWTCompositePanel
	 */
	
	public void postDisplay() {
		super.postDisplay();
		optionsZone.postDisplay();
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
	
	public AEMFTMetadataElementComposite getData() {
		return optionsZone.getData();
	}
}