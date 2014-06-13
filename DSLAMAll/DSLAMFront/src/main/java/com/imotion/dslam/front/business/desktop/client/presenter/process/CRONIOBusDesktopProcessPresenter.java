package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusDesktopProcessScreenView;
import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessPresenter extends DSLAMBusBasePresenter<CRONIOBusDesktopProcessDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "CRONIOBusDesktopProcessPresenter";

	public CRONIOBusDesktopProcessPresenter(CRONIOBusDesktopProcessDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		getAllProjects();
		String projectId = getAppController().getContextDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID);
		getAppController().getContextDataController().removeElement(CRONIOBOIProjectDataConstants.PROJECT_ID);
		String processId = getAppController().getContextDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_ID);
		getAppController().getContextDataController().removeElement(DSLAMBOIProcessDataConstants.PROCESS_ID);
		String sectionId = getAppController().getContextDataController().getElementAsString(CRONIOBOIProjectDataConstants.CURRENT_SECTION);
		getAppController().getContextDataController().removeElement(CRONIOBOIProjectDataConstants.CURRENT_SECTION);
		openProcessSection(projectId, processId, DSLAMBOIProject.PROJECT_PROCESS_VARIABLE_LIST);
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
		//String			sourceWidgetId	= evt.getSourceWidgetId();
		if (CRONIOBusDesktopProcessScreenView.NAME.equals(srcWidget)) {
			AEMFTMetadataElementComposite processData = (AEMFTMetadataElementComposite) evt.getElementAsComposite(CRONIOBOIProjectDataConstants.PROJECT_DATA);
			
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				updateProcess(processData);
			}
		} 
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.OPEN_EVENT.equals(type) 
				|| 
				LOGICAL_TYPE.SAVE_EVENT.equals(type); 			
	}
	
	/**
	 * PRIVATE
	 */

	private void updateProcess(AEMFTMetadataElementComposite processData) {
		getClientServerConnection().executeComm(processData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROCESS_UPDATE_PROCESS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite processData = getElementDataController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA, dataResult);
				getView().updateProcess(processData);
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getName(), getName());
				evt.setEventType(LOGICAL_TYPE.OK_EVENT);
				getLogicalEventHandlerManager().fireEvent(evt);	
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}
	

	
	private void openProcessSection(String projectId, String processId, String sectionId) {
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST_PREFFIX);
		sb.append(projectId);
		sb.append(AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR);
		sb.append(processId);
		
		String processDataKey = sb.toString();
		
		AEMFTMetadataElementComposite processData = getContextDataController().getElementAsComposite(processDataKey);
		getView().openProcessSection(sectionId, processData);
	}
	
	
	private void getAllProjects() {
		getClientServerConnection().executeComm(null, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_GET_ALL_PROJECTS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite projectListData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, dataResult);
				getView().setData(projectListData);
				getContextDataController().setElement(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, projectListData);
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}

}
