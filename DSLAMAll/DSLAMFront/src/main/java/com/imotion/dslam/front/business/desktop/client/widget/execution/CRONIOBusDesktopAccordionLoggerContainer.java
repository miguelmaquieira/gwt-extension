package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;

public class CRONIOBusDesktopAccordionLoggerContainer extends CRONIOBusDesktopProjectExecutionLogger {

	public static final String NAME = "CRONIOBusDesktopAccordionLoggerContainer";
	
	AEGWTBootstrapAccordionPanelContainer accordionPanelContainer;
	
	public CRONIOBusDesktopAccordionLoggerContainer(String loggerId) {
		super(loggerId);
		accordionPanelContainer = new AEGWTBootstrapAccordionPanelContainer();
		getLoggerContainer().add(accordionPanelContainer);
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
		accordionPanelContainer.postDisplay();
	}

	/**
	 * CRONIOBusDesktopProjectExecutionLogger
	 */

	@Override
	protected void addLogItem(AEMFTMetadataElementComposite logData) {
		AEGWTBootstrapAccordionPanel accordionPanel = new AEGWTBootstrapAccordionPanel("header");
		accordionPanelContainer.addWiget(accordionPanel);
		FlowPanel panelContent = new FlowPanel();
		accordionPanel.addWiget(panelContent);
		panelContent.addStyleName(AEGWTIBoostrapConstants.PANEL_BODY);
		panelContent.getElement().setInnerText("content");	
	}

}
