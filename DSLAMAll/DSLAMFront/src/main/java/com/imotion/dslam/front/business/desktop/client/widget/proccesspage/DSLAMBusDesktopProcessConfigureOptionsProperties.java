package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureOptionsProperties extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsProperties";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 	root;
	private FlowPanel 	headerZone;
	private FlowPanel 	propertiesZone;
	private CheckBox 	synchroCheckBox;


	public DSLAMBusDesktopProcessConfigureOptionsProperties() {
		FlowPanel root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.properties());
		headerZone.add(headerLabel);

		//PropertiesZone
		propertiesZone 	= new FlowPanel();
		root.add(propertiesZone);
		synchroCheckBox 	= new CheckBox(TEXTS.synchronous());
		propertiesZone.add(synchroCheckBox);
		propertiesZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES_CHECKBOX);
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		boolean synchronous = getElementController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_SYNCHRONOUS, data);
		synchroCheckBox.setValue(synchronous);
	}
	
	public AEMFTMetadataElementComposite getData() {
		AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		boolean synchronous = synchroCheckBox.getValue();
		data.addElement(DSLAMBOIProcessDataConstants.PROCESS_SYNCHRONOUS, synchronous);
		return data;
	}
	
	public void reset() {
		synchroCheckBox.setValue(false);
	}
}
