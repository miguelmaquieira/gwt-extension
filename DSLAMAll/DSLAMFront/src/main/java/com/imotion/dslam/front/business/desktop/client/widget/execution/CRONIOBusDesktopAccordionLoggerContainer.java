package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.imotion.dslam.bom.CRONIOBOILogDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapPager;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopAccordionLoggerContainer extends CRONIOBusDesktopProjectExecutionLoggerBase implements AEGWTHasLogicalEventHandlers  {

	public		static		final 	String 	NAME 	= "CRONIOBusDesktopAccordionLoggerContainer";
	private		static		final	int 	HEIGHT 	= 50;
	
	private AEGWTBootstrapAccordionPanelContainer 	accordionPanelContainer;
	private AEGWTBootstrapPager						pager;
	
	private String dateStr; 	
	private String 	nodeIp; 			
	private String 	nodeName;
	private String 	nodeRequest;
	private String 	nodeResponse;
	private String 	nodePrompt; 	
	private String header;
	
	public CRONIOBusDesktopAccordionLoggerContainer(String loggerId) {
		super(loggerId);
		accordionPanelContainer = new AEGWTBootstrapAccordionPanelContainer();
		getLoggerContainer().add(accordionPanelContainer);
		accordionPanelContainer.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_TABS_CONTAINER);
		
		pager = new AEGWTBootstrapPager("#","#");
		getLoggerContainer().add(pager);
	}
	
	public void beforeExitSection() {
		super.beforeExitSection();
	}
	
	public void setFilterVisible (boolean visible) {
		super.setFilterVisible(visible);
	}
	
	public void setPagerVisible (boolean visible) {
		pager.setVisible(visible);
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

	@Override
	public void setData(AEMFTMetadataElementComposite executionLogsData) {
		if (executionLogsData != null) {
			List<AEMFTMetadataElement> logList = executionLogsData.getSortedElementList();
			for (AEMFTMetadataElement log : logList) {
				AEMFTMetadataElementComposite logData = (AEMFTMetadataElementComposite) log;
				addLogItem(logData, false);
			}
		}
	}
	/**
	 * CRONIOBusDesktopProjectExecutionLogger
	 */

	@Override
	protected void addLogItem(AEMFTMetadataElementComposite logData, boolean isExecution) {
		if (isExecution) {
			AEMFTMetadataElementSingle 	date 			= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOIClientLoggerConstants.TIMESTAMP	, logData);
			dateStr 		= date.toString().replace("TIMESTAMP: ", "");
			nodeIp 			= getElementController().getElementAsString(CRONIOIClientLoggerConstants.NODE_IP						, logData);
			nodeName 		= getElementController().getElementAsString(CRONIOIClientLoggerConstants.NODE_NAME						, logData);
			nodeRequest 	= getElementController().getElementAsString(CRONIOIClientLoggerConstants.REQUEST_DATA					, logData);
			nodeResponse 	= getElementController().getElementAsString(CRONIOIClientLoggerConstants.RESPONSE_DATA					, logData);
			nodePrompt 		= getElementController().getElementAsString(CRONIOIClientLoggerConstants.PROMPT_DATA					, logData);
			header 			= dateStr + " " + nodeName + " " + nodeIp;
		} else {
			AEMFTMetadataElementSingle 	date 			= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOBOILogDataConstants.TIMESTAMP	, logData);
			dateStr 		= date.toString().replace("timestamp: ", "");
			AEMFTMetadataElementSingle 	messageData 	= (AEMFTMetadataElementSingle) getElementController().getElement(CRONIOBOILogDataConstants.MESSAGE	, logData);
			String message = messageData.toString();
			String[] messageSplit = message.split("\\,");
			nodeIp 			= messageSplit[1];
			nodeName 		= messageSplit[2];
			nodeRequest 	= messageSplit[3];
			nodeResponse 	= messageSplit[4];
			nodePrompt 		= messageSplit[5];
			header 			= dateStr + " " + nodeName + " " + nodeIp;
		}
		
		
		
		AEGWTBootstrapAccordionPanel accordionPanel = new AEGWTBootstrapAccordionPanel(header,true);
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
