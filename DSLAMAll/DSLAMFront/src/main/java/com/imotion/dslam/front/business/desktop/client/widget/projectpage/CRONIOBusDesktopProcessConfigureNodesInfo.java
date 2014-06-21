package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPanelWithHeading;

public class CRONIOBusDesktopProcessConfigureNodesInfo extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopProcessConfigureNodesInfo";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 										root;
	private FlowPanel 										nodeVariableListZone;
	private AEGWTBootstrapPanelWithHeading					nodeInfoPanel;
	private CRONIOBusDesktopProcessNodesInfoVariablesList 	nodeVariableList;
	
	public CRONIOBusDesktopProcessConfigureNodesInfo() {
		root = new FlowPanel();
		initWidget(root);

		nodeInfoPanel 		= new AEGWTBootstrapPanelWithHeading(TEXTS.node_information(),"");
		root.add(nodeInfoPanel);
		
		nodeVariableListZone = new FlowPanel();
		nodeVariableListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_NODE_VARIABLES_LIST);
		root.add(nodeVariableListZone);
		
		CRONIOBusDesktopHeaderListActions header = new CRONIOBusDesktopHeaderListActions(TEXTS.node_variable_list());
		nodeVariableListZone.add(header);
		
		header.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
		
		nodeVariableList 	= new CRONIOBusDesktopProcessNodesInfoVariablesList(header.getDeleteButton());
		nodeVariableListZone.add(nodeVariableList);
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
