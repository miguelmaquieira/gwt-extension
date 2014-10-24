package com.imotion.dslam.business.service.base;

import java.util.Date;

import com.imotion.cronio.backend.persistence.service.lognode.CRONIOBKILogNodePersistenceService;
import com.imotion.cronio.backend.persistence.service.node.CRONIOBKINodePersistenceService;
import com.imotion.cronio.backend.persistence.service.nodelist.CRONIOBKINodeListPersistenceService;
import com.imotion.dslam.antlr.CRONIOAntlrUtils;
import com.imotion.dslam.backend.persistence.log.CRONIOBKILogPersistenceService;
import com.imotion.dslam.backend.persistence.login.CRONIOBKILoginPersistenceService;
import com.imotion.dslam.backend.persistence.service.execution.CRONIOBKIExecutionPersistenceService;
import com.imotion.dslam.backend.persistence.service.file.CRONIOBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.machineproperties.CRONIOBKIMachinePropertiesPersistenceService;
import com.imotion.dslam.backend.persistence.service.preferences.CRONIOBKIPreferencesPersistenceService;
import com.imotion.dslam.backend.persistence.service.process.CRONIOBKIProcessPersistenceService;
import com.imotion.dslam.backend.persistence.service.project.CRONIOBKIProjectPersistenceService;
import com.imotion.dslam.backend.persistence.service.userpreferences.CRONIOBKIUserPreferencesPersistenceService;
import com.imotion.dslam.bom.CRONIOBOIFile;
import com.imotion.dslam.business.CRONIOBUIWrapperPersistence;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.back.persistence.module.jpa.AEMFTPersisteceJPAConnectionUtil;
import com.selene.arch.exe.bus.service.impl.AEMFTBusinessServiceBaseImpl;
import com.selene.arch.exe.bus.tagsearch.persistence.AEMFTTagIndexPersistence;

public abstract class CRONIOBUServiceBase extends AEMFTBusinessServiceBaseImpl<CRONIOBUIWrapperPersistence> implements CRONIOBUIBusinessService {

	private static final long serialVersionUID = -8777397730307974465L;

	private CRONIOBKIFilePersistenceService					filePersistence;
	private CRONIOBKIProcessPersistenceService				processPersistence;
	private CRONIOBKIProjectPersistenceService				projectPersistence;
	private CRONIOBKINodePersistenceService					nodePersistence;
	private CRONIOBKILogNodePersistenceService				logNodePersistence;
	private CRONIOBKINodeListPersistenceService				nodeListPersistence;
	private CRONIOBKIPreferencesPersistenceService			preferencesPersistence;
	private CRONIOBKIMachinePropertiesPersistenceService	machinePropertiesPersistence;
	private CRONIOBKILoginPersistenceService 				userPersistence;
	private CRONIOBKIUserPreferencesPersistenceService 		userPreferencesPersistence;
	private CRONIOBKIExecutionPersistenceService 			executionPersistence;
	private CRONIOBKILogPersistenceService 					logPersistence;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected CRONIOBUIWrapperPersistence createPersistenceWrapper() {
		return new CRONIOBUWrapperPersistence();
	}

	/**
	 *	PROTECTED 
	 */

	protected CRONIOBOIFile addCompiledCode(CRONIOBOIFile file, Date date) {
		String content			= file.getContent();
		String compiledContent	= CRONIOAntlrUtils.precompileCode(content, file.getContentType());
		file.setCompiledContent(compiledContent);
		file = getFilePersistence().updateFileContent(file.getFileId(), file.getContent(), file.getCompiledContent(), date);
		return file;
	}

	//PERSISTENCE SERVICES
	protected CRONIOBKIFilePersistenceService getFilePersistence() {
		if (filePersistence == null) {
			filePersistence =  getPersistence().getAppFactoryPersistence().newFilePersistence(getSessionId());
		}
		return filePersistence;
	}

	protected CRONIOBKIProcessPersistenceService getProcessPersistence() {
		if (processPersistence == null) {
			processPersistence =  getPersistence().getAppFactoryPersistence().newProcessPersistence(getSessionId());
		}
		return processPersistence;
	}

	protected CRONIOBKIProjectPersistenceService getProjectPersistence() {
		if (projectPersistence == null) {
			projectPersistence =  getPersistence().getAppFactoryPersistence().newProjectPersistence(getSessionId());
		}
		return projectPersistence;
	}

	protected CRONIOBKINodePersistenceService getNodePersistence() {
		if (nodePersistence == null) {
			nodePersistence =  getPersistence().getAppFactoryPersistence().newNodePersistence(getSessionId());
		}
		return nodePersistence;
	}
	
	protected CRONIOBKILogNodePersistenceService getLogNodePersistence() {
		if (logNodePersistence == null) {
			logNodePersistence =  getPersistence().getAppFactoryPersistence().newLogNodePersistence(getSessionId());
		}
		return logNodePersistence;
	}
	
	protected CRONIOBKINodeListPersistenceService getNodeListPersistence() {
		if (nodeListPersistence == null) {
			nodeListPersistence =  getPersistence().getAppFactoryPersistence().newNodeListPersistence(getSessionId());
		}
		return nodeListPersistence;
	}

	protected CRONIOBKIPreferencesPersistenceService getPreferencesPersistence() {
		if (preferencesPersistence == null) {
			preferencesPersistence =  getPersistence().getAppFactoryPersistence().newPreferencesPersistence(getSessionId());
		}
		return preferencesPersistence;
	}

