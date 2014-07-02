package com.imotion.dslam.logger.atmosphere.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CRONIOLoggerEventCollection implements Serializable {

	private static final long serialVersionUID = 5616167182639445634L;

	private List<CRONIOLoggerEvent> eventList;
	
	public CRONIOLoggerEventCollection() {
		eventList = new ArrayList<>();
	}
	
	public void add(CRONIOLoggerEvent event) {
		eventList.add(event);
	}
	
	public void clear() {
		eventList.clear();
	}
	
	public List<CRONIOLoggerEvent> getList() {
		return eventList;
	}
	
	public CRONIOLoggerEventCollection cloneObject() {
		CRONIOLoggerEventCollection eventcollection = new CRONIOLoggerEventCollection();
		for (CRONIOLoggerEvent event : this.getList()) {
			eventcollection.add(event);
		}
		return eventcollection;
	}
}
