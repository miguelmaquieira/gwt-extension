package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;

public class DSLAMBusDesktopProcessConfigureOptionsVariables extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsVariables";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 												root;
	private FlowPanel												headerZone; 
	private AEGWTBootstrapGlyphiconButton 							addVariableButton;
	private	 DSLAMBusDesktopVariablesList   						variableList;
	private DSLAMBusDesktopProcessConfigureOptionsVariablesForm		variableForm;
	private AEGWTPopup 												popup;

	public DSLAMBusDesktopProcessConfigureOptionsVariables() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_VARIABLES);

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
				getPartPopup().center();
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
	 * PRIVATE
	 */
	private AEGWTPopup getPartPopup() {
		if (popup == null || variableForm == null) {
			popup = new AEGWTPopup(true);
			variableForm	= new DSLAMBusDesktopProcessConfigureOptionsVariablesForm();
			popup.setContentWidget(variableForm);
			//variableForm.postDisplay();
		}
		variableForm.resetForm();
			
		return popup;
	}
}
