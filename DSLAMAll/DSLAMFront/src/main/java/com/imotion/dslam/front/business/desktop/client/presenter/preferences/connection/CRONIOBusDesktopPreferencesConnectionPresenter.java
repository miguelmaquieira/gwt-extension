package com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.business.service.base.CRONIOBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasPreferencesEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenterConstants;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopPreferencesMachineConfigureForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesConnectionPresenter extends CRONIOBusPreferencesBasePresenter<CRONIOBusDesktopPreferencesConnectionDisplay> implements AEGWTHasLogicalEventHandlers, CRONIOBusDesktopHasPreferencesEventHandlers {

	public static final String NAME = "CRONIOBusDesktopPreferencesConnectionPresenter";

	public CRONIOBusDesktopPreferencesConnectionPresenter(CRONIOBusDesktopPreferencesConnectionDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		LOGICAL_TYPE	type			= evt.getEventType();
		if (CRONIOBusDesktopPreferencesMachineConfigureForm.NAME.equals(srcWidget) && LOGICAL_TYPE.SUBMIT_EVENT.equals(type)) {
			evt.stopPropagation();
			saveCurrentMachineConfigureInDB();
		} 
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SUBMIT_EVENT.equals(type);
	}
	
	/**
	 * CRONIOBusDesktopHasPreferencesEventHandlers
	 */
	@Override
	public void dispatchEvent(CRONIOBusDesktopPreferencesEvent evt) {
		super.dispatchEvent(evt);
		String srcWindow 	= evt.getSourceWindow();
		EVENT_TYPE type		= evt.getEventType();
		if (CRONIOBusPreferencesBasePresenterConstants.PREFERENCES_PRESENTER.equals(srcWindow)) {
			String srcWidget	= evt.getSourceWidget();
			String sectionPath	= evt.getFinalSectionPath();
			String[] 	finalSectionPathSplit 	= sectionPath.split("\\.");
			String		section 				= finalSectionPathSplit[0];
			
			AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(SECTION_DATA);
			
			if (CRONIOBusPreferencesBasePresenter.NAME.equals(srcWidget) && EVENT_TYPE.SHOW_PREFERENCES_INFO.equals(type) && CRONIOBOIPreferences.PREFERENCES_DATA_MACHINE_PROPERTIES_LIST.equals(section)) {

				String 		finalSectionName 		= finalSectionPathSplit[finalSectionPathSplit.length-1];
				String		machineName				= finalSectionPathSplit[1];
				boolean 	sectionIsModified 		= getElementDataController().getElementAsBoolean(CRONIOBOIPreferences.INFO_IS_MODIFIED, finalSectionData);

				CRONIOBusDesktopPreferencesEvent showInfoEvent = new CRONIOBusDesktopPreferencesEvent(PREFERENCES_PRESENTER, getName());
				showInfoEvent.addElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME	, machineName);
				showInfoEvent.addElementAsBoolean(CRONIOBOIPreferences.INFO_IS_MODIFIED	, sectionIsModified);
				showInfoEvent.setFinalSectionId(finalSectionName);
				showInfoEvent.setEventType(EVENT_TYPE.SHOW_PREFERENCES_INFO);
				getLogicalEventHandlerManager().fireEvent(showInfoEvent);
			} 
		}
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return super.isDispatchEventType(type) || EVENT_TYPE.SHOW_PREFERENCES_INFO.equals(type);
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(String machineFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		getView().openMachinesSection(machineFinalSectionId, finalSectionData);
	}
	
	@Override
	protected String getSectionType() {
		return CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST;
	}
	
	/**
	 * PRIVATE
	 */
	
	private void saveCurrentMachineConfigureInDB() {
		AEMFTMetadataElementComposite navigationData = getContextDataController().getElementAsComposite(PREFERENCES_NAVIGATION_DATA);
		String currentSectionId = getElementDataController().getElementAsString(CURRENT_FINAL_SECTION_ID, navigationData);
		String[] currentSectionIdSplit = currentSectionId.split("\\.");
		String machine = currentSectionIdSplit[1];
		
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PREFERENCES_DATA);
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(machine);
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(CRONIOBOIMachineProperties.MACHINE_CONNECTION_CONFIG);
		String machineConfigureDataKey = sbKey.toString();
		
		AEMFTMetadataElementComposite machineConfigureData = getContextDataController().getElementAsComposite(machineConfigureDataKey);
		getClientServerConnection().executeComm(machineConfigureData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PREFERENCES_UPDATE_MACHINE_CONFIG_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
//				if (dataResult != null) {
//					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA);
//					if (projectData != null) {
//						//updateProjectClientData(currentProjectId, projectData, true);
//					}
//				}

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * PRIVATE
	 */
	
}
