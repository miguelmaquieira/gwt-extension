package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;

public class CRONIOBusDesktopAccordionLoggerItemContent extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopAccordionLoggerItemContent";
	
	AEGWTBootstrapAccordionPanelContainer accordionPanelContainer;
	
	public CRONIOBusDesktopAccordionLoggerItemContent(String response, String prompt) {
		FlowPanel contentZone = new FlowPanel();
		initWidget(contentZone);
		contentZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_CONTENT_ZONE);
		
		Label responseLabel = new Label(CRONIOIClientLoggerConstants.RESPONSE_LABEL);
		contentZone.add(responseLabel);
		FlowPanel responseZone = new FlowPanel();
		contentZone.add(responseZone);
		responseZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_RESPONSE_ZONE);
		Label responseContent = new Label(response);
		responseZone.add(responseContent);
		
		Label promptLabel = new Label(CRONIOIClientLoggerConstants.PROMPT_LABEL);
		contentZone.add(promptLabel);
		FlowPanel promptZone = new FlowPanel();
		contentZone.add(promptZone);
		promptZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_PROMPT_ZONE);
		Label promptContent = new Label(prompt);
		promptZone.add(promptContent);
	}

	/**
	 * AEGWTCompositePanel
	 */
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
		
	}
}
