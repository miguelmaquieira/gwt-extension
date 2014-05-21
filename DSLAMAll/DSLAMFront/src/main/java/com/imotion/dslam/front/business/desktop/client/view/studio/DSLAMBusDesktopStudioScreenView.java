package com.imotion.dslam.front.business.desktop.client.view.studio;

import java.util.Date;

import org.goda.time.DateTime;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFileConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.studio.DSLAMBusDesktopStudioDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopEditorFileList;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSetListRegroup;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

public class DSLAMBusDesktopStudioScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopStudioDisplay {

	public static final String NAME = "DSLAMBusDesktopStudioScreenView";
	
	private FlowPanel 						root;
	private DSLAMBusDesktopToolbar			toolbar;
	private DSLAMBusDesktopEditorFileList	fileList;
	
	public DSLAMBusDesktopStudioScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.EDITOR_VIEW);
		
		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setFilename("script1");
		toolbar.addStyleName(AEGWTIBoostrapConstants.ROW);
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
		
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
			
			getElementController().setElement(DSLAMBOIFileConstants.ID, fileData, "A-" + i);
			getElementController().setElement(DSLAMBOIFileConstants.FILE_NAME, fileData, "file-" + i);
			getElementController().setElement(DSLAMBOIFileConstants.CONTENT, fileData, "sdfsdfsfd \n aakskaskask \n\n scsdcscc -- " + i);
			getElementController().setElement(DSLAMBOIFileConstants.LAST_SAVED, fileData, fileDate.toDate());
		
			fileListData.addElement(fileData);
		}
		fileList.setData(fileListData);
	}

	
}
