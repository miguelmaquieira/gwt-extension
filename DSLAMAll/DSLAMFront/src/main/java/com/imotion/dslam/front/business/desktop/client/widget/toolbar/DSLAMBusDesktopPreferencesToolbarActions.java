package com.imotion.dslam.front.business.desktop.client.widget.toolbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.preferences.CRONIOBusDesktopNewConnectionPopupForm;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;

public class DSLAMBusDesktopPreferencesToolbarActions extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopPreferencesToolbarActions";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapGlyphiconButton 			newConnectionButton;
	private AEGWTBootstrapGlyphiconButton 			saveButton;
	private AEGWTBootstrapGlyphiconButton 			closeButton;
	private	 CRONIOBusDesktopNewConnectionPopupForm	connectionPopupForm;
	
	

	public DSLAMBusDesktopPreferencesToolbarActions() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS);
		
		//NEW CONNECTION
		newConnectionButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FILE, texts.create_connection(), texts.create());
		root.add(newConnectionButton); 

		//SAVE
		saveButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_FLOPPY_DISK, texts.save(), texts.save());
		root.add(saveButton);

		//EXIT
		closeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_REMOVE, texts.exit(), texts.exit());
		root.add(closeButton);
		closeButton.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_ACTIONS_CLOSE_BUTTON);
		
		newConnectionButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getConnectionPopup().center();
			}
		});
		
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			}
		});

		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!saveButton.isEnabled() || Window.confirm("Hay cambios sin guardar, seguro que quieres salir?")){
					CRONIOBusDesktopPreferencesEvent openProjectsEvent = new CRONIOBusDesktopPreferencesEvent(getWindowName(), getName());
					openProjectsEvent.setEventType(EVENT_TYPE.OPEN_PROJECTS_PAGE);
					getLogicalEventHandlerManager().fireEvent(openProjectsEvent);
				}
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

	public void hideConnectionForm() {
		getConnectionPopup().hide();
	}

	/**
	 * PRIVATE
	 */

	private CRONIOBusDesktopNewConnectionPopupForm getConnectionPopup() {
		if (connectionPopupForm == null) {
			connectionPopupForm = new CRONIOBusDesktopNewConnectionPopupForm(this);
		}
		return connectionPopupForm;
	}
}
