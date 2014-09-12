package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.imotion.dslam.bom.data.CRONIOBOLogFilter;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDateTimePickerTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldDropDownButtonLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBoxLabelTop;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProjectExecutionFilterForm extends AEGWTBootstrapForm {


	private static DSLAMBusI18NTexts 	TEXTS 				= GWT.create(DSLAMBusI18NTexts.class);

	public 	static final String 		NAME 				= "CRONIOBusDesktopProjectExecutionFilterForm";
	public 	static final String 		DATE_FORMAT_PICKER	= "d/m/Y H:i";

	private AEGWTBootstrapFormFieldDropDownButtonLabelTop  	severityDropdownButton;
	private AEGWTBootstrapFormFieldTextBoxLabelTop			filterTextBox;
	private	AEGWTBootstrapDateTimePickerTextBox            	safeBeforeDateTimePickerTextBox;
	private LabelElement									beforeLabel;	
	private AEGWTBootstrapFormFieldDropDownButtonLabelTop  	numberRowsDropdownButton;
	private RadioButton beforeNow;
	private RadioButton beforeDate;
		
	private String executionId;

	
	public CRONIOBusDesktopProjectExecutionFilterForm() {

		setGlyphIconButtonText(BUTTON_SUBMIT, TEXTS.filter(), AEGWTIBoostrapConstants.SPAN_GLYPHICON_FILTER);
		addButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.BTN);
		addButtonStyle(BUTTON_SUBMIT, AEGWTIBoostrapConstants.BTN_DEFAULT);
		addButtonsZoneStylename(AEGWTIBoostrapConstants.COL_XS_12);

		FlowPanel formZone = new FlowPanel();
		addWidget(formZone);
		formZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_FILTER_PANEL_FORM);

		FlowPanel severityZone = new FlowPanel();
		formZone.add(severityZone);
		severityZone.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_FILTER_PANEL_FORM_SEVERITYZONE);
		severityZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		severityDropdownButton = new AEGWTBootstrapFormFieldDropDownButtonLabelTop(TEXTS.filter_for_gravity());
		severityZone.add(severityDropdownButton);
		severityDropdownButton.addElement(TEXTS.all()		, TEXTS.all());
		severityDropdownButton.addElement(TEXTS.debug()		, TEXTS.debug());
		severityDropdownButton.addElement(TEXTS.info()		, TEXTS.info());
		severityDropdownButton.addElement(TEXTS.warning()	, TEXTS.warning());
		severityDropdownButton.addElement(TEXTS.error()		, TEXTS.error());
		severityDropdownButton.addElement(TEXTS.critical()	, TEXTS.critical());

		FlowPanel filterZone = new FlowPanel();
		formZone.add(filterZone);
		filterZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		filterTextBox 	= new AEGWTBootstrapFormFieldTextBoxLabelTop(TEXTS.filter_text_label(), TEXTS.search_placeholder());
		filterZone.add(filterTextBox);

		FlowPanel beforeZone = new FlowPanel();
		formZone.add(beforeZone);
		beforeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		beforeLabel 	= Document.get().createLabelElement();
		beforeLabel.setInnerText(TEXTS.before_label());
		beforeLabel.getStyle().setWidth(100, Unit.PCT);
		beforeLabel.getStyle().setPaddingLeft(0, Unit.PX);
		beforeZone.getElement().appendChild(beforeLabel);

		beforeNow 	= new RadioButton(TEXTS.before_date(),TEXTS.now());
		beforeDate 	= new RadioButton(TEXTS.before_date());
		beforeNow.getElement().addClassName(AEGWTIBoostrapConstants.COL_XS_12);
		beforeDate.getElement().addClassName(AEGWTIBoostrapConstants.COL_XS_1);
		beforeNow.setValue(true);
		beforeZone.add(beforeNow);
		beforeZone.add(beforeDate);

		safeBeforeDateTimePickerTextBox = new AEGWTBootstrapDateTimePickerTextBox(null);
		safeBeforeDateTimePickerTextBox.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
		safeBeforeDateTimePickerTextBox.addStyleName(DSLAMBusDesktopIStyleConstants.EXECUTION_LOGGER_FILTER_PANEL_FORM_DATETIME);
		beforeZone.add(safeBeforeDateTimePickerTextBox);

		FlowPanel rowsZone = new FlowPanel();
		formZone.add(rowsZone);
		rowsZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);

		numberRowsDropdownButton = new AEGWTBootstrapFormFieldDropDownButtonLabelTop(TEXTS.rows_for_page());
		rowsZone.add(numberRowsDropdownButton);
		numberRowsDropdownButton.addElement(TEXTS.number_10(), TEXTS.number_10());
		numberRowsDropdownButton.addElement(TEXTS.number_20(), TEXTS.number_20());
		numberRowsDropdownButton.addElement(TEXTS.number_50(), TEXTS.number_50());
		numberRowsDropdownButton.setItemSelected(TEXTS.number_20());

	}

	public boolean showValidateClientErrors() {
		boolean showErrors = false;
		return showErrors;
	}

	public void setExecutionId (String executionId) {
		this.executionId = executionId;
	}
	/**
	 * AEGWTICompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
		safeBeforeDateTimePickerTextBox.addJS(getId(), DATE_FORMAT_PICKER);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite connectionConfigureData) {

	}



	@Override
	public AEMFTMetadataElementComposite getData() {

		AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		getElementController().setElement(CRONIOBOLogFilter.LEVEL 			, formData	, severityDropdownButton.getSelectedId());
		getElementController().setElement(CRONIOBOLogFilter.FILTER_TEXT 	, formData	, filterTextBox.getText());				
		getElementController().setElement(CRONIOBOLogFilter.SIZE	 		, formData	, numberRowsDropdownButton.getSelectedId());
		getElementController().setElement(CRONIOBOLogFilter.OFFSET	 		, formData	, 0);
		getElementController().setElement(CRONIOBOLogFilter.EXECUTION_ID	, formData	, executionId);

				
		Date timestamp = null;
		if(beforeNow.getValue()) {
			timestamp = new Date();
		} else {			
			String dateStr = safeBeforeDateTimePickerTextBox.getDateText();
			DateTimeFormat formatter = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
			timestamp = formatter.parse(dateStr);
		}
		
		getElementController().setElement(CRONIOBOLogFilter.MAX_TIMESTAMP, formData, timestamp);

		
		
		return formData;

	}

	@Override
	public void initFormValidation(
			AEGWTIValidationChangeHandler formValidationhandler) {
		// TODO Auto-generated method stub
	}

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {

	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return super.isDispatchEventType(type)
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type);
	}

	/**
	 * PROTECTED
	 */

	@Override
	protected void resetForm() {
	}

	@Override
	protected void resetErrorLabel() {
		
	}

	@Override
	protected boolean isValidEvent(AEGWTLogicalEvent evt) {

		return false;
	}

	@Override
	protected void showErrors(AEMFTMetadataElementComposite dataErrors) {

	}

	
}
