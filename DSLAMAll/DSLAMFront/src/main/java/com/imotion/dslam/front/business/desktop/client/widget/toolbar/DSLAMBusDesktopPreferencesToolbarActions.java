package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class DSLAMBusDesktopPreferencesToolbarActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopPreferencesToolbarActions";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapGlyphiconButton 		saveButton;
	private AEGWTBootstrapGlyphiconButton 		closeButton;
	
	

	public DSLAMBusDesktopPreferencesToolbarActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS);

		//SAVE
		saveButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FLOPPY_DISK, texts.save_project(), texts.save());
		root.add(saveButton);


		//SAVE ALL
		closeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_HDD, texts.exit(), texts.exit());
		root.add(closeButton);
		closeButton.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_CLOSE_BUTTON);
		closeButton.setVisible(false);
		

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
//				saveProjectEvent.setEventType(EVENT_TYPE.SAVE_PROJECT);
//				getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
			}
		});

		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
//				saveProjectEvent.setEventType(EVENT_TYPE.SAVE_ALL_PROJECTS);
//				getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
			}
		});
	}

	public void setSaveEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
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

	/**
	 * PRIVATE
	 */
}
