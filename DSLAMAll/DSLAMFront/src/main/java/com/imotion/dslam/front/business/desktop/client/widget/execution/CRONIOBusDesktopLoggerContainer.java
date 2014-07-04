package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.user.client.ui.FlowPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapAccordionPanelContainer;

public class CRONIOBusDesktopLoggerContainer extends AEGWTCompositePanel {

	public static final String NAME = "CRONIOBusDesktopLoggerContainer";
	
	AEGWTBootstrapAccordionPanelContainer accordionPanelContainer;
	
	public CRONIOBusDesktopLoggerContainer() {
		accordionPanelContainer = new AEGWTBootstrapAccordionPanelContainer();
		initWidget(accordionPanelContainer);
	}
	
	public void addLineLog(String header, String href, String text) {
		AEGWTBootstrapAccordionPanel accordionPanel = new AEGWTBootstrapAccordionPanel(header,href);
		accordionPanelContainer.addWiget(accordionPanel);
		FlowPanel panelContent = new FlowPanel();
		accordionPanel.addWiget(panelContent);
		panelContent.addStyleName(AEGWTIBoostrapConstants.PANEL_BODY);
		panelContent.getElement().setInnerText(text);
		
	}

	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}

}
