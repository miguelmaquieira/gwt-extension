package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.DSLAMBusDesktopNewProjectPopupForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapDropdownGlyphIconButton;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class DSLAMBusDesktopProjectsToolbarActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopProjectsToolbarActions";
	private DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	public static int OPTION_TYPE_OPEN_PREFERENCES	= 1;
	public static int OPTION_TYPE_EXIT				= 2;

	private AEGWTBootstrapGlyphiconButton 		newButton;
	private AEGWTBootstrapGlyphiconButton 		saveButton;
	private AEGWTBootstrapGlyphiconButton 		saveAllButton;
	private AEGWTBootstrapGlyphiconButton 		executeButton;
	private	 DSLAMBusDesktopNewProjectPopupForm	projectPopupForm;

	public DSLAMBusDesktopProjectsToolbarActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS);

		//NEW
		newButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FILE, TEXTS.create(), TEXTS.create());
		root.add(newButton);

		//SAVE
		saveButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FLOPPY_DISK, TEXTS.save_project(), TEXTS.save());
		root.add(saveButton);

		//SAVE ALL
		saveAllButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_HDD, TEXTS.save_all(), TEXTS.save_all());
		root.add(saveAllButton);
		saveAllButton.setVisible(false);

		//EXECUTE
		executeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_PLAY, TEXTS.run(), TEXTS.run());
		root.add(executeButton);
		
		//OPTIONS		
		AEGWTBootstrapDropdownGlyphIconButton optionButton = new AEGWTBootstrapDropdownGlyphIconButton(AEGWTIBoostrapConstants.GLYPHICON_TASKS);		
		optionButton.addElement(String.valueOf(OPTION_TYPE_OPEN_PREFERENCES),  TEXTS.preferences());
		optionButton.addElement(String.valueOf(OPTION_TYPE_EXIT),TEXTS.exit());
		optionButton.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_OPTIONS_BUTTON);
		root.add(optionButton);
		
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
