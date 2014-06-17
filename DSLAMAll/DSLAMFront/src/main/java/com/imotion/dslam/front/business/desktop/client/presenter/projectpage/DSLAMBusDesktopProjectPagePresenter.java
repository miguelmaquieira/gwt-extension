package com.imotion.dslam.front.business.desktop.client.presenter.projectpage;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopProjectPagePresenter extends CRONIOBusProjectBasePresenter<DSLAMBusDesktopProjectPageDisplay> {

	public static final String NAME = "DSLAMBusDesktopProjectPagePresenter";

	public DSLAMBusDesktopProjectPagePresenter(DSLAMBusDesktopProjectPageDisplay view) {
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_PROJECT_ROOT;
	}
	
	/**
	 * PRIVATE
	 */

}
