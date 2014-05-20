package com.imotion.dslam.front.business.desktop.client.view.studio;

import java.util.Date;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.presenter.studio.DSLAMBusDesktopStudioDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class DSLAMBusDesktopStudioScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopStudioDisplay {

	public static final String NAME = "DSLAMBusDesktopStudioScreenView";
	
	private FlowPanel 						root;
	private DSLAMBusDesktopToolbar	toolbar;
	
	public DSLAMBusDesktopStudioScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		
		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setFilename("script1");
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
	}

	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/
	
	
	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	
}
