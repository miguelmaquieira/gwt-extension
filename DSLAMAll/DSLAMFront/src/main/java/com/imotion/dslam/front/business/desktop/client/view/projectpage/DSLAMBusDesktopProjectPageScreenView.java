package com.imotion.dslam.front.business.desktop.client.view.projectpage;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.DSLAMBOIProjectDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPageDisplay;
import com.imotion.dslam.front.business.desktop.client.presenter.projectpage.DSLAMBusDesktopProjectPagePresenter;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigator;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopNewProjectPopupForm;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureVariables;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProjectConfigure;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbarActions;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbarInfo;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProjectPageScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProjectPageDisplay,AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProjectPageScreenView";
	private static final DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	private static final String PROJECT_DATA_LIST = null;
	
	private FlowPanel 								root;
	private DSLAMBusDesktopToolbar					toolbar;
	private DSLAMBusDesktopNavigator				projectList;
	private DSLAMBusDesktopProjectConfigure			projectOptions;
	private DSLAMBusDesktopNewProjectPopupForm 		newProjectPopup;
	
	public DSLAMBusDesktopProjectPageScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_VIEW);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setMainTitleText("script1");
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
		toolbar.getInfo().setVisible(false);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_VIEW_BOTTOM_ZONE);

		//Bottom Zone - Projectlist zone
		FlowPanel projectListZone = new FlowPanel();
		bottomZone.add(projectListZone);
		projectListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		projectListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_LIST_ZONE);
		
		projectList = new DSLAMBusDesktopNavigator();
		projectListZone.add(projectList);

		//Bottom Zone - Project configure zone
		FlowPanel projectConfigureZone = new FlowPanel();
		bottomZone.add(projectConfigureZone);
		projectConfigureZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		projectConfigureZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECT_CONFIGURE_ZONE);

		projectOptions = new DSLAMBusDesktopProjectConfigure();
		projectConfigureZone.add(projectOptions);
		projectOptions.setVisibility(Visibility.HIDDEN);
		
	}
	
	
	public void addProject(AEMFTMetadataElementComposite processData) {
		newProjectPopup.hide();
		projectList.addElement(processData);
		openProject(processData);
	}
	
	public void updateProject(AEMFTMetadataElementComposite projectData) {
		if (projectData != null) {
			Long	projectId		= getElementController().getElementAsLong(DSLAMBOIProject.PROJECT_ID	, projectData);
			String	projectIdStr	= String.valueOf(projectId);
			String	currentProjectId= toolbar.getId();
			if (projectIdStr.equals(currentProjectId)) {
				toolbar.setData(projectData);
			}
			projectList.updateElement(projectData);
			newProjectPopup.hide();
		}
	}
	
	@Override
	public void removeProject(String projectId) {
		// TODO Auto-generated method stub
		
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
		projectList.postDisplay();
		projectOptions.postDisplay();
		newProjectPopup = new DSLAMBusDesktopNewProjectPopupForm(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		projectList.setData(data);
	}

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		String			srcWidgetId		= evt.getSourceWidgetId();
		String			srcContainerId	= evt.getSourceContainerId();
		LOGICAL_TYPE	type		= evt.getEventType();
		if (DSLAMBusDesktopToolbarActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				if (!toolbar.isModified() || Window.confirm(TEXTS.exit_without_save())) {
					closeCurrentProject();
					newProjectPopup.setMode(DSLAMBusDesktopNewProjectPopupForm.MODE_NEW_PROJECT);
					newProjectPopup.center();
				}
			} if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite optionsData = projectOptions.getData();
				fireSaveChanges(srcWidgetId, optionsData);
			}
		} else if (DSLAMBusDesktopProcessConfigureVariables.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				fireSaveVariablesDataEvent(evt);
			}
		} else if (DSLAMBusDesktopToolbarInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.CLOSE_EVENT.equals(type)) {
				closeCurrentProject();
			}
		} else if (AEGWTBootstrapTreeMenuFinalItem.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.OPEN_EVENT.equals(type)) {
				openSectionProject(evt);
			}
		} else if (DSLAMBusDesktopNewProjectPopupForm.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				fireSaveFormDataEvent(evt);
			}
		} if (isValidEvent(evt) && evt.getEventType().equals(LOGICAL_TYPE.OK_EVENT)) {
			reset();	
			Window.alert("Transacción finalizada");
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type)
				||
				LOGICAL_TYPE.NEW_EVENT.equals(type)
				||
				LOGICAL_TYPE.CLOSE_EVENT.equals(type)
				||
				LOGICAL_TYPE.OK_EVENT.equals(type)
				||
				LOGICAL_TYPE.OPEN_EVENT.equals(type);
	}
	
	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
	private boolean isValidEvent(AEGWTLogicalEvent evt) {
		if (DSLAMBusDesktopProjectPagePresenter.NAME.equals(evt.getSourceWidget())) {
			return true;
		} else return false;
	}
	
	private void reset() {
		projectOptions.reset();
		toolbar.reset();
	
	}
	
	private void closeCurrentProject() {
		toolbar.setFileInfoVisible(false);
		toolbar.setModified(false);
		projectOptions.setVisibility(Visibility.HIDDEN);
		projectOptions.reset();
		toolbar.setId(null);
	}
	
	private void openProject(AEMFTMetadataElementComposite projectData) {
		if (projectData != null) {
			Long	projectId		= getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID, projectData);
			String	projectIdStr	= String.valueOf(projectId);
			openProject(projectIdStr);
		}
	}
	
	private void openProject(String projectId) {
		if (!toolbar.isModified() || (toolbar.isModified() && Window.confirm(TEXTS.exit_without_save())) ) {
			closeCurrentProject();
			AEMFTMetadataElementComposite projectData = projectList.getElementData(projectId);
			
			toolbar.setData(projectData);
			
			toolbar.setFileInfoVisible(true);
			projectOptions.setVisibility(Visibility.VISIBLE);
			
			AEMFTMetadataElementComposite data = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLE_LIST, projectData);
			if(data != null) {
				projectOptions.setData(projectData);
			}
		}
	}
	
	private void fireSaveFormDataEvent(AEGWTLogicalEvent saveButtonEvt) {
		saveButtonEvt.stopPropagation();
		LOGICAL_TYPE	type		= saveButtonEvt.getEventType();
		String 			projectName			= saveButtonEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_NAME);
		String 			projectMachineType	= saveButtonEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE);
		AEMFTMetadataElementComposite existentProjectData = projectList.getElementDataByName(projectName);
		
		if (existentProjectData != null && !LOGICAL_TYPE.SELECT_EVENT.equals(type)) {
			newProjectPopup.setError(TEXTS.projectname_exists());
		} else {
			AEMFTMetadataElementComposite projectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_NAME			, projectName);
			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE	, projectMachineType);
			
			AEGWTLogicalEvent saveFileEvent = new AEGWTLogicalEvent(getWindowName(), getName());
			saveFileEvent.setEventType(saveButtonEvt.getEventType());
			saveFileEvent.addElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA , projectData);
			saveFileEvent.setSourceWidgetId(saveButtonEvt.getSourceWidgetId());
			getLogicalEventHandlerManager().fireEvent(saveFileEvent);
		}
	}
	
	private void fireSaveChanges(String projectId, AEMFTMetadataElementComposite optionsData) {
		AEMFTMetadataElementComposite updateProjectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		updateProjectData.addElement(DSLAMBOIProject.PROJECT_ID, projectId);
		updateProjectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_CONFIGURE_DATA , optionsData);

		AEGWTLogicalEvent updateEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		updateEvent.setEventType(LOGICAL_TYPE.SAVE_EVENT);
		updateEvent.addElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA ,updateProjectData);
		getLogicalEventHandlerManager().fireEvent(updateEvent);
	}
	
