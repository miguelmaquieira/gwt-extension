package com.imotion.transit.business.service;

import com.imotion.transit.backend.persistence.service.computedtransitdata.TRANSBKIComputedTransitDataPersistenceService;
import com.imotion.transit.backend.persistence.service.transitdata.TRANSBKITransitDataPersistenceService;
import com.imotion.transit.business.TRANSBUIWrapperPersistence;
import com.selene.arch.exe.back.persistence.AEMFTIPersistenceService;
import com.selene.arch.exe.bus.service.impl.AEMFTBusinessServiceBaseImpl;
import com.selene.arch.exe.bus.tagsearch.persistence.AEMFTTagIndexPersistence;

public abstract class TRANSBUBusinessServiceBase extends AEMFTBusinessServiceBaseImpl<TRANSBUIWrapperPersistence> implements TRANSBUIBusinessService {

	private static final long serialVersionUID = -8777397730307974465L;
	
	private TRANSBKITransitDataPersistenceService 			transitDataPersistence;
	
	private TRANSBKIComputedTransitDataPersistenceService 	computedTransitDataPersistence;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected TRANSBUIWrapperPersistence createPersistenceWrapper() {
		return new TRANSBUBusinessWrapperPersistence();
	}

	//PERSISTENCE SERVICES

	protected TRANSBKITransitDataPersistenceService getTransitDataPersistence() {
		if (transitDataPersistence == null) {
			transitDataPersistence =  getPersistence().getAppFactoryPersistence().newTransitDataPersistence();
		}
		return transitDataPersistence;
	}
	
	protected TRANSBKIComputedTransitDataPersistenceService getComputedTransitDataPersistence() {
		if (computedTransitDataPersistence == null) {
			computedTransitDataPersistence =  getPersistence().getAppFactoryPersistence().newComputedTransitDataPersistence();
		}
		return computedTransitDataPersistence;
	}
	
	@Override
	protected AEMFTTagIndexPersistence getTagIndexPersistence() {
		// TODO Auto-generated method stub
		return null;
	}

	/********************************************************************
	 * 								TRACES								*
	 ********************************************************************/

	public void traceNullParameter(String methodName, String paramName) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramName;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_PARAMETER_TRACE,
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

		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE,
				params);
	}

	public void traceDuplicatedItems(String methodName, int numberOfDuplicates, String duplicatedInfo) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfDuplicates;
		params[3] = duplicatedInfo;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_DUPLICATED_ITEM_TRACE,
				params);
	}

	public void traceNumberOfResults(String methodName, String resultType, int numberOfResults) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = numberOfResults;
		params[3] = resultType;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NUMBER_OF_RESULTS_TRACE,
				params);
	}

	public void traceNoResults(String methodName, String resultType) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = resultType;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_PERSISTENCE_NO_RESULTS_TRACE,
				params);
	}

	public void traceNoItemsFound(String methodName, String paramsSearch) {
		Object[] params = new Object[3];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramsSearch;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_NO_ITEMS_FOUND_TRACE,
				params);
	}

	public void traceItemNotFound(String methodName, Class<?> type, String id) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = type.getName();
		params[3] = id;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_NOT_FOUND,
				params);
	}

	public void traceUnexpectedElementType(String methodName, String typeFound, String typeExpected) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = typeFound;
		params[3] = typeExpected;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_ELEMENT_TYPE_TRACE,
				params);
	}

	public void traceUnexpectedParameterValue(String methodName, String paramName, String value) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = paramName;
		params[3] = value;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_UNEXPECTED_PARAM_VALUE_TRACE,
				params);
	}

	public void traceNewItemPersistent(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_CREATED_TRACE,
				params);
	}

	public void traceItemModifiedInPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_MODIFIED_TRACE,
				params);
	}

	public void traceItemRemovedFromPersistence(String methodName, String itemType, String itemId) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = itemId;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_REMOVED_TRACE,
				params);
	}

	public void traceItemRecoveredFromPersistence(String methodName, Class<?> itemType, String itemId) {
		// Trace-ini
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType.getName();
		params[3] = itemId;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_ITEM_RECOVERED_TRACE,
				params);
		// Trace-end
	}

	public void traceStartBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_START_TRACE,
				params);
	}

	public void traceEndBusinessServiceMethod(String methodName) {
		Object[] params = new Object[2];
		params[0] = getClass().getName();
		params[1] = methodName;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_METHOD_END_TRACE,
				params);
	}

	public void traceCurrentItem(String methodName, String itemType, String data) {
		Object[] params = new Object[4];
		params[0] = getClass().getName();
		params[1] = methodName;
		params[2] = itemType;
		params[3] = data;
		getTrace().trace(TRANSBUIBusinessCommonServiceTrace.CTE_BUSINESS_SERVICE_CURRENT_ITEM_TRACE,
				params);
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
		if (transitDataPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) transitDataPersistence);
			transitDataPersistence = null;
		}
		if (computedTransitDataPersistence != null) {
			getPersistence().getAppFactoryPersistence().release((AEMFTIPersistenceService<?, ?, ?>) computedTransitDataPersistence);
			computedTransitDataPersistence = null;
		}
	}

	/********************************************************************
	 * 				      PROTECTED FUNCTIONS    						*
	 ********************************************************************/


}
