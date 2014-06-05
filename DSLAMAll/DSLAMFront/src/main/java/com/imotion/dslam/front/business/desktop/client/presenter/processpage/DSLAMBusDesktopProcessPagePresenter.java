package com.imotion.dslam.front.business.desktop.client.presenter.processpage;

import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.processpage.DSLAMBusDesktopProcessPageScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessPagePresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopProcessPageDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessPagePresenter";

	public DSLAMBusDesktopProcessPagePresenter(DSLAMBusDesktopProcessPageDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		getAllProcesses();
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
		String			sourceWidgetId	= evt.getSourceWidgetId();
		if (DSLAMBusDesktopProcessPageScreenView.NAME.equals(srcWidget)) {
			AEMFTMetadataElementComposite processData = (AEMFTMetadataElementComposite) evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_DATA);
			
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				evt.stopPropagation();
				createProcess(processData);
			} else if (LOGICAL_TYPE.CHANGE_EVENT.equals(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				if (!processData.contains(DSLAMBOIProcessDataConstants.PROCESS_ID)) {
					processData.addElement(DSLAMBOIProcessDataConstants.PROCESS_ID	, sourceWidgetId);
				}
				updateProcess(processData);
			} else if (LOGICAL_TYPE.DELETE_EVENT.equals(type)) {
				evt.stopPropagation();
				deleteProcess(sourceWidgetId);
			}
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.NEW_EVENT.equals(type) 
				|| 
				LOGICAL_TYPE.SAVE_EVENT.equals(type) 
				|| 
				LOGICAL_TYPE.DELETE_EVENT.equals(type) 
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type);
	}
	
	/**
	 * PRIVATE
	 */
	
	private void getAllProcesses() {
		getClientServerConnection().executeComm(null, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROCESS_GET_ALL_PROCESSES_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite processListData = getElementDataController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA, dataResult);
				getView().setData(processListData);
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void createProcess(AEMFTMetadataElementComposite processData) {
		getClientServerConnection().executeComm(processData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROCESS_ADD_PROCESS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {
			
			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite processData = getElementDataController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA, dataResult);
				getView().addProcess(processData);
			}
			
			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
				
			}
		});
	}

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
	
	private void deleteProcess(String processId) {
		AEMFTMetadataElementComposite processData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		processData.addElement(DSLAMBOIProcess.PROCESS_ID, processId);
		getClientServerConnection().executeComm(processData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROCESS_REMOVE_PROCESS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite processData = getElementDataController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA, dataResult);
				if (processData != null) {
					Long	processId		= getElementDataController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID, processData);
					String	processIdStr	= String.valueOf(processId); 
					getView().removeProcess(processIdStr);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}

}
