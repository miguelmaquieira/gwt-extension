package com.imotion.dslam.front.business.desktop.client.presenter.scriptsmanager;

import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenter;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopScriptsManagerPresenter extends CRONIOBusProjectBasePresenter<CRONIOBusDesktopScriptsManagerDisplay> {

	public static final String NAME = "CRONIOBusDesktopScriptsManagerPresenter";

	public CRONIOBusDesktopScriptsManagerPresenter(CRONIOBusDesktopScriptsManagerDisplay view) {
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
	 * PROTECTED
	 */
	
	@Override
	protected void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData) {
		getView().setData(finalSectionData);
	}
	
	@Override
	protected String getSectionType() {
		return SECTION_TYPE_SCRIPT;
	}	
}
