package com.imotion.dslam.front.business.client.view.controller;

import com.selene.arch.exe.gwt.client.view.controller.AEGWTI18NControllerTexts;

public interface CRONIOI18NBusinessControllerTexts extends AEGWTI18NControllerTexts {

	@DefaultStringValue(" © 2013 iMotion Factory S.L.")
	String label_copyright_text();

	@DefaultStringValue("Descubre la solución perfecta para ti:")
	String business_controller_discover_text();

	@DefaultStringValue("¿Número de comensales?")
	String business_controller_commensals_number_text();

	@DefaultStringValue("¿Tipo de reunión?")
	String business_controller_meeting_type_text();

	@DefaultStringValue("¿Alguna preferencia?")
	String business_controller_meal_type_text();

	@DefaultStringValue("Descubridme las soluciones más adecuadas")
	String business_controller_discover_button_text();

	@DefaultStringValue("Ver todos los menús")
	String business_controller_show_all_menus_button_text();

	@DefaultStringValue("O busca aquí de la manera tradicional")
	String business_controller_search_menus_search_box_text();

	@DefaultStringValue("El Descubrimiento Saborean.do")
	String business_controller_week_offer_title();

	@DefaultStringValue("Hazte con uno antes de que se agoten!")
	String business_controller_week_offer_subtitle();

	@DefaultStringValue("Disfruta de:")
	String business_controller_week_offer_product_description_title();

	@DefaultStringValue("¡Me lo quedo!")
	String business_controller_week_offer_buy_button_text();

	@DefaultStringValue("Ver detalles")
	String business_controller_week_offer_details_button_text();

	@DefaultStringValue("Tuyo por solo:")
	String business_controller_week_offer_amount_title();

	@DefaultStringValue("Cómpralo antes de:")
	String business_controller_week_offer_remaining_time_title();

	@DefaultStringValue("Solo quedan:")
	String business_controller_week_offer_remaining_units_title();
	
	@DefaultStringValue("Estos te los puedes llevar ya (o próximamente...)")
	String business_controller_top_products_container_title();
	
	@DefaultStringValue("Productos a la venta por tiempo limitado")
	String business_controller_top_products_container_subtitle();
	
	@DefaultStringValue("Estos te los has perdido")
	String business_controller_bottom_products_container_title();
	
	@DefaultStringValue("Suscríbete para no volver a perderte nada")
	String business_controller_bottom_products_container_subtitle();

}
