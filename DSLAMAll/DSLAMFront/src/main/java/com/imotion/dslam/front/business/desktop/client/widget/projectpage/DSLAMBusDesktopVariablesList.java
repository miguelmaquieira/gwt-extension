package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTable;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class DSLAMBusDesktopVariablesList extends AEGWTBootstrapTable {

	public static final String NAME = "DSLAMBusDesktopVariablesList";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public DSLAMBusDesktopVariablesList(AEGWTButton deleteButton) {
		super(true,true,false, deleteButton);
	}

	public void reset () {
		clearUpdateList();
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
		if (data != null) {
			List<AEMFTMetadataElement> variableList = data.getSortedElementList();

			for (AEMFTMetadataElement variable : variableList) {
				String itemKey = variable.getKey();
				if (!CRONIOBOIProjectDataConstants.IS_MODIFIED.equals(itemKey)) {
					String 	name	 	= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, variable);
					String 	value 		= getElementController().getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, variable);
					int		type 		= getElementController().getElementAsInt(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE		, variable);

					String typeStr = "";
					if (DSLAMBOIVariablesDataConstants.VARIABLE_PROCESS_TYPE == type) {
						typeStr = TEXTS.process_variable();
					} else {
						typeStr = TEXTS.external_variable();
					}

					Map<String,String> variableRow = new HashMap<String, String>();
					variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_NAME	, name);
					variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, typeStr);
					variableRow.put(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, value);

					addRowItem(variableRow, name, true, true,false);
				}
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

		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_NAME		, TEXTS.variable());
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_TYPE	, TEXTS.type());
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, TEXTS.value());

	}

	@Override
	protected String getEventName() {
		return NAME;
	}

	@Override
	protected String getMsgDeleteText() {
		return TEXTS.delete_variables_confirmation();
	}


}
