package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureOptionsProperties extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsProperties";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 	root;
	private FlowPanel 	headerZone;
	private FlowPanel 	propertiesZone;


	public DSLAMBusDesktopProcessConfigureOptionsProperties() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.properties());
		headerZone.add(headerLabel);

		//PropertiesZone
		propertiesZone 	= new FlowPanel();
		root.add(propertiesZone);
		CheckBox synchroCheckBox 	= new CheckBox(TEXTS.synchronous());
		propertiesZone.add(synchroCheckBox);
		propertiesZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES_CHECKBOX);
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
}
