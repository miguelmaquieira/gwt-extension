package com.imotion.dslam.front.business.desktop.client.presenter.log;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopLogPresenter extends CRONIOBusProjectBasePresenter<CRONIOBusDesktopLogDisplay> {

	public static final String NAME = "CRONIOBusDesktopLogPresenter";

	public CRONIOBusDesktopLogPresenter(CRONIOBusDesktopLogDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	
	/**
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		getView().openLogSection(projectFinalSectionId, finalSectionData);
		
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_LOG;
	}

	
	
	/**
	 * PRIVATE
	 */

}
