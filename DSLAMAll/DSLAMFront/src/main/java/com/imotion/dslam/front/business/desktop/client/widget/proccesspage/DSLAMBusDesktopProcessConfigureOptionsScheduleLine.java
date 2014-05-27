package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDateTimePickerTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class DSLAMBusDesktopProcessConfigureOptionsScheduleLine extends AEGWTCompositePanel  {

	public 		static final String 		NAME 	= "DSLAMBusDesktopProcessConfigureOptionsScheduleLine";
	
	
	private AEGWTBootstrapDateTimePickerTextBox		dateTimeBox;
	private AEGWTButton 							deleteButton;

	public DSLAMBusDesktopProcessConfigureOptionsScheduleLine(AEMFTMetadataElement date) {
		FlowPanel root = new FlowPanel();
		root.addStyleName(AEGWTIBoostrapConstants.COL_XS_12);
		//root.addStyleName(BusinessDesktopManagementIStyleConstants.EVENT_LINE_DATE);
		initWidget(root);
		
		deleteButton = new AEGWTButton();
		deleteButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		deleteButton.addStyleName(AEGWTIBoostrapConstants.BTN_DANGER);
		deleteButton.setHTML(AEGWTIBoostrapConstants.SPAN_GLYPHICON_MINUS);

		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				remove();
			}
		});
		
		dateTimeBox 	= new AEGWTBootstrapDateTimePickerTextBox(null);
		
		
		FlowPanel deleteZone = new FlowPanel();
		deleteZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		//root.addStyleName(BusinessDesktopManagementIStyleConstants.EVENT_LINE_DELETEZONE);
		deleteZone.add(deleteButton);
		
		FlowPanel startDateZone = new FlowPanel();
		startDateZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_10);
		//root.addStyleName(BusinessDesktopManagementIStyleConstants.EVENT_LINE_START_DATE);
		startDateZone.add(dateTimeBox);
		
		
		
		root.add(deleteZone);
		root.add(startDateZone);
		
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
}
