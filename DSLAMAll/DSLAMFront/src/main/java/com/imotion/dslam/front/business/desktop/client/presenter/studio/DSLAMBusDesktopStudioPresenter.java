package com.imotion.dslam.front.business.desktop.client.presenter.studio;

import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIFileBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.view.studio.DSLAMBusDesktopStudioScreenView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopNewScriptPopupForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopStudioPresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopStudioDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopStudioPresenter";

	public DSLAMBusDesktopStudioPresenter(DSLAMBusDesktopStudioDisplay view) {
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
		String srcWidget = evt.getSourceWidget();
		if (DSLAMBusDesktopNewScriptPopupForm.NAME.equals(srcWidget)) {
			String filename		= evt.getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME);
			String contentType	= evt.getElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE);
			
			AEMFTMetadataElementComposite fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			fileData.addElement(DSLAMBOIFileDataConstants.FILE_NAME		, filename);
			fileData.addElement(DSLAMBOIFileDataConstants.CONTENT_TYPE	, contentType);
			
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
		} else if (DSLAMBusDesktopStudioScreenView.NAME.equals(srcWidget)) {
			AEMFTMetadataElementComposite fileData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
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
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type);
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

}
