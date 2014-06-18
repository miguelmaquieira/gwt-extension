package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.IsWidget;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessNodeListElement extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopProcessNodeListElement";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTICompositePanel 	parentWidget;
	private UListElement			ulElement;
	private LIElement				liElement;
	private AnchorElement 			anchorElement;

	public CRONIOBusDesktopProcessNodeListElement(final String nodeId, String nodeName, AEGWTCompositePanel parentWidget) {
		ulElement = Document.get().createULElement();
		setElement(ulElement);
		
		this.parentWidget = parentWidget;
		
		liElement = Document.get().createLIElement();
		ulElement.appendChild(liElement);
		
		anchorElement = Document.get().createAnchorElement();
		anchorElement.setInnerText(nodeName);
		anchorElement.setId(nodeId);
		liElement.appendChild(anchorElement);
		
		DOM.sinkEvents(anchorElement, Event.ONCLICK);
		DOM.setEventListener(anchorElement, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				AEGWTLogicalEvent openEvent = new AEGWTLogicalEvent(getWindowName(), getName());
				openEvent.setEventType(LOGICAL_TYPE.OPEN_EVENT);
				//openEvent.setSourceContainerId(getContainerId());
				openEvent.setSourceWidgetId(nodeId);
				getLogicalEventHandlerManager().fireEvent(openEvent);
			}
		});
		
		LIElement liElementDivider = Document.get().createLIElement();
		liElementDivider.addClassName(AEGWTIBoostrapConstants.NAV_DIVIDER);
		ulElement.appendChild(liElementDivider);
	}
	
	public String getElementName() {
		return anchorElement.getInnerText();
	}

	/****************************************************************************
	 *                        AEGWTICompositePanel
	 ****************************************************************************/
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public AEGWTICompositePanel getParentWidget(IsWidget child) {
		return parentWidget;
	}
	
}

