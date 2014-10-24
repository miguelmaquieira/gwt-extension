package com.imotion.dslam.front.business.desktop.client.widget.execution;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopLoggerExecutionInfo extends AEGWTCompositePanel {
	public static final String NAME = "CRONIOBusDesktopLoggerExecutionInfo";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private FlowPanel 										root;
	private CRONIOBusDesktopLoggerExecutionInfoPanel				loggerExecutionInfoPanel;
	private	 AEMFTMetadataElementComposite					loggerExecutionData;

	public CRONIOBusDesktopLoggerExecutionInfo() {
		root = new FlowPanel();
		initWidget(root);

		loggerExecutionInfoPanel 		= new CRONIOBusDesktopLoggerExecutionInfoPanel(TEXTS.execution_information());
		root.add(loggerExecutionInfoPanel);
	}

	public void reset() {
		loggerExecutionInfoPanel.reset();
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
		loggerExecutionData = data;
		loggerExecutionInfoPanel.setData(data);
	}

	public AEMFTMetadataElementComposite getData() {
		return loggerExecutionData;
	}

	/**
	 * PRIVATE
	 */
}
