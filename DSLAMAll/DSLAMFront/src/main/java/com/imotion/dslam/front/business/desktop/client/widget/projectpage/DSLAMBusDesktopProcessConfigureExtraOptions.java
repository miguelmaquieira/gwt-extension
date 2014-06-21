package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessConfigureExtraOptions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureExtraOptions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 	propertiesZone;
	private CheckBox 	synchroCheckBox;


	public DSLAMBusDesktopProcessConfigureExtraOptions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_CONTENT_IN_BOX);

		//PropertiesZone
		propertiesZone 	= new FlowPanel();
		root.add(propertiesZone);
		synchroCheckBox 	= new CheckBox(TEXTS.synchronous());
		propertiesZone.add(synchroCheckBox);
		propertiesZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_PROPERTIES_CHECKBOX);
		
		synchroCheckBox.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent saveEvt = new AEGWTLogicalEvent(getWindowName(), getName());
				saveEvt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
				getElementController().setElement(DSLAMBOIProcess.PROCESS_SYNC_OPTION, data, synchroCheckBox.getValue());
				saveEvt.addElementAsComposite(DSLAMBOIProcess.PROCESS_EXTRA_OPTIONS, data);
				getLogicalEventHandlerManager().fireEvent(saveEvt);
			}
		});
		
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
		boolean synchronous = getElementController().getElementAsBoolean(DSLAMBOIProcessDataConstants.PROCESS_SYNC_OPTION, data);
		synchroCheckBox.setValue(synchronous);
	}
	
	public AEMFTMetadataElementComposite getData() {
		AEMFTMetadataElementComposite data = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		boolean synchronous = synchroCheckBox.getValue();
		data.addElement(DSLAMBOIProcessDataConstants.PROCESS_EXTRA_OPTIONS, synchronous);
		return data;
	}
	
	public void reset() {
		synchroCheckBox.setValue(false);
	}
}
