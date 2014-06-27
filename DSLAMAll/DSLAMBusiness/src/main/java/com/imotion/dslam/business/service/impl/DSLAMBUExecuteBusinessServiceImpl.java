package com.imotion.dslam.business.service.impl;

import com.imotion.dslam.antlr.executor.CRONIOExecutorDSLAM;
import com.imotion.dslam.antlr.executor.CRONIOIExecutor;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.bom.data.DSLAMBOProject;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessService;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIExecuteBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class DSLAMBUExecuteBusinessServiceImpl extends DSLAMBUServiceBase implements DSLAMBUIExecuteBusinessService, DSLAMBUIExecuteBusinessServiceConstants, DSLAMBUIExecuteBusinessServiceTrace {

	private static final long serialVersionUID = 7761400309777540451L;

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

	/**
	 * PRIVATE 
	 */
	private CRONIOIExecutor getExecutor(DSLAMBOIProject project) {
		int machineType = project.getMachineType();
		CRONIOIExecutor executor = null;
		try {
			if (machineType == DSLAMBOIProject.PROJECT_MACHINE_TYPE_DSLAM) {
				executor = new CRONIOExecutorDSLAM(project);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executor;
	}

}
