package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.view.log.CRONIOBusI18NLogTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPanelWithHeading;

public class CRONIOBusDesktopLoggerExecutionInfoPanel extends AEGWTBootstrapPanelWithHeading {

	public static final String NAME = "CRONIOBusDesktopLoggerExecutionInfoPanel";
	
	private static CRONIOBusI18NLogTexts TEXTS = GWT.create(CRONIOBusI18NLogTexts.class);
	
	public CRONIOBusDesktopLoggerExecutionInfoPanel(String title) {
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
		
		boolean  					isSynchronous 			= getElementController().getElementAsBoolean(CRONIOBOIExecution.IS_SYNCHRONOUS								, data);
		String IsSynchronousLabelText 		= "";
		if (isSynchronous) {
			IsSynchronousLabelText 			= TEXTS.synchronous();
		} else {
			IsSynchronousLabelText			= TEXTS.asynchronous();
		}
		
		String	environmentName 		= getElementController().getElementAsString(CRONIOBOIExecution.ENVIRONMENT_NAME								, data);
		String 	environmentLabelText 	= TEXTS.environment() + environmentName;
		
		AEMFTMetadataElementSingle  initExecutionTime	 		= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOBOIExecution.CREATION_TIME			, data);
		String 						initExecutionTimeStr 		= initExecutionTime.getValueAsSerializable().toString();
		String[] 					initExecutionTimeStrSplit 	= initExecutionTimeStr.split("\\.");
		String[] 					initExecutionTimeStrSplit1 	= initExecutionTimeStrSplit[0].split(" ");
		String[] 					initExecutionTimeStrSplit2 	= initExecutionTimeStrSplit1[0].split("\\-");
		String 						initExecutionTimeStrFormat	= initExecutionTimeStrSplit2[2] + "-" + initExecutionTimeStrSplit2[1] + "-" + initExecutionTimeStrSplit2[0] + " " + initExecutionTimeStrSplit1[1];
		String						initExecutionTimeLabelText 	= TEXTS.init_time() + initExecutionTimeStrFormat;
		
		AEMFTMetadataElementSingle  finishExecutionTime	 			= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOBOIExecution.FINISH_EXECUTION_TIME	, data);
		String 						finishExecutionTimeLabelText 	= TEXTS.finish_time();
		if (finishExecutionTime != null && finishExecutionTime.getValueAsSerializable() != null) {
			String 		finishExecutionTimeStr 			= finishExecutionTime.getValueAsSerializable().toString();
			String[] 	finishExecutionTimeStrSplit 	= finishExecutionTimeStr.split("\\.");
			String[] 	finishExecutionTimeStrSplit1 	= finishExecutionTimeStrSplit[0].split(" ");
			String[] 	finishExecutionTimeStrSplit2 	= finishExecutionTimeStrSplit1[0].split("\\-");
			String 		finishExecutionTimeStrFormat	= finishExecutionTimeStrSplit2[2] + "-" + finishExecutionTimeStrSplit2[1] + "-" + finishExecutionTimeStrSplit2[0] + " " + finishExecutionTimeStrSplit1[1];
			finishExecutionTimeLabelText = finishExecutionTimeLabelText + finishExecutionTimeStrFormat;
		} 
		
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIOBOIExecution.USER);
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIUser.USERNAME);
		String userIdKey 			= sbKey.toString();
		String userName 			= getElementController().getElementAsString(userIdKey, data);
		String userNameLabelText 	= TEXTS.user() + userName;
		
		CRONIOBusDesktopLoggerExecutionPanelInfo executionInfo = new CRONIOBusDesktopLoggerExecutionPanelInfo(environmentLabelText, userNameLabelText, IsSynchronousLabelText, initExecutionTimeLabelText, finishExecutionTimeLabelText);
		setContent(executionInfo);
	}
}