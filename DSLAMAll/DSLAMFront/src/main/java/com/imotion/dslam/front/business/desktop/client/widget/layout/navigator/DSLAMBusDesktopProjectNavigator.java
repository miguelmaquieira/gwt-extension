package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
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

public class DSLAMBusDesktopProjectNavigator extends AEGWTCompositePanel implements AEGWTHasSort, AEGWTHasComparator {

	public static final String NAME = "DSLAMBusDesktopNavigator";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel elementListContainerZone;
	private FlowPanel elementListContainer;
	
	private AEGWTComparator nameComparatorAsc;

	public DSLAMBusDesktopProjectNavigator() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(DSLAMBusDesktopIStyleConstants.LIST);
		
		//Header
		FlowPanel headerZone = new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_ZONE_HEADER);
		
		AEGWTLabel headerLabel = new AEGWTLabel(TEXTS.navigator());
		headerZone.add(headerLabel);
		
		//Container
		elementListContainerZone = new FlowPanel();
		root.add(elementListContainerZone);
		elementListContainerZone.addStyleName(DSLAMBusDesktopIStyleConstants.LIST_CONTAINER_ZONE);
		
		elementListContainer = new FlowPanel();
		elementListContainerZone.add(elementListContainer);
		elementListContainer.addStyleName(DSLAMBusDesktopIStyleConstants.LIST_CONTAINER);
		
		initComparators();
	}
	
	public void setProjectSectionModified(String projectId, String sectionId) {
		DSLAMBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSectionModified(sectionId);
		}
	}
	
	public void setProjectSectionSeleted(String projectId, String sectionId) {
		DSLAMBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSectionSelected(sectionId);
		}
	}
	
	public void setProjectSaved(String projectId) {
		DSLAMBusDesktopProjectNavigatorElement projectElement = getElementById(projectId);
		if (projectElement != null) {
			projectElement.setProjectSaved();
		}
	}
	
	public void addElement(AEMFTMetadataElementComposite elementData) {
		String		projectId	= getElementController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID, elementData);
		String		projectName	= getElementController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME, elementData);
		
		DSLAMBusDesktopProjectNavigatorElement element = createElement(projectId, projectName);
		elementListContainer.add(element);
		element.setData(elementData);
		element.postDisplay();
		sort(null, false);
		AEGWTJQueryPerfectScrollBar.updateScroll(NAME);
	}

	public void updateElement(AEMFTMetadataElementComposite elementData) {
		if (elementData != null) {
			
			String projectId = getElementController().getElementAsString(DSLAMBOIProject.PROJECT_ID, elementData);
			DSLAMBusDesktopProjectNavigatorElement elementWidget = getElementById(String.valueOf(projectId));
			elementWidget.setData(elementData);
			sort(null, false);
		}
	}
	
	public void removeElement(String projectId) {
		DSLAMBusDesktopProjectNavigatorElement element = getElementById(projectId);
		elementListContainer.remove(element);
	}
	
	public List<String> getModifiedProjectIds() {
		List<String> projectIds = new ArrayList<>();
		int itemCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < itemCount; i++) {
			DSLAMBusDesktopProjectNavigatorElement currentElementWidget = (DSLAMBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			if (currentElementWidget.isModified()) {
				projectIds.add(currentElementWidget.getId());
			}
		}
		return projectIds;
	}
	
	public void removeProjectSectionSelected() {
		List<DSLAMBusDesktopProjectNavigatorElement> projectElementList = getElementWidgetList();
		for (DSLAMBusDesktopProjectNavigatorElement projectElement : projectElementList) {
			projectElement.removeProjectSectionSelected();
		}
		
	}
	
	public void addExecution(String projectId, String executionDateStr) {
		List<DSLAMBusDesktopProjectNavigatorElement> projectNavigatorElementList = getElementWidgetList();
		for (DSLAMBusDesktopProjectNavigatorElement ProjectNavigatorElement : projectNavigatorElementList) {
			if (ProjectNavigatorElement.getId().equals(projectId)) {
				ProjectNavigatorElement.addExecution(projectId, executionDateStr);
			}
		}
	}
	
	public void addNodeList(String projectId, String nodeListName) {
		List<DSLAMBusDesktopProjectNavigatorElement> projectNavigatorElementList = getElementWidgetList();
		for (DSLAMBusDesktopProjectNavigatorElement ProjectNavigatorElement : projectNavigatorElementList) {
			if (ProjectNavigatorElement.getId().equals(projectId)) {
				ProjectNavigatorElement.addNodeList(projectId, nodeListName);
			}
		}
	}
	
	public void hideAddNodeListForm(String projectId) {
		List<DSLAMBusDesktopProjectNavigatorElement> projectNavigatorElements = getElementWidgetList();
		for (DSLAMBusDesktopProjectNavigatorElement projectNavigatorElement : projectNavigatorElements) {
			if (projectId.equals(projectNavigatorElement.getId())) {
				projectNavigatorElement.hideAddNodeListForm();
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
				if (!CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA.equals(elementData.getKey()) && !DSLAMBUIProjectBusinessServiceConstants.LIST_NODELIST_DATA.equals(elementData.getKey())) {
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
		List<DSLAMBusDesktopProjectNavigatorElement> widgetList = getElementWidgetList();
		if (widgetList != null && widgetList.size() > 0) {
			Collections.sort(widgetList, getComparator(null, false));
			elementListContainer.clear();
			for (DSLAMBusDesktopProjectNavigatorElement item : widgetList) {
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
				DSLAMBusDesktopProjectNavigatorElement o1Element = (DSLAMBusDesktopProjectNavigatorElement) o1;
				DSLAMBusDesktopProjectNavigatorElement o2Element = (DSLAMBusDesktopProjectNavigatorElement) o2;
				return o1Element.getElementName().compareTo(o2Element.getElementName());
			}
		};
	}
	
	/**
	 * PROTECTED
	 */
	
	protected DSLAMBusDesktopProjectNavigatorElement createElement(String projectId, String name) {
		return new DSLAMBusDesktopProjectNavigatorElement(projectId, name);
	}
	
	/**
	 * PRIVATE
	 */
	private List<DSLAMBusDesktopProjectNavigatorElement> getElementWidgetList() {
		List<DSLAMBusDesktopProjectNavigatorElement> widgetList = new ArrayList<>();
		for (int i = 0; i < elementListContainer.getWidgetCount(); i++) {
			DSLAMBusDesktopProjectNavigatorElement elementWidget = (DSLAMBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			widgetList.add(elementWidget);
		}
		return widgetList;
	}
	
	private DSLAMBusDesktopProjectNavigatorElement getElementById(String elementId) {
		DSLAMBusDesktopProjectNavigatorElement elementWidget = null;
		int itemCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < itemCount; i++) {
			DSLAMBusDesktopProjectNavigatorElement currentElementWidget = (DSLAMBusDesktopProjectNavigatorElement) elementListContainer.getWidget(i);
			if (elementId.equals(currentElementWidget.getId())) {
				elementWidget = currentElementWidget;
				break;
			}
		}
		return elementWidget;
	}
}

