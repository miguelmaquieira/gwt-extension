package com.imotion.dslam.business.service.impl;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.bom.data.DSLAMBOProcess;
import com.imotion.dslam.business.service.DSLAMBUBomToMetadataConversor;
import com.imotion.dslam.business.service.DSLAMBUBusinessServiceBase;
import com.imotion.dslam.business.service.DSLAMBUIBusinessProcessServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;

public class DSLAMBUProcessBusinessServiceImpl extends DSLAMBUBusinessServiceBase implements DSLAMBUIProcessBusinessService, DSLAMBUIProcessBusinessServiceConstants, DSLAMBUIBusinessProcessServiceTrace {

	private static final long serialVersionUID = 15615107513521193L;

	@Override
	public void addProcess() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String 					processName		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME					, contextIn);
		boolean 				synchronous		= getElementDataController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_SYNCHRONOUS			, contextIn);
		String					processScriptId	= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT					, contextIn);
		//List<Date> 				scheduleList	= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_LIST			, contextIn);
		//List<DSLAMBOVariable> 	variableList	= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_VARIABLE_LIST			, contextIn);

		Long formatProcessScriptId = AEMFTCommonUtilsBase.getLongFromString(processScriptId);
		
		DSLAMBOIFile script = getFilePersistence().getFile(formatProcessScriptId);
		
		Date creationTime = new Date();
		DSLAMBOIProcess process = new DSLAMBOProcess();
		process.setProcessName(processName);
		process.setSynchronous(synchronous);
	//	process.setScheduleList(scheduleList);
	//	process.setVariableList(variableList);
		process.setCreationTime(creationTime);
		process.setSavedTime(creationTime);
		process.setProcessScript(script);
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
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String 					processId		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_ID		, contextIn);
		String 					processName		= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME		, contextIn);
		boolean 				synchronous		= getElementDataController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_SYNCHRONOUS		, contextIn);
	//	List<Date> 				scheduleList	= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_LIST		, contextIn);
	//	List<DSLAMBOVariable> 	variableList	= getElementDataController().getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_VARIABLE_LIST		, contextIn);
		
		
		Long processIdAsLong 	= AEMFTCommonUtilsBase.getLongFromString(processId);
		DSLAMBOIProcess updatedProcess = null;
		//TODO
//		if (AEMFTCommonUtilsBase.isEmptyString(processName)) {
//			updatedProcess = getProcessPersistence().updateProcessSynchronous(processIdAsLong	, synchronous);
//		} else {
//			updatedProcess = getProcessPersistence().updateProcessName(processIdAsLong			, processName);
//		}

		//init-trace
		traceItemModifiedInPersistence(METHOD_UPDATE_PROCESS, DSLAMBOIProcess.class.getSimpleName(), processId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcess(updatedProcess);
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
		AEMFTMetadataElementComposite processDataElement = DSLAMBUBomToMetadataConversor.fromProcessList(processList, fileList);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PROCESS_DATA, processDataElement);
	}

}
