package com.imotion.dslam.front.business.desktop.client.view.processpage;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIProcessDataConstants;
import com.imotion.dslam.business.service.DSLAMBUIProcessBusinessServiceConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPagePresenter;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorListElement;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopConnectionToolbar;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopNavigatorProcessList;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopNewProcessPopupForm;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopProcessConfigure;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopProcessToolbar;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopEditorToolbarActions;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopEditorToolbarInfo;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapSplitButtonDropdown;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTHasLogicalEventHandlers;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopProcessPageScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay,AEGWTHasLogicalEventHandlers {

	public static final String NAME = "DSLAMBusDesktopProcessPageScreenView";
	private static final DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);
	private static final String PROCESS_DATA_LIST = null;
	
	private FlowPanel 							root;
	private DSLAMBusDesktopProcessToolbar		toolbar;
	private DSLAMBusDesktopConnectionToolbar	connectionToolbar;
	private DSLAMBusDesktopNavigatorList		processList;
	private DSLAMBusDesktopProcessConfigure		processOptions;
	private DSLAMBusDesktopNewProcessPopupForm newProcessPopup;
	
	public DSLAMBusDesktopProcessPageScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW);

		toolbar = new DSLAMBusDesktopProcessToolbar();
		root.add(toolbar);
		toolbar.setMainTitleText("script1");
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
		toolbar.getInfo().setVisible(false);
		
		connectionToolbar = new DSLAMBusDesktopConnectionToolbar();
		root.add(connectionToolbar);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_BOTTOM_ZONE);

		//Bottom Zone - Process list zone
		FlowPanel processListZone = new FlowPanel();
		bottomZone.add(processListZone);
		processListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		processListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_LIST_ZONE);

		processList = new DSLAMBusDesktopNavigatorProcessList();
		processListZone.add(processList);

		//Bottom Zone - Process configure zone
		FlowPanel processConfigureZone = new FlowPanel();
		bottomZone.add(processConfigureZone);
		processConfigureZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		processConfigureZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESS_CONFIGURE_ZONE);

		processOptions = new DSLAMBusDesktopProcessConfigure();
		processConfigureZone.add(processOptions);
		processOptions.setVisibility(Visibility.HIDDEN);
		
	}
	
	
	public void addProcess(AEMFTMetadataElementComposite processData) {
		newProcessPopup.hide();
		processList.addElement(processData);
		openProcess(processData);
	}
	
	@Override
	public void updateProcess(AEMFTMetadataElementComposite processData) {
		if (processData != null) {
			Long	processId		= getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID	, processData);
			String	processIdStr	= String.valueOf(processId);
			String	currentFileId	= toolbar.getId();
			if (processIdStr.equals(currentFileId)) {
				toolbar.setData(processData);
			}
			processList.updateElement(processData);
			newProcessPopup.hide();
		}
	}
	
	
	/**
	 * AEGWTICompositePanel
	 */
	public String getName() {
		return NAME;
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		getLogicalEventHandlerManager().addLogicalEventHandler(this);
		processList.postDisplay();
		processOptions.postDisplay();
		newProcessPopup = new DSLAMBusDesktopNewProcessPopupForm(this);
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		AEMFTMetadataElementComposite processDataList = getElementController().getElementAsComposite(DSLAMBUIProcessBusinessServiceConstants.PROCESS_DATA_LIST, data);
		processList.setData(processDataList);
		newProcessPopup.setData(data);
		
	}

	/****************************************************************************
	 *                      AEGWTHasLogicalEventHandlers
	 ****************************************************************************/

	@Override
	public void dispatchEvent(AEGWTLogicalEvent evt) {
		String			srcWidget		= evt.getSourceWidget();
		String			srcWidgetId		= evt.getSourceWidgetId();
		String			srcContainerId	= evt.getSourceContainerId();
		LOGICAL_TYPE	type		= evt.getEventType();
		if (DSLAMBusDesktopEditorToolbarActions.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type)) {
				if (!toolbar.isModified() || Window.confirm(TEXTS.exit_without_save())) {
					closeCurrentProcess();
					newProcessPopup.setMode(DSLAMBusDesktopNewProcessPopupForm.MODE_NEW_PROCESS);
					newProcessPopup.center();
				}
			} if (LOGICAL_TYPE.SAVE_EVENT.equals(type)) {
				evt.stopPropagation();
				AEMFTMetadataElementComposite optionsData = processOptions.getData();
				fireSaveChanges(srcWidgetId, optionsData);
			}
		} else if (DSLAMBusDesktopEditorToolbarInfo.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.CLOSE_EVENT.equals(type)) {
				closeCurrentProcess();
			}
		} else if (AEGWTBootstrapSplitButtonDropdown.NAME.equals(srcWidget)) {
			boolean openProcess = AEGWTStringUtils.isEmptyString(srcContainerId) || DSLAMBusDesktopNavigatorListElement.OPEN_ID.equals(srcWidgetId);
			if (openProcess) {
				String processId = AEGWTStringUtils.isEmptyString(srcContainerId) ? srcWidgetId : srcContainerId;
				openProcess(processId);
			} 
		}
