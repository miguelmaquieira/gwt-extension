package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;


public class CRONIOBusDesktopLoggerSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "CRONIOBusDesktopLoggerSectionsDeckPanel";

	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private DeckPanel 										rootDeckPanel;
	private CRONIOBusDesktopAccordionLoggerContainer 		loggerContainer;
	private	 CRONIOBusDesktopLoggerNodes             logger;
	
	

	public CRONIOBusDesktopLoggerSectionsDeckPanel() {

		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTION_LOGGER_DECKPANEL);
		rootDeckPanel.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);

		//loggerContainer 	= new CRONIOBusDesktopAccordionLoggerContainer();
		logger 				= new CRONIOBusDesktopLoggerNodes();
		

		rootDeckPanel.add(logger);
		rootDeckPanel.add(loggerContainer);
		
	}


	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {

//		if (CRONIOBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
//			rootDeckPanel.showWidget(0);
//			variablesProcessConfigure.setData(sectionData);
//		} else if (CRONIOBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
//			rootDeckPanel.showWidget(1);
//			scheduleProcessConfigure.setData(sectionData);
//		} 
//		this.setVisibility(Visibility.VISIBLE);
//		AEGWTJQueryPerfectScrollBar.updateScroll(getName());
//		AEGWTJQueryPerfectScrollBar.top(getName());
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	public void postDisplay() {
		super.postDisplay();
//		variablesProcessConfigure.postDisplay();
//		scheduleProcessConfigure.postDisplay();
//		setHeightToDecrease(78);
//		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getName(), this, getCurrentHeight(), true);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
	}

	public AEMFTMetadataElementComposite getData() {
		return null;
	}
}
