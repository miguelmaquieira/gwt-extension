package com.imotion.dslam.business.service.impl;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessService;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;

public class CRONIOBULogBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUILogBusinessService, CRONIOBUILogBusinessServiceConstants, CRONIOBUILogBusinessServiceTrace {

	private static final long serialVersionUID = 698695971124755962L;

	public CRONIOBULogBusinessServiceImpl() {
		super();
	}
	
	
	@Override
	public void getAllLogs() { 
		
		List<CRONIOBOILog> logs = getLogPersistence().getAllLogs();
		
//		AEMFTMetadataElementComposite preferencesData = DSLAMBUBomToMetadataConversor.fromPreferences(preferences);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		AEMFTMetadataElement logsData = null;
		contextOut.addElement(LOGS_DATA, logsData);
	}
	
	@Override
	public void getExecutionLogs() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		AEMFTMetadataElementSingle 		executionIdData = (AEMFTMetadataElementSingle) contextIn.getElement(CRONIOBOIExecution.EXECUTION_ID);
		String							executionId 	= executionIdData.getValueAsString();
		
		List<CRONIOBOILog> logs = getLogPersistence().getExecutionLogs(executionId);
		
//		List<CRONIOBOLog> lineLogList = get
		int i = 0;
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

}
