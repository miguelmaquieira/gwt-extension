package com.imotion.dslam.business.service;

import com.imotion.dslam.backend.persistence.service.file.DSLAMBKIFilePersistenceService;
import com.imotion.dslam.backend.persistence.service.process.DSLAMBKIProcessPersistenceService;
import com.imotion.dslam.business.DSLAMBUIWrapperPersistence;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.bus.service.impl.AEMFTBusinessServiceBaseImpl;
import com.selene.arch.exe.bus.tagsearch.persistence.AEMFTTagIndexPersistence;

public abstract class DSLAMBUBusinessServiceBase extends AEMFTBusinessServiceBaseImpl<DSLAMBUIWrapperPersistence> implements DSLAMBUIBusinessService {

	private static final long serialVersionUID = -8777397730307974465L;
	
	private DSLAMBKIFilePersistenceService		filePersistence;
	private DSLAMBKIProcessPersistenceService	processPersistence;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected DSLAMBUIWrapperPersistence createPersistenceWrapper() {
		return new DSLAMBUBusinessWrapperPersistence();
	}

	//PERSISTENCE SERVICES
	protected DSLAMBKIFilePersistenceService getFilePersistence() {
		if (filePersistence == null) {
			filePersistence =  getPersistence().getAppFactoryPersistence().newFilePersistence();
		}
		return filePersistence;
	}
	
	protected DSLAMBKIProcessPersistenceService getProcessPersistence() {
		if (processPersistence == null) {
			processPersistence =  getPersistence().getAppFactoryPersistence().newProcessPersistence();
		}
		return processPersistence;
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
		super.releaseInstance();
		if (filePersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) filePersistence);
			filePersistence = null;
		}
		if (processPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) processPersistence);
			processPersistence = null;
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
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_PARAMETER_TRACE,
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

		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE,
				params);
	}

	public void traceDuplicatedItems(String methodName, int numberOfDuplicates, String duplicatedInfo) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfDuplicates;
		params[3] = duplicatedInfo;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_DUPLICATED_ITEM_TRACE,
				params);
	}

	public void traceNumberOfResults(String methodName, String resultType, int numberOfResults) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfResults;
		params[3] = resultType;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NUMBER_OF_RESULTS_TRACE,
				params);
	}

	public void traceNoResults(String methodName, String resultType) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = resultType;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_PERSISTENCE_NO_RESULTS_TRACE,
				params);
	}

	public void traceNoItemsFound(String methodName, String paramsSearch) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramsSearch;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NO_ITEMS_FOUND_TRACE,
				params);
	}

	public void traceItemNotFound(String methodName, Class<?> type, String id) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = type.getName();
		params[3] = id;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_NOT_FOUND,
				params);
	}

	public void traceUnexpectedElementType(String methodName, String typeFound, String typeExpected) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = typeFound;
		params[3] = typeExpected;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_ELEMENT_TYPE_TRACE,
				params);
	}

	public void traceUnexpectedParameterValue(String methodName, String paramName, String value) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramName;
		params[3] = value;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_PARAM_VALUE_TRACE,
				params);
	}

	public void traceNewItemPersistent(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_CREATED_TRACE,
				params);
	}

	public void traceItemModifiedInPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_MODIFIED_TRACE,
				params);
	}

	public void traceItemRemovedFromPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_REMOVED_TRACE,
				params);
	}

	public void traceItemRecoveredFromPersistence(String methodName, Class<?> itemType, String itemId) {
		// Trace-ini
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType.getName();
		params[3] = itemId;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_RECOVERED_TRACE,
				params);
		// Trace-end
	}

	public void traceStartBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_START_TRACE,
				params);
	}

	public void traceEndBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_END_TRACE,
				params);
	}

	public void traceCurrentItem(String methodName, String itemType, String data) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = data;
		getTrace().trace(DSLAMBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_CURRENT_ITEM_TRACE,
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

}
