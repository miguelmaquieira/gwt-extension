package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLoggerExecutionPanelInfo extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopLoggerExecutionPanelInfo";
	
	public CRONIOBusDesktopLoggerExecutionPanelInfo(String environmentName, String userName, String isSynchronous, String initExecutionTime, String finishExecutionTime) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		String[] environmentNameSplit 	= environmentName.split(" ");
		String[] userNameSplit 	= userName.split(" ");
		String[] initExecutionTimeSplit 	= initExecutionTime.split(" ");
		String[] finishExecutionTimeSplit 	= finishExecutionTime.split(" ");
		
		FlowPanel environmentNameInfoZone 	=  new FlowPanel();
		environmentNameInfoZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE);
		environmentNameInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(environmentNameInfoZone);
		Label environmentNameLabel 	= new Label(environmentNameSplit[0]);
		environmentNameLabel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL);
		environmentNameLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		environmentNameInfoZone.add(environmentNameLabel);
		Label environmentNameContent 	= new Label(environmentNameSplit[1]);
		environmentNameContent.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_CONTENT_LABEL);
		environmentNameInfoZone.add(environmentNameContent);
				
		FlowPanel userNameZone 	=  new FlowPanel();
		userNameZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE);
		userNameZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(userNameZone);
		Label userNameLabel 	= new Label(userNameSplit[0]);
		userNameLabel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL);
		userNameLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		userNameZone.add(userNameLabel);
		Label userNameContent 	= new Label(userNameSplit[1]);
		userNameContent.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_CONTENT_LABEL);
		userNameZone.add(userNameContent);
		
		FlowPanel initExecutionTimeZone 	=  new FlowPanel();
		initExecutionTimeZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE);
		initExecutionTimeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(initExecutionTimeZone);
		Label initExecutionTimeLabel 		= new Label(initExecutionTimeSplit[0]);
		initExecutionTimeLabel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL);
		initExecutionTimeLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		initExecutionTimeZone.add(initExecutionTimeLabel);
		Label initExecutionTimeContent 	= new Label(initExecutionTimeSplit[1]);
		initExecutionTimeContent.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_CONTENT_LABEL);
		initExecutionTimeZone.add(initExecutionTimeContent);
		
		FlowPanel finishExecutionTimeZone 	=  new FlowPanel();
		finishExecutionTimeZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE);
		finishExecutionTimeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(finishExecutionTimeZone);
		Label finishExecutionTimeLabel 		= new Label(finishExecutionTimeSplit[0]);
		finishExecutionTimeLabel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL);
		finishExecutionTimeLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		finishExecutionTimeZone.add(finishExecutionTimeLabel);
		
		if (finishExecutionTimeSplit.length == 2) {
			Label finishExecutionTimeContent 	= new Label(finishExecutionTimeSplit[1]);
			finishExecutionTimeContent.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_CONTENT_LABEL);
			finishExecutionTimeZone.add(initExecutionTimeContent);
		}
		
		FlowPanel isSynchronousZone 	=  new FlowPanel();
		isSynchronousZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_LABEL_ZONE);
		isSynchronousZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.add(isSynchronousZone);
		Label isSynchronousLabel 		= new Label(isSynchronous);
		isSynchronousLabel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_EXECUTION_INFO_ONLY_CONTENT_LABEL);
		isSynchronousLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		isSynchronousZone.add(isSynchronousLabel);
		
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