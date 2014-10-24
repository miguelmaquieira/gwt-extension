package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.imotion.dslam.bom.CRONIOBOILogNode;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.front.business.desktop.client.view.log.CRONIOBusI18NLogTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTable;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;

public class CRONIOBusDesktopLoggerNodesList extends AEGWTBootstrapTable {

	public 		static final String NAME = "CRONIOBusDesktopLoggerNodesList";
	protected 	static CRONIOBusI18NLogTexts TEXTS = GWT.create(CRONIOBusI18NLogTexts.class);

	public CRONIOBusDesktopLoggerNodesList(AEGWTButton deleteButton) {
		super(false,false,false, null);
		addTableHover();
		addClickableRowEvent();
	}

	public void reset () {
		clearUpdateList();
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
			List<AEMFTMetadataElement> loggerNodeList = data.getSortedElementList();

			for (AEMFTMetadataElement node : loggerNodeList) {
				String itemKey = node.getKey();
				if (!CRONIOBOIProjectDataConstants.INFO.equals(itemKey)) {
					String 		name	 	= getElementController().getElementAsString(CRONIOBOILogNode.NODE_NAME	, node);
					String 		ip 			= getElementController().getElementAsString(CRONIOBOILogNode.NODE_IP	, node);
					String		type 		= getElementController().getElementAsString(CRONIOBOILogNode.NODE_TYPE	, node);
					boolean		state 		= getElementController().getElementAsBoolean(CRONIOBOILogNode.STATE		, node);
					String 		stateStr 	= "";
					
					Map<String,String> nodeRow = new HashMap<String, String>();
					nodeRow.put(CRONIOBOILogNode.NODE_NAME	, name);
					nodeRow.put(CRONIOBOILogNode.NODE_IP	, ip);
					nodeRow.put(CRONIOBOILogNode.NODE_TYPE	, type);
					nodeRow.put(CRONIOBOILogNode.STATE		, stateStr);

					addRowItem(nodeRow, name, false, false,false);
				}
			}
		}
	}

	/*
	 * AEGWTBootstrapTable
	 */

	@Override
	protected void setupHeader() {
		super.headerDataFields.add(CRONIOBOILogNode.NODE_NAME);
		super.headerDataFields.add(CRONIOBOILogNode.NODE_IP);
		super.headerDataFields.add(CRONIOBOILogNode.NODE_TYPE);
		super.headerDataFields.add(CRONIOBOILogNode.STATE);

		super.headerMapFieldText.put(CRONIOBOILogNode.NODE_NAME	, TEXTS.logger_node_name());
		super.headerMapFieldText.put(CRONIOBOILogNode.NODE_IP	, TEXTS.logger_node_ip());
		super.headerMapFieldText.put(CRONIOBOILogNode.NODE_TYPE	, TEXTS.logger_node_machine_type());
		super.headerMapFieldText.put(CRONIOBOILogNode.STATE		, TEXTS.logger_node_state());

	}

	@Override
	protected String getEventName() {
		return getName();
	}

	@Override
	protected String getMsgDeleteText() {
		// TODO Auto-generated method stub
		return null;
	}
}
