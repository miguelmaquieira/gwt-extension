package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;

public class CRONIOBusDesktopAccordionLoggerItemContent extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopAccordionLoggerItemContent";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	AEGWTBootstrapAccordionPanelContainer accordionPanelContainer;
	
	public CRONIOBusDesktopAccordionLoggerItemContent(String response, String prompt, String request) {
		FlowPanel contentZone = new FlowPanel();
		initWidget(contentZone);
		contentZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_CONTENT_ZONE);
		
		Label requestLabel = new Label(TEXTS.request_label());
		contentZone.add(requestLabel);
		FlowPanel requestZone = new FlowPanel();
		contentZone.add(requestZone);
		requestZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_REQUEST_ZONE);
		HTMLPanel requestContent = new HTMLPanel(request);
		requestZone.add(requestContent);
		
		String responseAsHtml = response.replace("\n", "<br/>");
		Label responseLabel = new Label(TEXTS.response_label());
		contentZone.add(responseLabel);
		FlowPanel responseZone = new FlowPanel();
		contentZone.add(responseZone);
		responseZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_RESPONSE_ZONE);
		HTMLPanel responseContent = new HTMLPanel(responseAsHtml);
		responseZone.add(responseContent);
		
		Label promptLabel = new Label(TEXTS.prompt_label());
		contentZone.add(promptLabel);
		FlowPanel promptZone = new FlowPanel();
		contentZone.add(promptZone);
		promptZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_PROMPT_ZONE);
		HTMLPanel promptContent = new HTMLPanel(prompt);
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
