package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDateTimePickerTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class DSLAMBusDesktopProcessConfigureOptionsScheduleLine extends AEGWTCompositePanel  {

	public 	static final String 		NAME 	= "DSLAMBusDesktopProcessConfigureOptionsScheduleLine";
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	
	private AEGWTBootstrapDateTimePickerTextBox		dateTimeBox;
	private AEGWTBootstrapGlyphiconButton 			deleteButton;

	public DSLAMBusDesktopProcessConfigureOptionsScheduleLine(AEMFTMetadataElement date) {
		FlowPanel root = new FlowPanel();
		root.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_OPTIONS_SCHEDULE_LINE);
		initWidget(root);
		
		dateTimeBox 	= new AEGWTBootstrapDateTimePickerTextBox(null);
		
		FlowPanel startDateZone = new FlowPanel();
		startDateZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_10);
		startDateZone.add(dateTimeBox);
		
		AEGWTBootstrapGlyphiconButton deleteButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_REMOVE, null, TEXTS.delete());

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				remove();
			}
		});
		
		FlowPanel deleteZone = new FlowPanel();
		deleteZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		deleteZone.add(deleteButton);
		
		root.add(startDateZone);
		root.add(deleteZone);
		
//		if (date != null) {
//			
//			long start	= getElementController().getElementAsLong(SNDOBOICalendarDataConstants.START_TIME	, date);
//			long finish	= getElementController().getElementAsLong(SNDOBOICalendarDataConstants.FINISH_TIME	, date);
//			
//			Date dateStart 	= new Date(start);
//			Date dateFinish = new Date(finish);
//			
//			String formatDate ="dd/MM/yyyy HH:mm";
//			String formattedStartDate			= AEGWTBusinessUtils.getFormattedTimeMessage(dateStart		, formatDate);
//			String formattedFinishDate 			= AEGWTBusinessUtils.getFormattedTimeMessage(dateFinish		, formatDate);
//	
//			startDateTextBox.setText(formattedStartDate);
//			endDateTextBox.setText(formattedFinishDate);
//		}
	}

//	public long getStartDate() {
//		Date	date		= null;
//		String 	formatDate 	= "dd/MM/yyyy HH:mm";
//		long 	dateLong 	= 0;
//		String 	startDateString = startDateTextBox.getText();
//		if (!AEMFTCommonUtilsBase.isEmptyString(startDateString) && !startDateString.matches("([0][1-9]|[1-2][0-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/[0-9]{4}\\s([0-1][0-9]|[2][0-3])\\:[0-5][0-9]")) {
//			startDateTextBox.setErrorLabelTextAndShow(TEXTS.common_error_format());
//		} else {
//			if (AEMFTCommonUtilsBase.isEmptyString(startDateString)) {
//				startDateTextBox.setErrorLabelTextAndShow(TEXTS.common_error_empty());
//			} else {
//				date 		= AEGWTBusinessUtils.getDateFromFormattedTime(startDateString, formatDate);
//				dateLong	= date.getTime();
//				startDateTextBox.setErrorLabelVisible(false);
//			}
//		}
//		return dateLong;
//	}
//	
	public void remove(){
		this.removeFromParent();
	}
	
	public void setEmptyError() {
		dateTimeBox.setEmptyError(TEXTS.error_date_empty());
	}
	
	public void resetErrorLabel() {
		dateTimeBox.resetErrorLabel();
	}
	
	public String getDateText() {
		return dateTimeBox.getDateText();
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
		// TODO Auto-generated method stub
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		dateTimeBox.addJS(getId());
	}	
}
