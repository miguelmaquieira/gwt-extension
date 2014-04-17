package com.imotion.gwt.webmessenger.client.comm.atmosphere;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

import com.imotion.gwt.webmessenger.shared.ExtGWTWMRPCEvent;


@GwtRpcSerialTypes(ExtGWTWMRPCEvent.class)
abstract public class ExtGWTWMCommRPCSerializer extends GwtRpcClientSerializer {

}
