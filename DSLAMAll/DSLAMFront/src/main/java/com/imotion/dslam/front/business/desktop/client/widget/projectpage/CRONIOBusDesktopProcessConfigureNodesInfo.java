package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPanelWithHeading;

public class CRONIOBusDesktopProcessConfigureNodesInfo extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodesInfo";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 										root;
	private AEGWTBootstrapPanelWithHeading					nodeInfoPanel;
	private CRONIOBusDesktopProcessNodesInfoVariablesList 	nodeVariableList;
	
	public CRONIOBusDesktopProcessConfigureNodesInfo() {
		root = new FlowPanel();
		initWidget(root);

		nodeInfoPanel 		= new AEGWTBootstrapPanelWithHeading(TEXTS.node_information(),"");
		root.add(nodeInfoPanel);
		nodeVariableList 	= new CRONIOBusDesktopProcessNodesInfoVariablesList(null);
		root.add(nodeVariableList);
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
