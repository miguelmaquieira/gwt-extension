package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopEditorFileList extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopEditorFileList";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel fileListContainerZone;
	private FlowPanel fileListContainer;

	public DSLAMBusDesktopEditorFileList() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST);
		
		//Header
		FlowPanel headerZone = new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_HEADER);
		
		AEGWTLabel headerLabel = new AEGWTLabel(TEXTS.navigator());
		headerZone.add(headerLabel);
		
		//Container
		fileListContainerZone = new FlowPanel();
		root.add(fileListContainerZone);
		fileListContainerZone.addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_CONTAINER_ZONE);
		
		fileListContainer = new FlowPanel();
		fileListContainerZone.add(fileListContainer);
		fileListContainer.addStyleName(DSLAMBusDesktopIStyleConstants.FILE_LIST_CONTAINER);
	}
	
	public void addFile(AEMFTMetadataElementComposite fileData) {
		DSLAMBusDesktopEditorFileListElement file = new DSLAMBusDesktopEditorFileListElement();
		fileListContainer.add(file);
		file.setData(fileData);
	}
	
	public AEMFTMetadataElementComposite getFileData(String fileId) {
		AEMFTMetadataElementComposite fileData = null;
		int fileCount = fileListContainer.getWidgetCount();
		for (int i = 0; i < fileCount; i++) {
			DSLAMBusDesktopEditorFileListElement fileWidget = (DSLAMBusDesktopEditorFileListElement) fileListContainer.getWidget(i);
			if (fileId.equals(fileWidget.getId())) {
				fileData = fileWidget.getData();
				break;
			}
		}
		return fileData;
	}

	/**
	 * AEGWTCompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			fileListContainer.clear();
			List<AEMFTMetadataElement> filedataList = data.getSortedElementList();
			for (AEMFTMetadataElement fileData : filedataList) {
				addFile((AEMFTMetadataElementComposite) fileData);
			}
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		setHeightToDecrease(80);
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(NAME, fileListContainerZone, getCurrentHeight());
	}

}
