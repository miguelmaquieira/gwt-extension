package com.imotion.dslam.front.business.desktop.test.client.presenter.test;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSet;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBasePresenter;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.mvp.AEGWTIPresenter;

public class TestPresenter extends AEGWTBasePresenter<TestDisplay> implements AEGWTIPresenter {

	public TestPresenter(TestDisplay view) {
		super(view);
	}

	@Override
	public String getApplicationId() {
		return "TEST";
	}

	@Override
	public String getModuleId() {
		return getView().getModuleId();
	}

	@Override
	protected void bind() {
		getView().addSendClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				KEY_SERVICE_ID
				AEMFTMetadataElementCompositeRecordSet contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
//				getElementDataController().setElement(TestBusinessServiceConstants.XML_CONTENT, contextIn, getView().getXML());
//				getElementDataController().setElement(TestBusinessServiceConstants.XML_PATH, contextIn, getView().getXMLPath());
				runServiceId(getView().getSelectedServiceId(), contextIn);
			}
		});

	}

	@Override
	public String getName() {
		return TestPresenterConstants.NAME;
	}

	private void runServiceId(String serviceId, AEMFTMetadataElementComposite dataIN) {
		getClientServerConnection().executeComm(dataIN,
										serviceId,

										new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

											@Override
											public void onResult(AEMFTMetadataElementComposite dataResult) {
//												String error = getElementDataController().getElementAsString(TestBusinessServiceConstants.ERROR_KEY, dataResult);
//												if (AEGWTStringUtils.isEmptyString(error)) {
//													getView().serviceResultOK();
//												} else {
//													Window.alert(error);
//												}
											}

											@Override
											public void onError(Throwable th) {
												getView().serviceResultKO();
												Window.alert(th.getMessage());
											}
										});
	}

}
