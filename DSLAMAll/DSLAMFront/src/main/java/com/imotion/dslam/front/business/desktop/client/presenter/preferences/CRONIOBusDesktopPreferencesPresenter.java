package com.imotion.dslam.front.business.desktop.client.presenter.preferences;

import com.google.gwt.user.client.ui.HasWidgets;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopPreferencesPresenter extends CRONIOBusPreferencesBasePresenter<CRONIOBusDesktopPreferencesDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "CRONIOBusDesktopPreferencesPresenter";

	public CRONIOBusDesktopPreferencesPresenter(CRONIOBusDesktopPreferencesDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	protected void addView(HasWidgets container) {
		super.addView(container);
		getPreferencesLayout().setvisibleLayoutItemHeader(false);
		
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {

	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return false;
	}
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_ROOT;
	}
	
	/**
	 * PRIVATE
	 */
}
