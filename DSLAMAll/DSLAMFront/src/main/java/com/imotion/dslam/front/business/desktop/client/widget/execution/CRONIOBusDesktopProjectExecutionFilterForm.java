package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.validation.AEGWTIValidationChangeHandler;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDateTimePickerTextBox;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapForm;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldDropDownButtonLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFormFieldTextBoxLabelTop;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopProjectExecutionFilterForm extends AEGWTBootstrapForm {

	public static final String NAME 				= "CRONIOBusDesktopProjectExecutionFilterForm";
	public static final String DATE_FORMAT_PICKER	= "d/m/Y H:i";
	private static DSLAMBusI18NTexts 	TEXTS 		= GWT.create(DSLAMBusI18NTexts.class);
	
	private String executionId;

	private AEGWTBootstrapFormFieldDropDownButtonLabelTop  severityDropdownButton;
	private AEGWTBootstrapFormFieldTextBoxLabelTop			filterTextBox;
	private	 AEGWTBootstrapDateTimePickerTextBox            safeBeforeDateTimePickerTextBox;
	private LabelElement									beforeLabel;	
	private AEGWTBootstrapFormFieldDropDownButtonLabelTop  numberRowsDropdownButton;
	
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
		severityDropdownButton.addElement("id", TEXTS.all());
		severityDropdownButton.addElement("id", TEXTS.debug());
		severityDropdownButton.addElement("id", TEXTS.info());
		severityDropdownButton.addElement("id", TEXTS.warning());
		severityDropdownButton.addElement("id", TEXTS.error());
		severityDropdownButton.addElement("id", TEXTS.critical());
		
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
		
		RadioButton beforeNow 	= new RadioButton(TEXTS.before_date(),TEXTS.now());
		RadioButton beforeDate 	= new RadioButton(TEXTS.before_date());
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
//		if (connectionConfigureData != null) {
//			String 			userName 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME					, connectionConfigureData);
//			String 			password 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD					, connectionConfigureData);
//			int 			timeout 					= getElementController().getElementAsInt(CRONIOBOIMachineProperties.TIMEOUT						, connectionConfigureData);
//			String 			prompt 						= getElementController().getElementAsString(CRONIOBOIMachineProperties.PROMPT_REGEX				, connectionConfigureData);
//			String 			userPrompt 					= getElementController().getElementAsString(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX	, connectionConfigureData);
//			String 			passwordPrompt 				= getElementController().getElementAsString(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX	, connectionConfigureData);
//			String 			rollbackConditionPrompt 	= getElementController().getElementAsString(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX	, connectionConfigureData);
//			int 			protocolType 				= getElementController().getElementAsInt(CRONIOBOIMachineProperties.PROTOCOL_TYPE				, connectionConfigureData);
//
//			userNameTextBox.setText(userName);
//			passwordTextBox.setText(password);
//			if (timeout > 0) {
//				timeOutTextBox.setText(String.valueOf(timeout));
//			}
//			promptTextBox.setText(prompt);
//			if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET) {
//				userPromptTextBox.setText(userPrompt);
//				passwordPromptTextBox.setText(passwordPrompt);
//			}
//			rollbackConditionPromptTextBox.setText(rollbackConditionPrompt);
//			handleProtocolType(protocolType, false);
//		}
	}
	
	

	@Override
	public AEMFTMetadataElementComposite getData() {
//		boolean errors = showValidateClientErrors();
//		if (errors == false) {
//			String	timeoutStr		= timeOutTextBox.getText();
//			String	protocoltypeStr	= protocolTypeDropdownButton.getSelectedId();
//			int		timeoutInt		= AEMFTCommonUtilsBase.getIntegerFromString(timeoutStr);
//			int		protocoltypeInt	= AEMFTCommonUtilsBase.getIntegerFromString(protocoltypeStr);
//
			AEMFTMetadataElementComposite formData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			getElementController().setElement(CRONIOBOIMachineProperties.USERNAME					, formData	, "Probando");
//			getElementController().setElement(CRONIOBOIMachineProperties.PASSWORD					, formData	, passwordTextBox.getText());
//			getElementController().setElement(CRONIOBOIMachineProperties.TIMEOUT					, formData	, timeoutInt);
//			getElementController().setElement(CRONIOBOIMachineProperties.PROMPT_REGEX				, formData	, promptTextBox.getText());
//			getElementController().setElement(CRONIOBOIMachineProperties.USERNAME_PROMPT_REGEX		, formData	, userPromptTextBox.getText());
//			getElementController().setElement(CRONIOBOIMachineProperties.PASSWORD_PROMPT_REGEX		, formData	, passwordPromptTextBox.getText());
//			getElementController().setElement(CRONIOBOIMachineProperties.ROLLBACK_CONDITION_REGEX	, formData	, rollbackConditionPromptTextBox.getText());
//			getElementController().setElement(CRONIOBOIMachineProperties.PROTOCOL_TYPE				, formData	, protocoltypeInt);
//			getElementController().setElement(CRONIOBOIMachineProperties.MACHINE_NAME				, formData	, getId());
			return formData;
//		} else {
//			return null;
//		}
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
//		super.dispatchEvent(evt);
//		LOGICAL_TYPE type = evt.getEventType();
//		String sourceWidget = evt.getSourceWidget();
//		if (AEGWTBootstrapDropdownButton.NAME.equals(sourceWidget) && LOGICAL_TYPE.CHANGE_EVENT.equals(type)) {
//			String sourceContainerId = evt.getSourceContainerId();
//			if (NAME.equals(sourceContainerId)) {
//				String selectedId = evt.getSourceWidgetId();
//				int protocolType = AEMFTCommonUtilsBase.getIntegerFromString(selectedId);
//				handleProtocolType(protocolType, true);
//			}
//		}
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
//		userNameTextBox.setText("");
//		passwordTextBox.setText(""); 
//		timeOutTextBox.setText("");
//		promptTextBox.setText(""); 
//		userPromptTextBox.setText("");
//		passwordPromptTextBox.setText("");
//		rollbackConditionPromptTextBox.setText("");
//		protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1));
	}

	@Override
	protected void resetErrorLabel() {
//		userNameTextBox.setErrorLabelVisible(false);
//		passwordTextBox.setErrorLabelVisible(false);
//		timeOutTextBox.setErrorLabelVisible(false);
//		promptTextBox.setErrorLabelVisible(false);
//		userPromptTextBox.setErrorLabelVisible(false);
//		passwordPromptTextBox.setErrorLabelVisible(false);
//		rollbackConditionPromptTextBox.setErrorLabelVisible(false);
	}

	@Override
	protected boolean isValidEvent(AEGWTLogicalEvent evt) {
//		if (CRONIOBusDesktopPreferencesConnectionPresenter.NAME.equals(evt.getSourceWidget())) {
//			return true;
//		} else 
		return false;
	}

	@Override
	protected void showErrors(AEMFTMetadataElementComposite dataErrors) {

	}

	/**
	 * PRIVATE
	 */

	private void handleProtocolType(int protocolType, boolean fireEvent) {
//		if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET) {
//			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET));
//			userPromptTextBox.setVisible(true);
//			passwordPromptTextBox.setVisible(true);
//		} else if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1) {
//			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_1));
//			userPromptTextBox.setVisible(false);
//			passwordPromptTextBox.setVisible(false);
//		} else if (protocolType == CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_2) {
//			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH_2));
//			userPromptTextBox.setVisible(false);
//			passwordPromptTextBox.setVisible(false);
//		} else {
//			protocolTypeDropdownButton.setItemSelected(String.valueOf(CRONIOBOIMachineProperties.PROTOCOL_TYPE_TEST));
//			userPromptTextBox.setVisible(false);
//			passwordPromptTextBox.setVisible(false);
//		}
//		
//		if (fireEvent) {
//			AEMFTMetadataElementComposite formData = getData();
//
//			CRONIOBusDesktopPreferencesEvent connectionConfigureFormEvt = new CRONIOBusDesktopPreferencesEvent(getId(), getName());
//			connectionConfigureFormEvt.setEventType(EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT);
//			connectionConfigureFormEvt.addElementAsComposite(getId() ,formData);
//			connectionConfigureFormEvt.setConnectionName(getId());
//			getLogicalEventHandlerManager().fireEvent(connectionConfigureFormEvt);
//		}
//		
//		AEGWTJQueryPerfectScrollBar.updateScroll(CRONIOBusDesktopPreferencesMachineSectionsDeckPanel.NAME);
	}
}
