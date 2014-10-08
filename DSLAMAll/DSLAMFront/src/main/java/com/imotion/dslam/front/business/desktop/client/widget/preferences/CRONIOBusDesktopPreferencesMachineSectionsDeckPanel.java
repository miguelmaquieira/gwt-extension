package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.DeckPanel;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopEditor;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopEditorChangeHandler;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;


public class CRONIOBusDesktopPreferencesMachineSectionsDeckPanel extends AEGWTCompositePanel  {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineSectionsDeckPanel";

	private DeckPanel 											rootDeckPanel;
	private CRONIOBusDesktopEditor								editor;
	private CRONIOBusDesktopPreferencesMachineVariables			variablesPreferencesMachine;
	private CRONIOBusDesktopPreferencesMachineConfigureForm		preferencesMachineConfigureForm;

	public CRONIOBusDesktopPreferencesMachineSectionsDeckPanel() {
		rootDeckPanel = new DeckPanel();
		initWidget(rootDeckPanel);
		rootDeckPanel.addStyleName(CRONIOBusDesktopIStyleConstants.PREFERENCES_MACHINE_DECKPANEL);
		rootDeckPanel.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);

		variablesPreferencesMachine 		= new CRONIOBusDesktopPreferencesMachineVariables();
		rootDeckPanel.add(variablesPreferencesMachine);

		preferencesMachineConfigureForm 	= new CRONIOBusDesktopPreferencesMachineConfigureForm();
		rootDeckPanel.add(preferencesMachineConfigureForm);

		editor = new CRONIOBusDesktopEditor(new CRONIOBusDesktopEditorChangeHandler() {

			@Override
			public void fireEvent(AEMFTMetadataElementComposite fileData) {
				CRONIOBusDesktopPreferencesEvent saveEvt = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
				saveEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				saveEvt.addElementAsDataValue(fileData);
				getLogicalEventHandlerManager().fireEvent(saveEvt);
			}
		});
		rootDeckPanel.add(editor);
	}

	public void reset() {
	}

	public void showSection(String sectionId, AEMFTMetadataElementComposite sectionData) {
		if (sectionId.endsWith(CRONIOBOIMachineProperties.MACHINE_VARIABLES)) {
			rootDeckPanel.showWidget(0);
			variablesPreferencesMachine.setData(sectionData);
		} else if (sectionId.endsWith(CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG)) {
			String machineName = getElementController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, sectionData);
			preferencesMachineConfigureForm.resetForm();
			rootDeckPanel.showWidget(1);
			preferencesMachineConfigureForm.setId(machineName);
			preferencesMachineConfigureForm.setData(sectionData);
		} else if (sectionId.endsWith(CRONIOBOIMachineProperties.MACHINE_CONNECTION_SCRIPT)) {
			rootDeckPanel.showWidget(2);
			editor.setData(sectionData);
		} else if (sectionId.endsWith(CRONIOBOIMachineProperties.MACHINE_DISCONNECTION_SCRIPT)) {
			rootDeckPanel.showWidget(2);
			editor.setData(sectionData);
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
		setHeightToDecrease(85);
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(getName(), this, getCurrentHeight(), true);
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
