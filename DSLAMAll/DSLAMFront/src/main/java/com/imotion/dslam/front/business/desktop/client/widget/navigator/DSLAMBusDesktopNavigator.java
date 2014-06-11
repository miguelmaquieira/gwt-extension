package com.imotion.dslam.front.business.desktop.client.widget.navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIProjectDataConstants;
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

public class DSLAMBusDesktopNavigator extends AEGWTCompositePanel implements AEGWTHasSort, AEGWTHasComparator {

	public static final String NAME = "DSLAMBusDesktopNavigator";
	
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel elementListContainerZone;
	private FlowPanel elementListContainer;
	

	private AEGWTComparator nameComparatorAsc;

	public DSLAMBusDesktopNavigator() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(DSLAMBusDesktopIStyleConstants.LIST);
		
		//Header
		FlowPanel headerZone = new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.LIST_HEADER);
		
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
	
	public void addElement(AEMFTMetadataElementComposite elementData) {
		
		String projectName = getElementController().getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_NAME, elementData);
		
		DSLAMBusDesktopNavigatorElement element = createElement(projectName);
		elementListContainer.add(element);
		element.setData(elementData);
		sort(null, false);
	}

	public void updateElement(AEMFTMetadataElementComposite elementData) {
		if (elementData != null) {
			Long elementId = getItemIdAsLong(elementData);
			DSLAMBusDesktopNavigatorElement elementWidget = getElementById(String.valueOf(elementId));
			elementWidget.setData(elementData);
			sort(null, false);
		}
	}
	
	public AEMFTMetadataElementComposite getElementData(String elementId) {
		AEMFTMetadataElementComposite elementData = null;
		DSLAMBusDesktopNavigatorElement elementWidget = getElementById(elementId);
		if (elementWidget != null) {
			elementData = elementWidget.getData();
		}
		return elementData;
	}
	
	public AEMFTMetadataElementComposite getElementDataByName(String elementName) {
		AEMFTMetadataElementComposite elementData = null;
		int elementCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < elementCount; i++) {
			DSLAMBusDesktopNavigatorElement elementWidget = (DSLAMBusDesktopNavigatorElement) elementListContainer.getWidget(i);
			elementData = elementWidget.getData();
			String currentItemName = getItemNameFromData(elementData);
			if (elementName.equals(currentItemName)) {
				break;
			} else {
				elementData = null;	
			}
		}
		return elementData;
	}
	
	public FlowPanel getElementListContainer() {
		return elementListContainer;
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
				addElement((AEMFTMetadataElementComposite) elementData);
			}
		}
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		setHeightToDecrease(80);
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
		List<DSLAMBusDesktopNavigatorElement> widgetList = getElementWidgetList();
		if (widgetList != null && widgetList.size() > 0) {
			Collections.sort(widgetList, getComparator(null, false));
			elementListContainer.clear();
			for (DSLAMBusDesktopNavigatorElement item : widgetList) {
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
				DSLAMBusDesktopNavigatorElement o1Element = (DSLAMBusDesktopNavigatorElement) o1;
				DSLAMBusDesktopNavigatorElement o2Element = (DSLAMBusDesktopNavigatorElement) o2;
				return o1Element.getElementName().compareTo(o2Element.getElementName());
			}
		};
	}
	
	/**
	 * PROTECTED
	 */
	
	
	protected DSLAMBusDesktopNavigatorElement createElement(String name) {
		return new DSLAMBusDesktopNavigatorElement(name);
	}
	
	
	protected String getItemNameFromData(AEMFTMetadataElementComposite elementData) {
		String projectName = getElementController().getElementAsString(DSLAMBOIProject.PROJECT_NAME, elementData);
		return projectName;
	}

	
	protected Long getItemIdAsLong(AEMFTMetadataElementComposite elementData) {
		Long projectId = getElementController().getElementAsLong(DSLAMBOIProject.PROJECT_ID, elementData);;
		return projectId;
	}
	

	
	/**
	 * PRIVATE
	 */
	private List<DSLAMBusDesktopNavigatorElement> getElementWidgetList() {
		List<DSLAMBusDesktopNavigatorElement> widgetList = new ArrayList<>();
		for (int i = 0; i < elementListContainer.getWidgetCount(); i++) {
			DSLAMBusDesktopNavigatorElement elementWidget = (DSLAMBusDesktopNavigatorElement) elementListContainer.getWidget(i);
			widgetList.add(elementWidget);
		}
		return widgetList;
	}
	
	private DSLAMBusDesktopNavigatorElement getElementById(String elementId) {
		DSLAMBusDesktopNavigatorElement elementWidget = null;
		int fileCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < fileCount; i++) {
			DSLAMBusDesktopNavigatorElement currentElementWidget = (DSLAMBusDesktopNavigatorElement) elementListContainer.getWidget(i);
			if (elementId.equals(currentElementWidget.getId())) {
				elementWidget = currentElementWidget;
				break;
			}
		}
		return elementWidget;
	}

	public void removeElement(String fileId) {
		DSLAMBusDesktopNavigatorElement element = getElementById(fileId);
		elementListContainer.remove(element);
	}

}
