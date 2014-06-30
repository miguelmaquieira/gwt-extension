package com.imotion.dslam.front.business.desktop.client.widget.execution;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEvent;


@GwtRpcSerialTypes(CRONIOLoggerEvent.class)
abstract public class CRONIOLoggerRPCSerializer extends GwtRpcClientSerializer {

}
