package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;

public class CRONIOBusDesktopProcessNodeVariablesForm extends DSLAMBusDesktopProcessConfigureVariablesForm {

	public static final String NAME = "CRONIOBusDesktopProcessNodeVariablesForm";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	public CRONIOBusDesktopProcessNodeVariablesForm(AEGWTICompositePanel parent) {
		super(parent);
		addvariableScope(DSLAMBOIVariablesDataConstants.VARIABLE_SCOPE_NODE, TEXTS.node_variable());
	}	

	
	
	/**
	 * AEGWTCompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

}
