package com.imotion.transit.bom;

import java.io.Serializable;
import java.util.Date;

public interface TRANSBOITransitData extends Serializable, TRANSBOITransitDataConstants {

	Long getTransitDataId();

	void setTransitDataId(Long transitDataId);

	String getNodeId();

	void setNodeId(String nodeId);

	Date getTimestamp();

	void setTimestamp(Date timestamp);

	int getTotalTransit();

	void setTotalTransit(int totalTransit);

	int getIncomingTransit();

	void setIncomingTransit(int enteringTransit);

	int getPassingTransit();

	void setPassingTransit(int passingTransit);

	Date getTimeInit();

	void setTimeInit(Date timeInit);

	Date getTimeEnd();

	void setTimeEnd(Date timeEnd);

}
