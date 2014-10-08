package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasComparator;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasSort;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTComparator;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEvent;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEventTypes.SORT_TYPE;

public class CRONIOBusDesktopProjectNavigator extends AEGWTCompositePanel implements AEGWTHasSort, AEGWTHasComparator {

	public static final String NAME = "CRONIOBusDesktopProjectNavigator";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private FlowPanel elementListContainerZone;
	private FlowPanel elementListContainer;
	
	private AEGWTComparator nameComparatorAsc;

	public CRONIOBusDesktopProjectNavigator() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(CRONIOBusDesktopIStyleConstants.LIST);
		
		//Header
		FlowPanel headerZone = new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_ZONE_HEADER);
		
		AEGWTLabel headerLabel = new AEGWTLabel(TEXTS.navigator());
		headerZone.add(headerLabel);
		
		//Container
		elementListContainerZone = new FlowPanel();
		root.add(elementListContainerZone);
		elementListContainerZone.addStyleName(CRONIOBusDesktopIStyleConstants.LIST_CONTAINER_ZONE);
		
		elementListContainer = new FlowPanel();
		elementListContainerZone.add(elementListContainer);
		elementListContainer.addStyleName(CRONIOBusDesktopIStyleConstants.LIST_CONTAINER);
		
		initComparators();
	}
	
	public void setProjectSectionModified(String projectId, String sectionId) {
		CRONIOBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSectionModified(sectionId);
		}
	}
	
	public void setProjectSectionSeleted(String projectId, String sectionId) {
		CRONIOBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSectionSelected(sectionId);
		}
	}
	
	public void setProjectSaved(String projectId) {
		CRONIOBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSaved();
		}
	}
	
	public void addElement(AEMFTMetadataElementComposite elementData) {
		String		projectId	= getElementController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID, elementData);
		String		projectName	= getElementController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME, elementData);
		
		CRONIOBusDesktopProjectNavigatorElement element = createElement(projectId, projectName);
		elementListContainer.add(element);
		element.setData(elementData);
		element.postDisplay();
		sort(null, false);
		AEGWTJQueryPerfectScrollBar.updateScroll(NAME);
	}

	public void updateElement(AEMFTMetadataElementComposite elementData) {
		if (elementData != null) {
			
			String projectId = getElementController().getElementAsString(CRONIOBOIProject.PROJECT_ID, elementData);
			CRONIOBusDesktopProjectNavigatorElement elementWidget = getElementById(String.valueOf(projectId));
			elementWidget.setData(elementData);
			sort(null, false);
		}
	}
	
	public void removeElement(String projectId) {
		CRONIOBusDesktopProjectNavigatorElement element = getElementById(projectId);
		elementListContainer.remove(element);
	}
	
	public List<String> getModifiedProjectIds() {
		List<String> projectIds = new ArrayList<>();
		int itemCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < itemCount; i++) {
			CRONIOBusDesktopProjectNavigatorElement currentElementWidget = (CRONIOBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			if (currentElementWidget.isModified()) {
				projectIds.add(currentElementWidget.getId());
			}
		}
		return projectIds;
	}
	
	public void removeProjectSectionSelected() {
		List<CRONIOBusDesktopProjectNavigatorElement> projectElementList = getElementWidgetList();
		for (CRONIOBusDesktopProjectNavigatorElement projectElement : projectElementList) {
			projectElement.removeProjectSectionSelected();
		}
		
	}
	
	public void addExecution(String projectId, String executionDateStr) {
		List<CRONIOBusDesktopProjectNavigatorElement> projectNavigatorElementList = getElementWidgetList();
		for (CRONIOBusDesktopProjectNavigatorElement ProjectNavigatorElement : projectNavigatorElementList) {
			if (ProjectNavigatorElement.getId().equals(projectId)) {
				ProjectNavigatorElement.addExecution(projectId, executionDateStr);
			}
		}
	}
	
	public void addNodeList(String projectId, String nodeListName) {
		List<CRONIOBusDesktopProjectNavigatorElement> projectNavigatorElementList = getElementWidgetList();
		for (CRONIOBusDesktopProjectNavigatorElement ProjectNavigatorElement : projectNavigatorElementList) {
			if (ProjectNavigatorElement.getId().equals(projectId)) {
				ProjectNavigatorElement.addNodeList(projectId, nodeListName);
			}
		}
	}
	
	public void hideAddNodeListForm(String projectId) {
		List<CRONIOBusDesktopProjectNavigatorElement> projectNavigatorElements = getElementWidgetList();
		for (CRONIOBusDesktopProjectNavigatorElement projectNavigatorElement : projectNavigatorElements) {
			if (projectId.equals(projectNavigatorElement.getId())) {
				projectNavigatorElement.hideAddNodeListForm();
			}
		}
	}
	
	public void showDuplicateNodeListNameError(long projectId, String nodeListName) {
		List<CRONIOBusDesktopProjectNavigatorElement> projectNavigatorElementList = getElementWidgetList();
		for (CRONIOBusDesktopProjectNavigatorElement projectNavigatorElement : projectNavigatorElementList) {
			
			String projectNavigatorElementId 	= projectNavigatorElement.getId();
			String projectIdStr 				= String.valueOf(projectId);
			if (projectNavigatorElementId.equals(projectIdStr)) {
				projectNavigatorElement.showDuplicateNodeListNameError(nodeListName);
			}
		}
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
			elementListContainer.clear();
			List<AEMFTMetadataElement> elementDataList = data.getSortedElementList();
			for (AEMFTMetadataElement elementData : elementDataList) {
				if (!CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA.equals(elementData.getKey()) && !CRONIOBUIProjectBusinessServiceConstants.LIST_NODELIST_DATA.equals(elementData.getKey())) {
					addElement((AEMFTMetadataElementComposite) elementData);
				} else {
					AEMFTMetadataElementComposite elementDataComposite = (AEMFTMetadataElementComposite) elementData;
					if (CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA.equals(elementDataComposite.getKey())) {
						List<AEMFTMetadataElement> projectExecutionsListData = elementDataComposite.getSortedElementList();
						for (AEMFTMetadataElement projectExecutionData : projectExecutionsListData) {
							AEMFTMetadataElementComposite projectExecutionDataComposite = (AEMFTMetadataElementComposite) projectExecutionData;
							String projectId = projectExecutionDataComposite.getKey();
							List<AEMFTMetadataElement> executionDataList = projectExecutionDataComposite.getSortedElementList();
							for (AEMFTMetadataElement execution : executionDataList) {
								AEMFTMetadataElementComposite executionDataComposite = (AEMFTMetadataElementComposite) execution;
								AEMFTMetadataElementSingle creationTimeData = (AEMFTMetadataElementSingle) executionDataComposite.getElement(CRONIOBOIExecution.CREATION_TIME);
								String 		creationTimeStr 		= creationTimeData.getValueAsSerializable().toString();
								String[] 	creationTimeStrSplit 	= creationTimeStr.split("\\.");
								
								String[] creationTimeStrSplit1 = creationTimeStrSplit[0].split("\\-");
								String[] creationTimeStrSplit2 = creationTimeStrSplit1[2].split(" ");
								
								String 		creationTimeStrFormat	= creationTimeStrSplit2[0] + "-" + creationTimeStrSplit1[1] + "-" + creationTimeStrSplit1[0] + " " + creationTimeStrSplit2[1];
								addExecution(projectId, creationTimeStrFormat);
							}
						}
					} else {
						List<AEMFTMetadataElement> projectNodeListsData = elementDataComposite.getSortedElementList();
						for (AEMFTMetadataElement projectNodeListData : projectNodeListsData) {
							AEMFTMetadataElementComposite projectNodeListDataComposite = (AEMFTMetadataElementComposite) projectNodeListData;
							String projectId = projectNodeListDataComposite.getKey();
							List<AEMFTMetadataElement> nodeListDataList = projectNodeListDataComposite.getSortedElementList();
							for (AEMFTMetadataElement nodeList : nodeListDataList) {
								AEMFTMetadataElementComposite nodeListDataComposite = (AEMFTMetadataElementComposite) nodeList;
								String nodeListName = nodeListDataComposite.getKey();
								addNodeList(projectId, nodeListName);
							}
						}
					}
				}	
			}
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(NAME, elementListContainerZone, getCurrentHeight(), true);
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
		List<CRONIOBusDesktopProjectNavigatorElement> widgetList = getElementWidgetList();
		if (widgetList != null && widgetList.size() > 0) {
			Collections.sort(widgetList, getComparator(null, false));
			elementListContainer.clear();
			for (CRONIOBusDesktopProjectNavigatorElement item : widgetList) {
				elementListContainer.add(item);
			}
		}
	}

	@Override
	public AEGWTComparator getComparator(String key, boolean ascendent) {
		return nameComparatorAsc;
	}
	
	/**
	 * AEGWTHasComparator
	 */
	@Override
	public void initComparators() {
		nameComparatorAsc = new AEGWTComparator() {

			@Override
			public int compare(AEGWTICompositePanel o1, AEGWTICompositePanel o2) {
				CRONIOBusDesktopProjectNavigatorElement o1Element = (CRONIOBusDesktopProjectNavigatorElement) o1;
				CRONIOBusDesktopProjectNavigatorElement o2Element = (CRONIOBusDesktopProjectNavigatorElement) o2;
				return o1Element.getElementName().compareTo(o2Element.getElementName());
			}
		};
	}
	
	/**
	 * PROTECTED
	 */
	
	protected CRONIOBusDesktopProjectNavigatorElement createElement(String projectId, String name) {
		return new CRONIOBusDesktopProjectNavigatorElement(projectId, name);
	}
	
	/**
	 * PRIVATE
	 */
	private List<CRONIOBusDesktopProjectNavigatorElement> getElementWidgetList() {
		List<CRONIOBusDesktopProjectNavigatorElement> widgetList = new ArrayList<>();
		for (int i = 0; i < elementListContainer.getWidgetCount(); i++) {
			CRONIOBusDesktopProjectNavigatorElement elementWidget = (CRONIOBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			widgetList.add(elementWidget);
		}
		return widgetList;
	}
	
	private CRONIOBusDesktopProjectNavigatorElement getElementById(String elementId) {
		CRONIOBusDesktopProjectNavigatorElement elementWidget = null;
		int itemCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < itemCount; i++) {
			CRONIOBusDesktopProjectNavigatorElement currentElementWidget = (CRONIOBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			if (elementId.equals(currentElementWidget.getId())) {
				elementWidget = currentElementWidget;
				break;
			}
		}
		return elementWidget;
	}
}

