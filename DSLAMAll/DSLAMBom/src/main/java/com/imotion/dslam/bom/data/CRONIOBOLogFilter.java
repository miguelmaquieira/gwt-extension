package com.imotion.dslam.bom.data;

import java.util.Date;

import com.imotion.dslam.bom.CRONIOBOILogFilter;

public class CRONIOBOLogFilter implements CRONIOBOILogFilter {


	private static final long serialVersionUID = 5750049495070179922L;
	
	private int 	offset;
	private int 	size;
	private String 	executionID;
	private String 	filterText;
	private String	level;
	private Date	maxTimestamp;
	
	
	public CRONIOBOLogFilter() {}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String getExecutionID() {
		return executionID;
	}

	@Override
	public void setExecutionID(String executionID) {
		this.executionID = executionID;
	}

	@Override
	public String getFilterText() {
		return filterText;
	}

	@Override
	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}

	@Override
	public String getLevel() {
		return level;
	}

	@Override
	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public Date getMaxTimestamp() {
		return maxTimestamp;
	}

	@Override
	public void setMaxTimestamp(Date maxTimestamp) {
		this.maxTimestamp = maxTimestamp;
	}
	
	

}
