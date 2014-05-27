package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTable;

public class DSLAMBusDesktopVariablesList extends AEGWTBootstrapTable {

	public static final String NAME = "DSLAMBusDesktopVariablesList";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public DSLAMBusDesktopVariablesList() {
		super(true,true);
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
	}

	/*
	 * AEGWTBootstrapTable
	 */
	
	@Override
	protected void setupHeader() {
		super.headerDataFields.add(DSLAMBOIVariablesDataConstants.VARIABLE_ID);
		super.headerDataFields.add(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE);
		
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, TEXTS.variable());
		super.headerMapFieldText.put(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE	, TEXTS.value());
		
	}

	@Override
	protected String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMsgDeleteText() {
		// TODO Auto-generated method stub
		return null;
	}
}
	