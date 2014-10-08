package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.bom.CRONIOBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.exe.gwt.client.business.ui.utils.AEGWTBusinessUtils;
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
		if (data != null) {
			List<AEMFTMetadataElement> scheduleList = data.getSortedElementList();
			for (AEMFTMetadataElement scheduleData : scheduleList) {
				String itemKey = scheduleData.getKey();
				if (!CRONIOBOIProjectDataConstants.INFO.equals(itemKey)) {
					AEMFTMetadataElementSingle 	scheduleDataSingle 	= (AEMFTMetadataElementSingle) scheduleData;
					Date 						scheduleValue 		= (Date) scheduleDataSingle.getValueAsSerializable();
					String 						formattedDate 		= AEGWTBusinessUtils.getFormattedTimeMessage(scheduleValue, DSLAMBusDesktopProcessConfigureScheduleForm.DATE_FORMAT);

					Map<String,String> scheduleRow = new HashMap<String, String>();
					scheduleRow.put(CRONIOBOIProcessDataConstants.SCHEDULE_VALUE, formattedDate);

					addRowItem(scheduleRow, formattedDate, true, true,false);
				}
			}
		}
	}

	/*
	 * AEGWTBootstrapTable
	 */

	@Override
	protected void setupHeader() {
		super.headerDataFields.add(CRONIOBOIProcessDataConstants.SCHEDULE_VALUE);
		super.headerMapFieldText.put(CRONIOBOIProcessDataConstants.SCHEDULE_VALUE		, TEXTS.schedule());
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

