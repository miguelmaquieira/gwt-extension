package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasComparator;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasSort;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTComparator;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEvent;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEventTypes.SORT_TYPE;

public class DSLAMBusDesktopEditorFileList extends AEGWTCompositePanel implements AEGWTHasSort, AEGWTHasComparator {

	public static final String NAME = "DSLAMBusDesktopEditorFileList";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel fileListContainerZone;
	private FlowPanel fileListContainer;

	private AEGWTComparator filenameComparatorAsc;

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
		
		initComparators();
	}
	
	public void addFile(AEMFTMetadataElementComposite fileData) {
		DSLAMBusDesktopEditorFileListElement file = new DSLAMBusDesktopEditorFileListElement();
		fileListContainer.add(file);
		file.setData(fileData);
		sort(null, false);
	}
	
	public void updateFile(AEMFTMetadataElementComposite fileData) {
		if (fileData != null) {
			Long fileId = getElementController().getElementAsLong(DSLAMBOIFile.FILE_ID, fileData);
			DSLAMBusDesktopEditorFileListElement fileWidget = getFileElementById(String.valueOf(fileId));
			fileWidget.setData(fileData);
			sort(null, false);
		}
	}
	
	public AEMFTMetadataElementComposite getFileData(String fileId) {
		AEMFTMetadataElementComposite fileData = null;
		DSLAMBusDesktopEditorFileListElement fileWidget = getFileElementById(fileId);
		if (fileWidget != null) {
			fileData = fileWidget.getData();
		}
		return fileData;
	}
	
	public AEMFTMetadataElementComposite getFileDataByName(String filename) {
		AEMFTMetadataElementComposite fileData = null;
		int fileCount = fileListContainer.getWidgetCount();
		for (int i = 0; i < fileCount; i++) {
			DSLAMBusDesktopEditorFileListElement fileWidget = (DSLAMBusDesktopEditorFileListElement) fileListContainer.getWidget(i);
			fileData = fileWidget.getData();
			String currentFileName = getElementController().getElementAsString(DSLAMBOIFile.FILE_NAME, fileData);
			if (filename.equals(currentFileName)) {
				break;
			} else {
				fileData = null;	
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

	/**
	 * AEGWTHasSort
	 */
	@Override
	public void dispatchEvent(AEGWTSortEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDispatchEventType(SORT_TYPE type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sort(String comparatorKey, boolean ascendent) {
		List<DSLAMBusDesktopEditorFileListElement> widgetList = getFileWidgetList();
		if (widgetList != null && widgetList.size() > 0) {
			Collections.sort(widgetList, getComparator(null, false));
			fileListContainer.clear();
			for (DSLAMBusDesktopEditorFileListElement item : widgetList) {
				fileListContainer.add(item);
			}
		}
	}

	@Override
	public AEGWTComparator getComparator(String key, boolean ascendent) {
		return filenameComparatorAsc;
	}
	
	/**
	 * AEGWTHasComparator
	 */
	@Override
	public void initComparators() {
		filenameComparatorAsc = new AEGWTComparator() {

			@Override
			public int compare(AEGWTICompositePanel o1, AEGWTICompositePanel o2) {
				DSLAMBusDesktopEditorFileListElement o1File = (DSLAMBusDesktopEditorFileListElement) o1;
				DSLAMBusDesktopEditorFileListElement o2File = (DSLAMBusDesktopEditorFileListElement) o2;
				return o1File.getFilename().compareTo(o2File.getFilename());
			}
		};
	}
	
	/**
	 * PRIVATE
	 */
	private List<DSLAMBusDesktopEditorFileListElement> getFileWidgetList() {
		List<DSLAMBusDesktopEditorFileListElement> widgetList = new ArrayList<>();
		for (int i = 0; i < fileListContainer.getWidgetCount(); i++) {
			DSLAMBusDesktopEditorFileListElement fileWidget = (DSLAMBusDesktopEditorFileListElement) fileListContainer.getWidget(i);
			widgetList.add(fileWidget);
		}
		return widgetList;
	}
	
	private DSLAMBusDesktopEditorFileListElement getFileElementById(String fileId) {
		DSLAMBusDesktopEditorFileListElement fileWidget = null;
		int fileCount = fileListContainer.getWidgetCount();
		for (int i = 0; i < fileCount; i++) {
			DSLAMBusDesktopEditorFileListElement currentFileWidget = (DSLAMBusDesktopEditorFileListElement) fileListContainer.getWidget(i);
			if (fileId.equals(currentFileWidget.getId())) {
				fileWidget = currentFileWidget;
				break;
			}
		}
		return fileWidget;
	}

}
