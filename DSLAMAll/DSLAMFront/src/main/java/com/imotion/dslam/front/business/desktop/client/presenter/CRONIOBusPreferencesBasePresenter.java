package com.imotion.dslam.front.business.desktop.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.base.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopHasPreferencesEventHandlers;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopPreferencesLayout;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesMachineConfigureForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEventTypes;

public abstract class CRONIOBusPreferencesBasePresenter<T extends CRONIOBusPreferencesBaseDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusDesktopHasPreferencesEventHandlers, CRONIOBusPreferencesBasePresenterConstants {

	private CRONIOBusDesktopPreferencesLayout preferencesLayout;

	public CRONIOBusPreferencesBasePresenter(T view) {
		super(view);
	}
	
	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PREFERENCES_DATA, PREFERENCES_NAVIGATION_DATA};
	}
	
	/**
	 * CRONIOBusDesktopHasPreferencesEventHandlers
	 */
	
	@Override
	public void dispatchEvent(CRONIOBusDesktopPreferencesEvent evt) {
		EVENT_TYPE evtTyp = evt.getEventType();
		if (EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(evtTyp)) {
			String		mainSectionId		= evt.getMainSectionId();
			String 		finalSectionId		= evt.getFinalSectionId();
			String 		finalSectionKey 	= mainSectionId + "." + finalSectionId;
			String[] 	mainSectionIdSplit 	= mainSectionId.split("\\.");
			String 		sectionMenu 		= mainSectionIdSplit[0];
			boolean 	navigate 			= !getSectionType().equals(sectionMenu);

			updateNavigationData(sectionMenu, finalSectionKey);
			if (navigate) {
				AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(sectionMenu, getName());
				getFlowEventHandlerManager().fireEvent(flowEvent);
			} else {
				openFinalSection(mainSectionId, finalSectionId);
			}
		} else if (EVENT_TYPE.NEW_CONNECTION.equals(evtTyp)) {
			String	connectionName 	= evt.getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME);
			createConnection(connectionName);
		} else if (EVENT_TYPE.OPEN_PROJECTS_PAGE.equals(evtTyp)) {
			AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
			getFlowEventHandlerManager().fireEvent(flowEvent);
		} else if (EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(evtTyp)) {
			String srcWidget = evt.getSourceWidget();
			if(CRONIOBusDesktopPreferencesMachineConfigureForm.NAME.equals(srcWidget)) {
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(evt.getConnectionName());
				String machineConnection = evt.getConnectionName();
				updateFinalSectionInContext(finalSectionData, machineConnection, srcWidget);
			}
			
		} 
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.NEW_CONNECTION.equals(type)
				||
				EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(type)
				||
				EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(type)
				||
				EVENT_TYPE.OPEN_PROJECTS_PAGE.equals(type);
	}


	/**
	 * PROTECTED
	 */

	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PREFERENCES_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
		preferencesLayout = (CRONIOBusDesktopPreferencesLayout) layoutContainer.getCurrentLayout();
	}
	
	@Override
	protected void viewAdded() {
		super.viewAdded();
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasPreferencesEventHandlers.TYPE, this);
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasPreferencesEventHandlers.TYPE, getPreferencesLayout());

		String sectionKey = getContextDataController().getElementAsString(PREFERENCES_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID);
		AEMFTMetadataElementComposite preferencesData = getContextDataController().getElementAsComposite(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		openFinalSection(sectionKey, preferencesData);
	}
	
	protected abstract void openFinalSection(String machineFinalSectionId, AEMFTMetadataElementComposite finalSectionData);
	
	protected abstract String getSectionType();
	
	/**
	 * PRIVATE
	 */
	
	private void updateNavigationData(String sectionMenu, String finalSectionId) {
		AEMFTMetadataElementComposite navigationData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		navigationData.addElement(CURRENT_FINAL_SECTION_ID	, finalSectionId);

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(sectionMenu, getName());
		storageEvent.setFullKey(PREFERENCES_NAVIGATION_DATA);
		storageEvent.addElementAsDataValue(navigationData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

	}
	
	private void openFinalSection(String mainSectionId, String finalSectionId) {
		
		String finalSectionKey = mainSectionId + "." + finalSectionId;
		
		AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(finalSectionKey);
			if (finalSectionData != null) {
				finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
			}
			
			openFinalSection(finalSectionKey, finalSectionData);
	}
	
	private void createConnection(String connectionName) {
		AEMFTMetadataElementComposite newConnectionData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		newConnectionData.addElement(CRONIOBOIMachineProperties.MACHINE_NAME			, connectionName);
		

		getClientServerConnection().executeComm(newConnectionData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PREFERENCES_ADD_CONNECTION_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite connectionData = dataResult.getCompositeElement(CRONIOBUIPreferencesBusinessServiceConstants.CONNECTION_DATA);
					if (connectionData != null) {
						String connectionName = getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, connectionData);
						updateConnectionClientData(connectionName, connectionData, false);

						CRONIOBusDesktopPreferencesEvent connectionCreatedEvt = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
						connectionCreatedEvt.setEventType(EVENT_TYPE.CONNECTION_CREATED);
						connectionCreatedEvt.addElementAsDataValue(connectionData);
						getLogicalEventHandlerManager().fireEvent(connectionCreatedEvt);
					}
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void updateConnectionClientData(String connectionName, AEMFTMetadataElementComposite connectionData, boolean connectionSaved) {
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.CONNECTIONS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(connectionName);
		String connectionKey = sbKey.toString();

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PREFERENCES_PRESENTER, getName());
		storageEvent.setFullKey(connectionKey);
		storageEvent.addElementAsDataValue(connectionData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		connectionData = (AEMFTMetadataElementComposite) connectionData.cloneObject();
		getContextDataController().setElement(connectionKey, connectionData);
		if (connectionSaved) {
			fireConnectionSaved(connectionName);
		}
	}
	
	protected void updateFinalSectionInContext( AEMFTMetadataElementComposite finalSectionData, String machineConnection, String srcWidget) {
		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		
		if (CRONIOBusDesktopPreferencesMachineConfigureForm.NAME.equals(srcWidget)) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIOBOMachineProperties.MACHINE_CONNECTION_CONFIG);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(machineConnection);
			String sectionKey = sbKey.toString();
			
//			if (!DSLAMBOIProject.PROJECT_EXECUTION_LOG.equals(currentSectionId)) {
//			finalSectionData.addElement(DSLAMBOIProject.IS_MODIFIED, true);
//		}

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PREFERENCES_PRESENTER, getName());
		storageEvent.setFullKey(sectionKey);
		storageEvent.addElementAsComposite(sectionKey, finalSectionData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		getContextDataController().setElement(sectionKey, finalSectionData);
	//	fireSectionModified(currentProjectId, currentSectionId);
		}
	

	}
	
//	private void fireSectionModified(String projectId, String currentSectionId) {
//		if (!DSLAMBOIProject.PROJECT_EXECUTION_LOG.equals(currentSectionId)) {
//			CRONIOBusDesktopProjectEvent sectionModifiedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
//			sectionModifiedEvt.setEventType(EVENT_TYPE.SECTION_MODIFIED);
//			sectionModifiedEvt.setProjectId(projectId);
//			sectionModifiedEvt.setFinalSectionId(currentSectionId);
//			getLogicalEventHandlerManager().fireEvent(sectionModifiedEvt);
//		}	
//	}

	
	private void fireConnectionSaved(String connectionName) {
		CRONIOBusDesktopPreferencesEvent connectionSavedEvt = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
		connectionSavedEvt.setEventType(EVENT_TYPE.CONNECTION_SAVED);
		connectionSavedEvt.setConnectionName(connectionName);
		getLogicalEventHandlerManager().fireEvent(connectionSavedEvt);
	}
	
	private CRONIOBusDesktopPreferencesLayout getPreferencesLayout() {
		return preferencesLayout;
	}
	
}