	protected CRONIOBKIMachinePropertiesPersistenceService getMachinePropertiesPersistence() {
		if (machinePropertiesPersistence == null) {
			machinePropertiesPersistence =  getPersistence().getAppFactoryPersistence().newMachinePropertiesPersistence(getSessionId());
		}
		return machinePropertiesPersistence;
	}

	protected CRONIOBKILoginPersistenceService getUserPersistence() {
		if (userPersistence == null) {
			userPersistence =  getPersistence().getAppFactoryPersistence().newUserPersistence(getSession().getSessionId());
		}
		return userPersistence;
	}
	
	protected CRONIOBKIUserPreferencesPersistenceService getUserPreferencesPersistence() {
		if (userPreferencesPersistence == null) {
			userPreferencesPersistence =  getPersistence().getAppFactoryPersistence().newUserPreferencesPersistence(getSession().getSessionId());
		}
		return userPreferencesPersistence;
	}
	
	protected CRONIOBKIExecutionPersistenceService getExecutionPersistence() {
		if (executionPersistence == null) {
			executionPersistence =  getPersistence().getAppFactoryPersistence().newExecutionPersistence(getSession().getSessionId());
		}
		return executionPersistence;
	}
	
	protected CRONIOBKILogPersistenceService getLogPersistence() {
		if (logPersistence == null) {
			logPersistence =  getPersistence().getAppFactoryPersistence().newLogPersistence(getSession().getSessionId());
		}
		return logPersistence;
	}
	

	/******************************************************************
	 * 					      AEMFTIFactorable                        *
	 ******************************************************************/

	@Override
	public void initialize(Object[] args) {
		super.initialize(args);
	}

	@Override
	public void releaseInstance() {
		AEMFTPersisteceJPAConnectionUtil.getMonoInstance().destroyEntityManager(getSession().getSessionId());
		super.releaseInstance();
		if (filePersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) filePersistence);
			filePersistence = null;
		}
		if (processPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) processPersistence);
			processPersistence = null;
		}
		if (projectPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) projectPersistence);
			projectPersistence = null;
		}
		if (nodePersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) nodePersistence);
			nodePersistence = null;
		}
		if (logNodePersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) logNodePersistence);
			logNodePersistence = null;
		}
		if (nodeListPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) nodeListPersistence);
			nodeListPersistence = null;
		}
		if (preferencesPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) preferencesPersistence);
			preferencesPersistence = null;
		}
		if (machinePropertiesPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) machinePropertiesPersistence);
			machinePropertiesPersistence = null;
		}
		if (userPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) userPersistence);
			userPersistence = null;
		}
		if (userPreferencesPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) userPreferencesPersistence);
			userPreferencesPersistence = null;
		}
		if (executionPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) executionPersistence);
			executionPersistence = null;
		}
		if (logPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) logPersistence);
			logPersistence = null;
		}
	}

	/********************************************************************
	 * 								TRACES								*
	 ********************************************************************/

	public void traceNullParameter(String methodName, String paramName) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramName;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_PARAMETER_TRACE,
				params);
	}

	public void traceNullElement(String methodName) {
		traceNullElement(methodName, "unknown");
	}

	public void traceNullElement(String methodName, String elementType) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = elementType;

		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE,
				params);
	}

	public void traceDuplicatedItems(String methodName, int numberOfDuplicates, String duplicatedInfo) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfDuplicates;
		params[3] = duplicatedInfo;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_DUPLICATED_ITEM_TRACE,
				params);
	}

	public void traceNumberOfResults(String methodName, String resultType, int numberOfResults) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfResults;
		params[3] = resultType;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_NUMBER_OF_RESULTS_TRACE,
				params);
	}

	public void traceNoResults(String methodName, String resultType) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = resultType;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_PERSISTENCE_NO_RESULTS_TRACE,
				params);
	}

	public void traceNoItemsFound(String methodName, String paramsSearch) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramsSearch;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_NO_ITEMS_FOUND_TRACE,
				params);
	}

	public void traceItemNotFound(String methodName, Class<?> type, String id) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = type.getName();
		params[3] = id;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_NOT_FOUND,
				params);
	}

	public void traceUnexpectedElementType(String methodName, String typeFound, String typeExpected) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = typeFound;
		params[3] = typeExpected;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_ELEMENT_TYPE_TRACE,
				params);
	}

	public void traceUnexpectedParameterValue(String methodName, String paramName, String value) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramName;
		params[3] = value;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_PARAM_VALUE_TRACE,
				params);
	}

	public void traceNewItemPersistent(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_CREATED_TRACE,
				params);
	}

	public void traceItemModifiedInPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_MODIFIED_TRACE,
				params);
	}

	public void traceItemRemovedFromPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_REMOVED_TRACE,
				params);
	}

	public void traceItemRecoveredFromPersistence(String methodName, Class<?> itemType, String itemId) {
		// Trace-ini
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType.getName();
		params[3] = itemId;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_RECOVERED_TRACE,
				params);
		// Trace-end
	}

	public void traceStartBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_START_TRACE,
				params);
	}

	public void traceEndBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_END_TRACE,
				params);
	}

	public void traceCurrentItem(String methodName, String itemType, String data) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = data;
		getTrace().trace(CRONIOBUICommonServiceTrace.CTE_BUSINESS_SERVICE_CURRENT_ITEM_TRACE,
				params);
	}

	/********************************************************************
	 * 				      PROTECTED FUNCTIONS    						*
	 ********************************************************************/

	@Override
	protected AEMFTTagIndexPersistence getTagIndexPersistence() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * PRIVATE
	 */

	private String getSessionId() {
		return getSession().getSessionId();
	}

}
