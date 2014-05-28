package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.Date;

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class DSLAMBusDesktopToolbar extends AEGWTCompositePanel {
	
	public static final String NAME = "DSLAMBusDesktopToolbar";
	
	private DSLAMBusDesktopEditorToolbarFileActions fileActions;
	private DSLAMBusDesktopEditorToolbarFileInfo	 fileInfo;

	public DSLAMBusDesktopToolbar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR);
	
		//ACTIONS
		SimplePanel fileActionsZone = new SimplePanel();
		root.add(fileActionsZone);
		fileActionsZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		fileActionsZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_ZONE);
	
		fileActions = new DSLAMBusDesktopEditorToolbarFileActions();
		fileActionsZone.add(fileActions);
		
		//INFO
		SimplePanel fileInfoZone = new SimplePanel();
		root.add(fileInfoZone);
		fileInfoZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		
		fileInfo = new DSLAMBusDesktopEditorToolbarFileInfo();
		fileInfoZone.add(fileInfo);
	}
	
	public void setModified(boolean modified) {
		fileActions.setSaveEnabled(modified);
		fileInfo.setModified(modified);
	}
	
	public boolean isModified() {
		return fileInfo.isModified();
	}
	
	public void setLastSaved(Date date) {
		fileInfo.setLastSaved(date);
	}
	
	public void setFilename(String fileName) {
		fileInfo.setFileName(fileName);
	}
	
	public void setFileInfoVisible(boolean visible) {
		if (visible) {
			fileInfo.setVisibility(Visibility.VISIBLE);
		} else {
			fileInfo.setVisibility(Visibility.HIDDEN);
		}
	}

	/**
	 *	AEGWTICompositePanel 
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			Long	fileId	 	= getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID	, data);
			String	filename 	= getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME	, data);
			Date	lastSaved	= (Date) getElementController().getElementAsSerializable(DSLAMBOIFile.SAVED_TIME, data);
			
			String fileIdStr = String.valueOf(fileId);
			setId(fileIdStr);
			fileActions.setId(fileIdStr);
			setLastSaved(lastSaved);
			setFilename(filename);
			setModified(false);
		}

	}
	
	@Override
	public void setId(String id) {
		super.setId(id);
		fileActions.setId(id);
	}

}
