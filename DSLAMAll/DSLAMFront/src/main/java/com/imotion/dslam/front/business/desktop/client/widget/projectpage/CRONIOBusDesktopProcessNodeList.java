package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeDataConstants;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasComparator;
import com.selene.arch.exe.gwt.client.sort.AEGWTHasSort;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTComparator;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEvent;
import com.selene.arch.exe.gwt.mvp.event.sort.AEGWTSortEventTypes.SORT_TYPE;

public class CRONIOBusDesktopProcessNodeList extends AEGWTCompositePanel implements AEGWTHasSort, AEGWTHasComparator {
	
	public static final String NAME = "CRONIOBusDesktopProcessNodeList";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel elementListContainerZone;
	private FlowPanel elementListContainer;
	private CRONIOBusDesktopHeaderListFileActions header;

	private AEGWTComparator 					nameComparatorAsc;
	
	public CRONIOBusDesktopProcessNodeList() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(DSLAMBusDesktopIStyleConstants.NODE_LIST);
		
		header = new CRONIOBusDesktopHeaderListFileActions(TEXTS.node_list());
		root.add(header);
		
		//Container
		elementListContainerZone = new FlowPanel();
		root.add(elementListContainerZone);
		elementListContainerZone.addStyleName(DSLAMBusDesktopIStyleConstants.NODE_LIST_CONTAINER_ZONE);
		
		elementListContainer = new FlowPanel();
		elementListContainerZone.add(elementListContainer);
		elementListContainer.addStyleName(DSLAMBusDesktopIStyleConstants.NODE_LIST_CONTAINER);
		
		initComparators();
	}
	
	public void addElement(AEMFTMetadataElementComposite elementData) {
		String		nodeId			= getElementController().getElementAsString(CRONIOBOINodeDataConstants.NODE_ID	, elementData);
		String		nodeName		= getElementController().getElementAsString(CRONIOBOINodeDataConstants.NODE_NAME, elementData);
		String		machineType		= getElementController().getElementAsString(CRONIOBOINodeDataConstants.NODE_TYPE, elementData);
		AEMFTMetadataElementComposite machineTypesDataList = getElementController().getElementAsComposite(CRONIOBOIPreferencesDataConstants.PREFERENCES_MACHINE_PROPERTIES_LIST, elementData);
		boolean		machineExists	= getElementController().contains(machineType, machineTypesDataList);
		
		CRONIOBusDesktopProcessNodeListElement element = createElement(nodeId, nodeName, machineExists);
		elementListContainer.add(element);
		sort(null, false);
		AEGWTJQueryPerfectScrollBar.updateScroll(NAME);
	}

	public void updateElement(AEMFTMetadataElementComposite elementData) {
		if (elementData != null) {
			
			String nodeId = getElementController().getElementAsString(CRONIOBOINode.NODE_ID, elementData);
			CRONIOBusDesktopProcessNodeListElement elementWidget = getElementById(String.valueOf(nodeId));
			elementWidget.setData(elementData);
			sort(null, false);
		}
	}
	
	public void removeElement(String nodeId) {
		CRONIOBusDesktopProcessNodeListElement element = getElementById(nodeId);
		elementListContainer.remove(element);
	}
	
	public FlowPanel getElementListContainer() {
		return elementListContainer;
	}
	
	public void reset() {
		elementListContainer.clear();
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		header.postDisplay();
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
		List<CRONIOBusDesktopProcessNodeListElement> widgetList = getElementWidgetList();
		if (widgetList != null && widgetList.size() > 0) {
			Collections.sort(widgetList, getComparator(null, false));
			elementListContainer.clear();
			for (CRONIOBusDesktopProcessNodeListElement item : widgetList) {
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
				CRONIOBusDesktopProcessNodeListElement o1Element = (CRONIOBusDesktopProcessNodeListElement) o1;
				CRONIOBusDesktopProcessNodeListElement o2Element = (CRONIOBusDesktopProcessNodeListElement) o2;
				return o1Element.getElementName().compareTo(o2Element.getElementName());
			}
		};
	}
	
	/**
	 * PROTECTED
	 */
	
	protected CRONIOBusDesktopProcessNodeListElement createElement(String nodeId, String nodeName, boolean machineExist) {
		return new CRONIOBusDesktopProcessNodeListElement(nodeId, nodeName, this, machineExist);
	}
		 
	/**
	 * PRIVATE
	 */
	private List<CRONIOBusDesktopProcessNodeListElement> getElementWidgetList() {
		List<CRONIOBusDesktopProcessNodeListElement> widgetList = new ArrayList<>();
		for (int i = 0; i < elementListContainer.getWidgetCount(); i++) {
			CRONIOBusDesktopProcessNodeListElement elementWidget = (CRONIOBusDesktopProcessNodeListElement) elementListContainer.getWidget(i);
			widgetList.add(elementWidget);
		}
		return widgetList;
	}
	
	private CRONIOBusDesktopProcessNodeListElement getElementById(String elementId) {
		CRONIOBusDesktopProcessNodeListElement elementWidget = null;
		int itemCount = elementListContainer.getWidgetCount();
		for (int i = 0; i < itemCount; i++) {
			CRONIOBusDesktopProcessNodeListElement currentElementWidget = (CRONIOBusDesktopProcessNodeListElement) elementListContainer.getWidget(i);
			if (elementId.equals(currentElementWidget.getId())) {
				elementWidget = currentElementWidget;
				break;
			}
		}
		return elementWidget;
	}
}

