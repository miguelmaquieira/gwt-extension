package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.Date;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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
	
	public void setLastSaved(Date date) {
		fileInfo.setLastSaved(date);
	}
	
	public void setFilename(String fileName) {
		fileInfo.setFileName(fileName);
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
		// TODO Auto-generated method stub

	}

}
