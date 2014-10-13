package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Hidden;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.business.service.CRONIOBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.CRONIOBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.client.CRONIOBusPresenterBaseConstants;
import com.selene.arch.base.exe.bus.comm.AEMFTIFileUploadServerCommConstants;
import com.selene.arch.base.exe.bus.comm.AEMFTIGenericServerCommConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.utils.AEGWTJSONUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class CRONIOBusDesktopHeaderListFileActions extends CRONIOBusDesktopHeaderListActions {
	public static final String NAME = "CRONIOBusDesktopHeaderListActions";
	private static CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);
	
	private FormPanel							form;
	
	public CRONIOBusDesktopHeaderListFileActions(String text) {
		super(text);
		setAddButtonVisible(false);
		setDeleteButtonVisible(false);
		form = new FormPanel();
		addWidget(form);
		String fileServletUrl =  GWT.getModuleBaseURL() + AEMFTIFileUploadServerCommConstants.CTE_MFT_AE_BUS_COMM_FILE_SERVLET_URI;
		form.setAction(fileServletUrl);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		
		FlowPanel formContainer = new FlowPanel();
		form.add(formContainer);
		
		InputElement fileInput = Document.get().createFileInputElement();
		fileInput.setName(AEMFTIFileUploadServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_FILE);
		formContainer.getElement().appendChild(fileInput);
		
		Hidden hiddenFieldAppId = new Hidden();
		formContainer.add(hiddenFieldAppId);
		hiddenFieldAppId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_APPLICATION_ID);
		hiddenFieldAppId.setValue(CRONIOBusPresenterBaseConstants.APPLICATION_ID);
		
		Hidden hiddenFieldModuleId = new Hidden();
		formContainer.add(hiddenFieldModuleId);
		hiddenFieldModuleId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_MODULE_ID);
		hiddenFieldModuleId.setValue(CRONIOBusPresenterBaseConstants.MODULE_ID);
		
		Hidden hiddenFieldServiceId = new Hidden();
		formContainer.add(hiddenFieldServiceId);
		hiddenFieldServiceId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_SERVICE_ID);
		hiddenFieldServiceId.setValue(CRONIOBUIServiceIdConstant.CTE_CRONIO_BU_SRV_PROJECT_GET_CSV_NODES_ID);
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			
			public void onSubmitComplete(SubmitCompleteEvent event) {
				String jsonResponse = event.getResults();
				String jsonResponseNoPreTag = jsonResponse.replaceAll("<pre>", "");
				jsonResponseNoPreTag = jsonResponseNoPreTag.replaceAll("</pre>", "");
				if (!AEMFTCommonUtilsBase.isEmptyString(jsonResponseNoPreTag)) {
					JSONObject jsonValue = (JSONObject) JSONParser.parseStrict(jsonResponseNoPreTag);
					AEMFTMetadataElementComposite nodeListData = AEGWTJSONUtils.fromJSONToMetadataElement(jsonValue);
					nodeListData = getElementController().getElementAsComposite(CRONIOBUIProjectBusinessServiceConstants.NODES_DATA_LIST, nodeListData);
//					CRONIOBusDesktopProjectEvent getNodeListEvent = new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
//					getNodeListEvent.addElementAsComposite(CRONIOBOIProcessDataConstants.PROCESS_NODES_DATA, nodeListData);
//					getNodeListEvent.setEventType(EVENT_TYPE.GET_NODELIST_ID);
//					getLogicalEventHandlerManager().fireEvent(getNodeListEvent);
					
					AEGWTLogicalEvent openEvt = new AEGWTLogicalEvent(getWindowName(), getName());
					openEvt.setEventType(LOGICAL_TYPE.OPEN_EVENT);
					openEvt.setSourceWidget(getName());
					openEvt.addElementAsComposite(CRONIOBOINodeList.NODELIST_DATA, nodeListData);
					getLogicalEventHandlerManager().fireEvent(openEvt);
				}
			}
		});
		
	}
	
	public void uploadFile() {
		if (Window.confirm(getMsgDeleteText())) {
			form.submit();
		}
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
		
	}
	
	@Override
	public void postDisplay() {
		super.postDisplay();
		addFileInputJS();
		addJS(this);
	}
	
	/************************************************************************
	 * JS
	 ************************************************************************/

	private native void addFileInputJS() /*-{
		$wnd.jQuery(":file").filestyle({input: false,buttonText: "Cargar csv",iconName: "glyphicon-upload"}); 
	}-*/;
	
	private native void addJS(CRONIOBusDesktopHeaderListFileActions headerSelf) /*-{
	$wnd.jQuery("input[type='file']").bind("change",function() {
		 headerSelf.@com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderListFileActions::uploadFile()();
		});
	}-*/;
	
	/**
	 * PRIVATE
	 */
	
	private String getMsgDeleteText() {
		return TEXTS.delete_nodes_confirm();
	}

	
	
}