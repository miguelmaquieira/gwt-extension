package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.antlr.executor.CRONIOExecutorImpl;
import com.imotion.dslam.antlr.executor.CRONIOIExecutor;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceTrace;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.CRONIOBUServiceBase;
import com.imotion.dslam.business.service.utils.CRONIOBUBomToMetadataConversor;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBUExecuteBusinessServiceImpl extends CRONIOBUServiceBase implements CRONIOBUIExecuteBusinessService, CRONIOBUIExecuteBusinessServiceConstants, CRONIOBUIExecuteBusinessServiceTrace {

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
		String							nodeListName 			= getElementDataController().getElementAsString(CRONIOBOINodeList.NODELIST_NAME, contextIn);
		
		CRONIOBOIProject project = getProjectPersistence().getProject(projectId);
		
		if (project != null) {
			//init-trace
			traceItemRecoveredFromPersistence(METHOD_EXECUTE_PROJECT, CRONIOBOIProject.class, projectIdStr);
			//end-trace

			CRONIOIExecutor executor = getExecutor(project);
			executor.execute(executionId, nodeListName);
		} else {
			//init-trace
			traceItemNotFound(METHOD_EXECUTE_PROJECT, CRONIOBOIProject.class, projectIdStr);
			//end-trace
		}
	}
	
	@Override
	public void addExecution() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 							projectId		= getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID		, contextIn);
		String 							nodeListName	= getElementDataController().getElementAsString(CRONIOBOINodeList.NODELIST_NAME		, contextIn);
		Date 							creationTime 	= new Date();
		Long 							projectIdAsLong = Long.valueOf(projectId).longValue();
		CRONIOBOIProject 				project 		= getProjectPersistence().getProject(projectIdAsLong);
		
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
		contextOut.addElement(CRONIOBOINodeList.NODELIST_NAME, nodeListName);
	}

	@Override
	public void getAllExecutionsByProjectId() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		AEMFTMetadataElementComposite 	projectListdata = (AEMFTMetadataElementComposite) getElementDataController().getElementAsComposite(CRONIOBUIProjectBusinessServiceConstants.PROJECT_DATA_LIST, contextIn).cloneObject();
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
				AEMFTMetadataElementComposite executionProjectListData = CRONIOBUBomToMetadataConversor.fromExecutionsProjectList(executionProjectList);
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
	
	/**
	 * PRIVATE 
	 */
	private CRONIOIExecutor getExecutor(CRONIOBOIProject project) {
		CRONIOIExecutor executor = null;
		try {
			executor = new CRONIOExecutorImpl(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executor;
	}
}
