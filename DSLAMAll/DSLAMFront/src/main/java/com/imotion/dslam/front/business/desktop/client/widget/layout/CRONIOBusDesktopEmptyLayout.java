package com.imotion.dslam.front.business.desktop.client.widget.layout;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopEmptyLayout extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayout{


	public 	final static String NAME = "CRONIOBusDesktopEmptyLayout";
	
	private FlowPanel root;

	public CRONIOBusDesktopEmptyLayout() {	
		root = new FlowPanel();
		initWidget(root);
	}

	
	/**
	 * AEGWTCompositePanel
	 */
	
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
	}
	
		
	/**
	 * CRONIOBusDesktopIsLayout
	 */

	@Override
	public void setLayoutContent(Widget content) {
		root.clear();
		root.add(content);
	}

}
