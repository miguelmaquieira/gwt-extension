package com.imotion.dslam.front.business.desktop.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEventTypes;

public abstract class CRONIOBusProjectBasePresenter<T extends AEGWTCompositePanelLoggedViewDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusDesktopHasProjectEventHandlers, CRONIOBusProjectBasePresenterConstants {
	
	public CRONIOBusProjectBasePresenter(T view) {
		super(view);
	}
	
	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PROJECTS_DATA, PROJECT_NAVIGATION_DATA};
	}
	
	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */

	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		String projectId		= evt.getProjectId();
		String mainSectionId	= evt.getMainSectionId();
		String finalSectionId	= evt.getFinalSectionId();
		
		String currentProjectId		= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
		String currentMainSectionId = getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_MAIN_SECTION_ID);
		
		boolean navigate 		= !mainSectionId.equals(currentMainSectionId);
		boolean	 projectChange	= !projectId.equals(currentProjectId);
		
		if (navigate) {
			AEMFTMetadataElementComposite navigationData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			navigationData.addElement(CURRENT_PROJECT_ID		, projectId);
			navigationData.addElement(CURRENT_MAIN_SECTION_ID	, mainSectionId);
			navigationData.addElement(CURRENT_FINAL_SECTION_ID	, finalSectionId);
			
			
			AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(getName(), getName());
			storageEvent.setFullKey(PROJECT_NAVIGATION_DATA);
			storageEvent.addElementAsDataValue(navigationData);
			storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
			getLogicalEventHandlerManager().fireEvent(storageEvent);
			
			AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(getName(), getName());
			flowEvent.setSourceWidgetId(mainSectionId);
			getFlowEventHandlerManager().fireEvent(flowEvent);
		} else {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(projectId);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(mainSectionId);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(finalSectionId);
			
			String finalSectionKey = sbKey.toString();
			
			AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(finalSectionKey);
			
			openFinalSection(projectChange, projectId, finalSectionId,finalSectionData);
		}
		
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return type.equals(EVENT_TYPE.OPEN_FINAL_SECTION_EVENT);
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
	}
	
	@Override
	protected void viewAdded() {
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
	}
	
	protected abstract void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData);
	
	
	protected void updateFinalSectionInContext(String projectId, String mainSectionId, String finalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		//TODO
	}
	
}
