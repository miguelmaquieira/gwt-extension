package com.imotion.dslam.front.business.desktop.client.presenter.execution;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopExecutionPresenter extends CRONIOBusProjectBasePresenter<CRONIOBusDesktopExecutionDisplay> {

	public static final String NAME = "CRONIOBusDesktopExecutionPresenter";

	public CRONIOBusDesktopExecutionPresenter(CRONIOBusDesktopExecutionDisplay view) {
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
		getView().setData(finalSectionData);
		
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_EXECUTION;
	}

	
	
	/**
	 * PRIVATE
	 */

}
