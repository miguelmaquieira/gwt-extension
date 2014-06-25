package com.imotion.dslam.front.business.desktop.client.widget.projectpage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Hidden;
import com.imotion.dslam.business.service.base.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.client.DSLAMBusPresenterBaseConstants;
import com.selene.arch.base.exe.bus.comm.AEMFTIFileUploadServerCommConstants;
import com.selene.arch.base.exe.bus.comm.AEMFTIGenericServerCommConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.utils.AEGWTJSONUtils;

public class CRONIOBusDesktopHeaderListFileActions extends CRONIOBusDesktopHeaderListActions {
	public static final String NAME = "CRONIOBusDesktopHeaderListActions";
	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTBootstrapGlyphiconButton      uploadButton;
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
		
		FlowPanel formContaimer = new FlowPanel();
		form.add(formContaimer);
		
		InputElement fileInput = Document.get().createFileInputElement();
		fileInput.setName(AEMFTIFileUploadServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_FILE);
		formContaimer.getElement().appendChild(fileInput);
		
		Hidden hiddenFieldAppId = new Hidden();
		formContaimer.add(hiddenFieldAppId);
		hiddenFieldAppId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_APPLICATION_ID);
		hiddenFieldAppId.setValue(DSLAMBusPresenterBaseConstants.APPLICATION_ID);
		
		Hidden hiddenFieldModuleId = new Hidden();
		formContaimer.add(hiddenFieldModuleId);
		hiddenFieldModuleId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_MODULE_ID);
		hiddenFieldModuleId.setValue(DSLAMBusPresenterBaseConstants.MODULE_ID);
		
		Hidden hiddenFieldServiceId = new Hidden();
		formContaimer.add(hiddenFieldServiceId);
		hiddenFieldServiceId.setName(AEMFTIGenericServerCommConstants.CTE_MFT_AE_BUS_COMM_REQUEST_SERVICE_ID);
		hiddenFieldServiceId.setValue(DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_GET_CSV_NODES_ID);
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				String jsonResponse = event.getResults();
				if (!AEMFTCommonUtilsBase.isEmptyString(jsonResponse)) {
					JSONObject jsonValue = (JSONObject) JSONParser.parseStrict(jsonResponse);
					AEMFTMetadataElementComposite nodeListData = AEGWTJSONUtils.fromJSONToMetadataElement(jsonValue);
				}
			}
		});
		
	}
	
	public void uploadFile() {
		form.submit();
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
		$wnd.jQuery(":file").filestyle({input: false,buttonText: "  "}); 
	}-*/;
	
	private native void addJS(CRONIOBusDesktopHeaderListFileActions headerSelf) /*-{
	$wnd.jQuery("input[type='file']").bind("change",function() {
		 headerSelf.@com.imotion.dslam.front.business.desktop.client.widget.projectpage.CRONIOBusDesktopHeaderListFileActions::uploadFile()();
		});
	}-*/;
	
	/**
	 * PRIVATE
	 */
	
	
}