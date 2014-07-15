package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class CRONIOBusDesktopProcessNodesInfoVariablesList extends DSLAMBusDesktopVariablesList {

	public static final String NAME = "CRONIOBusDesktopProcessNodesInfoVariablesList";

	public CRONIOBusDesktopProcessNodesInfoVariablesList(AEGWTButton deleteButton) {
		super(deleteButton);
	}
	
	/**
	 * AEGWTICompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		List<AEMFTMetadataElement> variableList = data.getSortedElementList();
		for (AEMFTMetadataElement variable : variableList) {
			String itemKey = variable.getKey();
			if (!CRONIOBOIProjectDataConstants.IS_MODIFIED.equals(itemKey)) {
				String 	variableName 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, variable);
				int 	type	 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE			, variable);
				String 	valor 			= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, variable);

				String typeStr = "";
				if (DSLAMBOIVariablesDataConstants.VARIABLE_TYPE_TEXT == type) {
					typeStr = TEXTS.text_variable();
				} else {
					typeStr = TEXTS.numeric_variable();
				}
				
				Map<String,String> variableRow = new HashMap<String, String>();
				variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variableName);
				variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, typeStr);
				variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, valor);

				addRowItem(variableRow, variableName, true, true,false);
			}
		}	
	}

	/*
	 * AEGWTBootstrapTable
	 */

	@Override
	protected void setupHeader() {
		super.headerDataFields.add(DSLAMBOIVariablesDataConstants.VARIABLE_NAME);
		super.headerDataFields.add(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE);
		super.headerDataFields.add(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE);

		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, TEXTS.variable());
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, TEXTS.type());
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, TEXTS.value());
	}

}
