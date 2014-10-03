package com.imotion.dslam.front.business.desktop.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.header.AEGWTIHeader;
import com.selene.arch.exe.gwt.client.view.AEGWTCompositeDesktopPanelLoggedView;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;

public abstract class DSLAMBusDesktopPanelBaseView extends AEGWTCompositeDesktopPanelLoggedView implements AEGWTCompositePanelLoggedViewDisplay {

	/****************************************************************************
	 *                       AEGWTICompositeContainerPanel
	 ****************************************************************************/
	@Override
	public void initContentPanel(HasWidgets contentPanel) {
		FlowPanel rootPanel = new FlowPanel();
		initWidget(rootPanel);
		
		//CONTENT
		Widget contentPanelAsWidget = (Widget) contentPanel;
		rootPanel.add(contentPanelAsWidget);
		setContentPanel(contentPanel, false);
	}

	@Override
	public void loadAppData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
	}
	
	/****************************************************************************
	 *                          AEGWTCompositePanelView
	 ****************************************************************************/

	@Override
	protected AEGWTIHeader createHeaderView() {
		return null;
	}
	
	/****************************************************************************
	 *                       BusinessDesktopScreenViewLoggedDisplay
	 ****************************************************************************/

	@Override
	public int getHelpStepSize() {
		return 0;
	}

	@Override
	public String getHelpStreenTitle() {
		return null;
	}

	/****************************************************************************
	 *                          AEGWTICompositePanel
	 ****************************************************************************/

	@Override
	public void postDisplay() {
		super.postDisplay();
	}
	

	/****************************************************************************
	 * 					PROTECTED
	 *****************************************************************************/

	
	/****************************************************************************
	 *                          PRIVATE FUNCTIONS
	 ****************************************************************************/

	/****************************************************************************
	 *                          NATIVE FUNCTIONS
	 ****************************************************************************/

}
