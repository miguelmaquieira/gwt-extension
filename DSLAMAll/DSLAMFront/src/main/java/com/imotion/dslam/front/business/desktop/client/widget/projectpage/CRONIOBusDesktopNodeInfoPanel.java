package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusI18NProcessTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPanelWithHeading;

public class CRONIOBusDesktopNodeInfoPanel extends AEGWTBootstrapPanelWithHeading {

	public static final String NAME = "CRONIOBusDesktopNodeInfoPanel";
	
	private static CRONIOBusI18NProcessTexts TEXTS = GWT.create(CRONIOBusI18NProcessTexts.class);
	
	public CRONIOBusDesktopNodeInfoPanel(String title) {
		super(title);
	}
	
	public void reset() {
		getBody().clear();
	}

	/****************************************************************************
	 *                        AEGWTICompositePanel
	 ****************************************************************************/
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		resetContent();
		String  	nodeName 		= getElementController().getElementAsString(CRONIOBOINode.NODE_NAME	, data);
		String  	nodeIp	 		= getElementController().getElementAsString(CRONIOBOINode.NODE_IP	, data);
		String		nodeMachineType = getElementController().getElementAsString(CRONIOBOINode.NODE_TYPE	, data);
		boolean 	noWarning 		= getElementController().getElementAsBoolean(CRONIOBOINode.MACHINE_EXISTS, data);
		
		String nodeNameLabelText 	= TEXTS.node_name() + nodeName;
		String nodeTypeLabelText 	= TEXTS.node_type() + nodeMachineType;
		String nodeIpLabelText 		= TEXTS.node_ip() 	+ nodeIp;
		String nodeWarningLabelText = "";
		if (!noWarning) {
			nodeWarningLabelText = TEXTS.no_machine_exist() + " " + nodeMachineType;
		}
		
		CRONIOBusDesktopNodeInfo nodeInfo = new CRONIOBusDesktopNodeInfo(nodeNameLabelText, nodeTypeLabelText, nodeIpLabelText, nodeWarningLabelText);
		setContent(nodeInfo);
	}
}