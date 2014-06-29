package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.imotion.dslam.bom.CRONIOBOINode;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPanelWithHeading;

public class CRONIOBusDesktopNodeInfoPanel extends AEGWTBootstrapPanelWithHeading {

	public static final String NAME = "CRONIOBusDesktopNodeInfoPanel";
	
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
		String  nodeName 		= getElementController().getElementAsString(CRONIOBOINode.NODE_NAME, data);
		String  nodeIp	 		= getElementController().getElementAsString(CRONIOBOINode.NODE_IP, data);
		int		nodeMachineType = getElementController().getElementAsInt(CRONIOBOINode.NODE_MACHINE_TYPE, data);
		
		String  nodeMachineTypeStr = "";	
		
		if (nodeMachineType == CRONIOBOINode.NODE_TYPE_ISAM_FD) {
			nodeMachineTypeStr = CRONIOBOINode.NODE_MACHINE_TYPE_ISAM_FD;
		} else if (nodeMachineType == CRONIOBOINode.NODE_TYPE_ISAM_XD) {
			nodeMachineTypeStr = CRONIOBOINode.NODE_MACHINE_TYPE_ISAM_XD;
				}
		
		String NodeNameLabelText 	= "Nombre:" + nodeName;
		String NodeTypeLabelText 	= "Tipo: " + nodeMachineTypeStr;
		String NodeIpLabelText 		= "Ip: " + nodeIp;
		
		CRONIOBusDesktopNodeInfo nodeInfo = new CRONIOBusDesktopNodeInfo(NodeNameLabelText, NodeTypeLabelText, NodeIpLabelText);
		setContent(nodeInfo);
	}
}