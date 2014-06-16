package com.imotion.dslam.front.business.desktop.client.view.scriptsmanager;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class DSLAMBusDesktopScriptsManagerScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopScriptsManagerDisplay {

	public static final String NAME = "DSLAMBusDesktopScriptsManagerScreenView";

	private static final DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);


	private FlowPanel	root;
	private AceEditor	editor;

	public DSLAMBusDesktopScriptsManagerScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW_BOTTOM_ZONE);

		//Bottom Zone - Editor zone
		FlowPanel editorZone = new FlowPanel();
		bottomZone.add(editorZone);
		editorZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
//		editorZone.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR);

		// create first AceEditor widget
		editor = new AceEditor();
		editorZone.add(editor);
		editor.setWidth("100%");
		editor.setHeight("100%");


		// start the first editor and set its theme and mode
		editor.startEditor(); // must be called before calling setTheme/setMode/etc.
		editor.setTheme(AceEditorTheme.ECLIPSE);
		editor.setMode(AceEditorMode.DSLAM);
		editor.setAutoCompletionEnabled(true);
		editor.setShowPrintMargin(false);
		editor.setFontSize(14);
		editor.setVisible(false);

		editor.addOnChangeHandler(new AceEditorCallback() {

			@Override
			public void invokeAceCallback(JavaScriptObject obj) {
//				if (!toolbar.isModified()) {
//					toolbar.setModified(true);
//				}
			}
		});
		
		editor.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub
				
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
//			String text
		}
	}


	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	private void openFile(AEMFTMetadataElementComposite fileData) {
		if (fileData != null) {
			Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID, fileData);
			String	fileIdStr	= String.valueOf(fileId);
			openFile(fileIdStr);
		}
	}
	
	private void openFile(String fileId) {
//		if (!toolbar.isModified() || (toolbar.isModified() && Window.confirm(TEXTS.exit_without_save())) ) {
//			closeCurrentFile();
//			AEMFTMetadataElementComposite fileData = fileList.getElementData(fileId);
//			String	content 	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT		, fileData);
//			String	contentType	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT_TYPE	, fileData);
//			toolbar.setData(fileData);
//			editor.setText(content);
//			toolbar.setModified(false);
//			toolbar.setFileInfoVisible(true);
//			editor.setVisible(true);
//			editor.focus();
//			
//			if (DSLAMBOIFile.CONTENT_TYPE_DSLAM.equals(contentType)) {
//				editor.setMode(AceEditorMode.DSLAM);
//			} else {
//				//testing
//				editor.setMode(AceEditorMode.JAVA);
//			}
//		}
	}


}
