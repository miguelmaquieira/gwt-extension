package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

public class DSLAMBusDesktopProcessConfigureOptions extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 											root;
	private DSLAMBusDesktopProcessConfigureOptionsProperties	propertiesZone;
	private DSLAMBusDesktopProcessConfigureOptionsVariables		varListZone;
	private DSLAMBusDesktopProcessConfigureOptionsSchedule		scheduleZone;

	public DSLAMBusDesktopProcessConfigureOptions() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS);

		propertiesZone = new DSLAMBusDesktopProcessConfigureOptionsProperties();
		root.add(propertiesZone);
		propertiesZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_PROPERTIES_ZONE);
		propertiesZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);

		varListZone = new DSLAMBusDesktopProcessConfigureOptionsVariables();
		root.add(varListZone);
		varListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_VARIABLES_ZONE);
		varListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		scheduleZone = new DSLAMBusDesktopProcessConfigureOptionsSchedule();
		root.add(scheduleZone);
		scheduleZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_SCHEDULE_ZONE);
		scheduleZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
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
