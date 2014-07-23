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
	
	@DefaultStringValue("Entrar")
	String enter();
		
	@DefaultStringValue("Email")
	String email_placeholder();

	@DefaultStringValue("Cambiar contraseña")
	String change_password();

	
	@DefaultStringValue("Usuario")
	String user_placeholder();
	
	@DefaultStringValue("Contraseña")
	String password_placeholder();
	
	@DefaultStringValue("Timeout")
	String timeout_placeholder();
	
	@DefaultStringValue("Nodos")
	String nodes();
	
	@DefaultStringValue("Variable")
	String variable();
	
	@DefaultStringValue("Valor")
	String value();
	
	@DefaultStringValue("Ámbito")
	String scope();
	
	@DefaultStringValue("Tipo")
	String type();

	@DefaultStringValue("Cancelar")
	String cancel();

	@DefaultStringValue("El documento actual tiene cambios sin guardar. ¿Seguro que quieres cerrarlo?")
	String exit_without_save_document();
	
	@DefaultStringValue("Hay cambios sin guardar, ¿seguro que quieres salir?")
	String exit_without_save();
	
	@DefaultStringValue("Ya existe un fichero con ese nombre")
	String filename_exists();
	
	@DefaultStringValue("Ya existe un proyecto con ese nombre")
	String projectname_exists();
	
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
	
	@DefaultStringValue("Variable de conexión")
	String connection_variable();
	
	@DefaultStringValue("Variable de nodo")
	String node_variable();
	
	@DefaultStringValue("Texto")
	String text_variable();

	@DefaultStringValue("Numérica")
	String numeric_variable();
	
	@DefaultStringValue("Scripts")
	String scripts_label();
	
	@DefaultStringValue("Proceso")
	String process_label();
	
	@DefaultStringValue("Main Script")
	String main_script_label();
	
	@DefaultStringValue("Rollback Script")
	String rollback_script_label();
	
	@DefaultStringValue("Nombre del Proyecto")
	String projectname_input_placeholder();
	
	@DefaultStringValue("Nombre de la conexión")
	String connectionname_input_placeholder();
	
	@DefaultStringValue("DSLAM")
	String project_type_dslam();
	
	@DefaultStringValue("Información Nodo")
	String node_information();
	
	@DefaultStringValue("Lista de Nodos")
	String node_list();
	
	@DefaultStringValue("Variables del Nodo")
	String node_variable_list();
	
	@DefaultStringValue("Subir")
	String upload();
	
	@DefaultStringValue("Si ha introducido nodos anteriormente, estos se eliminarán ¿Deseas continuar?")
	String delete_nodes_confirm();

	@DefaultStringValue("Consola")
	String console_label();
	
	@DefaultStringValue("Respuesta:")
	String response_label();
	
	@DefaultStringValue("Prompt:")
	String prompt_label();
	
	@DefaultStringValue("Telnet")
	String telnet();
	
	@DefaultStringValue("SSH")
	String ssh();
	
	@DefaultStringValue("Prompt")
	String prompt_placeholder();
	
	@DefaultStringValue("Protocolo")
	String protocol_placeholder();
	
	@DefaultStringValue("Petición:")
	String request_label();

	@DefaultStringValue("Ejecución")
	String execution();
	
	@DefaultStringValue("Guardar Proyecto")
	String save_project();
	
	@DefaultStringValue("Preferencias")
	String preferences();
	
	@DefaultStringValue("Máquinas")
	String machines();
	
	@DefaultStringValue("DSLAM")
	String dslam();
	
	@DefaultStringValue("Script de conexión")
	String connection_script();
	
	@DefaultStringValue("Script de desconexión")
	String disconnection_script();
	
	@DefaultStringValue("Configuración")
	String config();
	
	@DefaultStringValue("Nueva Conexión")
	String create_connection();
	
	@DefaultStringValue("Bienvenido. Por favor, introduce tus datos para acceder al sistema")
	String sign_in_description_text();
	
	@DefaultStringValue("El email introducido no existe, por favor vuelva a intentarlo")
	String sign_in_mail_not_exists();
	
	@DefaultStringValue("La contraseña introducida es incorrecta, por favor vuelva a intentarlo")
	String sign_in_incorrect_password();
	
}
