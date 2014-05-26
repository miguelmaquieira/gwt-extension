package com.imotion.dslam.front.business.desktop.client.view.studio;

import java.util.Date;
import java.util.List;

import org.goda.time.DateTime;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.studio.DSLAMBusDesktopStudioDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopEditorFileList;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopEditorToolbarFileActions;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopEditorToolbarFileInfo;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopNewScriptPopupForm;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSetListRegroup;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapSplitButtonDropdown;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class DSLAMBusDesktopStudioScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopStudioDisplay , AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopStudioScreenView";

	private static final DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);


	private FlowPanel 							root;
	private DSLAMBusDesktopToolbar				toolbar;
	private DSLAMBusDesktopEditorFileList		fileList;
	private AceEditor							editor;
	private DSLAMBusDesktopNewScriptPopupForm	newScriptPopup;

	public DSLAMBusDesktopStudioScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setFilename("");
		toolbar.setLastSaved(null);
		toolbar.addStyleName(AEGWTIBoostrapConstants.ROW);
		toolbar.setModified(false);
		toolbar.setFileInfoVisible(false);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW_BOTTOM_ZONE);
		bottomZone.addStyleName(AEGWTIBoostrapConstants.ROW);

		//Bottom Zone - File list zone
		FlowPanel fileListZone = new FlowPanel();
		bottomZone.add(fileListZone);
		fileListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		fileListZone.addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_ZONE);

		fileList = new DSLAMBusDesktopEditorFileList();
		fileListZone.add(fileList);

		//Bottom Zone - Editor zone
		FlowPanel editorZone = new FlowPanel();
		bottomZone.add(editorZone);
		editorZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		editorZone.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_ZONE);

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
		fileList.addFile(fileData);

		String	filename 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME, fileData);
		Date	lastSaved	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME, fileData);
		toolbar.setLastSaved(lastSaved);
		toolbar.setFilename(filename);
	}

	@Override
	public void updateFile(AEMFTMetadataElementComposite fileData) {
		String	filename 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME, fileData);
		Date	lastSaved	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME, fileData);
		toolbar.setLastSaved(lastSaved);
		toolbar.setFilename(filename);

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
		newScriptPopup = new DSLAMBusDesktopNewScriptPopupForm(this);
		//		//TEST
		//		buildExample();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			List<AEMFTMetadataElement> fileDataList = data.getSortedElementList();
			for (AEMFTMetadataElement fileData : fileDataList) {
				fileList.addFile((AEMFTMetadataElementComposite) fileData);
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
		if (DSLAMBusDesktopEditorToolbarFileActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				newScriptPopup.center();
			} if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				String currentText = editor.getText();
				fireSave(srcWidgetId, currentText);
			}
		} else if (DSLAMBusDesktopEditorToolbarFileInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.CLOSE_EVENT.equals(type)) {
				closeCurrentFile();
			}
		} else if (AEGWTBootstrapSplitButtonDropdown.NAME.equals(srcWidget)) {
			boolean fileSelected = AEGWTStringUtils.isEmptyString(srcContainerId);
			String fileId = fileSelected ? srcWidgetId : srcContainerId;
			if (fileSelected) {
				openFile(fileId);
			} else {

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
				LOGICAL_TYPE.SELECT_EVENT.equals(type);
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
	private void buildExample() {
		AEMFTMetadataElementCompositeRecordSetListRegroup fileListData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getCompositeListRegroup();

		for (int i = 0; i < 20 ; i++) {
			AEMFTMetadataElementComposite fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			DateTime fileDate = new DateTime();
			fileDate = fileDate.minusDays(i);

			getElementController().setElement(DSLAMBOIFileDataConstants.FILE_ID, fileData, "A-" + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.FILE_NAME, fileData, "file-" + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.CONTENT, fileData, "sdfsdfsfd \n aakskaskask \n\n scsdcscc -- " + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.SAVED_TIME, fileData, fileDate.toDate());

			fileListData.addElement(fileData);
		}
		fileList.setData(fileListData);
	}

	private void closeCurrentFile() {
		toolbar.setFileInfoVisible(false);
		toolbar.setModified(false);
		editor.setVisible(false);
	}

	private void fireSave(String fileId, String content) {
		AEMFTMetadataElementComposite updateFileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		updateFileData.addElement(DSLAMBOIFile.FILE_ID, fileId);
		updateFileData.addElement(DSLAMBOIFile.CONTENT, content);

		AEGWTLogicalEvent updateEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		updateEvent.setEventType(LOGICAL_TYPE.SAVE_EVENT);
		updateEvent.addElementAsDataValue(updateFileData);
		getLogicalEventHandlerManager().fireEvent(updateEvent);
	}

	private void openFile(String fileId) {
		if (!toolbar.isModified() || (toolbar.isModified() && Window.confirm(TEXTS.exit_without_save())) ) {
			closeCurrentFile();
			AEMFTMetadataElementComposite fileData = fileList.getFileData(fileId);
			String	filename 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME		, fileData);
			String	content 	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT		, fileData);
			String	contentType	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT_TYPE	, fileData);
			Date	lastSaved	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME, fileData);
			toolbar.setLastSaved(lastSaved);
			toolbar.setFilename(filename);
			editor.setText(content);
			toolbar.setModified(false);
			toolbar.setFileInfoVisible(true);
			editor.setVisible(true);
		}
	}

}
