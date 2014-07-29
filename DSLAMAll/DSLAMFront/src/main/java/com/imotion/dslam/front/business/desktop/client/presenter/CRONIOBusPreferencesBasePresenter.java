package com.imotion.dslam.front.business.desktop.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.base.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasPreferencesEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopPreferencesLayout;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesMachineConfigureForm;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesMachineSectionsDeckPanel;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesMachineVariables;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesUserConfigureForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEventTypes;

public abstract class CRONIOBusPreferencesBasePresenter<T extends CRONIOBusPreferencesBaseDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusDesktopHasPreferencesEventHandlers, CRONIOBusPreferencesBasePresenterConstants {

	public static final String NAME = "CRONIOBusPreferencesBasePresenter";
	
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
			String 		mainSectionId 		= evt.getMainSectionId();
			String 		finalSectionPath 	= evt.getFinalSectionPath();
			boolean 	navigate 			= !getSectionType().equals(mainSectionId);

			updateNavigationData(mainSectionId, finalSectionPath);
			if (navigate) {
				
				String							finalSectionDataKey = CRONIODesktopIAppControllerConstants.PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + finalSectionPath;
				AEMFTMetadataElementComposite	finalSectionData	= getContextDataController().getElementAsComposite(finalSectionDataKey);
				if (finalSectionData != null) {
					finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
				}

				//SHOW HEADER INFO
				CRONIOBusDesktopPreferencesEvent showInfoEvent = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
				showInfoEvent.setFinalSectionPath(finalSectionPath);
				showInfoEvent.addElementAsComposite(SECTION_DATA, finalSectionData);
				showInfoEvent.setEventType(EVENT_TYPE.SHOW_PREFERENCES_INFO);
				getLogicalEventHandlerManager().fireEvent(showInfoEvent);
				
				AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(mainSectionId, getName());
				getFlowEventHandlerManager().fireEvent(flowEvent);
			} else {
				openFinalSection(finalSectionPath);
			}
		} else if (EVENT_TYPE.NEW_CONNECTION.equals(evtTyp)) {
			String	connectionName 	= evt.getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME);
			createConnection(connectionName);
		} else if (EVENT_TYPE.SAVE_PREFERENCES.equals(evtTyp)) {
			saveCurrentPreferencesInDB();
		} else if (EVENT_TYPE.OPEN_PROJECTS_PAGE.equals(evtTyp)) {
			AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
			getFlowEventHandlerManager().fireEvent(flowEvent);
		} else if (EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(evtTyp)) {
			String srcWidget = evt.getSourceWidget();
			if(CRONIOBusDesktopPreferencesMachineConfigureForm.NAME.equals(srcWidget)) {
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(evt.getConnectionName());
				String machineConnection = evt.getConnectionName();
				updateFinalSectionInContext(finalSectionData, machineConnection, srcWidget);
			} else if(CRONIOBusDesktopPreferencesMachineVariables.NAME.equals(srcWidget) || CRONIOBusDesktopPreferencesMachineSectionsDeckPanel.NAME.equals(srcWidget)) {
				AEMFTMetadataElementComposite navigationData = getContextDataController().getElementAsComposite(PREFERENCES_NAVIGATION_DATA);
				String currentSectionId = getElementDataController().getElementAsString(CURRENT_FINAL_SECTION_ID, navigationData);
				String[] currentSectionIdSplit = currentSectionId.split("\\.");
				String machine = currentSectionIdSplit[1];

				AEMFTMetadataElementComposite finalSectionData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite(); 

				if(CRONIOBusDesktopPreferencesMachineVariables.NAME.equals(srcWidget)) {
					finalSectionData = evt.getElementAsComposite(CRONIOBOIMachineProperties.MACHINE_VARIABLES);
				} else if (CRONIOBusDesktopPreferencesMachineSectionsDeckPanel.NAME.equals(srcWidget)) {
					finalSectionData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
					finalSectionData.setKey(currentSectionIdSplit[2]);
				} 

				updateFinalSectionInContext(finalSectionData, machine, srcWidget);
			} else if (CRONIOBusDesktopPreferencesUserConfigureForm.NAME.equals(srcWidget)) {
				AEMFTMetadataElementComposite finalSectionData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
				finalSectionData = evt.getElementAsComposite(CRONIOBOIUserPreferences.DOWNTIME);
				updateFinalSectionInContext(finalSectionData, srcWidget);
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
				EVENT_TYPE.OPEN_PROJECTS_PAGE.equals(type)
				||
				EVENT_TYPE.SAVE_PREFERENCES.equals(type);
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
		AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA_PREFFIX + sectionKey);
		openFinalSection(sectionKey, finalSectionData);
	}

	protected abstract void openFinalSection(String machineFinalSectionId, AEMFTMetadataElementComposite finalSectionData);

	protected abstract String getSectionType();

	/**
	 * PRIVATE
	 */

	private void updateNavigationData(String sectionMenu, String finalSectionId) {
		AEMFTMetadataElementComposite navigationData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		navigationData.addElement(CURRENT_FINAL_SECTION_ID	, finalSectionId);

		getContextDataController().setElement(PREFERENCES_NAVIGATION_DATA, navigationData.cloneObject());

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(sectionMenu, getName());
		storageEvent.setFullKey(PREFERENCES_NAVIGATION_DATA);
		storageEvent.addElementAsDataValue(navigationData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);
	}

	private void openFinalSection(String finalSectionPath) {
		String							finalSectionDataKey = CRONIODesktopIAppControllerConstants.PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + finalSectionPath;
		AEMFTMetadataElementComposite	finalSectionData	= getContextDataController().getElementAsComposite(finalSectionDataKey);
		if (finalSectionData != null) {
			finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		}

		//SHOW HEADER INFO
		CRONIOBusDesktopPreferencesEvent showInfoEvent = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
		showInfoEvent.setFinalSectionPath(finalSectionPath);
		showInfoEvent.addElementAsComposite(SECTION_DATA, finalSectionData);
		showInfoEvent.setEventType(EVENT_TYPE.SHOW_PREFERENCES_INFO);
		getLogicalEventHandlerManager().fireEvent(showInfoEvent);

		//SHOW CONTENT
		openFinalSection(finalSectionDataKey, finalSectionData);
	}

	private void createConnection(String connectionName) {
		AEMFTMetadataElementComposite newConnectionData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		
		String preferecesIdkey = CRONIOBOIPreferencesDataConstants.PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + CRONIOBOIPreferencesDataConstants.PREFERENCES_ID;
		long preferencesId = getContextDataController().getElementAsLong(preferecesIdkey);
		
		newConnectionData.addElement(CRONIOBOIMachineProperties.MACHINE_NAME		, connectionName);
		newConnectionData.addElement(CRONIOBOIPreferences.PREFERENCES_ID			, preferencesId);

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
		sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
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
	}

	private void updatePreferencesClientData(String preferencesId, AEMFTMetadataElementComposite preferencesData, boolean preferencesSaved) {
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		String preferencesKey = sbKey.toString();

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PREFERENCES_PRESENTER, getName());
		storageEvent.setFullKey(preferencesKey);
		storageEvent.addElementAsDataValue(preferencesData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		preferencesData = (AEMFTMetadataElementComposite) preferencesData.cloneObject();
		getContextDataController().setElement(preferencesKey, preferencesData);
		if (preferencesSaved) {
			firePreferencesSaved(preferencesId);
		}
	}

	private void saveCurrentPreferencesInDB() {
		AEMFTMetadataElementComposite preferencesData = getContextDataController().getElementAsComposite(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		getClientServerConnection().executeComm(preferencesData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PREFERENCES_UPDATE_PREFERENCES_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite preferencesData = dataResult.getCompositeElement(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
					if (preferencesData != null) {
						StringBuilder preferencesIdKey = new StringBuilder();
						preferencesIdKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
						preferencesIdKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
						preferencesIdKey.append(CRONIOBOIPreferences.PREFERENCES_ID);
						String preferencesKey = preferencesIdKey.toString();
						long preferencesId = getContextDataController().getElementAsLong(preferencesKey);
						updatePreferencesClientData(String.valueOf(preferencesId), preferencesData, true);
					}
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}

	protected void updateFinalSectionInContext( AEMFTMetadataElementComposite finalSectionData, String machineConnection, String srcWidget) {
		String finalsectionDataKey = finalSectionData.getKey();
		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();

		String preferencesIdKey = CRONIODesktopIAppControllerConstants.PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + CRONIOBOIPreferences.PREFERENCES_ID;
		long preferencesId = getContextDataController().getElementAsLong(preferencesIdKey);

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(machineConnection);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		if (CRONIOBusDesktopPreferencesMachineConfigureForm.NAME.equals(srcWidget)) {
			sbKey.append(CRONIOBOMachineProperties.MACHINE_CONNECTION_CONFIG);
		} else if (CRONIOBusDesktopPreferencesMachineVariables.NAME.equals(srcWidget)) {
			sbKey.append(CRONIOBOMachineProperties.MACHINE_VARIABLES);
		} else if (CRONIOBusDesktopPreferencesMachineSectionsDeckPanel.NAME.equals(srcWidget)) {
			sbKey.append(finalsectionDataKey);
		}

		String sectionKey = sbKey.toString();

		getElementDataController().setElement(CRONIOBOIPreferences.INFO_IS_MODIFIED, finalSectionData, true);

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PREFERENCES_PRESENTER, getName());
		storageEvent.setFullKey(sectionKey);
		storageEvent.addElementAsDataValue(finalSectionData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		getContextDataController().setElement(sectionKey, finalSectionData);

		fireSectionModified(String.valueOf(preferencesId), sectionKey);
	}
	
	protected void updateFinalSectionInContext( AEMFTMetadataElementComposite finalSectionData, String srcWidget) {
		//String finalsectionDataKey = finalSectionData.getKey();
		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();

		String preferencesIdKey = CRONIODesktopIAppControllerConstants.PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + CRONIOBOIPreferences.PREFERENCES_ID;
		long preferencesId = getContextDataController().getElementAsLong(preferencesIdKey);
		
		if (CRONIOBusDesktopPreferencesUserConfigureForm.NAME.equals(srcWidget)) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIOBOIPreferences.USER_CONFIG);
			String sectionKey = sbKey.toString();
			
			getElementDataController().setElement(CRONIOBOIPreferences.INFO_IS_MODIFIED, finalSectionData, true);
		
			AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PREFERENCES_PRESENTER, getName());
			storageEvent.setFullKey(sectionKey);
			storageEvent.addElementAsDataValue(finalSectionData);
			storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
			getLogicalEventHandlerManager().fireEvent(storageEvent);
	
			finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
			getContextDataController().setElement(sectionKey, finalSectionData);
	
			fireSectionModified(String.valueOf(preferencesId), sectionKey);
		}
	}

	private void fireSectionModified(String preferencesId, String sectionKey) {
		CRONIOBusDesktopPreferencesEvent sectionModifiedEvt = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
		sectionModifiedEvt.setEventType(EVENT_TYPE.SECTION_MODIFIED);
		sectionModifiedEvt.setPreferencesId(preferencesId);
		sectionModifiedEvt.setFinalSectionPath(sectionKey);
		getLogicalEventHandlerManager().fireEvent(sectionModifiedEvt);

	}


	private void firePreferencesSaved(String preferencesId) {
		CRONIOBusDesktopPreferencesEvent preferencesSavedEvt = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
		preferencesSavedEvt.setEventType(EVENT_TYPE.PREFERENCES_SAVED);
		preferencesSavedEvt.setPreferencesId(preferencesId);
		getLogicalEventHandlerManager().fireEvent(preferencesSavedEvt);
	}

	private CRONIOBusDesktopPreferencesLayout getPreferencesLayout() {
		return preferencesLayout;
	}

}
