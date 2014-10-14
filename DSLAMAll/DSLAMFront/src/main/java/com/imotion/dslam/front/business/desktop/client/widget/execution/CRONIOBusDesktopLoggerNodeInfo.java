package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLoggerNodeInfo extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopLoggerNodeInfo";
	
	public CRONIOBusDesktopLoggerNodeInfo(String name, String type, String ip, String warning) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		String[] nameSplit 	= name.split(" ");
		String[] typeSplit 	= type.split(" ");
		String[] ipSplit 	= ip.split(" ");
		
		FlowPanel nameInfoZone 	=  new FlowPanel();
		nameInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL_ZONE);
		nameInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(nameInfoZone);
		Label nodeNameLabel 	= new Label(nameSplit[0]);
		nodeNameLabel.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL);
		nodeNameLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		nameInfoZone.add(nodeNameLabel);
		Label nodeNameContent 	= new Label(nameSplit[1]);
		nodeNameContent.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_CONTENT_LABEL);
		nameInfoZone.add(nodeNameContent);
		
		
		FlowPanel typeInfoZone 	=  new FlowPanel();
		typeInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL_ZONE);
		typeInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(typeInfoZone);
		Label nodeTypeLabel 	= new Label(typeSplit[0]);
		nodeTypeLabel.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL);
		nodeTypeLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		typeInfoZone.add(nodeTypeLabel);
		Label nodeTypeContent 	= new Label(typeSplit[1]);
		nodeTypeContent.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_CONTENT_LABEL);
		typeInfoZone.add(nodeTypeContent);
		
		FlowPanel IpInfoZone 	=  new FlowPanel();
		IpInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL_ZONE);
		IpInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(IpInfoZone);
		Label nodeIpLabel 		= new Label(ipSplit[0]);
		nodeIpLabel.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_LABEL);
		nodeIpLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		IpInfoZone.add(nodeIpLabel);
		Label nodeIpContent 	= new Label(ipSplit[1]);
		nodeIpContent.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECT_PROCESS_NODE_INFO_CONTENT_LABEL);
		IpInfoZone.add(nodeIpContent);
		
		if (!AEMFTCommonUtilsBase.isEmptyString(warning)) {
			Label warningLabel 	= new Label(warning);
			warningLabel.addStyleName(AEGWTIBoostrapConstants.LABEL);
			warningLabel.addStyleName(AEGWTIBoostrapConstants.LABEL_DANGER);
			root.add(warningLabel);
		}
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