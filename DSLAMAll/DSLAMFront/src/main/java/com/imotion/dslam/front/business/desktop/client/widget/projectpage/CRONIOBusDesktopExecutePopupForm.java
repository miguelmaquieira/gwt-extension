package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.AEGWTICompositePanel;
import com.selene.arch.exe.gwt.client.ui.AEGWTIStylesConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapFieldDropDownButtonLabelTop;
import com.selene.arch.exe.gwt.client.ui.widget.button.AEGWTButton;
import com.selene.arch.exe.gwt.client.ui.widget.popup.AEGWTPopup;

public class CRONIOBusDesktopExecutePopupForm extends AEGWTPopup {

	public static final String NAME = "CRONIOBusDesktopExecutePopupForm";
	private CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private AEGWTBootstrapFieldDropDownButtonLabelTop		nodeListDropDownButton;
	private AEGWTButton										executeButton;
	private AEGWTButton										cancelButton;


	public CRONIOBusDesktopExecutePopupForm(AEGWTICompositePanel parent) {
		super(true, parent);
		FlowPanel root = new FlowPanel();
		setWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTE_FORM);

		//NodeList
		SimplePanel inputZone = new SimplePanel();
		root.add(inputZone);
		inputZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTE_FORM_INPUT_ZONE);

		nodeListDropDownButton = new AEGWTBootstrapFieldDropDownButtonLabelTop(TEXTS.enviroment());
		nodeListDropDownButton.addClassNameToUlElement(AEGWTIStylesConstants.SCROLLABLE_MENU);
		inputZone.add(nodeListDropDownButton);

		//SAVE
		FlowPanel saveButtonZone = new FlowPanel();
		root.add(saveButtonZone);
		saveButtonZone.addStyleName(CRONIOBusDesktopIStyleConstants.EXECUTE_FORM_SAVE_ZONE);

		executeButton = new AEGWTButton(TEXTS.execute());
		saveButtonZone.add(executeButton);
		executeButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		executeButton.addStyleName(AEGWTIBoostrapConstants.BTN_PRIMARY);

		//SAVE
		cancelButton = new AEGWTButton(TEXTS.cancel());
		saveButtonZone.add(cancelButton);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN);
		cancelButton.addStyleName(AEGWTIBoostrapConstants.BTN_LINK);

		executeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String nodeListDropDownButtonSelectedId = nodeListDropDownButton.getSelectedId();
				hide();
				CRONIOBusDesktopProjectEvent addExecutionEvent = new CRONIOBusDesktopProjectEvent(getWindowName(), getName());
				addExecutionEvent.setEventType(EVENT_TYPE.ADD_EXECUTION);
				addExecutionEvent.addElementAsString(CRONIOBOINodeList.NODELIST_NAME, nodeListDropDownButtonSelectedId);
				getLogicalEventHandlerManager().fireEvent(addExecutionEvent);
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}
	
	public void addNodeListsToExecuteForm(List<String> nodeLists) {
		if (!AEMFTCommonUtilsBase.isEmptyList(nodeLists)) {
			for (String nodeList : nodeLists) {
				nodeListDropDownButton.addElement(nodeList		, nodeList);
			}
		}
	}

	public void resetForm() {
		hide();
	}
	
	@Override
	public void center() {
		nodeListDropDownButton.removeAllElements();
		super.center();
	
	}

	/**
	 * AEGWTICompositePanel
	 */

	@Override
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
}
