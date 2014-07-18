package com.imotion.dslam.front.business.desktop.client.widget.common;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTTextDisplayBase;

public class CRONIOBusDesktopBootstrapFormErrorWidget extends AEGWTCompositePanel implements AEGWTTextDisplayBase {

	public static final String NAME = "CRONIOBusDesktopBootstrapFormErrorWidget";
	
	private HTML errorLabel;
	
	public CRONIOBusDesktopBootstrapFormErrorWidget() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.FORM_ERROR);
		
		errorLabel = new HTML();
		root.add(errorLabel);
	}
	
	public void setErrorText(String errorText) {
		errorLabel.setHTML(errorText);
	}
	
	/****************************************************************************
	 *                        	AEGWTICompositePanel
	 ****************************************************************************/

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// nothing to do
	}

	/**
	 * AEGWTTextDisplayBase
	 */
	
	@Override
	public String getText() {
		return errorLabel.getText();
	}

	@Override
	public void setText(String text) {
		errorLabel.setText(text);
	}

}

