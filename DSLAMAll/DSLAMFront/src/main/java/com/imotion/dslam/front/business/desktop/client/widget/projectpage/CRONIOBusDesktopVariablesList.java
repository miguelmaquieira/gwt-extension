package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTable;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class CRONIOBusDesktopVariablesList extends AEGWTBootstrapTable {

	public 		static final String NAME = "CRONIOBusDesktopVariablesList";
	protected 	static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	public CRONIOBusDesktopVariablesList(AEGWTButton deleteButton) {
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
				if (!CRONIOBOIProjectDataConstants.INFO.equals(itemKey)) {
					String 	name	 	= getElementController().getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_NAME	, variable);
					String 	value 		= getElementController().getElementAsString(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE	, variable);
					int		scope 		= getElementController().getElementAsInt(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE		, variable);
					int		type 		= getElementController().getElementAsInt(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE		, variable);
					
					String scopeStr = "";
					if (CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_PROCESS == scope) {
						scopeStr = TEXTS.process_variable();
					} else if (CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_NODE == scope) {
						scopeStr = TEXTS.node_variable();
					} else if (CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE_CONNECTION == scope) {
						scopeStr = TEXTS.connection_variable();
					} else {
						scopeStr = TEXTS.external_variable();
					}
					
					String typeStr = "";
					if (CRONIOBOIVariablesDataConstants.VARIABLE_TYPE_TEXT == type) {
						typeStr = TEXTS.text_variable();
					} else {
						typeStr = TEXTS.numeric_variable();
					}

					Map<String,String> variableRow = new HashMap<String, String>();
					variableRow.put(CRONIOBOIVariablesDataConstants.VARIABLE_NAME	, name);
					variableRow.put(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE	, scopeStr);
					variableRow.put(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE	, typeStr);
					variableRow.put(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE	, value);

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
		super.headerDataFields.add(CRONIOBOIVariablesDataConstants.VARIABLE_NAME);
		super.headerDataFields.add(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE);
		super.headerDataFields.add(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE);
		super.headerDataFields.add(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE);

		super.headerMapFieldText.put(CRONIOBOIVariablesDataConstants.VARIABLE_NAME	, TEXTS.variable());
		super.headerMapFieldText.put(CRONIOBOIVariablesDataConstants.VARIABLE_SCOPE	, TEXTS.scope());
		super.headerMapFieldText.put(CRONIOBOIVariablesDataConstants.VARIABLE_TYPE	, TEXTS.type());
		super.headerMapFieldText.put(CRONIOBOIVariablesDataConstants.VARIABLE_VALUE	, TEXTS.value());

	}

	@Override
	protected String getEventName() {
		return getName();
	}

	@Override
	protected String getMsgDeleteText() {
		return TEXTS.delete_variables_confirmation();
	}
}
