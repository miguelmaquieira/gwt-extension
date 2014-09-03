package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.antlr.executor.CRONIOExecutorImpl;
import com.imotion.dslam.antlr.executor.CRONIOIExecutor;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIExecutionDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceTrace;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.imotion.dslam.business.service.utils.DSLAMBUBomToMetadataConversor;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBUExecuteBusinessServiceImpl extends DSLAMBUServiceBase implements DSLAMBUIExecuteBusinessService, CRONIOBUIExecuteBusinessServiceConstants, DSLAMBUIExecuteBusinessServiceTrace {

	private static final long serialVersionUID = 7761400309777540451L;
	
	public CRONIOBUExecuteBusinessServiceImpl() {
		super();
	}

	@Override
	public void executeProject() {
		AEMFTMetadataElementComposite 	contextIn = getContext().getContextDataIN();
		String							projectIdStr 			= getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID, contextIn);
		long							projectId				= AEMFTCommonUtilsBase.getLongFromString(projectIdStr);
		AEMFTMetadataElementSingle		executionIdDataSingle	= (AEMFTMetadataElementSingle) getElementDataController().getElement(CRONIOBOIExecution.EXECUTION_ID, contextIn);
		long							executionId				= executionIdDataSingle.getValueAsLong();
		
		DSLAMBOIProject project = getProjectPersistence().getProject(projectId);
		
		if (project != null) {
			//init-trace
			traceItemRecoveredFromPersistence(METHOD_EXECUTE_PROJECT, DSLAMBOIProject.class, projectIdStr);
			//end-trace

			CRONIOIExecutor executor = getExecutor(project);
			executor.execute(executionId);
		} else {
			//init-trace
			traceItemNotFound(METHOD_EXECUTE_PROJECT, DSLAMBOIProject.class, projectIdStr);
			//end-trace
		}
	
	}
	
	@Override
	public void addExecution() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 							projectId		= getElementDataController().getElementAsString(CRONIOBOIExecutionDataConstants.PROJECT_ID		, contextIn);
		Date 							creationTime 	= new Date();
		Long 							projectIdAsLong = Long.valueOf(projectId).longValue();
		DSLAMBOIProject 				project 		= getProjectPersistence().getProject(projectIdAsLong);
		
		CRONIOBOIExecution execution = new CRONIOBOExecution();
		execution.setProject(project);
		execution.setCreationTime(creationTime);
		CRONIOBOIExecution executionSave = getExecutionPersistence().addExecution(execution);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_EXECUTION, CRONIOBOIExecution.class.getSimpleName(), projectId);
		//end-trace

		
		//ContextOut
		AEMFTMetadataElementComposite dateExecutionData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		String formatDate = "dd/MM/yyyy HH:mm:ss";
		String creationDateStr = AEMFTCommonUtils.formatDate(creationTime, formatDate, getSession().getCurrentLocale());
		dateExecutionData.addElement(CRONIOBOIExecution.CREATION_TIME, creationDateStr);
		dateExecutionData.addElement(CRONIOBOIExecution.PROJECT_ID, projectId);
		dateExecutionData.addElement(CRONIOBOIExecution.EXECUTION_ID, executionSave.getExecutionId());
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(EXECUTION_DATA, dateExecutionData);
	}

	@Override
	public void getAllExecutionsByProjectId() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		AEMFTMetadataElementComposite 	projectListdata = (AEMFTMetadataElementComposite) getElementDataController().getElementAsComposite(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, contextIn).cloneObject();
		int 							resultsNumber 	= 0;
		Long 							projectIdAsLong = null;
		
		
		List<AEMFTMetadataElement> projectList = projectListdata.getElementList();
		List<String> projectIdList = new ArrayList<>();
		for (AEMFTMetadataElement project : projectList) {
			String projectId = project.getKey();
			projectIdList.add(projectId);
		}
		
		AEMFTMetadataElementComposite executionsData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		
		for (String projectId : projectIdList) {
			projectIdAsLong = Long.valueOf(projectId).longValue();
			List<CRONIOBOIExecution> executionProjectList = getExecutionPersistence().getAllExecutionsByProject(projectIdAsLong);
			if (!AEMFTCommonUtilsBase.isEmptyList(executionProjectList)) {
				resultsNumber = resultsNumber + executionProjectList.size();
				AEMFTMetadataElementComposite executionProjectListData = DSLAMBUBomToMetadataConversor.fromExecutionsProjectList(executionProjectList);
				executionsData.addElement(projectId, executionProjectListData);
			}
		}
		
		//init-trace
		traceNumberOfResults(METHOD_GET_ALL_EXECUTIONS_BY_PROJECT_ID, CRONIOBOIExecution.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(EXECUTIONS_DATA, executionsData);
	}
	
	@Override
	public void getExecution() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		AEMFTMetadataElementSingle 		executionIdData = (AEMFTMetadataElementSingle) contextIn.getElement(CRONIOBOIExecution.EXECUTION_ID);
		String							executionId 	= executionIdData.getValueAsString();
		
//		List<CRONIOBOLog> lineLogList = get
//		int i = 0;
//		List<AEMFTMetadataElement> projectList = projectListdata.getElementList();
//		List<String> projectIdList = new ArrayList<>();
//		for (AEMFTMetadataElement project : projectList) {
//			String projectId = project.getKey();
//			projectIdList.add(projectId);
//		}
//		
//		AEMFTMetadataElementComposite executionsData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
//		
//		for (String projectId : projectIdList) {
//			projectIdAsLong = Long.valueOf(projectId).longValue();
//			List<CRONIOBOIExecution> executionProjectList = getExecutionPersistence().getAllExecutionsByProject(projectIdAsLong);
//			if (!AEMFTCommonUtilsBase.isEmptyList(executionProjectList)) {
//				resultsNumber = resultsNumber + executionProjectList.size();
//				AEMFTMetadataElementComposite executionProjectListData = DSLAMBUBomToMetadataConversor.fromExecutionsProjectList(executionProjectList);
//				executionsData.addElement(projectId, executionProjectListData);
//			}
//		}
//		
//		//init-trace
//		traceNumberOfResults(METHOD_GET_ALL_EXECUTIONS_BY_PROJECT_ID, CRONIOBOIExecution.class.getSimpleName(), resultsNumber);
//		//end-trace
//
//		//ContextOut
//		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
//		contextOut.addElement(EXECUTIONS_DATA, executionsData);
	}
	
	/**
	 * PRIVATE 
	 */
	private CRONIOIExecutor getExecutor(DSLAMBOIProject project) {
		CRONIOIExecutor executor = null;
		try {
			executor = new CRONIOExecutorImpl(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executor;
	}
}
