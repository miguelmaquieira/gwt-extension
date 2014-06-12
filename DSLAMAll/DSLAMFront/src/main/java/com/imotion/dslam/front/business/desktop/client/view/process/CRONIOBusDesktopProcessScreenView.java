package com.imotion.dslam.front.business.desktop.client.view.process;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.process.CRONIOBusDesktopProcessDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessSectionsDeckPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProcessScreenView extends DSLAMBusDesktopPanelBaseView implements CRONIOBusDesktopProcessDisplay, AEGWTHasLogicalEventHandlers {

	public		static final String				NAME = "CRONIOBusDesktopProcessScreenView";
	private	 	static final DSLAMBusI18NTexts 	TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 									root;
	private CRONIOBusDesktopProcessSectionsDeckPanel	processSectionsDeckPanel;
	
	
	public CRONIOBusDesktopProcessScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_VIEW);

		processSectionsDeckPanel = new CRONIOBusDesktopProcessSectionsDeckPanel();
		root.add(processSectionsDeckPanel);
		processSectionsDeckPanel.setVisibility(Visibility.HIDDEN);
		
	}
	
	@Override
	public void updateProcess(AEMFTMetadataElementComposite processData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openProcessSection(String sectionId ,AEMFTMetadataElementComposite processData) {
		String		processId			= getElementController().getElementAsString(DSLAMBOIProcess.PROCESS_ID, processData);
		
		AEMFTMetadataElementComposite sectionData = getElementController().getElementAsComposite(sectionId, processData);
		processSectionsDeckPanel.setVisibility(Visibility.VISIBLE);
		processSectionsDeckPanel.setData(sectionData);	
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		processSectionsDeckPanel.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		processSectionsDeckPanel.setData(data);
	}

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type)
				||
				LOGICAL_TYPE.OPEN_EVENT.equals(type);
	}
	
	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
//	private boolean isValidEvent(AEGWTLogicalEvent evt) {
//		if (DSLAMBusDesktopProjectPagePresenter.NAME.equals(evt.getSourceWidget())) {
//			return true;
//		} else return false;
//	}
//	
//	private void reset() {
//		processSectionsDeckPanel.reset();
//	}
//	
//	
//	private void fireSaveChanges(String projectId, AEMFTMetadataElementComposite optionsData) {
//		AEMFTMetadataElementComposite updateProjectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
//		updateProjectData.addElement(DSLAMBOIProject.PROJECT_ID, projectId);
//		updateProjectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_CONFIGURE_DATA , optionsData);
//
//		AEGWTLogicalEvent updateEvent = new AEGWTLogicalEvent(getWindowName(), getName());
//		updateEvent.setEventType(LOGICAL_TYPE.SAVE_EVENT);
//		updateEvent.addElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA ,updateProjectData);
//		getLogicalEventHandlerManager().fireEvent(updateEvent);
//	}
//
//
//
//	
//	private void fireSaveVariablesDataEvent(AEGWTLogicalEvent saveVariablesEvt) {
//		saveVariablesEvt.stopPropagation();
//		LOGICAL_TYPE	type		= saveVariablesEvt.getEventType();
//		String 			projectName			= saveVariablesEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_NAME);
//		String 			projectMachineType	= saveVariablesEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE);
//		AEMFTMetadataElementComposite existentProjectData = projectListNavigator.getElementDataByName(projectName);
//		
//		if (existentProjectData != null && !LOGICAL_TYPE.SELECT_EVENT.equals(type)) {
//			newProjectPopup.setError(TEXTS.projectname_exists());
//		} else {
//			AEMFTMetadataElementComposite projectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
//			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_NAME			, projectName);
//			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE	, projectMachineType);
//			
//			AEGWTLogicalEvent saveFileEvent = new AEGWTLogicalEvent(getWindowName(), getName());
//			saveFileEvent.setEventType(saveVariablesEvt.getEventType());
//			saveFileEvent.addElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA , projectData);
//			saveFileEvent.setSourceWidgetId(saveVariablesEvt.getSourceWidgetId());
//			getLogicalEventHandlerManager().fireEvent(saveFileEvent);
//		}
//	}
}
