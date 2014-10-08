package com.imotion.dslam.front.business.client;

import com.selene.arch.exe.gwt.client.common.AEGWTI18NCommonTexts;

public interface CRONIOBusI18NTexts extends AEGWTI18NCommonTexts {

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
	
	@DefaultStringValue("Filtrar")
	String filter();
	
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
	
	@DefaultStringValue("Buscar...")
	String search_placeholder();
	
	@DefaultStringValue("Filtrar:")
	String filter_label();
	
	@DefaultStringValue("Filtro de texto:")
	String filter_text_label();
	
	@DefaultStringValue("Contraseña")
	String password_placeholder();
	
	@DefaultStringValue("Timeout")
	String timeout_placeholder();
	
	@DefaultStringValue("Timeout (ms)")
	String timeout_ms_placeholder();
	
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
	
	@DefaultStringValue("¿Seguro que quieres salir?")
	String exit_confirmation();
	
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
	
	@DefaultStringValue("El nodo ya existe")
	String error_node_exist();
	
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
	
	@DefaultStringValue("Entornos")
	String environments();
	
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
	
	@DefaultStringValue("Entorno")
	String enviroment();
	
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
	
	@DefaultStringValue("SSH1")
	String ssh1();
	
	@DefaultStringValue("SSH2")
	String ssh2();
	
	@DefaultStringValue("Test")
	String test();
	
	@DefaultStringValue("Prompt")
	String prompt_placeholder();
	
	@DefaultStringValue("Prompt (Expresión regular)")
	String prompt_regex_placeholder();
	
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
	
	@DefaultStringValue("Su sesión ha expirado por inactividad")
	String session_timeout();
	
	@DefaultStringValue("Usuario")
	String user_label();

	@DefaultStringValue("Todos")
	String all();
	
	@DefaultStringValue("Info")
	String info();
	
	@DefaultStringValue("Debug")
	String debug();
	
	@DefaultStringValue("Warning")
	String warning();
	
	@DefaultStringValue("Error")
	String error();
	
	@DefaultStringValue("Critical")
	String critical();
	
	@DefaultStringValue("Filtro por gravedad:")
	String filter_for_gravity();
	
	@DefaultStringValue("Filtrado")
	String filtered();
	
	@DefaultStringValue("Trazas por página:")
	String rows_for_page();
	
	@DefaultStringValue("Antes de:")
	String before_label();
	
	@DefaultStringValue("beforeDate")
	String before_date();
	
	@DefaultStringValue("Ahora")
	String now();
	
	@DefaultStringValue("10")
	String number_10();
	
	@DefaultStringValue("20")
	String number_20();
	
	@DefaultStringValue("50")
	String number_50();
	
	@DefaultStringValue("Logs")
	String logs();
	
	@DefaultStringValue("Log")
	String log();
	
	@DefaultStringValue("Debe introducir una fecha válida")
	String empty_date_error();
	
	@DefaultStringValue("addNode")
	String add_node();
	
	@DefaultStringValue("Añadir Nodo")
	String add_node_label();
	
	@DefaultStringValue("Nombre del Nodo")
	String node_name();
	
	@DefaultStringValue("Nombre de la lista de Nodos")
	String node_list_name();
	
	@DefaultStringValue("La dirección IP no es válida ( [0..255].[0..255].[0..255].[0..255] o localhost )")
	String ip_error_textbox();
	
	@DefaultStringValue("localhost")
	String localhost();
	
	@DefaultStringValue("Formato:<br/>[Nombre variable] [valor variable] [Tipo Variable],<br/>Tipos de Variables:<br/>t = variable tipo texto<br/>n = variable numérica<br/>Ejemplos:<br/>contador 6 n, palabra casa t,")
	String format_variables_input();
	
	@DefaultStringValue("Ejecutar")
	String execute();
	
	@DefaultStringValue("Ya existe una lista de nodos con el nombre:")
	String nodelist_exist_error();
}
