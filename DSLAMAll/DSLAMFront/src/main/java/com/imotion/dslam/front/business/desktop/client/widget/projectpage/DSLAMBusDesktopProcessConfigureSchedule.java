package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.base.exe.core.appli.metadata.element.single.AEMFTMetadataElementSingle;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureSchedule extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureSchedule";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 						root;
	private FlowPanel						headerZone;
	private FlowPanel 						scheduleListZone;
	private AEGWTBootstrapGlyphiconButton	addDateTimeButton;
	private int 							numberAddDateTimePicker;							

	public DSLAMBusDesktopProcessConfigureSchedule() {
		root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_SCHEDULE_HEADER);

		AEGWTLabel headerLabel 		= new AEGWTLabel(TEXTS.schedule());
		headerLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_10);
		headerZone.add(headerLabel);

		numberAddDateTimePicker = 0;

		addDateTimeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
		addDateTimeButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		headerZone.add(addDateTimeButton);

		addDateTimeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
 				if (numberAddDateTimePicker == 0 || dateTimePickersEmpty() == null) {
					numberAddDateTimePicker++;
					addDateTimeBox(numberAddDateTimePicker);
				} else {
					List<String> errors = dateTimePickersEmpty();
					
					for (String error : errors) {
						DSLAMBusDesktopProcessConfigureScheduleLine dateWidget = (DSLAMBusDesktopProcessConfigureScheduleLine) scheduleListZone.getWidget(Integer.valueOf(error));
						dateWidget.setEmptyError();
					}
				}
			}
		});

		scheduleListZone = new FlowPanel();
		scheduleListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_SCHEDULE_LIST);
		root.add(scheduleListZone);

	}
	
	public void reset() {
		int numberDateTimePickers = scheduleListZone.getWidgetCount();
		for (int i = 0; i < numberDateTimePickers; i++) {
			scheduleListZone.getWidget(0).removeFromParent();
		}
	}
	
	/**
	 * AEGWTCompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		AEMFTMetadataElementComposite scheduleListData = getElementController().getElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_SCHEDULE_LIST, data);
		List<AEMFTMetadataElement> scheduleList = scheduleListData.getSortedElementList();
		for (AEMFTMetadataElement scheduleData : scheduleList) {
			numberAddDateTimePicker++;
			AEMFTMetadataElementSingle schedule = (AEMFTMetadataElementSingle) scheduleData;
			String scheduleStr = schedule.getValueAsString();
			DSLAMBusDesktopProcessConfigureScheduleLine line = addDateTimeBox(numberAddDateTimePicker);
			line.setDateText(scheduleStr);	
		}
	}
	
	public AEMFTMetadataElementComposite getData() {
		AEMFTMetadataElementComposite scheduleListData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		int numberDateTimePickers = scheduleListZone.getWidgetCount();
		for (int i = 0; i < numberDateTimePickers; i++) {
			DSLAMBusDesktopProcessConfigureScheduleLine line = (DSLAMBusDesktopProcessConfigureScheduleLine) scheduleListZone.getWidget(i);
			scheduleListData.addElement(String.valueOf(i),line.getDateText());	 
		}	
		return scheduleListData;
	}

	/**
	 * PRIVATE
	 */

	private DSLAMBusDesktopProcessConfigureScheduleLine addDateTimeBox(int numberAddDateTimePicker) {

		DSLAMBusDesktopProcessConfigureScheduleLine line = new DSLAMBusDesktopProcessConfigureScheduleLine(null);
		scheduleListZone.add(line);
		line.setId(String.valueOf(numberAddDateTimePicker));
		line.postDisplay();
		return line;
	}

	private List<String> dateTimePickersEmpty() {
		
		List<String> emptyList = null;
		boolean errors = false;
		
		int count = scheduleListZone.getWidgetCount();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				DSLAMBusDesktopProcessConfigureScheduleLine lineDateWidget = (DSLAMBusDesktopProcessConfigureScheduleLine) scheduleListZone.getWidget(i);
				String lineDate = lineDateWidget.getDateText();
				if (AEMFTCommonUtilsBase.isEmptyString(lineDate)) {
					if (errors == false) {
						emptyList = new ArrayList<>();
						errors = true;
					}
					emptyList.add(String.valueOf(i));	
				} else {
					lineDateWidget.resetErrorLabel();
				}
			}
		}
		if (errors == true) {
			return emptyList;
		} else {
			return null;
		}
	}
}
