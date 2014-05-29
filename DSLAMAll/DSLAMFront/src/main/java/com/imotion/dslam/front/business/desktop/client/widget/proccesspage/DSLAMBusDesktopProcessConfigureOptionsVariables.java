package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIVariablesDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureOptionsVariables extends AEGWTCompositePanel implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsVariables";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 												root;
	private FlowPanel												headerZone; 
	private AEGWTBootstrapGlyphiconButton 							addVariableButton;
	private	 DSLAMBusDesktopVariablesList   						variableList;
	private DSLAMBusDesktopProcessConfigureOptionsVariablesForm		variablesForm;
	private	 AEMFTMetadataElementComposite							variablesData;

	public DSLAMBusDesktopProcessConfigureOptionsVariables() {
		variablesData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite(); 
		root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_VARIABLES_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.variables());
		headerLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_10);
		headerZone.add(headerLabel);
		
		addVariableButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
		addVariableButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		headerZone.add(addVariableButton);
		
		addVariableButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				variablesForm.setEditMode(DSLAMBOIVariablesDataConstants.SAVE_MODE);
				variablesForm.center();
			}
		});	
		
		variableList = new DSLAMBusDesktopVariablesList();
		root.add(variableList);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * AEGWTICompositePanel
	 */
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		variablesForm = new DSLAMBusDesktopProcessConfigureOptionsVariablesForm(this);
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		if (LOGICAL_TYPE.SAVE_EVENT.equals(evt.getEventType())) {
			String id 		=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_ID);
			String value 	=  evt.getElementAsString(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE);
			AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();

			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_ID		, data	, id);
			getElementController().setElement(DSLAMBOIVariablesDataConstants.VARIABLE_VALUE		, data	, value);
			if (variablesForm.getEditMode()) {
				addVariables(id,data);
			} else if (!variablesData.contains(id)) {
				addVariables(id,data);
			} else {
				variablesForm.setErrorVariableExist();
			}
		}
		
		if(LOGICAL_TYPE.EDIT_EVENT.equals(evt.getEventType())) {
			
			AEMFTMetadataElement variableData = variablesData.getElement(evt.getSourceWidgetId());
			variablesForm.setData((AEMFTMetadataElementComposite) variableData);
			variablesForm.setEditMode(DSLAMBOIVariablesDataConstants.EDIT_MODE);
			variablesForm.setVariableIdTextBoxEnable(false);
			variablesForm.center();
		}
		
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type) || LOGICAL_TYPE.EDIT_EVENT.equals(type);
		
	}
	
	/**
	 * PRIVATE
	 */
	
	private void addVariables(String id, AEMFTMetadataElementComposite data) {
		variableList.clearList();
		variablesData.addElement(id,data);
		variableList.setData(variablesData);
		variablesForm.resetForm();	
	}
}
