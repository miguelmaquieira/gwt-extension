package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopProcessConfigureVariablesForm;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;

public class CRONIOBusDesktopPreferencesMachineVariablesForm extends CRONIOBusDesktopProcessConfigureVariablesForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariablesForm";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	public CRONIOBusDesktopPreferencesMachineVariablesForm(AEGWTICompositePanel parent) {
		super(parent);
		addVariableScope(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_CONNECTION	, TEXTS.connection_variable());
		addVariableScope(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_EXTERNAL		, TEXTS.external_variable());
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * PRIVATE
	 */
	
}
