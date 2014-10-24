package com.imotion.dslam.front.business.desktop.client.view.log;

import com.google.gwt.i18n.client.Constants;

public interface CRONIOBusI18NLogTexts extends Constants {



	@DefaultStringValue("Usuario: ")
	String user();

	@DefaultStringValue("Entorno: ")
	String environment();

	@DefaultStringValue("Inicio: ")
	String init_time();
	
	@DefaultStringValue("Fin: ")
	String finish_time();
	
	@DefaultStringValue("Sincrono")
	String synchronous();
	
	@DefaultStringValue("Asincrono")
	String asynchronous();
	
	@DefaultStringValue("Lista de Nodos")
	String logger_node_list();
	
	@DefaultStringValue("Nombre")
	String logger_node_name();
	
	@DefaultStringValue("Dirección IP")
	String logger_node_ip();
	
	@DefaultStringValue("Tipo de Máquina")
	String logger_node_machine_type();
	
	@DefaultStringValue("Estado")
	String logger_node_state();
	
}
