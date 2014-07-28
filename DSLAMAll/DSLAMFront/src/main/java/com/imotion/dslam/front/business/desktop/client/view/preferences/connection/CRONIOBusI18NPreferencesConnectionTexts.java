package com.imotion.dslam.front.business.desktop.client.view.preferences.connection;

import com.google.gwt.i18n.client.Constants;

public interface CRONIOBusI18NPreferencesConnectionTexts extends Constants {

	@DefaultStringValue("Prompt de usuario (Expresión regular)")
	String userPromptLabel();

	@DefaultStringValue("Prompt de contraseña (Expresión regular)")
	String passwordPromptLabel();
	
	@DefaultStringValue("Por ej.: Login:\\\\s")
	String userPromptPlaceHolder();

	@DefaultStringValue("Por ej.: Password: \\\\s")
	String passwordPromptPlaceHolder();
	
	@DefaultStringValue("Condición para rollback (Expresión regular)")
	String rollbackConditionPromptLabel();
	
	@DefaultStringValue("Expresión regular")
	String rollbackConditionPromptPlaceHolder();
	
}
