package com.imotion.dslam.front.business.desktop.client.widget.proccesspage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopProcessConfigureOptionsSchedule extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProcessConfigureOptionsSchedule";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 						root;
	private FlowPanel						headerZone; 
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
				numberAddDateTimePicker++;
				addDateTimeBox(numberAddDateTimePicker);
			}
		});	
		
	}
	
	private void addDateTimeBox(int numberAddDateTimePicker) {
		
		DSLAMBusDesktopProcessConfigureOptionsScheduleLine line = new DSLAMBusDesktopProcessConfigureOptionsScheduleLine(null);
		root.add(line);
		line.setId(String.valueOf(numberAddDateTimePicker));
		line.postDisplay();
//		AEGWTButton deleteButton = new AEGWTButton();
//		deleteButton.addStyleName(AEGWTIBoostrapConstants.BTN);
//		deleteButton.addStyleName(AEGWTIBoostrapConstants.BTN_DANGER);
//		deleteButton.setHTML(AEGWTIBoostrapConstants.SPAN_GLYPHICON_MINUS);
//
//		deleteButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				remove();
//			}
//		});
//		AEGWTBootstrapFormFieldTextBox dateTimeBox = new AEGWTBootstrapFormFieldTextBox();
//		root.add(dateTimeBox);
//		
	}
//	
//	public void remove() {
//		this.re
//	}
	
	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
}
