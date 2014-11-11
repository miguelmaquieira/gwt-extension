package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.antlr.executor.CRONIOExecutorImpl;
import com.imotion.dslam.antlr.executor.CRONIOIExecutor;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOILogNode;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.imotion.dslam.bom.data.CRONIOBOLogNode;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
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
		AEMFTMetadataElementSingle		executionIdDataSingle	= (AEMFTMetadataElementSingle) getElementDataController().getElement(CRONIOBOIExecution.EXECUTION_ID, contextIn);
		long							executionId				= executionIdDataSingle.getValueAsLong();
		String							nodeListName 			= getElementDataController().getElementAsString(CRONIOBOINodeList.NODELIST_NAME, contextIn);
		String							projectId				= getElementDataController().getElementAsString(CRONIOBOIExecution.PROJECT_ID, contextIn);

		CRONIOBOIProject project = getProjectPersistence().getProject(Long.parseLong(projectId));

		if (project != null) {
			//init-trace
			traceItemRecoveredFromPersistence(METHOD_EXECUTE_PROJECT, CRONIOBOIProject.class, projectId);
			//end-trace

			CRONIOIExecutor executor = getExecutor(project);
			CRONIOBOIExecution execution = getExecutionPersistence().getExecution(executionId);
			List<CRONIOBOILogNode> stateLogNodes = executor.execute(executionId, nodeListName);

		//wait

			List<CRONIOBOILogNode> logNodes = execution.getLogNodes();
			for (CRONIOBOILogNode logNode : logNodes) {
				for (CRONIOBOILogNode stateLogNode :stateLogNodes) {
					if (logNode.getNodeName().equals(stateLogNode.getNodeName())) {
						logNode.setState(stateLogNode.getState());
						getLogNodePersistence().addLogNode(logNode);
					}
				}
			}
			execution.setLogNodes(logNodes);
			execution.setFinishExecutionTime(new Date());
			getExecutionPersistence().addExecution(execution);

		} else {
			//init-trace
			traceItemNotFound(METHOD_EXECUTE_PROJECT, CRONIOBOIProject.class, projectId);
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
		String 							userIdStrId		= getElementDataController().getElementAsString(CRONIOBOIUser.USER_ID				, contextIn);
		long                           	userId			= Long.parseLong(userIdStrId);

		List<CRONIOBOINodeList> nodeLists = getNodeListPersistence().getAllNodeListsByProject(projectIdAsLong);
		List<CRONIOBOINode> 	nodes;
		List<CRONIOBOILogNode> 	logNodes = new ArrayList<>();
		String environmentName = "";

		for (CRONIOBOINodeList nodeList : nodeLists) {
			if(nodeListName.equals(nodeList.getNodeListName())) {
				nodes = nodeList.getNodeList();
				for (CRONIOBOINode node : nodes) {
					CRONIOBOILogNode newLogNode = new CRONIOBOLogNode();
					newLogNode.setNodeName(node.getNodeName());
					newLogNode.setNodeIp(node.getNodeIp());
					newLogNode.setNodeType(node.getNodeType());
					newLogNode.setCreationTime(new Date());
					newLogNode.setState(CRONIOBOILogNode.STATE_NULL);
					newLogNode= getLogNodePersistence().addLogNode(newLogNode);
					logNodes.add(newLogNode);	
				}
				environmentName = nodeList.getNodeListName();
			}
		}

		CRONIOBOIUser user = getUserPersistence().getUserById(userId);

		project.getProcess().isSynchronous();

		CRONIOBOIExecution execution = new CRONIOBOExecution();
		execution.setProject(project);
		execution.setCreationTime(creationTime);
		execution.setLogNodes(logNodes);
		execution.setEnvironmentName(environmentName);
		execution.setUser(user);
		execution.setIsSynchronous(project.getProcess().isSynchronous());
		CRONIOBOIExecution executionSave = getExecutionPersistence().addExecution(execution);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_EXECUTION, CRONIOBOIExecution.class.getSimpleName(), projectId);
		//end-trace

		//ContextOut

		AEMFTMetadataElementComposite dateExecutionData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		String formatDate = "yyyy-MM-dd HH:mm:ss";
		String creationDateStr = AEMFTCommonUtils.formatDate(creationTime, formatDate, getSession().getCurrentLocale());
		dateExecutionData.addElement(CRONIOBOIExecution.CREATION_TIME			, creationDateStr);
		dateExecutionData.addElement(CRONIOBOIExecution.PROJECT_ID				, projectId);
		dateExecutionData.addElement(CRONIOBOIExecution.EXECUTION_ID			, executionSave.getExecutionId());
		dateExecutionData.addElement(CRONIOBOIExecution.ENVIRONMENT_NAME		, environmentName);
		dateExecutionData.addElement(CRONIOBOIExecution.IS_SYNCHRONOUS			, executionSave.getIsSynchronous());
		dateExecutionData.addElement(CRONIOBOIExecution.LOGNODES				, CRONIOBUBomToMetadataConversor.fromLogNodeList(logNodes));
		dateExecutionData.addElement(CRONIOBOIExecution.USER					, CRONIOBUBomToMetadataConversor.fromUser(user, null));
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(EXECUTION_DATA, dateExecutionData);
		contextOut.addElement(CRONIOBOINodeList.NODELIST_NAME, nodeListName);
	}

	@Override
	public void getExecution() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String							executionIdStr	= getElementDataController().getElementAsString(CRONIOBOIExecution.EXECUTION_ID		, contextIn);
		long							executionId		= Long.parseLong(executionIdStr);
		long							projectId		= getElementDataController().getElementAsLong(CRONIOBOIExecution.PROJECT_ID			, contextIn);
		String							creationTime	= getElementDataController().getElementAsString(CRONIOBOIExecution.CREATION_TIME	, contextIn);

		CRONIOBOIExecution execution = getExecutionPersistence().getExecution(executionId);

		if (execution == null) {
			//init-trace
			traceItemNotFound(METHOD_GET_EXECUTION, CRONIOBOIExecution.class, executionIdStr);
			//end-trace
		}

		AEMFTMetadataElementComposite executionData = CRONIOBUBomToMetadataConversor.fromExecution(execution);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(EXECUTION_DATA, executionData);
		contextOut.addElement(CRONIOBOIExecution.PROJECT_ID, projectId);
		contextOut.addElement(CRONIOBOIExecution.CREATION_TIME, creationTime);

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
