package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.ArrayList;
import java.util.List;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamReader;
import com.google.gwt.user.client.rpc.impl.Serializer;
import com.imotion.dslam.logger.atmosphere.base.CRONIOLoggerEventCollection;


@GwtRpcSerialTypes(CRONIOLoggerEventCollection.class)
public abstract class CRONIOLoggerRPCSerializer extends GwtRpcClientSerializer {
	
    
    @Override
    public Object deserialize(String raw) throws SerializationException {
       
      // split up in different parts - based on the []
      // this is necessary because multiple objects can be chunked in one single string
      int brackets = 0;
      int start = 0;
      List<String> messages = new ArrayList<String>();
      for (int i = 0; i < raw.length(); i++) {

          // detect brackets
          if (raw.charAt(i) == '[') {
             ++brackets;
          }
          else if (raw.charAt(i) == ']') --brackets;

          // new message
          if (brackets == 0) {
              messages.add(raw.substring(start, i + 1));
              start = i + 1;
          }
      }

      // create the objects
      List<Object> objects = new ArrayList<Object>(messages.size());
      for (String message : messages) {
          try {
              Serializer serializer = getRPCSerializer();
              ClientSerializationStreamReader reader = new ClientSerializationStreamReader(serializer);
              reader.prepareToRead(message);
              objects.add(reader.readObject());
          } catch (RuntimeException e) {
             throw new SerializationException(e);
          }
      }
      return objects;

    }

}
