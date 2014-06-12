package com.imotion.dslam.front.business.desktop.client.view.scriptsmanager;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager.DSLAMBusDesktopScriptsManagerDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopProjectNavigator;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopNewProjectPopupForm;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbarActions;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbarInfo;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class DSLAMBusDesktopScriptsManagerScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopScriptsManagerDisplay , AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopScriptsManagerScreenView";

	private static final DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);


	private FlowPanel 							root;
	private DSLAMBusDesktopToolbar				toolbar;
	private DSLAMBusDesktopProjectNavigator			fileList;
	private AceEditor							editor;
	private DSLAMBusDesktopNewProjectPopupForm	newScriptPopup;

	public DSLAMBusDesktopScriptsManagerScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setMainTitleText("");
		toolbar.setLastSaved(null);
		toolbar.setModified(false);
		toolbar.setFileInfoVisible(false);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW_BOTTOM_ZONE);

		//Bottom Zone - File list zone
		FlowPanel fileListZone = new FlowPanel();
		bottomZone.add(fileListZone);
		fileListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		fileListZone.addStyleName(DSLAMBusDesktopIStyleConstants.LIST_ZONE);

		fileList = new DSLAMBusDesktopProjectNavigator();
		fileListZone.add(fileList);

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
				if (!toolbar.isModified()) {
					toolbar.setModified(true);
				}
			}
		});
	}

	@Override
	public void addFile(AEMFTMetadataElementComposite fileData) {
		newScriptPopup.hide();
		fileList.addElement(fileData);
		openFile(fileData);
	}

	@Override
	public void updateFile(AEMFTMetadataElementComposite fileData) {
		if (fileData != null) {
			Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID	, fileData);
			String	fileIdStr	= String.valueOf(fileId);
			String	currentFileId	= toolbar.getId();
			if (fileIdStr.equals(currentFileId)) {
				toolbar.setData(fileData);
			}
			fileList.updateElement(fileData);
			newScriptPopup.hide();
		}
	}
	
	@Override
	public void removeFile(String fileId) {
		fileList.removeElement(fileId);
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
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		fileList.postDisplay();
		newScriptPopup = new DSLAMBusDesktopNewProjectPopupForm(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			List<AEMFTMetadataElement> fileDataList = data.getSortedElementList();
			for (AEMFTMetadataElement fileData : fileDataList) {
				fileList.addElement((AEMFTMetadataElementComposite) fileData);
			}
		}
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		String			srcWidgetId		= evt.getSourceWidgetId();
		String			srcContainerId	= evt.getSourceContainerId();
		LOGICAL_TYPE	type		= evt.getEventType();
		if (DSLAMBusDesktopToolbarActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				if (!toolbar.isModified() || Window.confirm(TEXTS.exit_without_save())) {
					closeCurrentFile();
					newScriptPopup.setMode(DSLAMBusDesktopNewProjectPopupForm.MODE_NEW_PROJECT);
					newScriptPopup.center();
				}
			} if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				String currentText = editor.getText();
				fireSaveChanges(srcWidgetId, currentText);
			}
		} else if (DSLAMBusDesktopToolbarInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.CLOSE_EVENT.equals(type)) {
				closeCurrentFile();
			}
		} else if (DSLAMBusDesktopNewProjectPopupForm.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type) || LOGICAL_TYPE.CHANGE_EVENT.equals(type)) {
				fireSaveFormDataEvent(evt);
			}
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type)
				||
				LOGICAL_TYPE.NEW_EVENT.equals(type)
				||
				LOGICAL_TYPE.CLOSE_EVENT.equals(type)
				||
				LOGICAL_TYPE.SELECT_EVENT.equals(type)
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type);
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	private void closeCurrentFile() {
		toolbar.setFileInfoVisible(false);
		toolbar.setModified(false);
		editor.setVisible(false);
		toolbar.setId(null);
	}

	private void fireSaveChanges(String fileId, String content) {
		AEMFTMetadataElementComposite updateFileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		updateFileData.addElement(DSLAMBOIFile.FILE_ID, fileId);
		updateFileData.addElement(DSLAMBOIFile.CONTENT, content);

		AEGWTLogicalEvent updateEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		updateEvent.setEventType(LOGICAL_TYPE.SAVE_EVENT);
		updateEvent.addElementAsDataValue(updateFileData);
		getLogicalEventHandlerManager().fireEvent(updateEvent);
	}

	private void openFile(AEMFTMetadataElementComposite fileData) {
		if (fileData != null) {
			Long	fileId		= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID, fileData);
			String	fileIdStr	= String.valueOf(fileId);
			openFile(fileIdStr);
		}
	}
	
	private void openFile(String fileId) {
		if (!toolbar.isModified() || (toolbar.isModified() && Window.confirm(TEXTS.exit_without_save())) ) {
			closeCurrentFile();
			AEMFTMetadataElementComposite fileData = fileList.getElementData(fileId);
			String	content 	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT		, fileData);
			String	contentType	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT_TYPE	, fileData);
			toolbar.setData(fileData);
			editor.setText(content);
			toolbar.setModified(false);
			toolbar.setFileInfoVisible(true);
			editor.setVisible(true);
			editor.focus();
			
			if (DSLAMBOIFile.CONTENT_TYPE_DSLAM.equals(contentType)) {
				editor.setMode(AceEditorMode.DSLAM);
			} else {
				//testing
				editor.setMode(AceEditorMode.JAVA);
			}
		}
	}

	private void fireSaveFormDataEvent(AEGWTLogicalEvent saveButtonEvt) {
		saveButtonEvt.stopPropagation();
		String filename		= saveButtonEvt.getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME);
		AEMFTMetadataElementComposite existentFileData = fileList.getElementDataByName(filename);
		if (existentFileData != null) {
			newScriptPopup.setError(TEXTS.filename_exists());
		} else {
			String contentType = saveButtonEvt.getElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE);

			AEMFTMetadataElementComposite fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			fileData.addElement(DSLAMBOIFileDataConstants.FILE_NAME		, filename);
			fileData.addElement(DSLAMBOIFileDataConstants.CONTENT_TYPE	, contentType);

			AEGWTLogicalEvent saveFileEvent = new AEGWTLogicalEvent(getWindowName(), getName());
			saveFileEvent.setEventType(saveButtonEvt.getEventType());
			saveFileEvent.addElementAsDataValue(fileData);
			saveFileEvent.setSourceWidgetId(saveButtonEvt.getSourceWidgetId());
			getLogicalEventHandlerManager().fireEvent(saveFileEvent);
		}
	}

	private void showRenameForm(AEMFTMetadataElementComposite fileData) {
		newScriptPopup.setMode(DSLAMBusDesktopNewProjectPopupForm.MODE_RENAME_PROJECT);
		newScriptPopup.center();
		newScriptPopup.setData(fileData);
	}

	private void fireDeleteFile(String fileId) {
		AEGWTLogicalEvent deleteEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		deleteEvent.setSourceWidgetId(fileId);
		deleteEvent.setEventType(LOGICAL_TYPE.DELETE_EVENT);
		getLogicalEventHandlerManager().fireEvent(deleteEvent);
	}

}
