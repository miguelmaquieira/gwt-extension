package com.imotion.dslam.front.business.desktop.client.view.processpage;

import java.util.Date;

import org.goda.time.DateTime;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopEditorFileList;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSetListRegroup;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

public class DSLAMBusDesktopProcessPageScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessPageScreenView";

	private FlowPanel 						root;
	private DSLAMBusDesktopToolbar			toolbar;
	private DSLAMBusDesktopEditorFileList	fileList;
	//private AceEditor						editor;

	public DSLAMBusDesktopProcessPageScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setFilename("script1");
		toolbar.addStyleName(AEGWTIBoostrapConstants.ROW);
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_BOTTOM_ZONE);
		bottomZone.addStyleName(AEGWTIBoostrapConstants.ROW);

		//Bottom Zone - File list zone
		FlowPanel fileListZone = new FlowPanel();
		bottomZone.add(fileListZone);
		fileListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		fileListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_LIST_ZONE);

		fileList = new DSLAMBusDesktopEditorFileList();
		fileListZone.add(fileList);

		//Bottom Zone - Editor zone
		FlowPanel editorZone = new FlowPanel();
		bottomZone.add(editorZone);
		editorZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		editorZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_ZONE);

//		// create first AceEditor widget
//		editor = new AceEditor();
//		editorZone.add(editor);
//		editor.setWidth("100%");
//		editor.setHeight("100%");
//
//
//		// start the first editor and set its theme and mode
//		editor.startEditor(); // must be called before calling setTheme/setMode/etc.
//		editor.setTheme(AceEditorTheme.ECLIPSE);
//		editor.setMode(AceEditorMode.DSLAM);
//		editor.setAutoCompletionEnabled(true);
//		editor.setShowPrintMargin(false);
//		editor.setFontSize(14);
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
		fileList.postDisplay();
		//TEST
		buildExample();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
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


}
