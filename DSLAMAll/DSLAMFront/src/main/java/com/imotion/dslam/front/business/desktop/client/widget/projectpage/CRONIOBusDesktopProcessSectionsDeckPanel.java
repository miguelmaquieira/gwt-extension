package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;


public class CRONIOBusDesktopProcessSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "CRONIOBusDesktopProcessSectionsDeckPanel";

	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private DeckPanel 										rootDeckPanel;
	private DSLAMBusDesktopProcessConfigureVariables		variablesProcessConfigure;
	private DSLAMBusDesktopProcessConfigureSchedule			scheduleProcessConfigure;
	private DSLAMBusDesktopProcessConfigureExtraOptions		extraOptionsConfigure;
	private CRONIOBusDesktopProcessConfigureNodes			nodesConfigure;

	public CRONIOBusDesktopProcessSectionsDeckPanel() {

		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_CONFIGURE_DECKPANEL);

		variablesProcessConfigure 	= new DSLAMBusDesktopProcessConfigureVariables();
		scheduleProcessConfigure 	= new DSLAMBusDesktopProcessConfigureSchedule();
		extraOptionsConfigure 		= new DSLAMBusDesktopProcessConfigureExtraOptions();
		nodesConfigure				= new CRONIOBusDesktopProcessConfigureNodes();

		rootDeckPanel.add(variablesProcessConfigure);
		rootDeckPanel.add(scheduleProcessConfigure);
		rootDeckPanel.add(extraOptionsConfigure);
		rootDeckPanel.add(nodesConfigure);
	}

	public void reset() {
	}

	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {

		if (DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST.equals(sectionId)) {
			rootDeckPanel.showWidget(0);
			variablesProcessConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_SCHEDULE_LIST.equals(sectionId)) {
			rootDeckPanel.showWidget(1);
			scheduleProcessConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_EXTRA_OPTIONS.equals(sectionId)) {
			rootDeckPanel.showWidget(2);
			extraOptionsConfigure.setData(sectionData);
		} else if (DSLAMBOIProject.PROJECT_PROCESS_NODE_LIST.equals(sectionId)) {
			rootDeckPanel.showWidget(3);
			nodesConfigure.setData(sectionData);
		}
		this.setVisibility(Visibility.VISIBLE);
		AEGWTJQueryPerfectScrollBar.updateScroll(getName());
		AEGWTJQueryPerfectScrollBar.top(getName());
	}

	/**
	 * AEGWTCompositePanel
	 */

	public void postDisplay() {
		super.postDisplay();
		variablesProcessConfigure.postDisplay();
		scheduleProcessConfigure.postDisplay();
		extraOptionsConfigure.postDisplay();
		nodesConfigure.postDisplay();
		setHeightToDecrease(78);
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getName(), this, getCurrentHeight(), true);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		//optionsZone.setData(data);
	}

	public AEMFTMetadataElementComposite getData() {
		//return optionsZone.getData();
		return null;
	}
}
