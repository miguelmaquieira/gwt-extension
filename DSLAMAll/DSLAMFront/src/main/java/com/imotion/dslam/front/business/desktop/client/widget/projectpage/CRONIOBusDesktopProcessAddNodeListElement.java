package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;

public class CRONIOBusDesktopProcessAddNodeListElement extends AEGWTCompositePanel {
	
	public static final String NAME = "CRONIOBusDesktopProcessAddNodeListElement";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private AEGWTICompositePanel 	parentWidget;
	private AEGWTBootstrapTreeMenu menu;
	private AEGWTBootstrapTreeMenuFinalItem menuItem;

	public CRONIOBusDesktopProcessAddNodeListElement(AEGWTCompositePanel parentWidget) {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		
		menu = new AEGWTBootstrapTreeMenu();
		root.add(menu);
		
		menuItem = new CRONIOBusDesktopProcessAddNodeFinalItem(parentWidget);
		menu.addWidget(menuItem);
		
		menu.addSeparator();
	}
	
	public String getElementName() {
		return menuItem.getElementName();
	}
	
	public void setSelected(boolean selected) {
		menuItem.setSelected(selected);
	}
	
	/****************************************************************************
	 *                        AEGWTICompositePanel
	 ****************************************************************************/
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public AEGWTICompositePanel getParentWidget(IsWidget child) {
		return parentWidget;
	}
}