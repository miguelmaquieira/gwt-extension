package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.process.CRONIOBusDesktopProcessScreenView;
import com.selene.arch.base.exe.bus.AEMFTIBusinessConstant;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessPresenter extends CRONIOBusProjectBasePresenter<CRONIOBusDesktopProcessDisplay> implements AEGWTHasLogicalEventHandlers {

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
		String			sectionId		= evt.getSourceWidgetId();
		String			projectId		= evt.getSourceContainerId();
		if (AEGWTBootstrapTreeMenuFinalItem.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				evt.stopPropagation();
				openProcessSection(projectId, sectionId);
			}
		} 
		
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
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		// TODO Auto-generated method stub
		
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
	
	private void openProcessSection(String projectId, String sectionId) {
		StringBuilder sb = new StringBuilder();
		sb.append(DSLAMBUIProjectBusinessServiceConstants.PROJECTS_DATA_PREFFIX);
		sb.append(projectId);
		sb.append(AEMFTIBusinessConstant.CTE_MFT_AE_BUS_SERVICE_ID_SEPARATOR);
		sb.append(CRONIOBOIProjectDataConstants.PROJECT_PROCESS);
		String processDataKey = sb.toString();
		
		AEMFTMetadataElementComposite processData = getContextDataController().getElementAsComposite(processDataKey);
		getView().openProcessSection(sectionId, processData);
	}
}
