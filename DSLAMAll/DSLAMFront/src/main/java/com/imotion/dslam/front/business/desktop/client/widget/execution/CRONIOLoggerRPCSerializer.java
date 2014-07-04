package com.imotion.dslam.front.business.desktop.client.widget.execution;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEventCollection;


@GwtRpcSerialTypes(CRONIOLoggerEventCollection.class)
public abstract class CRONIOLoggerRPCSerializer extends GwtRpcClientSerializer {

}
