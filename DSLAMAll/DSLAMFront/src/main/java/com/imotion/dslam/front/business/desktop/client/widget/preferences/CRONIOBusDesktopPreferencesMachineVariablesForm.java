package com.imotion.dslam.front.business.desktop.client.widget.preferences;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopProcessConfigureVariablesForm;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;

public class CRONIOBusDesktopPreferencesMachineVariablesForm extends DSLAMBusDesktopProcessConfigureVariablesForm {

	public static final String NAME = "CRONIOBusDesktopPreferencesMachineVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public CRONIOBusDesktopPreferencesMachineVariablesForm(AEGWTICompositePanel parent) {
		super(parent);
		addVariableScope(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE_CONNECTION, TEXTS.connection_variable());
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
