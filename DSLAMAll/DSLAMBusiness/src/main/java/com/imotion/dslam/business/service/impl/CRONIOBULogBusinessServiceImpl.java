package com.imotion.dslam.business.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.imotion.dslam.bom.CRONIOBOIExecution;
import com.imotion.dslam.bom.CRONIOBOILog;
import com.imotion.dslam.bom.data.CRONIOBOLogFilter;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessService;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceConstants;
import com.imotion.dslam.business.service.CRONIOBUILogBusinessServiceTrace;
import com.imotion.dslam.business.service.base.DSLAMBUServiceBase;
import com.imotion.dslam.business.service.utils.DSLAMBUBomToMetadataConversor;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;

public class CRONIOBULogBusinessServiceImpl extends DSLAMBUServiceBase implements CRONIOBUILogBusinessService, CRONIOBUILogBusinessServiceConstants, CRONIOBUILogBusinessServiceTrace {

	private static final long serialVersionUID = 698695971124755962L;

	public CRONIOBULogBusinessServiceImpl() {
		super();
	}
	
	
	@Override
	public void getFilteredLogs() { 
		
		AEMFTMetadataElementComposite 	contextIn 		= getContext().getContextDataIN();
		String 	filterText		= getElementDataController().getElementAsString(CRONIOBOLogFilter.FILTER_TEXT, contextIn);
		String 	timestampStr	= getElementDataController().getElementAsString(CRONIOBOLogFilter.MAX_TIMESTAMP, contextIn);
		String 	numRowsStr		= getElementDataController().getElementAsString(CRONIOBOLogFilter.SIZE, contextIn);
		String	level			= getElementDataController().getElementAsString(CRONIOBOLogFilter.LEVEL, contextIn);
		String	executionId		= getElementDataController().getElementAsString(CRONIOBOLogFilter.EXECUTION_ID, contextIn);
		int 	offset			= getElementDataController().getElementAsInt(CRONIOBOLogFilter.OFFSET, contextIn);
		
		SimpleDateFormat formatText = new SimpleDateFormat("dd/MM/YYYY HH:mm");
		Date timestamp = null;
		try {
			timestamp = formatText.parse(timestampStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numRows = Integer.valueOf(numRowsStr);
		
		
		CRONIOBOLogFilter filterData = new CRONIOBOLogFilter();
		filterData.setOffset(offset);
		filterData.setSize(numRows);
		filterData.setExecutionID(executionId);
		filterData.setLevel(level);
		filterData.setFilterText(filterText);
		filterData.setMaxTimestamp(timestamp);
		
		
		
		List<CRONIOBOILog> logs = getLogPersistence().getLogsByfilter(filterData);
		
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
		
		int resultsNumber = logs.size();
		
		AEMFTMetadataElementComposite logListData = DSLAMBUBomToMetadataConversor.fromLogList(logs);

		//init-trace
		traceNumberOfResults(METHOD_GET_EXECUTION_LOGS, CRONIOBOILog.class.getSimpleName(), resultsNumber);
		//end-trace

		//ContextOut
		AEMFTMetadataElementComposite contextOut = getContext().getContextOUT();
		contextOut.addElement(EXECUTION_LOGS_DATA, logListData);
	}

}
