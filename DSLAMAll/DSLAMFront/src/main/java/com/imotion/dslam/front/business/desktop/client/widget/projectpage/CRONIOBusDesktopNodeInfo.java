package com.imotion.dslam.front.business.desktop.client.widget.projectpage;


import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;


public class CRONIOBusDesktopNodeInfo extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopNodeInfo";
	
	public CRONIOBusDesktopNodeInfo(String name, String type, String ip) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		Label nodeName 	= new Label(name);
		root.add(nodeName);
		Label nodeType 	= new Label(type);
		root.add(nodeType);
		Label nodeIp 	= new Label(ip);
		root.add(nodeIp);
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
		
	}
}