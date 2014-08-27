package com.imotion.dslam.business.service.impl;

import java.util.Date;

import com.imotion.dslam.antlr.executor.CRONIOExecutorImpl;
import com.imotion.dslam.antlr.executor.CRONIOIExecutor;
import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOIExecutionDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.CRONIOBOExecution;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.appli.metadata.element.factory.AEMFTMetadataElementReflectionBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBUExecuteBusinessServiceImpl extends DSLAMBUServiceBase implements DSLAMBUIExecuteBusinessService, DSLAMBUIExecuteBusinessServiceConstants, DSLAMBUIExecuteBusinessServiceTrace {

	private static final long serialVersionUID = 7761400309777540451L;
	
	public CRONIOBUExecuteBusinessServiceImpl() {
		super();
	}

	@Override
	public void executeProject() {
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String	projectIdStr 	= getElementDataController().getElementAsString(DSLAMBOProject.PROJECT_ID, contextIn);
		long	projectId		= AEMFTCommonUtilsBase.getLongFromString(projectIdStr);

		DSLAMBOIProject project = getProjectPersistence().getProject(projectId);
		if (project != null) {
			//init-trace
			traceItemRecoveredFromPersistence(METHOD_EXECUTE_PROJECT, DSLAMBOIProject.class, projectIdStr);
			//end-trace

			CRONIOIExecutor executor = getExecutor(project);
			executor.execute();
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
		
//		String 							userIdStr		= getElementDataController().getElementAsString(CRONIOBOIUser.USER_ID			, contextIn);
//		long							userId			= AEMFTCommonUtilsBase.getLongFromString(userIdStr);
		
		CRONIOBOIExecution execution = new CRONIOBOExecution();
		
		Long projectIdasLong = Long.valueOf(projectId).longValue();
		DSLAMBOIProject project = getProjectPersistence().getProject(projectIdasLong);
		
		execution.setProject(project);
		execution.setCreationTime(creationTime);
		
		
		getExecutionPersistence().addExecution(execution);

		//init-trace
		traceNewItemPersistent(METHOD_ADD_EXECUTION, CRONIOBOIExecution.class.getSimpleName(), projectId);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite dateExecutionData = AEMFTMetadataElementReflectionBasedFactory.getMonoInstance().getComposite();
		String formatDate = "dd/MM/yyyy HH:mm";
		String creationDateStr = AEMFTCommonUtils.formatDate(creationTime, formatDate, getSession().getCurrentLocale());
		dateExecutionData.addElement(CRONIOBOIExecution.CREATION_TIME, creationDateStr);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(DATE_EXECUTION_DATA, dateExecutionData);
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
