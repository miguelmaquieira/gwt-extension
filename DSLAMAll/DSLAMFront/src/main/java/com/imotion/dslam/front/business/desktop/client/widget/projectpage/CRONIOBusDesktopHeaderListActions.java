package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopHeaderListActions extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopHeaderListActions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel											headerZone;
	private FlowPanel											actionsZone;
	private AEGWTBootstrapGlyphiconButton 						addButton;
	private AEGWTBootstrapGlyphiconButton 						deleteButton;
	
	public CRONIOBusDesktopHeaderListActions(String text) {
		headerZone = new FlowPanel();
		initWidget(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.HEADER_ACTIONS);
		
		if (!AEMFTCommonUtilsBase.isEmptyString(text)) {
			AEGWTLabel headerLabel = new AEGWTLabel(text);
			headerLabel.addStyleName(AEGWTIBoostrapConstants.COL_XS_6);
			headerZone.add(headerLabel);
			
			actionsZone = new FlowPanel();
			headerZone.add(actionsZone);
			
			deleteButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_TRASH, null, TEXTS.delete());
			deleteButton.addStyleName(DSLAMBusDesktopIStyleConstants.HEADER_ACTIONS_WITH_LABEL_BUTTON);
			deleteButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
			deleteButton.setVisible(false);
			actionsZone.add(deleteButton);
			
			addButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
			addButton.addStyleName(DSLAMBusDesktopIStyleConstants.HEADER_ACTIONS_WITH_LABEL_BUTTON);
			addButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
			actionsZone.add(addButton);
			
		} else {
			actionsZone = new FlowPanel();
			headerZone.add(actionsZone);
			
			addButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLUS, null, TEXTS.add());
			addButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
			actionsZone.add(addButton);
			
			deleteButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_TRASH, null, TEXTS.delete());
			deleteButton.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
			deleteButton.setVisible(false);
			actionsZone.add(deleteButton);
		}
	}
	
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addButton.addClickHandler(handler);
	}
	
	public AEGWTBootstrapGlyphiconButton getDeleteButton() {
		return deleteButton;
	}
	
	public AEGWTBootstrapGlyphiconButton getAddButton() {
		return addButton;
	}
	
	public void setAddButtonVisible(boolean visible) {
		addButton.setVisible(visible);
	}
	
	public void setDeleteButtonVisible(boolean visible) {
		deleteButton.setVisible(visible);
	}
	
	public AEGWTBootstrapGlyphiconButton addButton(String glyphIcon, String text, String title) {
		AEGWTBootstrapGlyphiconButton button = new AEGWTBootstrapGlyphiconButton(glyphIcon, text, title);
		actionsZone.add(button);
		return button;
	}
	
	public void addWidget(Widget widget) {
		actionsZone.add(widget);
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