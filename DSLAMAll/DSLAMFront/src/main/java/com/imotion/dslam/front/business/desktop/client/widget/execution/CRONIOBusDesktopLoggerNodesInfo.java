package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopNodeInfoPanel;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLoggerNodesInfo extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopLoggerNodeInfo";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private FlowPanel 										root;
	private CRONIOBusDesktopNodeInfoPanel					loggerNodeInfoPanel;
	private	 AEMFTMetadataElementComposite					loggerNodeData;

	public CRONIOBusDesktopLoggerNodesInfo() {
		root = new FlowPanel();
		initWidget(root);

		loggerNodeInfoPanel 		= new CRONIOBusDesktopNodeInfoPanel(TEXTS.node_information());
		root.add(loggerNodeInfoPanel);
	}

	public void reset() {
		loggerNodeInfoPanel.reset();
	}

	/**
	 * AEGWTICompositePanel
	 */

	@Override
	public void postDisplay() {
		super.postDisplay();
	}

	@Override
	public String getName() {
		return NAME;
	}
	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		loggerNodeData = data;
		loggerNodeInfoPanel.setData(data);
	}

	public AEMFTMetadataElementComposite getData() {
		return loggerNodeData;
	}

	/**
	 * PRIVATE
	 */
}
