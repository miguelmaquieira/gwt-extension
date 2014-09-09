package com.imotion.dslam.front.business.desktop.client.presenter;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIExecutionDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopProjectsLayout;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopProjectsToolbarActions;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.presenter.controller.AEGWTILoginAppControllerConstants;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownGlyphIconButton;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEventTypes;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public abstract class CRONIOBusProjectBasePresenter<T extends CRONIOBusProjectBaseDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusDesktopHasProjectEventHandlers, AEGWTHasLogicalEventHandlers, CRONIOBusProjectBasePresenterConstants {

	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private CRONIOBusDesktopProjectsLayout projectsLayout;

	public CRONIOBusProjectBasePresenter(T view) {
		super(view);
	}

	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PROJECTS_DATA, PROJECT_NAVIGATION_DATA, CRONIODesktopIAppControllerConstants.PREFERENCES_DATA, CRONIODesktopIAppControllerConstants.EXECUTIONS_DATA, AEGWTILoginAppControllerConstants.SESSION};
	}

	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */

	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		EVENT_TYPE evtTyp = evt.getEventType();
		if (EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(evtTyp)) {
			String projectId		= evt.getProjectId();
			String mainSectionId	= evt.getMainSectionId();
			String finalSectionId	= evt.getFinalSectionId();
			
			if (finalSectionId == null) {
				fireSectionSelected(projectId, mainSectionId);
			} else{
				String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);

				boolean navigate 		= !getSectionType().equals(mainSectionId);
				boolean	 projectChange	= !projectId.equals(currentProjectId);

				getView().beforeExitSection();
				updateNavigationData(projectId, mainSectionId, finalSectionId);
				if (navigate) {
					AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
					flowEvent.setSourceWidgetId(mainSectionId);
					getFlowEventHandlerManager().fireEvent(flowEvent);
				} else {
					openFinalSection(projectChange, projectId, finalSectionId);
				}
				fireFinalSectionSelected(projectId, finalSectionId);
			}	
		} else if (EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(evtTyp)) {
			AEMFTMetadataElementComposite finalSectionData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
			updateFinalSectionInContext(finalSectionData);
		} else if (EVENT_TYPE.SAVE_PROJECT.equals(evtTyp)) {
			saveCurrentProjectInDB();
		} else if (EVENT_TYPE.SAVE_ALL_PROJECTS.equals(evtTyp)) {
			saveModifiedProjectsInDB();
		} else if (EVENT_TYPE.NEW_PROJECT.equals(evtTyp)) {
			String	projectName 	= evt.getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME);
			String	machineTypeStr 	= evt.getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE);
			int		machinetTypeInt	= AEMFTCommonUtilsBase.getIntegerFromString(machineTypeStr);
			createProject(projectName, machinetTypeInt);
		} else if (EVENT_TYPE.EXECUTE.equals(evtTyp)) {
			String projectId = evt.getElementAsString(CRONIOBOIExecution.PROJECT_ID);
			Long executionId = evt.getElementAsLong(CRONIOBOIExecution.EXECUTION_ID);
			executeProject(projectId, executionId);
		} else if (EVENT_TYPE.ADD_EXECUTION.equals(evtTyp)) {
			String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
			addExecutionToDB(currentProjectId);
			
		}		
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(type)
				||
				EVENT_TYPE.OPEN_PREFERENCES_EVENT.equals(type)
				||
				EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(type)
				||
				EVENT_TYPE.SAVE_PROJECT.equals(type)
				||
				EVENT_TYPE.SAVE_ALL_PROJECTS.equals(type)
				||
				EVENT_TYPE.NEW_PROJECT.equals(type)
				||
				EVENT_TYPE.EXECUTE.equals(type)
				||
				EVENT_TYPE.ADD_EXECUTION.equals(type);
	}


	/**
	 * AEGWTHasLogicalEventHandlers
	 */

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		LOGICAL_TYPE evtTyp = evt.getEventType();
		String sourceWidgetId = evt.getSourceWidgetId();
		String sourceWidget = evt.getSourceWidget();
		if (LOGICAL_TYPE.SELECT_EVENT.equals(evtTyp)) { 

			if (AEGWTBootstrapDropdownGlyphIconButton.NAME.equals(sourceWidget)) {

				if(DSLAMBusDesktopProjectsToolbarActions.OPTION_TYPE_OPEN_PREFERENCES == Integer.valueOf(sourceWidgetId)) {
					if (AEMFTCommonUtilsBase.isEmptyList(projectsLayout.getModifiedProjetIds()) || (Window.confirm(TEXTS.exit_without_save()))) {
						AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusPreferencesBasePresenterConstants.PREFERENCES_PRESENTER, getName());
						getFlowEventHandlerManager().fireEvent(flowEvent);
					}

				} else if(DSLAMBusDesktopProjectsToolbarActions.OPTION_TYPE_EXIT == Integer.valueOf(sourceWidgetId)) {
					exit();
				}
			}			
		} else if (LOGICAL_TYPE.SUBMIT_EVENT.equals(evtTyp)) { 
			getAllLogsTest();

		} else if (LOGICAL_TYPE.GET_EVENT.equals(evtTyp)) {
			AEMFTMetadataElementComposite executionData = evt.getElementAsComposite(CRONIOBOIExecution.EXECUTION_DATA);
			AEMFTMetadataElementSingle executionIdDataSingle = (AEMFTMetadataElementSingle) executionData.getElement(CRONIOBOIExecution.EXECUTION_ID);
			String executionId = executionIdDataSingle.getValueAsString();
			getLogData(executionId);
			

		}
		
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return	LOGICAL_TYPE.SELECT_EVENT.equals(type) || 
				LOGICAL_TYPE.SUBMIT_EVENT.equals(type)||
				LOGICAL_TYPE.GET_EVENT.equals(type);
	}
	
	/**
	 * PROTECTED
	 */

	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
		projectsLayout = (CRONIOBusDesktopProjectsLayout) layoutContainer.getCurrentLayout();
		projectsLayout.setvisibleLayoutItemHeader(true);	
	}

	@Override
	protected void viewAdded() {
		super.viewAdded();
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, getProjectsLayout());
		getLogicalEventHandlerManager().addLogicalEventHandler(this);

		String currentProjectId			= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
		String currentFinalSectionId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID);
		if (!AEGWTStringUtils.isEmptyString(currentFinalSectionId)) {
			openFinalSection(true, currentProjectId, currentFinalSectionId);
		}
	}

	protected abstract void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData);

	protected void updateFinalSectionInContext( AEMFTMetadataElementComposite finalSectionData) {
		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();

		String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
		String currentSectionId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID);

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentSectionId);
		String finalSectionKey = sbKey.toString();

		if (!DSLAMBOIProject.PROJECT_EXECUTION_CONSOLE.equals(currentSectionId)) {
			getElementDataController().setElement(DSLAMBOIProject.INFO_IS_MODIFIED, finalSectionData, true);
		}

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
		storageEvent.setFullKey(finalSectionKey);
		storageEvent.addElementAsDataValue(finalSectionData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		getContextDataController().setElement(finalSectionKey, finalSectionData);

		fireSectionModified(currentProjectId, currentSectionId);
	}
	
	protected CRONIOBusDesktopProjectsLayout getProjectsLayout() {
		return projectsLayout;
	}

	protected abstract String getSectionType();

	/**
	 *	PRIVATE 
	 */
	
	private void getLogData(String executionId) {
		
		AEMFTMetadataElementComposite logData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		
		logData.addElement(CRONIOBOIExecutionDataConstants.EXECUTION_ID		, executionId);
		
		getClientServerConnection().executeComm(logData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_LOG_GET_EXECUTION_LOGS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
//					AEMFTMetadataElementComposite executionData = dataResult.getCompositeElement(CRONIOBUIExecuteBusinessServiceConstants.EXECUTION_DATA);
//					String dateExecutionStr = getElementDataController().getElementAsString(CRONIOBOIExecution.CREATION_TIME, executionData);
//					String projectId = getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID, executionData);
//					Long executionId = getElementDataController().getElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionData);
//					projectsLayout.addExecution(projectId, dateExecutionStr);
//					
//					CRONIOBusDesktopProjectEvent addExecutionEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
//					addExecutionEvt.setEventType(EVENT_TYPE.EXECUTE);
//					addExecutionEvt.addElementAsString(CRONIOBOIExecution.PROJECT_ID, projectId);
//					addExecutionEvt.addElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionId);
//					getLogicalEventHandlerManager().fireEvent(addExecutionEvt);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void addExecutionToDB(String currentId) {
		
		AEMFTMetadataElementComposite newExecutionData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		
		newExecutionData.addElement(CRONIOBOIExecutionDataConstants.PROJECT_ID		, currentId);
		
		
		
		getClientServerConnection().executeComm(newExecutionData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_EXECUTE_ADD_EXECUTION_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite executionData = dataResult.getCompositeElement(CRONIOBUIExecuteBusinessServiceConstants.EXECUTION_DATA);
					String dateExecutionStr = getElementDataController().getElementAsString(CRONIOBOIExecution.CREATION_TIME, executionData);
					String projectId = getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID, executionData);
					Long executionId = getElementDataController().getElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionData);
					projectsLayout.addExecution(projectId, dateExecutionStr);
					
					CRONIOBusDesktopProjectEvent addExecutionEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
					addExecutionEvt.setEventType(EVENT_TYPE.EXECUTE);
					addExecutionEvt.addElementAsString(CRONIOBOIExecution.PROJECT_ID, projectId);
					addExecutionEvt.addElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionId);
					getLogicalEventHandlerManager().fireEvent(addExecutionEvt);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	

	private void updateNavigationData(String projectId, String mainSectionId, String finalSectionId) {
		AEMFTMetadataElementComposite navigationData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		navigationData.addElement(CURRENT_PROJECT_ID		, projectId);
		navigationData.addElement(CURRENT_MAIN_SECTION_ID	, mainSectionId);
		navigationData.addElement(CURRENT_FINAL_SECTION_ID	, finalSectionId);

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
		storageEvent.setFullKey(PROJECT_NAVIGATION_DATA);
		storageEvent.addElementAsDataValue(navigationData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		getContextDataController().setElement(PROJECT_NAVIGATION_DATA, navigationData.cloneObject());
	}

	private void createProject(String projectName, int machinetTypeInt) {
		AEMFTMetadataElementComposite newProjectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		newProjectData.addElement(DSLAMBOIProject.PROJECT_NAME			, projectName);
		newProjectData.addElement(DSLAMBOIProject.PROJECT_MACHINE_TYPE	, machinetTypeInt);

		String userIdKey = AEGWTILoginAppControllerConstants.SESSION + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + AEGWTILoginAppControllerConstants.USER_ID;
		AEMFTMetadataElementSingle userIdData = (AEMFTMetadataElementSingle) getContextDataController().getElement(userIdKey);
		String userIdStr = userIdData.getValueAsString();
		
		newProjectData.addElement(CRONIOBOIUser.USER_ID, userIdStr);
		
		getClientServerConnection().executeComm(newProjectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_ADD_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
			if (dataResult != null) {
					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA);
					if (projectData != null) {
						String projectId = getElementDataController().getElementAsString(DSLAMBOIProject.PROJECT_ID, projectData);
						updateProjectClientData(projectId, projectData, false);

						CRONIOBusDesktopProjectEvent projectCreatedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
						projectCreatedEvt.setEventType(EVENT_TYPE.PROJECT_CREATED);
						projectCreatedEvt.addElementAsDataValue(projectData);
						getLogicalEventHandlerManager().fireEvent(projectCreatedEvt);
					}
				}

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId) {
		boolean isLog = false;
		
		if (!AEGWTStringUtils.isEmptyString(projectId) && !AEGWTStringUtils.isEmptyString(projectFinalSectionId)) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			
			String dateRegEx = "[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}\\s([0-9]{2})\\:([0-9]{2})\\:([0-9]{2})";
					
			if (projectFinalSectionId.matches(dateRegEx)) {
				sbKey.append(CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA);
				sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(projectId);
				isLog = true;
			} else {
				sbKey.append(projectId);
				if (!DSLAMBOIProject.PROJECT_EXECUTION_CONSOLE.equals(projectFinalSectionId)) {
					sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
					//Final Section Data
					sbKey.append(projectFinalSectionId);
				}
			}
			
			String finalSectionKey = sbKey.toString();

			//Project name
			StringBuilder projectNamesbKey = new StringBuilder();
			projectNamesbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			projectNamesbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			projectNamesbKey.append(projectId);
			projectNamesbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			projectNamesbKey.append(DSLAMBOIProject.PROJECT_NAME);
			String projectNameKey = projectNamesbKey.toString();
			String projectName = getContextDataController().getElementAsString(projectNameKey);

			AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(finalSectionKey);
			if (finalSectionData != null) {
				finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
				if (isLog) {
					List<AEMFTMetadataElement> executionList = finalSectionData.getElementList();
					for (AEMFTMetadataElement execution : executionList) {
						AEMFTMetadataElementComposite executionData = (AEMFTMetadataElementComposite) execution;
						AEMFTMetadataElementSingle creationTime = (AEMFTMetadataElementSingle) executionData.getElement(CRONIOBOIExecution.CREATION_TIME);
						String creationTimeStr = creationTime.getValueAsSerializable().toString();
						String[] creationTimeStrSplit 	= creationTimeStr.split("\\.");
						String[] creationTimeStrSplit1 	= creationTimeStrSplit[0].split("\\-");
						String[] creationTimeStrSplit2 	= creationTimeStrSplit1[2].split(" ");
						String creationTimeStrFormat = creationTimeStrSplit2[0] + "-" + creationTimeStrSplit1[1] + "-" + creationTimeStrSplit1[0] + " " + creationTimeStrSplit2[1];
						if(creationTimeStrFormat.equals(projectFinalSectionId)) {
							finalSectionData = executionData;
						}
					}
					
				}
			}
			
			
			boolean sectionIsModified = getElementDataController().getElementAsBoolean(DSLAMBOIProject.INFO_IS_MODIFIED, finalSectionData);

			//SHOW HEADER INFO
			CRONIOBusDesktopProjectEvent showInfoEvent = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
			showInfoEvent.addElementAsString(DSLAMBOIProject.PROJECT_NAME	, projectName);
			showInfoEvent.addElementAsBoolean(DSLAMBOIProject.INFO_IS_MODIFIED	, sectionIsModified);
			showInfoEvent.setProjectId(projectId);
			showInfoEvent.setFinalSectionId(projectFinalSectionId);
			showInfoEvent.setEventType(EVENT_TYPE.SHOW_PROJECT_INFO);
			getLogicalEventHandlerManager().fireEvent(showInfoEvent);

			//SHOW CONTENT
			openFinalSection(projectChange, projectId, projectFinalSectionId, finalSectionData);


		}
	}

	private void saveCurrentProjectInDB() {

		final String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		String projectDataKey = sbKey.toString();
		
		String userIdKey = AEGWTILoginAppControllerConstants.SESSION + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + AEGWTILoginAppControllerConstants.USER_ID;
		AEMFTMetadataElementSingle userIdData = (AEMFTMetadataElementSingle) getContextDataController().getElement(userIdKey);
		String userIdStr = userIdData.getValueAsString();
		
		AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
		projectData.addElement(CRONIOBOIUser.USER_ID, userIdStr);
		
		getClientServerConnection().executeComm(projectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA);
					if (projectData != null) {
						updateProjectClientData(currentProjectId, projectData, true);
					}
				}

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void updateProjectClientData(String currentProjectId, AEMFTMetadataElementComposite projectData, boolean projectSaved) {
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		String projectKey = sbKey.toString();

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
		storageEvent.setFullKey(projectKey);
		storageEvent.addElementAsDataValue(projectData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		projectData = (AEMFTMetadataElementComposite) projectData.cloneObject();
		getContextDataController().setElement(projectKey, projectData);
		if (projectSaved) {
			fireProjectSaved(currentProjectId);
		}
	}

	private void saveModifiedProjectsInDB() {
		List<String> modifiedProjectIds = getProjectsLayout().getModifiedProjetIds();

		AEMFTMetadataElementComposite projectsData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		for (String projectId : modifiedProjectIds) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(projectId);
			String projectDataKey = sbKey.toString();

			AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
			projectsData.addElement(projectId, projectData);
		}

		AEMFTMetadataElementComposite contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		contextIn.addElement(DSLAMBUIProjectBusinessServiceConstants.PROJECTS_DATA, projectsData);
		getClientServerConnection().executeComm(contextIn, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECTS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectsData = dataResult.getCompositeElement(DSLAMBUIProjectBusinessServiceConstants.PROJECTS_DATA);
					List<AEMFTMetadataElement> projectsDataElements = projectsData.getElementList();
					for (AEMFTMetadataElement projectDataElement :projectsDataElements) {
						updateProjectClientData(projectDataElement.getKey(), (AEMFTMetadataElementComposite) projectDataElement, true);
					}
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}

		});
	}

	private void fireProjectSaved(String projectId) {
		CRONIOBusDesktopProjectEvent projectSavedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
		projectSavedEvt.setEventType(EVENT_TYPE.PROJECT_SAVED);
		projectSavedEvt.setProjectId(projectId);
		getLogicalEventHandlerManager().fireEvent(projectSavedEvt);
	}

	private void fireSectionModified(String projectId, String currentSectionId) {
		if (!DSLAMBOIProject.PROJECT_EXECUTION_CONSOLE.equals(currentSectionId)) {
			CRONIOBusDesktopProjectEvent sectionModifiedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
			sectionModifiedEvt.setEventType(EVENT_TYPE.SECTION_MODIFIED);
			sectionModifiedEvt.setProjectId(projectId);
			sectionModifiedEvt.setFinalSectionId(currentSectionId);
			getLogicalEventHandlerManager().fireEvent(sectionModifiedEvt);
		}	
	}
	
	private void fireSectionSelected(String projectId, String currentSectionId) {
		CRONIOBusDesktopProjectEvent sectionSelectedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
		sectionSelectedEvt.setEventType(EVENT_TYPE.SECTION_SELECTED);
		sectionSelectedEvt.setProjectId(projectId);
		sectionSelectedEvt.setMainSectionId(currentSectionId);
		getLogicalEventHandlerManager().fireEvent(sectionSelectedEvt);	
	}
	
	private void fireFinalSectionSelected(String projectId, String currentSectionId) {
		CRONIOBusDesktopProjectEvent sectionSelectedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
		sectionSelectedEvt.setEventType(EVENT_TYPE.SECTION_SELECTED);
		sectionSelectedEvt.setProjectId(projectId);
		sectionSelectedEvt.setFinalSectionId(currentSectionId);
		getLogicalEventHandlerManager().fireEvent(sectionSelectedEvt);
	}

	private void executeProject(String projectId, Long executionId) {
		AEMFTMetadataElementComposite contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		contextIn.addElement(CRONIOBOIExecution.PROJECT_ID, projectId);
		contextIn.addElement(CRONIOBOIExecution.EXECUTION_ID, executionId);
		getClientServerConnection().executeComm(contextIn, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_EXECUTE_EXECUTE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}

		});
	}

	private void exit() {	 
		boolean confirmExit = false; 			
		if (AEMFTCommonUtilsBase.isEmptyList(projectsLayout.getModifiedProjetIds()) || (confirmExit = Window.confirm(TEXTS.exit_without_save()))) {
			if(!confirmExit) {
				confirmExit = Window.confirm(TEXTS.exit_confirmation());
			}
		}		
		if(confirmExit) {
			logout();
		}
	}
	
	private void getAllLogsTest() {
		
		AEMFTMetadataElementComposite newProjectData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		newProjectData.addElement(DSLAMBOIProject.PROJECT_NAME			, "");
		newProjectData.addElement(DSLAMBOIProject.PROJECT_MACHINE_TYPE	, "");
		
		getClientServerConnection().executeComm(newProjectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_LOG_GET_ALL_LOGS, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	
}
