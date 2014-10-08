package com.imotion.dslam.front.business.desktop.client.presenter;

import com.selene.arch.exe.gwt.client.presenter.base.AEGWTBaseLoggedPresenter;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTHasNavigationEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEvent;
import com.selene.arch.exe.gwt.mvp.event.navigator.AEGWTNavigationEventTypes.NAVIGATOR_TYPE;

public abstract class CRONIOBusBasePresenter<T extends AEGWTCompositePanelLoggedViewDisplay> extends AEGWTBaseLoggedPresenter<T> implements AEGWTHasNavigationEventHandlers {

	
	public CRONIOBusBasePresenter(T view) {
		super(view);
	}

	@Override
	public void dispatchEvent(AEGWTNavigationEvent evt) {
		super.dispatchEvent(evt);
	}

	@Override
	public boolean isDispatchEventType(NAVIGATOR_TYPE type) {
		return super.isDispatchEventType(type);
	}
	
}
