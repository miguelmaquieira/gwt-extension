
package com.imotion.dslam.front.business.desktop.client.view.scriptsmanager;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.CRONIOBusDesktopScriptsManagerDisplay;
import com.imotion.dslam.front.business.desktop.client.view.CRONIOBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopEditor;
import com.imotion.dslam.front.business.desktop.client.widget.editor.CRONIOBusDesktopEditorChangeHandler;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopScriptsManagerScreenView extends CRONIOBusDesktopPanelBaseView implements CRONIOBusDesktopScriptsManagerDisplay {

	public		static final String 	NAME			= "CRONIOBusDesktopScriptsManagerScreenView";

	private CRONIOBusDesktopEditor editor;
	
	public CRONIOBusDesktopScriptsManagerScreenView() {
		//Editor zone
		FlowPanel editorZone = new FlowPanel();
		initContentPanel(editorZone);
		editorZone.addStyleName(CRONIOBusDesktopIStyleConstants.SCRIPTS_EDITOR_CONTAINER);

		editor = new CRONIOBusDesktopEditor(new CRONIOBusDesktopEditorChangeHandler() {
			
			@Override
			public void fireEvent(AEMFTMetadataElementComposite fileData) {
				CRONIOBusDesktopProjectEvent saveEvt = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
				saveEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
				saveEvt.addElementAsDataValue(fileData);
				getLogicalEventHandlerManager().fireEvent(saveEvt);
			}
		});
		editorZone.add(editor);
	}

	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		editor.setData(data);
	}
	
	/**
	 * CRONIOBusProjectBaseDisplay
	 */
	
	@Override
	public void beforeExitSection() {
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/
	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
}
