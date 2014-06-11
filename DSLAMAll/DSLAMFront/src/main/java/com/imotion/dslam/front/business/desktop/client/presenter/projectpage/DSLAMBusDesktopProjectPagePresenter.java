package com.imotion.dslam.front.business.desktop.client.presenter.projectpage;

import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIProjectDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.projectpage.DSLAMBusDesktopProjectPageScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProjectPagePresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopProjectPageDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProjectPagePresenter";

	public DSLAMBusDesktopProjectPagePresenter(DSLAMBusDesktopProjectPageDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		getAllProjects();
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
		if (DSLAMBusDesktopProjectPageScreenView.NAME.equals(srcWidget)) {
			AEMFTMetadataElementComposite projectData = (AEMFTMetadataElementComposite) evt.getElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA);
			
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				evt.stopPropagation();
				createProject(projectData);
			} else if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				updateProject(projectData);
			} else if (LOGICAL_TYPE.DELETE_EVENT.equals(type)) {
				evt.stopPropagation();
				deleteProject(sourceWidgetId);
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
				;
	}
	
	/**
	 * PRIVATE
	 */
	
	private void getAllProjects() {
		getClientServerConnection().executeComm(null, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_GET_ALL_PROJECTS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite projectListData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, dataResult);
				getView().setData(projectListData);
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void createProject(AEMFTMetadataElementComposite projectData) {
		getClientServerConnection().executeComm(projectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_ADD_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {
			
			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite projectData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA, dataResult);
				getView().addProject(projectData);
			}
			
			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void updateProject(AEMFTMetadataElementComposite projectData) {
		getClientServerConnection().executeComm(projectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite projectData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA, dataResult);
				getView().updateProject(projectData);
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
	
	private void deleteProject(String projectId) {
		AEMFTMetadataElementComposite projectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		projectData.addElement(DSLAMBOIProject.PROJECT_ID, projectId);
		getClientServerConnection().executeComm(projectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_REMOVE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite projectData = getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA, dataResult);
				if (projectData != null) {
					Long	projectId		= getElementDataController().getElementAsLong(DSLAMBOIProject.PROJECT_ID, projectData);
					String	projectIdStr	= String.valueOf(projectId); 
					getView().removeProject(projectIdStr);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}

}
