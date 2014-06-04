package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureOptionsSchedule extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsSchedule";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private FlowPanel 						root;
	private FlowPanel						headerZone;
	private FlowPanel 						scheduleListZone;
	private AEGWTBootstrapGlyphiconButton	addDateTimeButton;
	private int 							numberAddDateTimePicker;

	public DSLAMBusDesktopProcessConfigureOptionsSchedule() {
		root = new FlowPanel();
		initWidget(root);

		//Header
		headerZone 		= new FlowPanel();
		root.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_SCHEDULE_HEADER);

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
						DSLAMBusDesktopProcessConfigureOptionsScheduleLine dateWidget = (DSLAMBusDesktopProcessConfigureOptionsScheduleLine) scheduleListZone.getWidget(Integer.valueOf(error));
						dateWidget.setEmptyError();
					}
				}
			}
		});

		scheduleListZone = new FlowPanel();
		scheduleListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_SCHEDULE_LIST);
		root.add(scheduleListZone);

	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(NAME, scheduleListZone, scheduleListZone.getElement().getClientHeight(), false);

	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
	}
	
	public AEMFTMetadataElementComposite getData() {
		return null;
	}

	/**
	 * PRIVATE
	 */

	private void addDateTimeBox(int numberAddDateTimePicker) {

		DSLAMBusDesktopProcessConfigureOptionsScheduleLine line = new DSLAMBusDesktopProcessConfigureOptionsScheduleLine(null);
		scheduleListZone.add(line);
		line.setId(String.valueOf(numberAddDateTimePicker));
		line.postDisplay();
	}

	private List<String> dateTimePickersEmpty() {
		
		List<String> emptyList = null;
		boolean errors = false;
		
		int count = scheduleListZone.getWidgetCount();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				DSLAMBusDesktopProcessConfigureOptionsScheduleLine lineDateWidget = (DSLAMBusDesktopProcessConfigureOptionsScheduleLine) scheduleListZone.getWidget(i);
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
