package com.imotion.dslam.front.business.desktop.client.view.process;

import com.google.gwt.i18n.client.Constants;

public interface CRONIOBusI18NProcessTexts extends Constants {

	@DefaultStringValue("Guardar")
	String save();
	
	@DefaultStringValue("Guardar todo")
	String save_all();

	@DefaultStringValue("Nombre: ")
	String node_name();

	@DefaultStringValue("Tipo: ")
	String node_type();

	@DefaultStringValue("Ip: ")
	String node_ip();
	
}
