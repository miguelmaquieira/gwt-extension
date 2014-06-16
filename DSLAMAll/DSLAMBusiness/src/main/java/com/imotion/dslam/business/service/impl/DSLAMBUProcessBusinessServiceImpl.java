package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.bom.data.DSLAMBOVariable;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessProcessServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class DSLAMBUProcessBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements DSLAMBUIProcessBusinessService, DSLAMBUIProcessBusinessServiceConstants, DSLAMBUIBusinessProcessServiceTrace {

	private static final long serialVersionUID = 15615107513521193L;

	@Override
	public void addProcess() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String 					processName		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME					, contextIn);

		Date creationTime = new Date();
		DSLAMBOIProcess process = new DSLAMBOProcess();
		process.setProcessName(processName);
		process.setCreationTime(creationTime);
		process.setSavedTime(creationTime);
		process = getProcessPersistence().addProcess(process);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_PROCESS, DSLAMBOIProcess.class.getSimpleName(), String.valueOf(process.getProcessId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcess(process);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROCESS_DATA, processDataElement);
	}

	@Override
	public void updateProcess() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		AEMFTMetadataElementComposite 	optionsData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_OPTIONS_DATA	, contextIn);
		AEMFTMetadataElementComposite 	propertiesData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_PROPERTIES_DATA	, optionsData);
		AEMFTMetadataElementComposite 	variablesData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_VARIABLES_DATA	, optionsData);
		AEMFTMetadataElementComposite 	scheduleData 	= getElementDataController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_DATA	, optionsData);
		boolean 						synchronous		= getElementDataController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_EXTRA_OPTIONS		, propertiesData);
		String 							processId		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_ID					, contextIn);
		String 							processName		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME				, contextIn);


		DSLAMBOIProcess updatedProcess = null;
		Long processIdAsLong 		= AEMFTCommonUtilsBase.getLongFromString(processId);

		if (AEMFTCommonUtilsBase.isEmptyString(processName)) {
			List<Date> scheduleList= new ArrayList<>();
			String formatDate = "dd/MM/yyyy HH:mm";
			for (int i= 0; i < scheduleData.getElementList().size(); i++) {
				AEMFTMetadataElementSingle schedule = (AEMFTMetadataElementSingle) scheduleData.getElement(String.valueOf(i));
				Date date = AEMFTCommonUtils.getDateFromFormattedString(schedule.getValueAsString(), formatDate);
				scheduleList.add(date);
			}

			List<DSLAMBOIVariable> variableList= new ArrayList<>();
			List<AEMFTMetadataElement> variableListData =  variablesData.getSortedElementList();
			String id 		= "";
			String value 	= "";
			String type		= "";

			if(!AEMFTCommonUtilsBase.isEmptyList(variableListData)){
				for (AEMFTMetadataElement variableData : variableListData) {
					DSLAMBOIVariable variable = new DSLAMBOVariable();
					AEMFTMetadataElementComposite variableComposite = (AEMFTMetadataElementComposite) variableData;
					id 		= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, variableComposite);
					value 	= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variableComposite);
					type 	= getElementDataController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, variableComposite);

					variable.setVariableName(id);
					variable.setVariableValue(value);
					variable.setVariableType(Integer.parseInt(type));
					variableList.add(variable);
				}
			}

			updatedProcess = getProcessPersistence().updateProcessSynchronous(processIdAsLong	, synchronous);
			updatedProcess = getProcessPersistence().updateProcessScheduleList(processIdAsLong	, scheduleList);
			updatedProcess = getProcessPersistence().updateProcessVariableList(processIdAsLong	, variableList);	
		} else {
			updatedProcess = getProcessPersistence().updateProcessName(processIdAsLong			, processName);
		}
	
	//init-trace
	traceItemModifiedInPersistence(METHOD_UPDATE_PROCESS, DSLAMBOIProcess.class.getSimpleName(), processId);
	//end-trace

	//ContextOut
	AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcessFull(updatedProcess, getSession().getCurrentLocale());
	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
	contextOut.addElement(PROCESS_DATA, processDataElement);
}

@Override
public void removeProcess() {
	//ContextIn
	AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
	String processId		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_ID		, contextIn);

	Long processIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(processId);
	getProcessPersistence().removeProcess(processIdAsLong);

	//init-trace
	traceItemRemovedFromPersistence(METHOD_REMOVE_PROCESS, DSLAMBOIProcess.class.getSimpleName(), processId);
	//end-trace

	//ContextOut
	AEMFTMetadataElementComposite processDataElement = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
	processDataElement.addElement(DSLAMBOIProcessDataConstants.PROCESS_ID, processIdAsLong);

	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
	contextOut.addElement(PROCESS_DATA, processDataElement);
}


@Override
public void getAllProcesses() {
	List<DSLAMBOIProcess> 	processList = getProcessPersistence().getAllProcesses();
	List<DSLAMBOIFile> 		fileList 	= getFilePersistence().getAllFiles();

	//trace-init
	int resultsNumber = 0;
	if (!AEMFTCommonUtilsBase.isEmptyList(processList)) {
		resultsNumber = processList.size();
	}
	traceNumberOfResults(METHOD_GET_ALL_PROCESSES, DSLAMBOIProcess.class.getSimpleName(), resultsNumber);
	//end-trace

	//ContextOut
	AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcessList(processList, fileList, getSession().getCurrentLocale());
	AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
	contextOut.addElement(PROCESS_DATA, processDataElement);
}

}
