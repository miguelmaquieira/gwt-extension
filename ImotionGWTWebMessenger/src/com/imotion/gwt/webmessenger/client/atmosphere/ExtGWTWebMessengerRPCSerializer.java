package com.imotion.gwt.webmessenger.client.atmosphere;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

import com.imotion.gwt.webmessenger.shared.ExtGWTWebMessengerRPCEvent;


@GwtRpcSerialTypes(ExtGWTWebMessengerRPCEvent.class)
abstract public class ExtGWTWebMessengerRPCSerializer extends GwtRpcClientSerializer {

}