//	private void showRenameForm(AEMFTMetadataElementComposite processData) {
//		newProjectPopup.setMode(DSLAMBusDesktopNewProjectPopupForm.MODE_RENAME_PROJECT);
//		newProjectPopup.center();
//		newProjectPopup.setData(processData);
//	}
//	
//	private void showChangeScriptForm(AEMFTMetadataElementComposite processData) {
//		newProjectPopup.setMode(DSLAMBusDesktopNewProjectPopupForm.MODE_CHANGE_SCRIPT_PROJECT);
//		newProjectPopup.center();
//		newProjectPopup.setData(processData);
//	}

	private void fireDeleteProject(String projectId) {
		AEGWTLogicalEvent deleteEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		deleteEvent.setSourceWidgetId(projectId);
		deleteEvent.setEventType(LOGICAL_TYPE.DELETE_EVENT);
		getLogicalEventHandlerManager().fireEvent(deleteEvent);
	}
	
	private void openSectionProject(AEGWTLogicalEvent evt) {
		String itemId = evt.getElementAsString(AEGWTBootstrapTreeMenuFinalItem.ITEM_ID);
		projectOptions.showSection(itemId);
		projectOptions.setVisibility(Visibility.VISIBLE);
	}
	
	private void fireSaveVariablesDataEvent(AEGWTLogicalEvent saveVariablesEvt) {
		saveVariablesEvt.stopPropagation();
		LOGICAL_TYPE	type		= saveVariablesEvt.getEventType();
		String 			projectName			= saveVariablesEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_NAME);
		String 			projectMachineType	= saveVariablesEvt.getElementAsString(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE);
		AEMFTMetadataElementComposite existentProjectData = projectList.getElementDataByName(projectName);
		
		if (existentProjectData != null && !LOGICAL_TYPE.SELECT_EVENT.equals(type)) {
			newProjectPopup.setError(TEXTS.projectname_exists());
		} else {
			AEMFTMetadataElementComposite projectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_NAME			, projectName);
			projectData.addElement(DSLAMBOIProjectDataConstants.PROJECT_MACHINE_TYPE	, projectMachineType);
			
			AEGWTLogicalEvent saveFileEvent = new AEGWTLogicalEvent(getWindowName(), getName());
			saveFileEvent.setEventType(saveVariablesEvt.getEventType());
			saveFileEvent.addElementAsComposite(DSLAMBOIProjectDataConstants.PROJECT_DATA , projectData);
			saveFileEvent.setSourceWidgetId(saveVariablesEvt.getSourceWidgetId());
			getLogicalEventHandlerManager().fireEvent(saveFileEvent);
		}
	}
}
