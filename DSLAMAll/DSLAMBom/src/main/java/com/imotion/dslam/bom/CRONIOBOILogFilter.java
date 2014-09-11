package com.imotion.dslam.bom;

import java.io.Serializable;
import java.util.Date;

public interface CRONIOBOILogFilter extends Serializable, CRONIOBOILogFilterDataConstants{

	public int getOffset();
	public void setOffset(int offset);
	public int getSize();
	public void setSize(int size);
	public String getExecutionID();
	public void setExecutionID(String executionID);
	public String getFilterText();
	public void setFilterText(String filterText);
	public String getLevel();
	public void setLevel(String level);
	public Date getMaxTimestamp();
	public void setMaxTimestamp(Date maxTimestamp);

}
