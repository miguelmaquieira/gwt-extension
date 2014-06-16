package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

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

public class DSLAMBusDesktopToolbarActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopToolbarFileActions";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapGlyphiconButton newButton;
	private AEGWTBootstrapGlyphiconButton saveButton;
	private AEGWTBootstrapGlyphiconButton saveAllButton;

	public DSLAMBusDesktopToolbarActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS);

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


		//SAVE ALL
		SimplePanel saveAllButtonZone = new SimplePanel();
		root.add(saveAllButtonZone);
		saveAllButtonZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_4);
		saveAllButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_HDD, texts.save_all(), texts.save_all());
		saveAllButtonZone.add(saveAllButton);

		newButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.NEW_EVENT);
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.SAVE_EVENT);
				evt.setSourceWidgetId(getId());
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});
		
		saveAllButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AEGWTLogicalEvent evt = new AEGWTLogicalEvent(getWindowName(), getName());
				evt.setEventType(LOGICAL_TYPE.SAVE_ALL_EVENT);
				evt.setSourceWidgetId(getId());
				getLogicalEventHandlerManager().fireEvent(evt);
			}
		});
		
	}

	public void reset() {
		setSaveEnabled(false);
		setSaveAllEnabled(false);
	}


	public void setSaveEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
	}
	
	public void setSaveAllEnabled(boolean enabled) {
		saveAllButton.setEnabled(enabled);
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
