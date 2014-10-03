package com.imotion.dslam.front.business.desktop.client;

import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.business.service.CRONIOBUIExecuteBusinessServiceConstants;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;

public interface CRONIODesktopIAppControllerConstants {

	String PROJECTS_DATA	= "PROJECTS_DATA";
	String USER_DATA	= "USER_DATA";
	String PREFERENCES_DATA	= CRONIOBOIPreferencesDataConstants.PREFERENCES_DATA;
	String CONNECTIONS_DATA	= "CONNECTIONS_DATA";
	String EXECUTIONS_DATA  = CRONIOBUIExecuteBusinessServiceConstants.EXECUTIONS_DATA;
	String LIST_NODELIST_DATA  = DSLAMBUIProjectBusinessServiceConstants.LIST_NODELIST_DATA;
	
	String PREFERENCES_DATA_PREFFIX = PREFERENCES_DATA + DSLAMBusCommonConstants.ELEMENT_SEPARATOR;
	
	int DEFAULT_DOWNTIME = 15;
}
