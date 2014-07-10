package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;


public class CRONIOBusDesktopPreferencesMachineSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineSectionsDeckPanel";

	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private DeckPanel 											rootDeckPanel;
	private AceEditor											editor;
	private CRONIOBusDesktopPreferencesMachineVariables			variablesPreferencesMachine;
	private CRONIOBusDesktopPreferencesMachineConfirureForm		preferencesMachineConfigureForm;

	public CRONIOBusDesktopPreferencesMachineSectionsDeckPanel() {

		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_MACHINE_DECKPANEL);

		variablesPreferencesMachine 		= new CRONIOBusDesktopPreferencesMachineVariables();
		preferencesMachineConfigureForm 	= new CRONIOBusDesktopPreferencesMachineConfirureForm();
	
		rootDeckPanel.add(variablesPreferencesMachine);
		rootDeckPanel.add(preferencesMachineConfigureForm);
		//rootDeckPanel.add(editor);
	}

	public void reset() {
	}

	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {

		String[] 	sectionIdSplit 		= sectionId.split("\\.");
		int			sectionIdSplitSize	= sectionIdSplit.length;
		String		finalSectionId 		= sectionIdSplit[sectionIdSplitSize-1];
		
		if (CRONIOBOIMachineProperties.MACHINE_VARIABLES.equals(finalSectionId)) {
			rootDeckPanel.showWidget(0);
			variablesPreferencesMachine.setData(sectionData);
		} else if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG.equals(finalSectionId)) {
			rootDeckPanel.showWidget(1);
			preferencesMachineConfigureForm.setData(sectionData);
		} else if (CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT.equals(finalSectionId)) {
			rootDeckPanel.showWidget(2);
			//editor.setData(sectionData);
		} else if (CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT.equals(finalSectionId)) {
			rootDeckPanel.showWidget(2);
			//editor.setData(sectionData);
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
		variablesPreferencesMachine.postDisplay();
		preferencesMachineConfigureForm.postDisplay();
	//	editor.postDisplay();
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
