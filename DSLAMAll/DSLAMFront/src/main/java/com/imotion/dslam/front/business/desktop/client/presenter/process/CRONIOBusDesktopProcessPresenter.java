package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderListActions;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureNodes;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureExtraOptions;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureSchedule;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureVariables;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
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

		if (DSLAMBusDesktopProcessConfigureVariables.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (DSLAMBusDesktopProcessConfigureSchedule.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (DSLAMBusDesktopProcessConfigureExtraOptions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_EXTRA_OPTIONS);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (CRONIOBusDesktopHeaderListActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (CRONIOBusDesktopProcessConfigureNodes.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_NODES_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} 
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.OPEN_EVENT.equals(type); 			
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		getView().openProcessSection(projectFinalSectionId, finalSectionData);
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_PROCESS;
	}
	
	/**
	 * PRIVATE
	 */
}
