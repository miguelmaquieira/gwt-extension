package com.imotion.dslam.business.service.impl;

import java.util.List;

import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessService;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBULogBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUILogBusinessService, CRONIOBUILogBusinessServiceConstants, CRONIOBUILogBusinessServiceTrace {

	private static final long serialVersionUID = 698695971124755962L;

	public CRONIOBULogBusinessServiceImpl() {
		super();
	}
	
	
	@Override
	public void getFilteredLogs() { 
		
		List<CRONIOBOILog> logs = getLogPersistence().getLogsByfilter();
		
//		AEMFTMetadataElementComposite preferencesData = DSLAMBUBomToMetadataConversor.fromPreferences(preferences);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		AEMFTMetadataElement logsData = null;
		contextOut.addElement(LOGS_DATA, logsData);
	}

}
