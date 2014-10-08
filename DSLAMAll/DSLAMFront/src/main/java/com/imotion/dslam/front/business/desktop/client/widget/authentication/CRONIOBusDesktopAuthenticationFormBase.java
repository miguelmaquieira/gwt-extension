package com.imotion.dslam.front.business.desktop.client.widget.authentication;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.imotion.dslam.front.business.client.CRONIOBusCommonIImageConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.widget.common.CRONIOBusDesktopBootstrapFormErrorWidget;
import com.selene.arch.exe.gwt.client.ui.widget.form.AEGWTFormContainerPanelBase;
import com.selene.arch.exe.gwt.mvp.event.authentication.AEGWTAuthenticationEventTypes.AUTHENTICATION_TYPE;

public abstract class CRONIOBusDesktopAuthenticationFormBase extends AEGWTFormContainerPanelBase {

	private static final CRONIOBusI18NTexts 	TEXTS 	= GWT.create(CRONIOBusI18NTexts.class);
	
	private Image 										logoImg;
	private HTML										descriptionHTML;
	private CRONIOBusDesktopBootstrapFormErrorWidget 	errorWidget;
	private FlowPanel									fieldsZone;
	private Button										actionButton;
	private FlowPanel									bottomZone;
	private FlowPanel 									buttonActionZone;
	private HTMLPanel	 								root;
	
	
	public CRONIOBusDesktopAuthenticationFormBase(String description) {
		
		root = new HTMLPanel("");
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM);

		//Logo zone
		FlowPanel logoZone = new FlowPanel();
		root.add(logoZone);
		logoZone.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_LOGO);

		logoImg = new Image(CRONIOBusCommonIImageConstants.LOGO);
		logoZone.add(logoImg);

		//Description
		descriptionHTML = new HTML();
		root.add(descriptionHTML);
		descriptionHTML.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_DESCRIPTION);
		setDescriptionHTML(description);

		//Container
		FlowPanel container = new FlowPanel();
		root.add(container);
		container.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_CONTAINER);
		
		//Error
		errorWidget = new CRONIOBusDesktopBootstrapFormErrorWidget();
		container.add(errorWidget);
		errorWidget.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_ERROR);
		getErrorWidget().setVisibility(Visibility.HIDDEN);

		//Fields zone
		fieldsZone = new FlowPanel();
		container.add(fieldsZone);
		fieldsZone.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_FIELDS_ZONE);
		setContentPanel(fieldsZone, false);

		//Action Button
		buttonActionZone = new FlowPanel();
		root.add(buttonActionZone);
		buttonActionZone.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_ACTION_BUTTON);
		
		actionButton = new Button(TEXTS.enter());
		buttonActionZone.add(actionButton);
		
		//Bottom zone
		bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(CRONIOBusDesktopIStyleConstants.AUTHENTICATION_FORM_BOTTOM_ZONE);
	}
	
	public void setError(String errorText) {
		errorWidget.setErrorText(errorText);
		errorWidget.setVisibility(Visibility.VISIBLE);
		errorWidget.setVisible(true);
	}
	
	/****************************************************************************
	 *                           PROTECTED FUNCTIONS
	 ****************************************************************************/
	protected abstract	boolean checkErrors(int errorCode);
	protected abstract 	AUTHENTICATION_TYPE getAuthenticationEventType();

	protected void setDescriptionHTML(String description) {
		descriptionHTML.setHTML(description);
	}

	protected void setActionButtonHTML(String html) {
		actionButton.setHTML(html);
	}
	

	protected Button getActionButton() {
		return actionButton;
	}

	protected FlowPanel getFieldsZone() {
		return fieldsZone;
	}

	protected FlowPanel getActionZone() {
		return buttonActionZone;
	}

	protected FlowPanel getBottomZone() {
		return bottomZone;
	}
	
	protected HTMLPanel getRootPanel() {
		return root;
	}

	protected CRONIOBusDesktopBootstrapFormErrorWidget getErrorWidget() {
		return errorWidget;
	}

}
