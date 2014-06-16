package com.imotion.dslam.business.service.impl;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessProjectServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class DSLAMBUProjectBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements DSLAMBUIProjectBusinessService, DSLAMBUIProjectBusinessServiceConstants, DSLAMBUIBusinessProjectServiceTrace {

	private static final long serialVersionUID = -3287573002047034251L;
	@Override
	public void addProject() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 	= getContext().getContextDataIN();
		String 							projectName	= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_NAME			, contextIn);
		String 							machineType	= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_MACHINE_TYPE		, contextIn);
		
		DSLAMBOIFile 	mainScript 		= new DSLAMBOFile();
		DSLAMBOIFile 	rollBackScript 	= new DSLAMBOFile();
		DSLAMBOIProcess process 		= new DSLAMBOProcess();
		
		int machineTypeInt = Integer.parseInt(machineType);
		Date creationTime = new Date();
		DSLAMBOIProject project = new DSLAMBOProject();
		project.setProjectName(projectName);
		project.setMachineType(machineTypeInt);
		project.setMainScript(mainScript);
		project.setRollBackScript(rollBackScript);
		project.setProcess(process);
		project.setCreationTime(creationTime);
		project.setSavedTime(creationTime);
		project = getProjectPersistence().addProject(project);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_PROJECT, DSLAMBOIProject.class.getSimpleName(), String.valueOf(project.getProjectId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProject(project);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROJECT_DATA, projectDataElement);
	}

	@Override
	public void updateProject() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
//		AEMFTMetadataElementComposite 	optionsData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_OPTIONS_DATA	, contextIn);
//		AEMFTMetadataElementComposite 	propertiesData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_PROPERTIES_DATA	, optionsData);
//		AEMFTMetadataElementComposite 	variablesData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA	, optionsData);
//		AEMFTMetadataElementComposite 	scheduleData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA	, optionsData);
//		boolean 						synchronous		= getElementDataController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_SYNCHRONOUS		, propertiesData);
//		String 							projectId		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_ID					, contextIn);
//		String 							projectName		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME				, contextIn);
//
//
//		DSLAMBOIProcess updatedProject = null;
//		Long projectIdAsLong 		= AEMFTCommonUtilsBase.getLongFromString(projectId);
//
//		if (AEMFTCommonUtilsBase.isEmptyString(projectName)) {
//			List<Date> scheduleList= new ArrayList<>();
//			String formatDate = "dd/MM/yyyy HH:mm";
//			for (int i= 0; i < scheduleData.getElementList().size(); i++) {
//				AEMFTMetadataElementSingle schedule = (AEMFTMetadataElementSingle) scheduleData.getElement(String.valueOf(i));
//				Date date = AEMFTCommonUtils.getDateFromFormattedString(schedule.getValueAsString(), formatDate);
//				scheduleList.add(date);
//			}
//
//			List<DSLAMBOVariable> variableList= new ArrayList<>();
//			List<AEMFTMetadataElement> variableListData =  variablesData.getSortedElementList();
//			String id 		= "";
//			String value 	= "";
//			String type		= "";
//
//			if(!AEMFTCommonUtilsBase.isEmptyList(variableListData)){
//				for (AEMFTMetadataElement variableData : variableListData) {
//					DSLAMBOVariable variable = new DSLAMBOVariable();
//					AEMFTMetadataElementComposite variableComposite = (AEMFTMetadataElementComposite) variableData;
//					id 		= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableComposite);
//					value 	= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableComposite);
//					type 	= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, variableComposite);
//
//					variable.setVariableName(id);
//					variable.setVariableValue(value);
//					variable.setVariableType(Integer.parseInt(type));
//					variableList.add(variable);
//				}
//			}
//
//			updatedProcess = getProcessPersistence().updateProcessSynchronous(processIdAsLong	, synchronous);
//			updatedProcess = getProcessPersistence().updateProcessScheduleList(processIdAsLong	, scheduleList);
//			updatedProcess = getProcessPersistence().updateProcessVariableList(processIdAsLong	, variableList);	
//		} else {
//			updatedProcess = getProcessPersistence().updateProcessName(processIdAsLong			, processName);
//		}
//
//	//init-trace
//	traceItemModifiedInPersistence(METHOD_UPDATE_PROCESS, DSLAMBOIProcess.class.getSimpleName(), processId);
//	//end-trace
//
//	//ContextOut
//	AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcessFull(updatedProcess, getSession().getCurrentLocale());
//	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//	contextOut.addElement(PROCESS_DATA, processDataElement);
}

@Override
public void removeProject() {
	//ContextIn
	AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
	String projectId		= getElementDataController().getElementAsString(CRONIOBOIProjectDataConstants.PROJECT_ID		, contextIn);

	Long projectIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(projectId);
	getProjectPersistence().removeProject(projectIdAsLong);

	//init-trace
	traceItemRemovedFromPersistence(METHOD_REMOVE_PROJECT, DSLAMBOIProject.class.getSimpleName(), projectId);
	//end-trace

	//ContextOut
	AEMFTMetadataElementComposite projectDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
	projectDataElement.addElement(CRONIOBOIProjectDataConstants.PROJECT_ID, projectIdAsLong);

	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
	contextOut.addElement(PROJECT_DATA, projectDataElement);
}


@Override
public void getAllProjects() {
	List<DSLAMBOIProject> 	projectList = getProjectPersistence().getAllProjects();

	//trace-init
	int resultsNumber = 0;
	if (!AEMFTCommonUtilsBase.isEmptyList(projectList)) {
		resultsNumber = projectList.size();
	}
	traceNumberOfResults(METHOD_GET_ALL_PROJECTS, DSLAMBOIProject.class.getSimpleName(), resultsNumber);
	//end-trace

	//ContextOut
	AEMFTMetadataElementComposite projectDataElement = DSLAMBUBomToMetadataConversor.fromProjectList(projectList,getSession().getCurrentLocale());
	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
	contextOut.addElement(PROJECT_DATA_LIST, projectDataElement);
}

}
