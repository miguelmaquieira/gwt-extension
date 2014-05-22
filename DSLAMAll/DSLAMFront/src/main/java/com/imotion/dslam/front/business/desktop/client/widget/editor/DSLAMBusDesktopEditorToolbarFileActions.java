package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopEditorToolbarFileActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopEditorToolbar";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapGlyphiconButton newButton;
	private AEGWTBootstrapGlyphiconButton saveButton;
	private AEGWTBootstrapGlyphiconButton runButton;

	public DSLAMBusDesktopEditorToolbarFileActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_FILE_ACTIONS);

		//NEW
		SimplePanel newButtonZone = new SimplePanel();
		root.add(newButtonZone);
		newButtonZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		
		newButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FILE, texts.create(), texts.create());
		newButtonZone.add(newButton);
		
		//SAVE
		SimplePanel saveButtonZone = new SimplePanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		
		saveButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FLOPPY_DISK, texts.save(), texts.save());
		saveButtonZone.add(saveButton);

		//RUN
		SimplePanel runButtonZone = new SimplePanel();
		root.add(runButtonZone);
		runButtonZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		
		runButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLAY_CIRCLE, texts.run(), texts.run());
		runButtonZone.add(runButton);

		newButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.ADD_EVENT);
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});

		runButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.RUN_EVENT);
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});
	}
	
	public void setSaveEnabled(boolean modified) {
		saveButton.setEnabled(modified);
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
