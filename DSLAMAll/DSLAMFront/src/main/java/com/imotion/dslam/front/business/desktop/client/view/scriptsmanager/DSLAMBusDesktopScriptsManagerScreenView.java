
package com.imotion.dslam.front.business.desktop.client.view.scriptsmanager;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class DSLAMBusDesktopScriptsManagerScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopScriptsManagerDisplay {

	public		static final String 	NAME			= "DSLAMBusDesktopScriptsManagerScreenView";
	private		static final int		MILIS_TO_SAVE 	= 1000;

	private AceEditor						editor;
	private AEMFTMetadataElementComposite 	fileData;
	private Timer 							timer;

	public DSLAMBusDesktopScriptsManagerScreenView() {
		//Editor zone
		FlowPanel editorZone = new FlowPanel();
		initWidget(editorZone);
		editorZone.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR_CONTAINER);

		// create first AceEditor widget
		editor = new AceEditor();
		editorZone.add(editor);
		editor.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR_AREA);

		// start the first editor and set its theme and mode
		editor.startEditor(); // must be called before calling setTheme/setMode/etc.
		editor.setTheme(AceEditorTheme.ECLIPSE);
		editor.setAutoCompletionEnabled(true);
		editor.setShowPrintMargin(false);
		editor.setFontSize(14);
		editor.setVisible(false);

		editor.addOnChangeHandler(new AceEditorCallback() {
			
			@Override
			public void invokeAceCallback(JavaScriptObject obj) {
				handleChanges(false);
			}
			
		});

		editor.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				handleChanges(true);
			}
		});
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
		if (data != null) {
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
			editor.setVisible(true);
			fileData = data;
			String 	content		= getElementController().getElementAsString(DSLAMBOIFile.CONTENT, data);
			int		contentType	= getElementController().getElementAsInt(DSLAMBOIFile.CONTENT_TYPE, data);

			editor.setText(content);

			if (DSLAMBOIProject.PROJECT_MACHINE_TYPE_DSLAM == contentType) {
				editor.setMode(AceEditorMode.DSLAM);
			} else {
				editor.setMode(AceEditorMode.TEXT);
			}
		}
	}
	
	/**
	 * CRONIOBusProjectBaseDisplay
	 */
	
	@Override
	public void beforeExitSection() {
		// TODO Auto-generated method stub
		
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	private void handleChanges(boolean now) {
		String originalContent 			= getElementController().getElementAsString(DSLAMBOIFile.CONTENT, fileData);
		final String currentContent		= editor.getText();
		if (!currentContent.equals(originalContent)) {
			if (now) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
				fireChangeEvent(currentContent);
			} else {
				if (timer == null) {
					timer = new Timer() {

						@Override
						public void run() {
							fireChangeEvent(currentContent);
						}

					};
				}
				timer.schedule(MILIS_TO_SAVE);
			}
		}
	}

	private void fireChangeEvent(String currentContent) {
		fileData.addElement(DSLAMBOIFile.CONTENT, currentContent);
		fileData.addElement(DSLAMBOIFile.SAVED_TIME, new Date());

		CRONIOBusDesktopProjectEvent saveEvt = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
		saveEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
		saveEvt.addElementAsDataValue(fileData);
		getLogicalEventHandlerManager().fireEvent(saveEvt);
	}

}
