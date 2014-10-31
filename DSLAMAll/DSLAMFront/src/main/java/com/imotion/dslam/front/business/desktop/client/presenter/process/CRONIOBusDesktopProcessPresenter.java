package com.imotion.dslam.front.business.desktop.client.presenter.process;

import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureExtraOptions;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureNodes;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureSchedule;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureVariables;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessPresenter extends CRONIOBusProjectBasePresenter<CRONIOBusDesktopProcessDisplay> implements AEGWTHasLogicalEventHandlers, CRONIOBusDesktopHasProjectEventHandlers {

	public static final String NAME = "CRONIOBusDesktopProcessPresenter";

	public CRONIOBusDesktopProcessPresenter(CRONIOBusDesktopProcessDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		//getLogicalEventHandlerManager().addLogicalEventHandler(this);
		
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
		super.dispatchEvent(evt);
		
 		String			srcWidget		= evt.getSourceWidget();
		LOGICAL_TYPE	type			= evt.getEventType();

		if (CRONIOBusDesktopProcessConfigureVariables.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_VARIABLES_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (CRONIOBusDesktopProcessConfigureSchedule.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_SCHEDULE_DATA);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (CRONIOBusDesktopProcessConfigureExtraOptions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_EXTRA_OPTIONS);
				updateFinalSectionInContext(finalSectionData);
			}	
		} else if (CRONIOBusDesktopProcessConfigureNodes.NAME.equals(srcWidget) && LOGICAL_TYPE.UPDATE_EVENT.equals(type)) {
			AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(CRONIOBOINodeList.NODELIST_DATA);
			updateFinalSectionInContext(finalSectionData);	
		} else if (CRONIOBusDesktopProcessConfigureNodes.NAME.equals(srcWidget) && LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
			evt.stopPropagation();
			AEMFTMetadataElementComposite finalSectionData = evt.getElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_NODELIST_DATA);
			updateFinalSectionInContext(finalSectionData);
		} 
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return super.isDispatchEventType(type) || LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.UPDATE_EVENT.equals(type); 			
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		if (projectFinalSectionId.contains(CRONIOBOIProject.PROJECT_PROCESS_NODE_LISTS)) {
			finalSectionData.addElement(CRONIOBOIPreferencesDataConstants.PREFERENCES_MACHINE_PROPERTIES_LIST, getMachinesFromPreferences());
		}
		getView().openProcessSection(projectFinalSectionId, finalSectionData);
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_PROCESS;
	}
	
	/**
	 * PRIVATE
	 */
	
	private AEMFTMetadataElementComposite getMachinesFromPreferences() {
		AEMFTMetadataElementComposite machinesData = getContextDataController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_DATA_MACHINE_PROPERTIES_LIST);
		return machinesData;
	}
	
}
