package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTable;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class DSLAMBusDesktopScheduleList extends AEGWTBootstrapTable {

	public static final String NAME = "DSLAMBusDesktopScheduleList";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	public DSLAMBusDesktopScheduleList(AEGWTButton deleteButton) {
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
		List<AEMFTMetadataElement> scheduleList = data.getSortedElementList();
		for (AEMFTMetadataElement schedule : scheduleList) {
			
			String itemKey = schedule.getKey();
			if (!CRONIOBOIProjectDataConstants.IS_MODIFIED.equals(itemKey)) {

				String scheduleValue = ((AEMFTMetadataElementSingle) schedule).getValueAsString();

				Map<String,String> scheduleRow = new HashMap<String, String>();

				scheduleRow.put(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE		, scheduleValue);

				addRowItem(scheduleRow, scheduleValue, true, true,false);
			}
		}	
	}

	/*
	 * AEGWTBootstrapTable
	 */
	
	@Override
	protected void setupHeader() {
		super.headerDataFields.add(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE);
		
		super.headerMapFieldText.put(DSLAMBOIProcessDataConstants.SCHEDULE_VALUE		, TEXTS.schedule());
	}

	@Override
	protected String getEventName() {
		
		return NAME;
	}

	@Override
	protected String getMsgDeleteText() {
		return TEXTS.delete_schedules_confirmation();
	}


}
	
