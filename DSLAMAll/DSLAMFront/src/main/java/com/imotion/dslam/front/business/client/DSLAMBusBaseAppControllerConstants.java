package com.imotion.dslam.front.business.client;

import com.selene.arch.exe.gwt.client.presenter.info.AEGWTInfoPresenterConstants;

public interface DSLAMBusBaseAppControllerConstants {

	//Info
	public static final String INFO_TITLE_TEXT						= AEGWTInfoPresenterConstants.DATA_IN_TITLE_TEXT;
	public static final String INFO_DESCRIPTION_TEXT					= AEGWTInfoPresenterConstants.DATA_IN_DESCRIPTION_TEXT;
	public static final String INFO_IMAGE_ICON_URL					= AEGWTInfoPresenterConstants.DATA_IN_DESCRIPTION_TEXT;
	
	//URLS
	public static final String GWT_DEV_SUFFIX							= "?gwt.codesvr=127.0.0.1:9997";	
	public static final String DESKTOP_URI							= "DSLAMDesktopModule.jsp";
	public static final String DESKTOP_URI_DEV						= DESKTOP_URI + GWT_DEV_SUFFIX;
	public static final String TOUCH_URI								= "DSLAMTouchModule.jsp";
	public static final String TOUCH_URI_DEV							= TOUCH_URI + GWT_DEV_SUFFIX;
	
	// STORE
	public static final String DSLAM_STORE							= "DSLAM_STORE";
	
	//JS
	public static final String BOOTSTRAP_JS_URL 					= "bootstrap-3.1.1-dist/js/bootstrap.min.js";
	public static final String BOOTSTRAP_DATETIMEPICKER_JS		= "js/bootstrap-datetimepicker.min.js";	
	public static final String JQUERY_JS_URL 						= "jquery/jquery-2.0.3.min.js";
	public static final String JQUERY_PERFECT_SCROLLBAR_JS_URL 	= "jquery/perfect-scrollbar-0.4.10/perfect-scrollbar.js";
	public static final String JQUERY_MOUSE_WHEEL_JS_URL 			= "jquery/perfect-scrollbar-0.4.10/jquery.mousewheel.js";
	
}
