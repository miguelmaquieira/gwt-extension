package com.imotion.dslam.front.business.desktop.client.presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIExecutionDataConstants;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.CRONIOBOILogDataConstants;
import com.imotion.dslam.bom.CRONIOBOILogFilterDataConstants;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOINodeListDataConstants;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.CRONIOBOIProcess;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.CRONIOBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.CRONIOBusCommonConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopProjectsLayout;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessAddNodeFinalItem;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessAddNodeListForm;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.CRONIOBusDesktopProjectsToolbarActions;
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

public abstract class CRONIOBusProjectBasePresenter<T extends CRONIOBusProjectBaseDisplay> extends CRONIOBusBasePresenter<T> implements CRONIOBusDesktopHasProjectEventHandlers, AEGWTHasLogicalEventHandlers, CRONIOBusProjectBasePresenterConstants {

	private CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private CRONIOBusDesktopProjectsLayout projectsLayout;

	public CRONIOBusProjectBasePresenter(T view) {
		super(view);
	}

	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PROJECTS_DATA, PROJECT_NAVIGATION_DATA, CRONIODesktopIAppControllerConstants.PREFERENCES_DATA, CRONIODesktopIAppControllerConstants.EXECUTIONS_DATA, CRONIODesktopIAppControllerConstants.LIST_NODELIST_DATA, AEGWTILoginAppControllerConstants.SESSION};
	}

	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */

	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		EVENT_TYPE evtTyp 	= evt.getEventType();
		String srcWidget	= evt.getSourceWidget();
		if (EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(evtTyp)) {
			String projectId		= evt.getProjectId();
			String mainSectionId	= evt.getMainSectionId();
			String finalSectionId	= evt.getFinalSectionId();
			String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
			if (finalSectionId == null) {
				fireSectionSelected(projectId, mainSectionId);
			} else{
				boolean navigate 		= !getSectionType().equals(mainSectionId);
				if (getSectionType() ==CRONIOBusProjectBasePresenterConstants.SECTION_TYPE_PROCESS && CRONIOBusProjectBasePresenterConstants.SECTION_TYPE_ENVIROMENTS.equals(mainSectionId)  ) {
					navigate = false;
				}
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
			String 	projectId 		= evt.getElementAsString(CRONIOBOIExecution.PROJECT_ID);
			Long 	executionId 	= evt.getElementAsLong(CRONIOBOIExecution.EXECUTION_ID);
			String 	nodeListName 	= evt.getElementAsString(CRONIOBOINodeList.NODELIST_NAME);
			executeProject(projectId, executionId, nodeListName);
		} else if (EVENT_TYPE.ADD_EXECUTION.equals(evtTyp)) {
			String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
			String nodeListName		= evt.getElementAsString(CRONIOBOINodeList.NODELIST_NAME);
			addExecutionToDB(currentProjectId, nodeListName);
		} else if (EVENT_TYPE.GET_MACHINE_TYPES.equals(evtTyp) && CRONIOBusDesktopProcessAddNodeFinalItem.NAME.equals(srcWidget)) {
			evt.stopPropagation();
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIOBOIPreferencesDataConstants.PREFERENCES_DATA);
			sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIOBOIPreferencesDataConstants.PREFERENCES_MACHINE_PROPERTIES_LIST);
			String machineTypesKey = sbKey.toString();

			AEMFTMetadataElementComposite machinePropertiesListData = getContextDataController().getElementAsComposite(machineTypesKey);
			List<AEMFTMetadataElement> machinePropertiesList = machinePropertiesListData.getSortedElementList();
			List<String> machineList = new ArrayList<>();
			String	 machine = null;
			for (AEMFTMetadataElement machineProperties : machinePropertiesList) {
				machine = machineProperties.getKey();
				if (machine != null) {
					machineList.add(machine);
				}
			}

			CRONIOBusDesktopProjectEvent 	getMachineTypesEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
			getMachineTypesEvt.setEventType(EVENT_TYPE.GET_MACHINE_TYPES);
			getMachineTypesEvt.addElementAsSerializableDataValue((Serializable) machineList);
			getLogicalEventHandlerManager().fireEvent(getMachineTypesEvt);
		} else if (EVENT_TYPE.GET_PROCESS_NODELISTS.equals(evtTyp) && CRONIOBusDesktopProjectsToolbarActions.NAME.equals(srcWidget)) {
			evt.stopPropagation();
			String currentProjectId = getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
			
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(CRONIODesktopIAppControllerConstants.LIST_NODELIST_DATA);
			sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(currentProjectId);
			String nodeListsDataKey = sbKey.toString();

			AEMFTMetadataElementComposite projectNodeListsData = getContextDataController().getElementAsComposite(nodeListsDataKey);
			List<AEMFTMetadataElement> projectNodeLists = projectNodeListsData.getSortedElementList();
			List<String> nodeLists = new ArrayList<>();
			String	 nodeList = null;
			for (AEMFTMetadataElement nodeListData : projectNodeLists) {
				nodeList = nodeListData.getKey();
				if (nodeList != null) {
					nodeLists.add(nodeList);
				}
			}

			CRONIOBusDesktopProjectEvent 	getNodeListsEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
			getNodeListsEvt.setEventType(EVENT_TYPE.GET_PROCESS_NODELISTS);
			getNodeListsEvt.addElementAsSerializableDataValue((Serializable) nodeLists);
			getLogicalEventHandlerManager().fireEvent(getNodeListsEvt);
		}		
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(type)||
				EVENT_TYPE.OPEN_PREFERENCES_EVENT.equals(type)||
				EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(type)||
				EVENT_TYPE.SAVE_PROJECT.equals(type)||
				EVENT_TYPE.SAVE_ALL_PROJECTS.equals(type)||
				EVENT_TYPE.NEW_PROJECT.equals(type)||
				EVENT_TYPE.EXECUTE.equals(type)||
				EVENT_TYPE.ADD_EXECUTION.equals(type)||
				EVENT_TYPE.GET_MACHINE_TYPES.equals(type)||
				EVENT_TYPE.GET_PROCESS_NODELISTS.equals(type);
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

				if(CRONIOBusDesktopProjectsToolbarActions.OPTION_TYPE_OPEN_PREFERENCES == Integer.valueOf(sourceWidgetId)) {
					if (AEMFTCommonUtilsBase.isEmptyList(projectsLayout.getModifiedProjetIds()) || (Window.confirm(TEXTS.exit_without_save()))) {
						AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusPreferencesBasePresenterConstants.PREFERENCES_PRESENTER, getName());
						getFlowEventHandlerManager().fireEvent(flowEvent);
					}

				} else if(CRONIOBusDesktopProjectsToolbarActions.OPTION_TYPE_EXIT == Integer.valueOf(sourceWidgetId)) {
					exit();
				}
			}			
		} else if (LOGICAL_TYPE.SUBMIT_EVENT.equals(evtTyp)) { 
			AEMFTMetadataElementComposite filterData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
			getFilteredLogs(filterData);

		} else if (LOGICAL_TYPE.GET_EVENT.equals(evtTyp)) {
			long 	executionId;
			String  executionIdStr;
			int 	offset;
			int 	numberResults;

			AEMFTMetadataElementComposite executionData = evt.getElementAsComposite(CRONIOBOIExecution.EXECUTION_DATA);
			if (executionData == null) {
				AEMFTMetadataElementComposite 	filterFormData 	= evt.getElementAsComposite(CRONIOBOILogFilterDataConstants.FILTER_FORM_DATA);
				AEMFTMetadataElementSingle		offsetData 		= (AEMFTMetadataElementSingle) evt.getElement(CRONIOBOILogDataConstants.OFFSET);
				filterFormData.addElement(CRONIOBOILogFilterDataConstants.OFFSET, offsetData);
				getFilteredLogs(filterFormData);
			} else {
				AEMFTMetadataElementSingle executionIdDataSingle = (AEMFTMetadataElementSingle) executionData.getElement(CRONIOBOIExecution.EXECUTION_ID);
				executionId 	= executionIdDataSingle.getValueAsLong();
				executionIdStr	= String.valueOf(executionId);	
				offset 			= evt.getElementAsInt(CRONIOBOILog.OFFSET);
				numberResults 	= evt.getElementAsInt(CRONIOBOILog.NUMBER_RESULTS);

				if (offset < 0 || numberResults < 0) {
					getExecutionLogsData(executionIdStr, 0, 20);
				} else{
					getExecutionLogsData(executionIdStr, offset, numberResults);
				}
			} 	
		} else if (LOGICAL_TYPE.SAVE_EVENT.equals(evtTyp) && CRONIOBusDesktopProcessAddNodeListForm.NAME.equals(sourceWidget)) {
			String nodeListName = evt.getElementAsString(CRONIOBOINodeList.NODELIST_NAME);
			String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
			if (AEMFTCommonUtilsBase.isEmptyString(currentProjectId)) {
				currentProjectId = evt.getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID);
			}
			addNodeListToDB(nodeListName, currentProjectId);
		}	
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return	LOGICAL_TYPE.SELECT_EVENT.equals(type) || 
				LOGICAL_TYPE.SUBMIT_EVENT.equals(type)||
				LOGICAL_TYPE.GET_EVENT.equals(type) ||
				LOGICAL_TYPE.SAVE_EVENT.equals(type);
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
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentSectionId);
		String finalSectionKey = sbKey.toString();

		if (!CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(currentSectionId)) {
			getElementDataController().setElement(CRONIOBOIProject.INFO_IS_MODIFIED, finalSectionData, true);
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

	private void getExecutionLogsData(String executionId, int offset, int numberResults) {

		AEMFTMetadataElementComposite logData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		logData.addElement(CRONIOBOIExecutionDataConstants.EXECUTION_ID		, executionId);
		logData.addElement(CRONIOBOILogDataConstants.OFFSET					, offset);
		logData.addElement(CRONIOBOILogDataConstants.NUMBER_RESULTS			, numberResults);

		getClientServerConnection().executeComm(logData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_LOG_GET_EXECUTION_LOGS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite 	executionLogsData 		= dataResult.getCompositeElement(CRONIOBUIExecuteBusinessServiceConstants.EXECUTION_LOGS_DATA);
					CRONIOBusDesktopProjectEvent 	getExecutionLogsEvt 	= new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
					AEMFTMetadataElementSingle 		isFilterData 			= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.ISFILTER);
					AEMFTMetadataElementSingle 		offsetData				= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.OFFSET);
					AEMFTMetadataElementSingle 		numberResultsData		= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.NUMBER_RESULTS);
					AEMFTMetadataElementSingle 		totalExecutionLogsData	= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.TOTAL_EXECUTION_LOGS);
					boolean 	isFilter 			= isFilterData.getValueAsBool();
					int 		offset 				= offsetData.getValueAsInt();
					int 		numberResults 		= numberResultsData.getValueAsInt();
					int			totalExecutionLogs	= totalExecutionLogsData.getValueAsInt();

					getExecutionLogsEvt.setEventType(EVENT_TYPE.ADD_EXECUTION_LOGS);
					getExecutionLogsEvt.addElementAsComposite(CRONIOBUIExecuteBusinessServiceConstants.EXECUTION_LOGS_DATA, executionLogsData);
					getExecutionLogsEvt.addElementAsBoolean(CRONIOBOILog.ISFILTER			, isFilter);
					getExecutionLogsEvt.addElementAsInt(CRONIOBOILog.OFFSET					, offset);
					getExecutionLogsEvt.addElementAsInt(CRONIOBOILog.NUMBER_RESULTS			, numberResults);
					getExecutionLogsEvt.addElementAsInt(CRONIOBOILog.TOTAL_EXECUTION_LOGS	, totalExecutionLogs);
					getLogicalEventHandlerManager().fireEvent(getExecutionLogsEvt);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void addExecutionToDB(String currentId, String nodeListName) {

		AEMFTMetadataElementComposite newExecutionData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		newExecutionData.addElement(CRONIOBOIExecution.PROJECT_ID		, currentId);
		newExecutionData.addElement(CRONIOBOINodeList.NODELIST_NAME		, nodeListName);

		getClientServerConnection().executeComm(newExecutionData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_EXECUTE_ADD_EXECUTION_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementSingle nodeListNameData = (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOINodeList.NODELIST_NAME);
					String nodeListName = nodeListNameData.getValueAsString();
					AEMFTMetadataElementComposite executionData = dataResult.getCompositeElement(CRONIOBUIExecuteBusinessServiceConstants.EXECUTION_DATA);
					String dateExecutionStr = getElementDataController().getElementAsString(CRONIOBOIExecution.CREATION_TIME, executionData);
					dateExecutionStr = dateExecutionStr.replace("/", "-");
					String 	projectId 		= getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID, executionData);
					Long 	executionId 	= getElementDataController().getElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionData);
					String 	executionIdStr	= String.valueOf(executionId);	
					projectsLayout.addExecution(projectId, dateExecutionStr);

					StringBuilder sbKey = new StringBuilder();
					sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
					sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
					sbKey.append(CRONIODesktopIAppControllerConstants.EXECUTIONS_DATA);
					sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
					sbKey.append(projectId);
					String finalSectionKey = sbKey.toString();

					AEMFTMetadataElementComposite executionsData = (AEMFTMetadataElementComposite) getContextDataController().getElement(finalSectionKey);

					if (executionsData != null) {
						executionsData = (AEMFTMetadataElementComposite) executionsData.cloneObject();

					} else {
						executionsData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite(); 
					}

					executionData.setKey(executionIdStr);
					executionsData.addElement(executionData);

					AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
					storageEvent.setFullKey(finalSectionKey);
					storageEvent.addElementAsDataValue(executionsData);
					storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
					getLogicalEventHandlerManager().fireEvent(storageEvent);

					CRONIOBusDesktopProjectEvent addExecutionEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
					addExecutionEvt.setEventType(EVENT_TYPE.EXECUTE);
					addExecutionEvt.addElementAsString(CRONIOBOIExecution.PROJECT_ID, projectId);
					addExecutionEvt.addElementAsLong(CRONIOBOIExecution.EXECUTION_ID, executionId);
					addExecutionEvt.addElementAsString(CRONIOBOINodeList.NODELIST_NAME, nodeListName);
					getLogicalEventHandlerManager().fireEvent(addExecutionEvt);
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void addNodeListToDB(String nodeListName, String currentProjectId) {

		AEMFTMetadataElementComposite newNodeListData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

		newNodeListData.addElement(CRONIOBOINodeListDataConstants.NODELIST_NAME	, nodeListName);
		newNodeListData.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID		, currentProjectId);
		
		getClientServerConnection().executeComm(newNodeListData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_ADD_NODELIST_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite errorData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.KEY_EXIST_ERRORS);
					if (errorData != null) {
						CRONIOBusDesktopProjectEvent errorDuplicateNodeListNameEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
						errorDuplicateNodeListNameEvt.setEventType(EVENT_TYPE.DUPLICATE_NODELIST_ERROR);
						errorDuplicateNodeListNameEvt.addElementAsDataValue(errorData);
						getLogicalEventHandlerManager().fireEvent(errorDuplicateNodeListNameEvt);
					} else {
						AEMFTMetadataElementComposite nodeListData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.NODELIST_DATA);
						String projectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
						String nodeListName	= getElementDataController().getElementAsString(CRONIOBOINodeList.NODELIST_NAME	, nodeListData);	
						if (AEMFTCommonUtilsBase.isEmptyString(projectId)) {
							AEMFTMetadataElementSingle projectIdData = (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOIProjectDataConstants.PROJECT_ID);
							projectId	= projectIdData.getValueAsString();
						}
						projectsLayout.addNodeList(projectId, nodeListName);

						StringBuilder sbKey = new StringBuilder();
						sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
						sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
						sbKey.append(projectId);
						sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
						sbKey.append(CRONIOBOINodeList.NODELIST_PROCESS);
						sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
						sbKey.append(CRONIOBOIProcess.PROCESS_NODELIST_LIST);
						String finalSectionKey = sbKey.toString();

						AEMFTMetadataElementComposite listNodeListData = (AEMFTMetadataElementComposite) getContextDataController().getElement(finalSectionKey);

						if (listNodeListData != null) {
							listNodeListData = (AEMFTMetadataElementComposite) listNodeListData.cloneObject();
						} else {
							listNodeListData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite(); 
						}

						nodeListData.setKey(nodeListName);
						listNodeListData.addElement(nodeListName ,nodeListData);

						AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
						storageEvent.setFullKey(finalSectionKey);
						storageEvent.addElementAsDataValue(listNodeListData);
						storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
						getLogicalEventHandlerManager().fireEvent(storageEvent);
						
						getContextDataController().setElement(finalSectionKey, listNodeListData.cloneObject());

						CRONIOBusDesktopProjectEvent nodeListCreaTedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
						nodeListCreaTedEvt.setEventType(EVENT_TYPE.NODELIST_CREATED);
						nodeListCreaTedEvt.addElementAsString(CRONIOBOIProject.PROJECT_ID, projectId);
						getLogicalEventHandlerManager().fireEvent(nodeListCreaTedEvt);
					}
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
		newProjectData.addElement(CRONIOBOIProject.PROJECT_NAME			, projectName);
		newProjectData.addElement(CRONIOBOIProject.PROJECT_MACHINE_TYPE	, machinetTypeInt);

		String userIdKey = AEGWTILoginAppControllerConstants.SESSION + CRONIOBusCommonConstants.ELEMENT_SEPARATOR + AEGWTILoginAppControllerConstants.USER_ID;
		AEMFTMetadataElementSingle userIdData = (AEMFTMetadataElementSingle) getContextDataController().getElement(userIdKey);
		String userIdStr = userIdData.getValueAsString();

		newProjectData.addElement(CRONIOBOIUser.USER_ID, userIdStr);

		getClientServerConnection().executeComm(newProjectData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_ADD_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA);
					if (projectData != null) {
						String projectId = getElementDataController().getElementAsString(CRONIOBOIProject.PROJECT_ID, projectData);
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
			sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);

			String dateRegEx 	= "[0-9]{2}\\-[0-9]{2}\\-[0-9]{4}\\s([0-9]{2})\\:([0-9]{2})\\:([0-9]{2})";

			if (projectFinalSectionId.matches(dateRegEx)) {
				sbKey.append(CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA);
				sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
				sbKey.append(projectId);
				isLog = true;
			} else {
				sbKey.append(projectId);
				if (!CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(projectFinalSectionId)) {
					sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
					//Final Section Data
					sbKey.append(projectFinalSectionId);
				}
			}

			String finalSectionKey = sbKey.toString();

			//Project name
			StringBuilder projectNamesbKey = new StringBuilder();
			projectNamesbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			projectNamesbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			projectNamesbKey.append(projectId);
			projectNamesbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			projectNamesbKey.append(CRONIOBOIProject.PROJECT_NAME);
			String projectNameKey = projectNamesbKey.toString();
			String projectName = getContextDataController().getElementAsString(projectNameKey);

			AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(finalSectionKey);
			if (finalSectionData != null) {
				finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
				if (isLog) {
					List<AEMFTMetadataElement> executionList = finalSectionData.getSortedElementList();
					for (AEMFTMetadataElement execution : executionList) {
						AEMFTMetadataElementComposite executionData = (AEMFTMetadataElementComposite) execution;
						AEMFTMetadataElementSingle creationTime = (AEMFTMetadataElementSingle) executionData.getElement(CRONIOBOIExecution.CREATION_TIME);
						String creationTimeStr = creationTime.getValueAsSerializable().toString();
						creationTimeStr = creationTimeStr.replace("/", "-");
						String[] creationTimeStrSplit 	= creationTimeStr.split("\\.");
						String[] creationTimeStrSplit1 	= creationTimeStrSplit[0].split("\\-");
						String[] creationTimeStrSplit2 	= creationTimeStrSplit1[2].split(" ");
						String creationTimeStrFormat = creationTimeStrSplit2[0] + "-" + creationTimeStrSplit1[1] + "-" + creationTimeStrSplit1[0] + " " + creationTimeStrSplit2[1];
						if(creationTimeStrFormat.equals(projectFinalSectionId) || creationTimeStr.equals(projectFinalSectionId)) {
							finalSectionData = executionData;
						}
					}

				}
			}
			
			boolean sectionIsModified = getElementDataController().getElementAsBoolean(CRONIOBOIProject.INFO_IS_MODIFIED, finalSectionData);

			//SHOW HEADER INFO
			CRONIOBusDesktopProjectEvent showInfoEvent = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
			showInfoEvent.addElementAsString(CRONIOBOIProject.PROJECT_NAME	, projectName);
			showInfoEvent.addElementAsBoolean(CRONIOBOIProject.INFO_IS_MODIFIED	, sectionIsModified);
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
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		String projectDataKey = sbKey.toString();

		String userIdKey = AEGWTILoginAppControllerConstants.SESSION + CRONIOBusCommonConstants.ELEMENT_SEPARATOR + AEGWTILoginAppControllerConstants.USER_ID;
		AEMFTMetadataElementSingle userIdData = (AEMFTMetadataElementSingle) getContextDataController().getElement(userIdKey);
		String userIdStr = userIdData.getValueAsString();

		AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
		projectData.addElement(CRONIOBOIUser.USER_ID, userIdStr);

		getClientServerConnection().executeComm(projectData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_UPDATE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA);
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
		sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
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
			sbKey.append(CRONIOBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(projectId);
			String projectDataKey = sbKey.toString();

			AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
			projectsData.addElement(projectId, projectData);
		}

		AEMFTMetadataElementComposite contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		contextIn.addElement(CRONIOBUIProjectBusinessServiceConstants.PROJECTS_DATA, projectsData);
		getClientServerConnection().executeComm(contextIn, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_UPDATE_PROJECTS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectsData = dataResult.getCompositeElement(CRONIOBUIProjectBusinessServiceConstants.PROJECTS_DATA);
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
		if (!CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(currentSectionId)) {
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

	private void executeProject(String projectId, Long executionId, String nodeListName) {
		AEMFTMetadataElementComposite contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		contextIn.addElement(CRONIOBOIExecution.PROJECT_ID, projectId);
		contextIn.addElement(CRONIOBOIExecution.EXECUTION_ID, executionId);
		contextIn.addElement(CRONIOBOINodeList.NODELIST_NAME, nodeListName);
		getClientServerConnection().executeComm(contextIn, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_EXECUTE_EXECUTE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

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

	private void getFilteredLogs(AEMFTMetadataElementComposite filterData) {


		getClientServerConnection().executeComm(filterData, CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_LOG_GET_FILTERED_LOGS, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				AEMFTMetadataElementComposite 	filteredLogsData 		= dataResult.getCompositeElement(CRONIOBUILogBusinessServiceConstants.FILTERED_LOGS_DATA);
				CRONIOBusDesktopProjectEvent 	getFilteredLogsEvt 		= new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
				AEMFTMetadataElementSingle 		isFilterData 			= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.ISFILTER);
				AEMFTMetadataElementSingle 		offsetData				= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.OFFSET);
				AEMFTMetadataElementSingle 		numberResultsData		= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.NUMBER_RESULTS);
				AEMFTMetadataElementSingle 		totalFilteredLogsData	= (AEMFTMetadataElementSingle) dataResult.getElement(CRONIOBOILog.TOTAL_FILTERED_LOGS);
				boolean 	isFilter 			= isFilterData.getValueAsBool();
				int 		offset 				= offsetData.getValueAsInt();
				int 		numberResults 		= numberResultsData.getValueAsInt();
				int			totalFilteredLogs	= totalFilteredLogsData.getValueAsInt();

				getFilteredLogsEvt.setEventType(EVENT_TYPE.ADD_FILTERED_LOGS);
				getFilteredLogsEvt.addElementAsComposite(CRONIOBUILogBusinessServiceConstants.FILTERED_LOGS_DATA, filteredLogsData);
				getFilteredLogsEvt.addElementAsBoolean(CRONIOBOILog.ISFILTER			, isFilter);
				getFilteredLogsEvt.addElementAsInt(CRONIOBOILog.OFFSET					, offset);
				getFilteredLogsEvt.addElementAsInt(CRONIOBOILog.NUMBER_RESULTS			, numberResults);
				getFilteredLogsEvt.addElementAsInt(CRONIOBOILog.TOTAL_FILTERED_LOGS		, totalFilteredLogs);
				getLogicalEventHandlerManager().fireEvent(getFilteredLogsEvt);	

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
			}
		});
	}
}
