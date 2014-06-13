package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLayoutContainer extends AEGWTCompositePanel implements CRONOIOBusDesktopIsLayoutContainer {

	public static final String NAME = "CRONIOBusDesktopLayoutContainer";
	
	public static final String LAYOUT_PROJECT_ID = "PROJECT_LAYOUT_ID";

	private DeckLayoutPanel rootSwitcher;

	private Map<String, Integer> layoutIndexMap;
	
	public CRONIOBusDesktopLayoutContainer() {
		rootSwitcher = new DeckLayoutPanel();
		initWidget(rootSwitcher);
		layoutIndexMap = new HashMap<>();
	}

	@Override
	public void addLayout(String layoutId, CRONIOBusDesktopIsLayout layout) {
		int layoutIndex = rootSwitcher.getWidgetCount();
		rootSwitcher.add(layout);
		layoutIndexMap.put(layoutId, layoutIndex);
	}
	
	@Override
	public void showLayout(String layoutId) {
		int layoutIndex = layoutIndexMap.get(layoutId);
		rootSwitcher.showWidget(layoutIndex);
	}

	@Override
	public void setLayoutContent(Widget widget) {
		CRONIOBusDesktopIsLayout currentLayout = getCurrentLayout();
		currentLayout.setLayoutContent(widget);
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

	/**
	 * HasWidgets
	 */
	@Deprecated
	@Override
	public void add(Widget w) {
		//nothing to do
	}

	@Deprecated
	@Override
	public void clear() {
		//nothing to do
	}

	@Deprecated
	@Override
	public Iterator<Widget> iterator() {
		//nothing to do
		return null;
	}

	@Deprecated
	@Override
	public boolean remove(Widget w) {
		//nothing to do
		return false;
	}

	/**
	 * PRIVATE
	 */

	private CRONIOBusDesktopIsLayout getCurrentLayout() {
		return (CRONIOBusDesktopIsLayout) rootSwitcher.getVisibleWidget();
	}

}
