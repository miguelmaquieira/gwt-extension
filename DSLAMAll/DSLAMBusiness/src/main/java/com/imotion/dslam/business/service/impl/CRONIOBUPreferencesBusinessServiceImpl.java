package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIUser;
import com.imotion.dslam.bom.CRONIOBOIUserPreferences;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.bom.CRONIOBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.CRONIOBOFile;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceTrace;
import com.imotion.dslam.business.service.base.CRONIOBUServiceBase;
import com.imotion.dslam.business.service.utils.CRONIOBUMetadataToBomConversor;
import com.imotion.dslam.business.service.utils.DSLAMBUBomToMetadataConversor;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBUPreferencesBusinessServiceImpl extends CRONIOBUServiceBase implements CRONIOBUIPreferencesBusinessService, CRONIOBUIPreferencesBusinessServiceConstants, CRONIOBUIPreferencesBusinessServiceTrace {

	private static final long serialVersionUID = -5226864913741297047L;

	@Override
	public void getPreferences() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String userId			= getElementDataController().getElementAsString(AEMFTILoginDataConstants.USER_ID		, contextIn);
		long userIdLong = Long.valueOf(userId);
		CRONIOBOIUser user = getUserPersistence().getUserById(userIdLong);
		CRONIOBOIPreferences preferences = user.getPreferences();
		long preferencesId   	= preferences.getPreferencesId();
		String preferencesIdStr = String.valueOf(preferencesId);

		if (AEMFTCommonUtilsBase.isEmptyString(preferencesIdStr) ) {
			traceNullParameter(METHOD_GET_PREFERENCES, CRONIOBOIPreferencesDataConstants.PREFERENCES_ID);
		} else {

			//ContextOut
			AEMFTMetadataElementComposite preferencesData = DSLAMBUBomToMetadataConversor.fromPreferences(preferences);
			AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
			contextOut.addElement(PREFERENCES_DATA, preferencesData);
		}
	}

	@Override
	public void addConnection() {
		//ContextIn
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 							connectionName	= getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME			, contextIn);
		Date 							creationTime 	= new Date();

		long preferencesId   	= getElementDataController().getElementAsLong(CRONIOBOIPreferences.PREFERENCES_ID			, contextIn);

		//InitScript
		CRONIOBOIFile connectionScript = new CRONIOBOFile();
		connectionScript.setSavedTime(creationTime);
		connectionScript.setCreationTime(creationTime);
		connectionScript.setFilename(CRONIOBOIMachineProperties.CONNECTION_SCRIPT_DEFAULT_NAME);

		//disconnectionScript
		CRONIOBOIFile disconnectionScript = new CRONIOBOFile();
		disconnectionScript.setSavedTime(creationTime);
		disconnectionScript.setCreationTime(creationTime);
		disconnectionScript.setFilename(CRONIOBOIMachineProperties.DISCONNECTION_SCRIPT_DEFAULT_NAME);

		//VariableList
		List<CRONIOBOIVariable> variableList = new ArrayList<>();

		//MachineProperties
		CRONIOBOIMachineProperties machineProperties = new CRONIOBOMachineProperties();
		machineProperties.setMachineName(connectionName);
		machineProperties.setInitConnectionScript(connectionScript);
		machineProperties.setCloseConnectionScript(disconnectionScript);
		machineProperties.setConnectionVariables(variableList);
		machineProperties.setCreationTime(creationTime);
		machineProperties.setSaveTime(creationTime);
		machineProperties = getMachinePropertiesPersistence().addMachineProperties(machineProperties, preferencesId);
		
		//init-trace
		traceNewItemPersistent(METHOD_ADD_CONNECTION, CRONIOBOIMachineProperties.class.getSimpleName(), machineProperties.getMachineName());
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite connectionDataElement = DSLAMBUBomToMetadataConversor.fromMachineProperties(machineProperties);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(CONNECTION_DATA, connectionDataElement);
	}

	@Override
	public void updateMachineConfig() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String userId			= getElementDataController().getElementAsString(AEMFTILoginDataConstants.USER_ID		, contextIn);
		long userIdLong = Long.valueOf(userId);
		CRONIOBOIUser user = getUserPersistence().getUserById(userIdLong);
		CRONIOBOIPreferences preferences = user.getPreferences();
		long preferencesId   	= preferences.getPreferencesId();

		String machineName = getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, contextIn);
		CRONIOBOIMachineProperties currentMachineDB = getMachinePropertiesPersistence().getMachineProperties(preferencesId, machineName);
		CRONIOBOIMachineProperties machineConfig = CRONIOBUMetadataToBomConversor.fromMachineConfigData(contextIn);
		long currentMachineDBId = currentMachineDB.getMachinePropertiesId();
		getMachinePropertiesPersistence().updateMachineProperties(currentMachineDBId, machineConfig);
	}
	//	@Override
	public void updatePreferences() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		contextIn.setKey(PREFERENCES_DATA);

		String	preferencesIdKey 	= CRONIOBOIPreferences.PREFERENCES_ID;
		AEMFTMetadataElementSingle	preferencesIdData 		= (AEMFTMetadataElementSingle) getElementDataController().getElement(preferencesIdKey, contextIn);
		long preferencesId = preferencesIdData.getValueAsLong();
		Date 	date 				= new Date();
		
		CRONIOBOIPreferences 		preferencesDB 		= getPreferencesPersistence().getPreferences(preferencesId);
		CRONIOBOIUserPreferences 	userPreferencesDB 	= preferencesDB.getUserPreferences();
		Long 						userPreferencesId 	= userPreferencesDB.getUserPreferencesId();
		
		contextIn.addElement(CRONIOBOIUserPreferences.USER_PREFERENCES_ID, userPreferencesId);
		
		CRONIOBOIPreferences preferences = CRONIOBUMetadataToBomConversor.fromPreferencesData(contextIn);
		preferences.setSavedTime(date);

		List<CRONIOBOIMachineProperties> machineProList = preferences.getMachinePropertiesList();
		
		for (CRONIOBOIMachineProperties machine : machineProList) {
			machine.setSaveTime(date);
			machine.setPreferences(preferences);
			
			long machineId = machine.getMachinePropertiesId();
			CRONIOBOIFile connectionScript 			= machine.getInitConnectionScript();
			connectionScript = addCompiledCode(connectionScript, date);
			
			CRONIOBOIFile disconnectionScript 	= machine.getCloseConnectionScript();
			disconnectionScript = addCompiledCode(disconnectionScript, date);
			
			machine	= getMachinePropertiesPersistence().updateMachineProperties(machineId, machine);
		}
		
		preferences.getUserPreferences().setSavedTime(date);
		CRONIOBOIUserPreferences userPreferences = preferences.getUserPreferences();
		
		getUserPreferencesPersistence().updateUserPreferences(userPreferencesId, userPreferences);
		getPreferencesPersistence().updatePreferences(preferences.getPreferencesId(), preferences);
		//init-trace
		traceItemModifiedInPersistence(METHOD_UPDATE_PREFERENCES, CRONIOBOIPreferences.class.getSimpleName(), String.valueOf(preferences.getPreferencesId()));
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite preferencesDataElement = DSLAMBUBomToMetadataConversor.fromPreferences(preferences);
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(PREFERENCES_DATA, preferencesDataElement);
	}

	@Override
	public void updateUserPreferences() {

	}
	
	@Override
	public void removePreferences() {

	}
	/**
	 * PRIVATE
	 */

}
