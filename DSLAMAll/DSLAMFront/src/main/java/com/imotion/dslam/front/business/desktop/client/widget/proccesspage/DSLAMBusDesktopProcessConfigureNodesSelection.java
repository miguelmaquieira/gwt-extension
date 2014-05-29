package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureNodesSelection extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureNodesSelection";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 	root;
	private FlowPanel 	headerZone;
	private FlowPanel 	propertiesZone;


	public DSLAMBusDesktopProcessConfigureNodesSelection() {
		FlowPanel root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODES_SELECTION_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.nodes());
		headerZone.add(headerLabel);

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
