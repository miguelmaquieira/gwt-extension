package com.imotion.dslam.front.business.desktop.client.common;

import com.selene.arch.exe.gwt.client.common.AEGWTI18NCommonTexts;

public interface DSLAMBusI18NTexts extends AEGWTI18NCommonTexts {

	/**********************************************
	 *				COMMON
	 *********************************************/
	@DefaultStringValue("Nombre")
	String common_name();

	@DefaultStringValue("#")
	String common_number();
	
	@DefaultStringValue("Id")
	String common_id();
	
	@DefaultStringValue("Introduce el Id")
	String common_intro_id();
	
	@DefaultStringValue("Imagen Url")
	String common_imageurl();
	
	@DefaultStringValue("Website")
	String common_website();
	
	@DefaultStringValue("Descripción")
	String common_description();
	
	@DefaultStringValue("Html")
	String common_htmlcontent();
	
	@DefaultStringValue("Categoría")
	String common_category();
	
	@DefaultStringValue("Frase")
	String common_quote();
	
	@DefaultStringValue("Autor Frase")
	String common_quotetitle();
	
	@DefaultStringValue("Link")
	String common_link();
	
	@DefaultStringValue("Detalle")
	String common_detail();
	
	@DefaultStringValue("Lista Imágenes")
	String common_image_url_list();
	
	@DefaultStringValue("urlImagen1,urlImagen2,urlImagen3....")
	String common_image_url_list_placeholder();
	
	@DefaultStringValue("Precio")
	String common_price();
	
	@DefaultStringValue("Formato")
	String common_format();
	
	@DefaultStringValue("Guardar")
	String common_save();
	

	/**********************************************
	 *				PRODUCT
	 *********************************************/
	
	@DefaultStringValue("Lista de productos")
	String common_product_list();
	
	@DefaultStringValue("idProduct1,idProduct2,idProduct3")
	String common_ej_product_list();
	
	@DefaultStringValue("Product Group")
	String common_productgroup_label();
	
	
	/**********************************************
	 *				AUTHOR
	 *********************************************/
	
	@DefaultStringValue("Biografía")
	String common_bio();
	
	@DefaultStringValue("Feed Url")
	String common_feedurl();
	
	@DefaultStringValue("Author Id")
	String common_authorid();
	
	@DefaultStringValue("Producto destacado")
	String common_featured_item();
	
	@DefaultStringValue("Id sellable")
	String common_ej_featured_item();
	
	@DefaultStringValue("Oferta semanal")
	String common_weekoffer_item();
	
	/**********************************************
	 *				PRODUCER
	 *********************************************/
	
	@DefaultStringValue("Producer Id")
	String common_producerid();
	
	/**********************************************
	 *				SELLABLE
	 *********************************************/
	
	@DefaultStringValue("Tipo")
	String common_type();
	
	@DefaultStringValue("Item Id")
	String common_itemid();
	
	@DefaultStringValue("Título Cabezera")
	String common_screentitle();
	
	@DefaultStringValue("Subtítulo Cabezera")
	String common_screensubtitle();
	
	@DefaultStringValue("Título Contenido")
	String common_contenttitle();
	
	@DefaultStringValue("Contenido")
	String common_content();
	
	@DefaultStringValue("Descuento (%)")
	String common_discount_percentage();
	
	@DefaultStringValue("Cantidad")
	String common_quantity();
	
	@DefaultStringValue("Fecha Inicio")
	String common_startdate();
	
	@DefaultStringValue("Fecha Fin")
	String common_enddate();
	
	@DefaultStringValue("20/02/2014 20:00")
	String common_format_date();
	
	@DefaultStringValue("Status")
	String common_status();
	
	@DefaultStringValue("Pedido Mínimo")
	String common_min_order_amount();
	
	@DefaultStringValue("Gastos de Envío")
	String common_shippingcosts();
	
	@DefaultStringValue("Fecha de entrega Temprana")
	String common_shipping_datestart();
	
	@DefaultStringValue("Fecha de entrega Tardía")
	String common_shipping_dateend();
	
	@DefaultStringValue("Días Envío si Laborables")
	String common_shipping_working_daysdelay();
	
	@DefaultStringValue("Días Envío si No Laborables")
	String common_shipping_fixed_daysdelay();
	
	@DefaultStringValue("Etiquetas")
	String common_customTags();
	
	@DefaultStringValue("Ej: Etiqueta1,Etiqueta2,Etiqueta3")
	String common_ej_custom_tags();
	
	@DefaultStringValue("Producto")
	String common_product_label();
	
	@DefaultStringValue("Menú")
	String common_menu_label();
	
	@DefaultStringValue("Evento")
	String common_event_label();
	
	@DefaultStringValue("Producto Complementario")
	String common_extra_related();
	
	@DefaultStringValue("Producto Alternativo")
	String common_part_related();
	
	@DefaultStringValue("Hora Limite (h)")
	String common_limit_hour();
	
	@DefaultStringValue("Retraso Envio (d)")
	String common_delay_in_days();
	
