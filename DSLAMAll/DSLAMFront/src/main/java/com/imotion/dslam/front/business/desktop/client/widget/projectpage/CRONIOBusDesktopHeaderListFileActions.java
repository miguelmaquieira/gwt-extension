package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.FormPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class CRONIOBusDesktopHeaderListFileActions extends CRONIOBusDesktopHeaderListActions {
	public static final String NAME = "CRONIOBusDesktopHeaderListActions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
//	private FlowPanel							headerZone;
//	private FlowPanel							actionsZone;
//	private AEGWTBootstrapGlyphiconButton 		addButton;
//	private AEGWTBootstrapGlyphiconButton 		deleteButton;
	private AEGWTBootstrapGlyphiconButton      uploadButton;
	private FormPanel							form;
	
	public CRONIOBusDesktopHeaderListFileActions(String text) {
		super(text);
		setAddButtonVisible(false);
		setDeleteButtonVisible(false);
		form = new FormPanel();
		addWidget(form);
		form.setAction("");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		InputElement fileInput = Document.get().createFileInputElement();
		fileInput.setId("inputFile");
		fileInput.setName("inputFile");
		form.getElement().appendChild(fileInput);
	}
	

	/**
	 * AEGWTCompositePanel
	 */
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	
	
	/**
	 * PROTECTED
	 */
	
	protected FormPanel getForm() {
		return form;
	}
	
	/**
	 * PRIVATE
	 */
}