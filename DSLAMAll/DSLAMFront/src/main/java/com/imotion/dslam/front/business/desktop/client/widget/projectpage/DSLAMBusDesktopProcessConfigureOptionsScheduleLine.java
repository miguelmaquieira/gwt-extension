package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

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
	}

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
	
	public void setDateText(String text) {
		dateTimeBox.setDateText(text);
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
