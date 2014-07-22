package com.imotion.dslam.front.business.desktop.client.view.preferences.connection;

import com.google.gwt.i18n.client.Constants;

public interface CRONIOBusI18NPreferencesConnectionTexts extends Constants {

	@DefaultStringValue("Prompt de usuario")
	String userPromptLabel();

	@DefaultStringValue("Prompt de contraseña")
	String passwordPromptLabel();
	
	@DefaultStringValue("User: ")
	String userPromptPlaceHolder();

	@DefaultStringValue("Password: ")
	String passwordPromptPlaceHolder();
	
}
