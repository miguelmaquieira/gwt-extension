package com.imotion.dslam.front.business.client;

import com.selene.arch.exe.gwt.client.common.AEGWTI18NCommonTexts;

public interface DSLAMBusI18NTexts extends AEGWTI18NCommonTexts {

	/**********************************************
	 *				COMMON
	 *********************************************/
	@DefaultStringValue("Guardar")
	String save();
	
	@DefaultStringValue("Nuevo")
	String create();
	
	@DefaultStringValue("Ejecutar")
	String run();

	@DefaultStringValue("Salir")
	String exit();
	
	@DefaultStringValue("Último guardado: ")
	String last_saved();

	@DefaultStringValue("Abrir")
	String open();
	
	@DefaultStringValue("Añadir")
	String add();
	
	@DefaultStringValue("Renombrar")
	String rename();
	
	@DefaultStringValue("Eliminar")
	String delete();
	
	@DefaultStringValue("Navegador")
	String navigator();
	
	@DefaultStringValue("Propiedades")
	String properties();
	
	@DefaultStringValue("Variables")
	String variables();
	
	@DefaultStringValue("Schedule")
	String schedule();
	
	@DefaultStringValue("Síncrono")
	String synchronous();
	
	@DefaultStringValue("Servidor: ")
	String server();
	
	@DefaultStringValue("Usuario: ")
	String user();
	
	@DefaultStringValue("Contraseña: ")
	String password();
	
	@DefaultStringValue("Usuario")
	String user_placeholder();
	
	@DefaultStringValue("Contraseña")
	String password_placeholder();
	
	@DefaultStringValue("Nodos")
	String nodes();
	
	@DefaultStringValue("Variable")
	String variable();
	
	@DefaultStringValue("Valor")
	String value();

	@DefaultStringValue("Cancelar")
	String cancel();

	@DefaultStringValue("El documento actual tiene cambios sin guardar. ¿Seguro que quieres cerrarlo?")
	String exit_without_save();

	@DefaultStringValue("Ya existe un fichero con ese nombre")
	String filename_exists();
	
	@DefaultStringValue("Protocolo1")
	String protocol1();

	@DefaultStringValue("Protocolo2")
	String protocol2();
	
	@DefaultStringValue("Dirección Ip")
	String ip();
	
	@DefaultStringValue("Campo vacío")
	String empty_textbox();

	@DefaultStringValue("¿Seguro que quieres eliminarlo?")
	String delete_confirmation();
	
	@DefaultStringValue("La variable ya existe")
	String error_variable_exist();
	
	@DefaultStringValue("¿Desea eliminar las variables seleccionadas?")
	String delete_variables_confirmation();

}
