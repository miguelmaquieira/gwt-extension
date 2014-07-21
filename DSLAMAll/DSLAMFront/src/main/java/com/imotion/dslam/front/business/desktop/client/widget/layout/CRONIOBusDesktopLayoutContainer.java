package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenter;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.mvp.event.AEGWTIEventHandlerManager;

public class CRONIOBusDesktopLayoutContainer extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayoutContainer {

	public static final String NAME = "CRONIOBusDesktopLayoutContainer";
	
	public static final String LAYOUT_PROJECT_ID 		= "PROJECT_LAYOUT_ID";
	public static final String LAYOUT_PREFERENCES_ID 	= "PREFERENCES_LAYOUT_ID";
	public static final String LAYOUT_EMPTY_ID 			= "EMPTY_LAYOUT_ID";

	private DeckLayoutPanel			rootSwitcher;
	private AEGWTBasePresenter<?>	currentPresenter;
	private Map<String, Integer>	layoutIndexMap;
	
	public CRONIOBusDesktopLayoutContainer() {
		rootSwitcher = new DeckLayoutPanel();
		initWidget(rootSwitcher);
		layoutIndexMap = new HashMap<>();
	}

	@Override
	public CRONIOBusDesktopIsLayout getCurrentLayout() {
		return (CRONIOBusDesktopIsLayout) rootSwitcher.getVisibleWidget();
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

	@Override
	public void setCurrentPresenter(AEGWTBasePresenter<?> currentPresenter) {
		this.currentPresenter = currentPresenter;
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
		if (data != null) {
			List<AEMFTMetadataElement> dataElements = data.getElementList();
			for (AEMFTMetadataElement layoutData : dataElements) {
				String layoutId = layoutData.getKey();
				if (layoutIndexMap.containsKey(layoutId)) {
					setLayoutData(layoutId, (AEMFTMetadataElementComposite) layoutData);
				}
			}
		}

	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		getCurrentLayout().postDisplay();
	}
	
	@Override
	public AEGWTIEventHandlerManager getLogicalEventHandlerManager() {
		return currentPresenter.getLogicalEventHandlerManager();
	}

	@Override
	public AEGWTIEventHandlerManager getFlowEventHandlerManager() {
		return currentPresenter.getFlowEventHandlerManager();
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
	
	private void setLayoutData(String layoutId, AEMFTMetadataElementComposite data) {
		int								layoutIndex = layoutIndexMap.get(layoutId);
		CRONIOBusDesktopIsLayout		layout		= (CRONIOBusDesktopIsLayout) rootSwitcher.getWidget(layoutIndex);
		layout.setData(data);
	}

}
