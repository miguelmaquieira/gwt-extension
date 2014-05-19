package com.imotion.transit.business.service;

import com.selene.arch.exe.core.envi.trace.AEMFTITraceConstant;

public interface TRANSBUIBusinessCommonServiceTrace {



	/***********************************************************************
	 *                         TRACE LEVEL 1                               *
	 ***********************************************************************/

	/**
	* Message: The execution of the service {0}, method {1} has found {2} elements in the
	* 			search result for the parameters {3}
	*/
	public static long CTE_BUSINESS_SERVICE_DUPLICATED_ITEM_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_1
			| 0x0000000000000001L;

	/**
	* Message: The execution of the service {0}, method {1} has found that the parameter {2} is null
	*/
	public static long CTE_BUSINESS_SERVICE_NULL_PARAMETER_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_1
			| 0x0000000000000002L;

	/**
	* Message: The execution of the service {0}, method {1} has found an unexpected null element with {2} type
	*/
	public static long CTE_BUSINESS_SERVICE_NULL_ELEMENT_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_1
			| 0x0000000000000003L;

	/**
	* Message: The execution of the service {0}, method {1} has found an element of type {2} where {3} was expected
	*/
	public static long CTE_BUSINESS_SERVICE_UNEXPECTED_ELEMENT_TYPE_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_1
			| 0x0000000000000004L;


	/***********************************************************************
	 *                         TRACE LEVEL 2                               *
	 ***********************************************************************/

	/**
	 * Message: The execution of the service {0}, method {1}, has found that the parameter {2} has an unexpected value {3}
	 */
	public static long CTE_BUSINESS_SERVICE_UNEXPECTED_PARAM_VALUE_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_2
			| 0x0000000000000001L; ;

	/***********************************************************************
	 *                         TRACE LEVEL 3                               *
	 ***********************************************************************/

	/**
	 *  Message : Start of method method {0} of service {1}.
	 */
	public static long CTE_BUSINESS_SERVICE_METHOD_START_TRACE =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3
			| 0x0000000000000001L;

	/**
	 *  Message : End of method method {0} of service {1}.
	 */
	public static long CTE_BUSINESS_SERVICE_METHOD_END_TRACE =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3
			| 0x0000000000000002L;

	/**
	 *  Message : The execution of the service {0}, method {1} has not obtained the object of kind {2} and id : {3}
	 */
	public static long CTE_BUSINESS_SERVICE_ITEM_NOT_FOUND =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_3
			| 0x0000000000000003L;



	/***********************************************************************
	 *                         TRACE LEVEL 4                               *
	 ***********************************************************************/

	/**
	* Message: The execution of the service {0}, method {1} has not obtained results in the search with params {2}
	*/
	public static long CTE_BUSINESS_SERVICE_NO_ITEMS_FOUND_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000001L;


	/**
	* Message: The execution of the service {0}, method {1} has obtained {2} results of {3} type
	*/
	public static long CTE_BUSINESS_SERVICE_NUMBER_OF_RESULTS_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000002L;

	/**
	 *  Message : The execution of the service {0}, method {1} has saved new item of type {2} with this authorName:  {3}.
	 */
	public static long CTE_BUSINESS_SERVICE_ITEM_CREATED_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000003L;

	/**
	 *  Message : The execution of the service {0}, method {1} has erased an item of type {2} with this id:  {3}.
	 */
	public static long CTE_BUSINESS_SERVICE_ITEM_REMOVED_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000004L;

	/**
	 *  Message : The execution of the service {0}, method {1} has recovered an item of type {2} with this id:  {3}.
	 */
	public static long CTE_BUSINESS_SERVICE_ITEM_RECOVERED_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000005L;

	/**
	 *  Message : The execution of the service {0}, method {1} hasn't got results of type {2}.
	 */
	public static long CTE_BUSINESS_SERVICE_PERSISTENCE_NO_RESULTS_TRACE =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000006L;

	/**
	 *  Message : The execution of the service {0}, method {1} has modified a item of type {2} with this id:  {3}.
	 */
	public static long CTE_BUSINESS_SERVICE_ITEM_MODIFIED_TRACE =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000007L;

	/**
	 *  Message : BusinessServiceBase, method -{0}- new tag created with this id : {1}.
	 */
	public static long CTE_BUSINESS_SERVICE_NEW_TAG_CREATED_TRACE =
			TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000008L;

	/**
	 *  Message : The execution of the service {0}, method {1} is working with an item of type {2} and this data:  {3}.
	 */
	public static long CTE_BUSINESS_SERVICE_CURRENT_ITEM_TRACE = TRANSBUIBusinessCommonServiceConstants.CTE_BUSINESS_SERVICE_COMMON_RANGE_TRACE
			| AEMFTITraceConstant.CTE_MFT_AE_CORE_ENVI_TRACE_LEVEL_4
			| 0x0000000000000009L;
}

