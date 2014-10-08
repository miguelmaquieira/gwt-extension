package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureVariablesForm;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;

public class CRONIOBusDesktopPreferencesMachineVariablesForm extends DSLAMBusDesktopProcessConfigureVariablesForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

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