//			else {
//				if (DSLAMBusDesktopNavigatorListElement.RENAME_ID.equals(srcWidgetId)) {
//					showRenameForm((AEMFTMetadataElementComposite) evt.getElementAsDataValue());
//				} else if (DSLAMBusDesktopNavigatorListElement.DELETE_ID.equals(srcWidgetId)) {
//					fireDeleteFile(srcContainerId);
//				}
//			}
//		} 
		else if (DSLAMBusDesktopNewProcessPopupForm.NAME.equals(srcWidget)) {
			if (LOGICAL_TYPE.NEW_EVENT.equals(type) || LOGICAL_TYPE.CHANGE_EVENT.equals(type)) {
				fireSaveFormDataEvent(evt);
			}
		} if (isValidEvent(evt) && evt.getEventType().equals(LOGICAL_TYPE.OK_EVENT)) {
			reset();	
			Window.alert("Transacción finalizada");
		} else if (isValidEvent(evt) && evt.getEventType().equals(LOGICAL_TYPE.ERROR_EVENT)) {
			AEMFTMetadataElementComposite dataErrors = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
			//showErrors(dataErrors);
			Window.alert("Transacción errónea");
		}
	}

	@Override
	public boolean isDispatchEventType(LOGICAL_TYPE type) {
		return LOGICAL_TYPE.SAVE_EVENT.equals(type)
				||
				LOGICAL_TYPE.NEW_EVENT.equals(type)
				||
				LOGICAL_TYPE.CLOSE_EVENT.equals(type)
				||
				LOGICAL_TYPE.SELECT_EVENT.equals(type)
				||
				LOGICAL_TYPE.CHANGE_EVENT.equals(type)
				||
				LOGICAL_TYPE.OK_EVENT.equals(type)
				||
				LOGICAL_TYPE.ERROR_EVENT.equals(type);
	}
	
	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
	private boolean isValidEvent(AEGWTLogicalEvent evt) {
		if (DSLAMBusDesktopProcessPagePresenter.NAME.equals(evt.getSourceWidget())) {
			return true;
		} else return false;
	}
	
	private void reset() {
		processOptions.reset();
		toolbar.reset();
	
	}
	
	private void closeCurrentProcess() {
		toolbar.setFileInfoVisible(false);
		toolbar.setModified(false);
		processOptions.setVisibility(Visibility.HIDDEN);
		toolbar.setId(null);
	}
	
	private void openProcess(AEMFTMetadataElementComposite processData) {
		if (processData != null) {
			Long	processId		= getElementController().getElementAsLong(DSLAMBOIProcess.PROCESS_ID, processData);
			String	processIdStr	= String.valueOf(processId);
			openProcess(processIdStr);
		}
	}
	
	private void openProcess(String processId) {
		if (!toolbar.isModified() || (toolbar.isModified() && Window.confirm(TEXTS.exit_without_save())) ) {
			closeCurrentProcess();
			AEMFTMetadataElementComposite processData = processList.getElementData(processId);
		//	String	content 	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT		, fileData);
		//	String	contentType	= getElementController().getElementAsString(DSLAMBOIFile.CONTENT_TYPE	, fileData);
			
			
			toolbar.setData(processData);
			//editor.setText(content);
			toolbar.setFileInfoVisible(true);
			processOptions.setVisibility(Visibility.VISIBLE);
		}
	}
	
	private void fireSaveFormDataEvent(AEGWTLogicalEvent saveButtonEvt) {
		saveButtonEvt.stopPropagation();
		String processName		= saveButtonEvt.getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_NAME);
		AEMFTMetadataElementComposite existentProcessData = processList.getElementDataByName(processName);
		if (existentProcessData != null) {
			newProcessPopup.setError(TEXTS.filename_exists());
		} else {
			String processScript = saveButtonEvt.getElementAsString(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT);

			AEMFTMetadataElementComposite processData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			processData.addElement(DSLAMBOIProcessDataConstants.PROCESS_NAME		, processName);
			processData.addElement(DSLAMBOIProcessDataConstants.PROCESS_SCRIPT		, processScript);
		
			AEGWTLogicalEvent saveFileEvent = new AEGWTLogicalEvent(getWindowName(), getName());
			saveFileEvent.setEventType(saveButtonEvt.getEventType());
			saveFileEvent.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_DATA , processData);
			saveFileEvent.setSourceWidgetId(saveButtonEvt.getSourceWidgetId());
			getLogicalEventHandlerManager().fireEvent(saveFileEvent);
		}
	}
	
	private void fireSaveChanges(String processId, AEMFTMetadataElementComposite optionsData) {
		AEMFTMetadataElementComposite updateProcessData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		updateProcessData.addElement(DSLAMBOIProcess.PROCESS_ID, processId);
		updateProcessData.addElement(DSLAMBOIProcessDataConstants.PROCESS_OPTIONS_DATA , optionsData);

		AEGWTLogicalEvent updateEvent = new AEGWTLogicalEvent(getWindowName(), getName());
		updateEvent.setEventType(LOGICAL_TYPE.SAVE_EVENT);
		updateEvent.addElementAsComposite(DSLAMBOIProcessDataConstants.PROCESS_DATA ,updateProcessData);
		getLogicalEventHandlerManager().fireEvent(updateEvent);
	}
}
