package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;

public class CRONIOBusDesktopLoggerSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "CRONIOBusDesktopLoggerSectionsDeckPanel";

	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private DeckPanel 										rootDeckPanel;
	private CRONIOBusDesktopAccordionLoggerContainer 		loggerContainer;
	private	 CRONIOBusDesktopLoggerNodes             		logger;
	
	public CRONIOBusDesktopLoggerSectionsDeckPanel() {

		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_DECKPANEL);
		rootDeckPanel.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);

		//loggerContainer 	= new CRONIOBusDesktopAccordionLoggerContainer();
		logger 				= new CRONIOBusDesktopLoggerNodes();
		
		rootDeckPanel.add(logger);
		//rootDeckPanel.add(loggerContainer);
	}

	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {
		String dateRegEx 	= "[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}\\s([0-9]{2})\\:([0-9]{2})\\:([0-9]{2}).*";
		if (sectionId.matches(dateRegEx)) {
			rootDeckPanel.showWidget(0);
			logger.setData(sectionData);
			this.setVisibility(Visibility.VISIBLE);
			AEGWTJQueryPerfectScrollBar.updateScroll(getName());
			AEGWTJQueryPerfectScrollBar.top(getName());
		} else {
			AEMFTMetadataElementSingle projectIdData = (AEMFTMetadataElementSingle) sectionData.getElement(CRONIOBOIProject.PROJECT_ID);
			String projectId = projectIdData.getValueAsString();
			if (loggerContainer == null) {
				loggerContainer 	= new CRONIOBusDesktopAccordionLoggerContainer(projectId);
				rootDeckPanel.add(loggerContainer);
				loggerContainer.postDisplay();
			}
			rootDeckPanel.showWidget(1);
			loggerContainer.setData(sectionData);
			this.setVisibility(Visibility.VISIBLE);
			AEGWTJQueryPerfectScrollBar.updateScroll(getName());
			AEGWTJQueryPerfectScrollBar.top(getName());
		}	
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	public void postDisplay() {
		super.postDisplay();
		logger.postDisplay();
		//loggerContainer.postDisplay();
		//setHeightToDecrease(78);
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getName(), this, getCurrentHeight(), true);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		//logger.setData(data);
		loggerContainer.setData(data);
	}

	public AEMFTMetadataElementComposite getData() {
		return null;
	}
}