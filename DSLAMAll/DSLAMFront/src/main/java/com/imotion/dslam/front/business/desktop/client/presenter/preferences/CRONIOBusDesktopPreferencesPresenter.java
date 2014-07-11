package com.imotion.dslam.front.business.desktop.client.presenter.preferences;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenter;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesPresenter extends CRONIOBusPreferencesBasePresenter<CRONIOBusDesktopPreferencesDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "CRONIOBusDesktopPreferencesPresenter";

	public CRONIOBusDesktopPreferencesPresenter(CRONIOBusDesktopPreferencesDisplay view) {
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
// 		String			srcWidget		= evt.getSourceWidget();
//		LOGICAL_TYPE	type			= evt.getEventType();
//
//		if (DSLAMBusDesktopProcessConfigureVariables.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
//				evt.stopPropagation();
//				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA);
//				updateFinalSectionInContext(finalSectionData);
//			}	
//		} else if (DSLAMBusDesktopProcessConfigureSchedule.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
//				evt.stopPropagation();
//				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA);
//				updateFinalSectionInContext(finalSectionData);
//			}	
//		} else if (DSLAMBusDesktopProcessConfigureExtraOptions.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
//				evt.stopPropagation();
//				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_EXTRA_OPTIONS);
//				updateFinalSectionInContext(finalSectionData);
//			}	
//		} else if (CRONIOBusDesktopHeaderListActions.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
//				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA);
//				updateFinalSectionInContext(finalSectionData);
//			}	
//		} else if (CRONIOBusDesktopProcessConfigureNodes.NAME.equals(srcWidget)) {
//			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
//				evt.stopPropagation();
//				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA);
//				updateFinalSectionInContext(finalSectionData);
//			}	
//		} 
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
	//	return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.OPEN_EVENT.equals(type);
		return false;
	}
	
	/**
	 * PROTECTED
	 */
	
//	@Override
//	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
//		getView().openProcessSection(projectFinalSectionId, finalSectionData);
//	}
	
//	@Override
//	protected String getSectionType() {
//		return SECTION_TYPE_CONNECTION;
//	}
	
	/**
	 * PRIVATE
	 */
}
