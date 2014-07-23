package com.imotion.dslam.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.bom.CRONIOBOIPreferencesDataConstants;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.DSLAMBOFile;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessService;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUIPreferencesBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.imotion.dslam.business.service.utils.CRONIOBUMetadataToBomConversor;
import com.imotion.dslam.business.service.utils.DSLAMBUBomToMetadataConversor;
import com.selene.arch.base.bom.AEMFTILoginDataConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOBUPreferencesBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUIPreferencesBusinessService, CRONIOBUIPreferencesBusinessServiceConstants, CRONIOBUIPreferencesBusinessServiceTrace {

	private static final long serialVersionUID = -5226864913741297047L;

	@Override
	public void getPreferences() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		String userId			= getElementDataController().getElementAsString(AEMFTILoginDataConstants.USER_ID		, contextIn);
		long preferencesId   	= CRONIOBOIPreferencesDataConstants.PREFERENCES_DEFAULT_ID;
		String preferencesIdStr = String.valueOf(preferencesId);

		if (AEMFTCommonUtilsBase.isEmptyString(preferencesIdStr) ) {
			traceNullParameter(METHOD_GET_PREFERENCES, CRONIOBOIPreferencesDataConstants.PREFERENCES_ID);
		} else {

			CRONIOBOIPreferences 	preferences = getPreferencesPersistence().getPreferences(preferencesId);

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

		//Debemos conseguir el preferences Id del usuario, de momento puesto a pelo
		long preferencesId = 1L;

		//InitScript
		DSLAMBOIFile connectionScript = new DSLAMBOFile();
		connectionScript.setSavedTime(creationTime);
		connectionScript.setCreationTime(creationTime);
		connectionScript.setFilename(CRONIOBOIMachineProperties.CONNECTION_SCRIPT_DEFAULT_NAME);

		//disconnectionScript
		DSLAMBOIFile disconnectionScript = new DSLAMBOFile();
		disconnectionScript.setSavedTime(creationTime);
		disconnectionScript.setCreationTime(creationTime);
		disconnectionScript.setFilename(CRONIOBOIMachineProperties.DISCONNECTION_SCRIPT_DEFAULT_NAME);

		//VariableList
		List<DSLAMBOIVariable> variableList = new ArrayList<>();

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

		String machineName = getElementDataController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, contextIn);
		CRONIOBOIMachineProperties currentMachineDB = getMachinePropertiesPersistence().getMachineProperties(CRONIOBOIPreferencesDataConstants.PREFERENCES_DEFAULT_ID, machineName);
		CRONIOBOIMachineProperties machineConfig = CRONIOBUMetadataToBomConversor.fromMachineConfigData(contextIn);
		long currentMachineDBId = currentMachineDB.getMachinePropertiesId();
		//TODO preferencesId sustituir cuando usuarios
		getMachinePropertiesPersistence().updateMachineProperties(currentMachineDBId, machineConfig);

		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		//		contextOut.addElement(PROJECT_DATA, machineConfigData);
	}
	//	@Override
	public void updatePreferences() {
		//ContextIn
		AEMFTMetadataElementComposite contextIn = getContext().getContextDataIN();
		contextIn.setKey(PREFERENCES_DATA);
		//AEMFTMetadataElementComposite machinePropertiesListData = getElementDataController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST			, contextIn);
		//List<AEMFTMetadataElement> machinePropertiesList = machinePropertiesListData.getSortedElementList();

		Date 	date 		= new Date();
		CRONIOBOIPreferences preferences = CRONIOBUMetadataToBomConversor.fromPreferencesData(contextIn);
		preferences.setSavedTime(date);

		List<CRONIOBOIMachineProperties> machineProList = preferences.getMachinePropertiesList();
		
		for (CRONIOBOIMachineProperties machine : machineProList) {

			machine.setSaveTime(date);
			machine.setPreferences(preferences);
			
			long machineId = machine.getMachinePropertiesId();
			DSLAMBOIFile connectionScript 		= machine.getInitConnectionScript();
			DSLAMBOIFile disconnectionScript 	= machine.getCloseConnectionScript();
			
			connectionScript	= getFilePersistence().updateFileContent(connectionScript.getFileId(), connectionScript.getContent(), date);
			disconnectionScript	= getFilePersistence().updateFileContent(disconnectionScript.getFileId(), disconnectionScript.getContent(), date);
			machine	= getMachinePropertiesPersistence().updateMachineProperties(machineId, machine);
		}
		
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
	public void removePreferences() {

	}
	/**
	 * PRIVATE
	 */

}
