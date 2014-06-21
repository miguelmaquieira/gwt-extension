package com.imotion.dslam.front.business.client;

import com.selene.arch.exe.gwt.client.common.AEGWTI18NCommonTexts;

public interface DSLAMBusI18NTexts extends AEGWTI18NCommonTexts {

	/**********************************************
	 *				COMMON
	 *********************************************/
	@DefaultStringValue("Guardar")
	String save();
	
	@DefaultStringValue("Guardar todo")
	String save_all();
	
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
	
	@DefaultStringValue("Cambiar Script")
	String change_file();
	
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
	
	@DefaultStringValue("Tipo")
	String type();

	@DefaultStringValue("Cancelar")
	String cancel();

	@DefaultStringValue("El documento actual tiene cambios sin guardar. ¿Seguro que quieres cerrarlo?")
	String exit_without_save();

	@DefaultStringValue("Ya existe un fichero con ese nombre")
	String filename_exists();
	
	@DefaultStringValue("Ya existe un proyecto con ese nombre")
	String projectname_exists();
	
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
	
	@DefaultStringValue("La fecha ya existe")
	String error_schedule_exist();
	
	@DefaultStringValue("El script no existe")
	String error_script_no_exist();
	
	@DefaultStringValue("Este campo esta vacio")
	String error_date_empty();
	
	@DefaultStringValue("¿Desea eliminar las variables seleccionadas?")
	String delete_variables_confirmation();
	
	@DefaultStringValue("¿Desea eliminar las fechas seleccionadas?")
	String delete_schedules_confirmation();
	
	@DefaultStringValue("Nombre del proceso")
	String process_name();
	
	@DefaultStringValue("Nombre del Script")
	String script_name();
	
	@DefaultStringValue("Variable de proceso")
	String process_variable();
	
	@DefaultStringValue("Variable externa")
	String external_variable();
	
	@DefaultStringValue("Scripts")
	String scripts_label();
	
	@DefaultStringValue("Proceso")
	String process_label();
	
	@DefaultStringValue("Main Script")
	String main_script_label();
	
	@DefaultStringValue("Rollback Script")
	String rollback_script_label();
	
	@DefaultStringValue("Nombre del Proyecto")
	String projectame_input_placeholder();
	
	@DefaultStringValue("DSLAM")
	String project_type_dslam();
	
	@DefaultStringValue("Información Nodo")
	String node_information();
	
	@DefaultStringValue("Lista de Nodos")
	String node_list();
	
	@DefaultStringValue("Variables del Nodo")
	String node_variable_list();
	
}