	@DefaultStringValue("Último dia de envio semanal")
	String common_last_delivery_day_of_week();
	
	@DefaultStringValue("Domingo = 1 ... Sabado = 7")
	String common_ej_last_delivery_day_of_week();		
	
	@DefaultStringValue("Tracking")
	String common_tracking();
	
	@DefaultStringValue("Tracking (%)")
	String common_tracking_percentage();
	
	/**********************************************
	 *				PROMOTION
	 *********************************************/
	
	@DefaultStringValue("Descuento (€)")
	String common_discount_amount();
	
	@DefaultStringValue("Unidades máximas")
	String common_max_units();
	
	@DefaultStringValue("Lista de sellables")
	String common_sellable_list();
	
	@DefaultStringValue("Activo")
	String common_active();
	
	@DefaultStringValue("Activar código promocional")
	String common_active_code();
	
	@DefaultStringValue("Ej: idSellable1,idSellable2,idSellable3")
	String common_ej_sellable_list();
	
	@DefaultStringValue("Sellable Id")
	String common_sellable_id();
	
	/**********************************************
	 *				MENU
	 *********************************************/
	
	
	@DefaultStringValue("La Comida")
	String common_titleLablel_typePart0();
	
	@DefaultStringValue("La Bebida")
	String common_titleLablel_typePart1();
	
	@DefaultStringValue("La Sobremesa")
	String common_titleLablel_typePart2();
	
	@DefaultStringValue("Número minimo comensales")
	String common_commensals();
	
	@DefaultStringValue("Puntuación")
	String common_rate();
	
	@DefaultStringValue("Número de Votos")
	String common_ratecount();
	
	@DefaultStringValue(" Opcional")
	String common_optional();
	
	@DefaultStringValue(" Plegado")
	String common_folded();
	
	@DefaultStringValue("Blocktype")
	String common_blocktype();
	
	@DefaultStringValue("Lista de productos")
	String common_blocktype_productlist();
	
	@DefaultStringValue("Entrantes")
	String common_blocktype0();
	
	@DefaultStringValue("Principal")
	String common_blocktype1();
	
	@DefaultStringValue("Postre")
	String common_blocktype2();
	
	@DefaultStringValue("Bebidas")
	String common_blocktype3();
	
	@DefaultStringValue("Sobremesa")
	String common_blocktype4();
	
	@DefaultStringValue("Selecciona los blocktypes y opciones  de la parte del menú antes de añadir configuraciones:")
	String common_infolabel();
	
	@DefaultStringValue("Si deseas cambiar los blocktypes selecionados y estos estan bloqueados, puedes resetear el formulario pulsando reset.")
	String common_resetlabel();
	
	@DefaultStringValue("Añadir configuraciones")
	String common_addconfiglabel();
	
	/**********************************************
	 *				Home
	 *********************************************/
	
	@DefaultStringValue("Autor recomendado")
	String common_featured_author();
	
	@DefaultStringValue("Lista de sellables recomendados")
	String common_recommendeds();
	
	@DefaultStringValue("Lista de sellables más vendidos")
	String common_topsolds();
	
	@DefaultStringValue("Oferta semanal")
	String common_weekOffer();
	

	@DefaultStringValue("Error al realizar la operación.")
	String error_operation_text();

	@DefaultStringValue("No actualizado correctamente.")
	String not_updated_text();	
	
	/**********************************************
	 *				LOCATION
	 *********************************************/
	
	@DefaultStringValue("Dirección")
	String common_address();
	
	@DefaultStringValue("Horarios")
	String common_timetable();
	
	@DefaultStringValue("Tipo de cocina")
	String common_cooking_type();
	
	@DefaultStringValue("Plazas")
	String common_capacity();
	
	@DefaultStringValue("Otros servicios")
	String common_facilities();
	
	@DefaultStringValue("Ej: 0x01111010111010")
	String common_ej_facilities();
	
	/**********************************************
	 *				EVENT
	 *********************************************/
	
	@DefaultStringValue("Evento Privado")
	String common_private_event();
	
	@DefaultStringValue("Coordenadas Evento")
	String common_coordinates_event();
	
	@DefaultStringValue("42.4305156,-8.640568799999983")
	String common_ej_coordinates_event();
	
	@DefaultStringValue("Lugar del Evento")
	String common_location_event();
	
	@DefaultStringValue("Costes de Gestión")
	String common_management_costs();
	
	@DefaultStringValue("Tickets vendidos")
	String common_sold_tickets();
	
	@DefaultStringValue("Propietario del Evento")
	String common_owner_event();
	
	@DefaultStringValue("Localización")
	String common_location_label();
	
	@DefaultStringValue("Autor")
	String common_author_label();
	
	@DefaultStringValue("Productor")
	String common_producer_label();
	
	@DefaultStringValue("Horarios")
	String common_dates();
	
	@DefaultStringValue("Teléfono")
	String common_location_phone();
	
	@DefaultStringValue("Fecha de Inicio")
	String common_start_date();
}
