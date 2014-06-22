package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class CRONIOBusDesktopHeaderListFileActions extends CRONIOBusDesktopHeaderListActions {
	public static final String NAME = "CRONIOBusDesktopHeaderListActions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel							headerZone;
	private FlowPanel							actionsZone;
	private AEGWTBootstrapGlyphiconButton 		addButton;
	private AEGWTBootstrapGlyphiconButton 		deleteButton;
	private AEGWTBootstrapGlyphiconButton      uploadButton;
	
	public CRONIOBusDesktopHeaderListFileActions(String text) {
		super(text);
		setAddButtonVisible(false);
		setDeleteButtonVisible(false);
		addFileInput();
		uploadButton = addButton(AEGWTIBoostrapConstants.GLYPHICON_UPLOAD, TEXTS.upload(), TEXTS.upload());
		
	}
	
	public void probando() {
		int i = 0;
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
	
	/**
	 * PRIVATE
	 */
}