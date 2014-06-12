package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusDesktopProcessScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
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
		} else if (AEGWTBootstrapTreeMenuFinalItem.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				openProcessSection(evt);
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
	

	
	private void openProcessSection(AEGWTLogicalEvent evt) {
		String			processId	= evt.getSourceContainerId();
		String			sectionId	= evt.getSourceWidgetId();
		
		StringBuilder sb = new StringBuilder();
		sb.append(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST_PREFFIX);
		sb.append(processId);
		
		String processDataKey = sb.toString();
		
		AEMFTMetadataElementComposite processData = getContextDataController().getElementAsComposite(processDataKey);
		getView().openProcessSection(sectionId, processData);
	}

}
