package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopAccordionLoggerContainer extends CRONIOBusDesktopProjectExecutionLoggerBase implements AEGWTHasLogicalEventHandlers  {

	public		static		final 	String 	NAME 	= "CRONIOBusDesktopAccordionLoggerContainer";
	private		static		final	int 	HEIGHT 	= 50;
	
	private AEGWTBootstrapAccordionPanelContainer accordionPanelContainer;
	
	public CRONIOBusDesktopAccordionLoggerContainer(String loggerId) {
		super(loggerId);
		accordionPanelContainer = new AEGWTBootstrapAccordionPanelContainer();
		getLoggerContainer().add(accordionPanelContainer);
		accordionPanelContainer.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_CONTAINER);
	}
	
	public void beforeExitSection() {
		super.beforeExitSection();
	}

	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	/**
	 * CRONIOBusDesktopProjectExecutionLogger
	 */

	@Override
	protected void addLogItem(AEMFTMetadataElementComposite logData) {
		
		AEMFTMetadataElementSingle 	date 			= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOIClientLoggerConstants.TIMESTAMP	, logData);
		String 						dateStr 		= date.toString().replace("TIMESTAMP: ", "");
		String 						nodeIp 			= getElementController().getElementAsString(CRONIOIClientLoggerConstants.NODE_IP						, logData);
		String 						nodeName 		= getElementController().getElementAsString(CRONIOIClientLoggerConstants.NODE_NAME						, logData);
		String 						nodeRequest 	= getElementController().getElementAsString(CRONIOIClientLoggerConstants.REQUEST_DATA					, logData);
		String 						nodeResponse 	= getElementController().getElementAsString(CRONIOIClientLoggerConstants.RESPONSE_DATA					, logData);
		String 						nodePrompt 		= getElementController().getElementAsString(CRONIOIClientLoggerConstants.PROMPT_DATA					, logData);
		
		String header = dateStr + " " + nodeName + " " + nodeIp;
		
		AEGWTBootstrapAccordionPanel accordionPanel = new AEGWTBootstrapAccordionPanel(header);
		accordionPanelContainer.addWiget(accordionPanel);
		accordionPanel.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS);
		
		Label subElementHeader = new Label(nodeRequest);
		subElementHeader.addStyleName("subElementHeader");
		subElementHeader.setTitle(nodeRequest);
		accordionPanel.addHeaderWidgetToAnchor(subElementHeader);
		
		FlowPanel panelContent = new FlowPanel();
		accordionPanel.addContentWidget(panelContent);
		panelContent.addStyleName(AEGWTIBoostrapConstants.PANEL_BODY);
		CRONIOBusDesktopAccordionLoggerItemContent content = new CRONIOBusDesktopAccordionLoggerItemContent(nodeResponse, nodePrompt, nodeRequest);
		panelContent.add(content);
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		String			srcWidgetId		= evt.getSourceWidgetId();
		LOGICAL_TYPE	type			= evt.getEventType();
		
		if (AEGWTBootstrapAccordionPanel.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.CHANGE_EVENT.equals(type)) {
				evt.stopPropagation();
				int count = accordionPanelContainer.getWidgetCount();
				AEGWTBootstrapAccordionPanel panel = null; 
				for (int i = 0; i < count; i++) {
					panel = (AEGWTBootstrapAccordionPanel) accordionPanelContainer.getWidget(i);
					if (!panel.getIdFinal().equals(srcWidgetId)) {
						panel.setIconUp();
					}
				}
			}	
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.CHANGE_EVENT.equals(type);
	}

	@Override
	protected int getItemHeight() {
		return HEIGHT;
	}
}
