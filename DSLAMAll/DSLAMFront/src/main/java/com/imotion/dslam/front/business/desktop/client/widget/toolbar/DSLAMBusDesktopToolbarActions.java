package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopNewProjectPopupForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class DSLAMBusDesktopToolbarActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopToolbarFileActions";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapGlyphiconButton 		newButton;
	private AEGWTBootstrapGlyphiconButton 		saveButton;
	private AEGWTBootstrapGlyphiconButton 		saveAllButton;
	private AEGWTBootstrapGlyphiconButton 		executeButton;
	private	 DSLAMBusDesktopNewProjectPopupForm	projectPopupForm;

	public DSLAMBusDesktopToolbarActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS);

		//NEW
		newButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FILE, texts.create(), texts.create());
		root.add(newButton);

		//SAVE
		saveButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FLOPPY_DISK, texts.save_project(), texts.save());
		root.add(saveButton);


		//SAVE ALL
		saveAllButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_HDD, texts.save_all(), texts.save_all());
		root.add(saveAllButton);
		saveAllButton.setVisible(false);

		//EXECUTE
		executeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLAY, texts.run(), texts.run());
		root.add(executeButton);

		newButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getProjectPopup().center();
			}
		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
				saveProjectEvent.setEventType(EVENT_TYPE.SAVE_PROJECT);
				getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
			}
		});

		saveAllButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
				saveProjectEvent.setEventType(EVENT_TYPE.SAVE_ALL_PROJECTS);
				getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
			}
		});
		
		executeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CRONIOBusDesktopProjectEvent saveProjectEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
				saveProjectEvent.setEventType(EVENT_TYPE.EXECUTE);
				getLogicalEventHandlerManager().fireEvent(saveProjectEvent);
			}
		});

	}

	public void hideProjectForm() {
		getProjectPopup().hide();
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
	
	public void setExecuteEnabled(boolean enabled) {
		executeButton.setEnabled(enabled);
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

	private DSLAMBusDesktopNewProjectPopupForm getProjectPopup() {
		if (projectPopupForm == null) {
			projectPopupForm = new DSLAMBusDesktopNewProjectPopupForm(this);
		}
		return projectPopupForm;
	}

}
