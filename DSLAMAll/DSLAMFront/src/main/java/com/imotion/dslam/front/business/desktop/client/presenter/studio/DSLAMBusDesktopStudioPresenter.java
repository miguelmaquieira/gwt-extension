package com.imotion.dslam.front.business.desktop.client.presenter.studio;

import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.DSLAMBusBasePresenter;
import com.imotion.dslam.front.business.desktop.client.widget.editor.DSLAMBusDesktopNewScriptPopupForm;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopStudioPresenter extends DSLAMBusBasePresenter<DSLAMBusDesktopStudioDisplay> implements AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopStudioPresenter";

	public DSLAMBusDesktopStudioPresenter(DSLAMBusDesktopStudioDisplay view) {
		super(view);
	}

	@Override
	public void bind() {
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * AEGWTHasLogicalEventHandlers
	 */
	
	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String srcWidget = evt.getSourceWidget();
		if (DSLAMBusDesktopNewScriptPopupForm.NAME.equals(srcWidget)) {
			String filename		= evt.getElementAsString(DSLAMBOIFileDataConstants.FILE_NAME);
			String contentType	= evt.getElementAsString(DSLAMBOIFileDataConstants.CONTENT_TYPE);
			String lala = "";
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type);
	}

}
