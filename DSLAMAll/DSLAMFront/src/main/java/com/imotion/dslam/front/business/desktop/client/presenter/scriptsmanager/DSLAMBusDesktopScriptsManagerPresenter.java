package com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.scriptsmanager.DSLAMBusDesktopScriptsManagerScreenView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopScriptsManagerPresenter extends CRONIOBusProjectBasePresenter<DSLAMBusDesktopScriptsManagerDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopScriptsManagerPresenter";

	public DSLAMBusDesktopScriptsManagerPresenter(DSLAMBusDesktopScriptsManagerDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		loadFiles();
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
		if (DSLAMBusDesktopScriptsManagerScreenView.NAME.equals(srcWidget)) {
			AEMFTMetadataElementComposite fileData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				evt.stopPropagation();
				createFile(fileData);
			} else if (LOGICAL_TYPE.CHANGE_EVENT.equals(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				if (!fileData.contains(DSLAMBOIFileDataConstants.FILE_ID)) {
					fileData.addElement(DSLAMBOIFileDataConstants.FILE_ID	, sourceWidgetId);
				}
				updateFile(fileData);
			} else if (LOGICAL_TYPE.DELETE_EVENT.equals(type)) {
				evt.stopPropagation();
				deleteFile(sourceWidgetId);
			}
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type)
				||
				LOGICAL_TYPE.NEW_EVENT.equals(type)
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type)
				||
				LOGICAL_TYPE.DELETE_EVENT.equals(type);
	}

	/**
	 * PRIVATE
	 */

	private void loadFiles() {
		getClientServerConnection().executeComm(null, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_FILE_GET_ALL_FILES_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite fileListData = getElementDataController().getElementAsComposite(DSLAMBUIFileBusinessServiceConstants.FILE_DATA, dataResult);
				getView().setData(fileListData);
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void createFile(AEMFTMetadataElementComposite fileData) {
		getClientServerConnection().executeComm(fileData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_FILE_ADD_FILE_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {
			
			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite fileData = getElementDataController().getElementAsComposite(DSLAMBUIFileBusinessServiceConstants.FILE_DATA, dataResult);
				getView().addFile(fileData);
			}
			
			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void updateFile(AEMFTMetadataElementComposite fileData) {
		getClientServerConnection().executeComm(fileData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_FILE_UPDATE_FILE_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite fileData = getElementDataController().getElementAsComposite(DSLAMBUIFileBusinessServiceConstants.FILE_DATA, dataResult);
				getView().updateFile(fileData);
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void deleteFile(String fileId) {
		AEMFTMetadataElementComposite fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		fileData.addElement(DSLAMBOIFile.FILE_ID, fileId);
		getClientServerConnection().executeComm(fileData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_FILE_REMOVE_FILE_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite fileData = getElementDataController().getElementAsComposite(DSLAMBUIFileBusinessServiceConstants.FILE_DATA, dataResult);
				if (fileData != null) {
					Long	fileId		= getElementDataController().getElementAsLong(DSLAMBOIFile.FILE_ID, fileData);
					String	fileIdStr	= String.valueOf(fileId); 
					getView().removeFile(fileIdStr);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}
		});
	}

}
