package com.imotion.transit.front.business.client;

import com.selene.arch.exe.gwt.client.presenter.info.AEGWTInfoPresenterConstants;

public interface TRANSBusBaseAppControllerConstants {

	//Info
	public static final String INFO_TITLE_TEXT						= AEGWTInfoPresenterConstants.DATA_IN_TITLE_TEXT;
	public static final String INFO_DESCRIPTION_TEXT					= AEGWTInfoPresenterConstants.DATA_IN_DESCRIPTION_TEXT;
	public static final String INFO_IMAGE_ICON_URL					= AEGWTInfoPresenterConstants.DATA_IN_DESCRIPTION_TEXT;
	
	//URLS
	public static final String GWT_DEV_SUFFIX							= "?gwt.codesvr=127.0.0.1:9997";	
	public static final String DESKTOP_URI							= "TRANSDesktopModule.jsp";
	public static final String DESKTOP_URI_DEV						= DESKTOP_URI + GWT_DEV_SUFFIX;
	public static final String TOUCH_URI								= "TRANSTouchModule.jsp";
	public static final String TOUCH_URI_DEV							= TOUCH_URI + GWT_DEV_SUFFIX;
	
	// STORE
	public static final String TRANS_STORE							= "TRANS_STORE";
	
}
