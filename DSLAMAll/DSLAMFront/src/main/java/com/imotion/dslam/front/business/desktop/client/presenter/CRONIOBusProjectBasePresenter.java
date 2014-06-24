package com.imotion.dslam.front.business.desktop.client.presenter;

import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.business.service.DSLAMBUIProjectBusinessServiceConstants;
import com.imotion.dslam.business.service.base.DSLAMBUIServiceIdConstant;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.desktop.client.CRONIODesktopIAppControllerConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopLayoutContainer;
import com.imotion.dslam.front.business.desktop.client.widget.layout.CRONIOBusDesktopProjectsLayout;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.service.comm.AEGWTCommClientAsynchCallbackRequest;
import com.selene.arch.exe.gwt.client.utils.AEGWTStringUtils;
import com.selene.arch.exe.gwt.mvp.AEGWTCompositePanelLoggedViewDisplay;
import com.selene.arch.exe.gwt.mvp.event.flow.AEGWTFlowEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEvent;
import com.selene.arch.exe.gwt.mvp.event.localstorage.AEGWTLocalStorageEventTypes;

public abstract class CRONIOBusProjectBasePresenter<T extends AEGWTCompositePanelLoggedViewDisplay> extends DSLAMBusBasePresenter<T> implements CRONIOBusDesktopHasProjectEventHandlers, CRONIOBusProjectBasePresenterConstants {

	private CRONIOBusDesktopProjectsLayout projectsLayout;

	public CRONIOBusProjectBasePresenter(T view) {
		super(view);
	}

	@Override
	public String[] getInMapping() {
		return new String[] {CRONIODesktopIAppControllerConstants.PROJECTS_DATA, PROJECT_NAVIGATION_DATA};
	}

	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */

	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		EVENT_TYPE evtTyp = evt.getEventType();
		if (EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(evtTyp)) {
			String projectId		= evt.getProjectId();
			String mainSectionId	= evt.getMainSectionId();
			String finalSectionId	= evt.getFinalSectionId();

			String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);

			boolean navigate 		= !getSectionType().equals(mainSectionId);
			boolean	 projectChange	= !projectId.equals(currentProjectId);

			AEMFTMetadataElementComposite navigationData  = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			navigationData.addElement(CURRENT_PROJECT_ID		, projectId);
			navigationData.addElement(CURRENT_MAIN_SECTION_ID	, mainSectionId);
			navigationData.addElement(CURRENT_FINAL_SECTION_ID	, finalSectionId);


			AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
			storageEvent.setFullKey(PROJECT_NAVIGATION_DATA);
			storageEvent.addElementAsDataValue(navigationData);
			storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
			getLogicalEventHandlerManager().fireEvent(storageEvent);

			getContextDataController().setElement(PROJECT_NAVIGATION_DATA, navigationData.cloneObject());

			if (navigate) {
				AEGWTFlowEvent flowEvent = new AEGWTFlowEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
				flowEvent.setSourceWidgetId(mainSectionId);
				getFlowEventHandlerManager().fireEvent(flowEvent);
			} else {
				openFinalSection(projectChange, projectId, finalSectionId);
			}
		} else if (EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(evtTyp)) {
			AEMFTMetadataElementComposite finalSectionData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
			updateFinalSectionInContext(finalSectionData);
		} else if (EVENT_TYPE.SAVE_PROJECT.equals(evtTyp)) {
			saveCurrentProjectInDB();
		} else if (EVENT_TYPE.SAVE_ALL_PROJECTS.equals(evtTyp)) {
			saveModifiedProjectsInDB();
		}
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.OPEN_FINAL_SECTION_EVENT.equals(type)
				||
				EVENT_TYPE.SAVE_SECTION_TEMPORARILY_EVENT.equals(type)
				||
				EVENT_TYPE.SAVE_PROJECT.equals(type)
				||
				EVENT_TYPE.SAVE_ALL_PROJECTS.equals(type);
	}

	/**
	 * PROTECTED
	 */

	@Override
	protected void addView(HasWidgets container) {
		CRONIOBusDesktopLayoutContainer layoutContainer = (CRONIOBusDesktopLayoutContainer) container;
		layoutContainer.setCurrentPresenter(this);
		layoutContainer.showLayout(CRONIOBusDesktopLayoutContainer.LAYOUT_PROJECT_ID);
		Widget viewAsWidget = getView().asWidget();
		layoutContainer.setLayoutContent(viewAsWidget);
		projectsLayout = (CRONIOBusDesktopProjectsLayout) layoutContainer.getCurrentLayout();
	}

	@Override
	protected void viewAdded() {
		super.viewAdded();
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, this);
		getLogicalEventHandlerManager().addEventHandler(CRONIOBusDesktopHasProjectEventHandlers.TYPE, getProjectsLayout());

		String currentProjectId			= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
		String currentFinalSectionId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID);
		openFinalSection(true, currentProjectId, currentFinalSectionId);
	}

	protected abstract void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId, AEMFTMetadataElementComposite finalSectionData);


	protected void updateFinalSectionInContext( AEMFTMetadataElementComposite finalSectionData) {
		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();

		String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);
		String currentSectionId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_FINAL_SECTION_ID);

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentSectionId);
		String finalSectionKey = sbKey.toString();

		finalSectionData.addElement(DSLAMBOIProject.IS_MODIFIED, true);

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
		storageEvent.setFullKey(finalSectionKey);
		storageEvent.addElementAsDataValue(finalSectionData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
		getContextDataController().setElement(finalSectionKey, finalSectionData);

		fireSectionModified(currentProjectId, currentSectionId);
	}

	protected abstract String getSectionType();

	/**
	 *	PRIVATE 
	 */

	private void openFinalSection(boolean projectChange, String projectId, String projectFinalSectionId) {
		if (!AEGWTStringUtils.isEmptyString(projectId) && !AEGWTStringUtils.isEmptyString(projectFinalSectionId)) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(projectId);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);

			//Project name
			String projectNameKey = new StringBuilder(sbKey.toString()).append(DSLAMBOIProject.PROJECT_NAME).toString() ;
			String projectName = getContextDataController().getElementAsString(projectNameKey);

			//Final Section Data
			sbKey.append(projectFinalSectionId);
			String finalSectionKey = sbKey.toString();

			AEMFTMetadataElementComposite finalSectionData = getContextDataController().getElementAsComposite(finalSectionKey);
			if (finalSectionData != null) {
				finalSectionData = (AEMFTMetadataElementComposite) finalSectionData.cloneObject();
			}
			boolean sectionIsModified = getElementDataController().getElementAsBoolean(DSLAMBOIProject.IS_MODIFIED, finalSectionData);

			//SHOW HEADER INFO
			CRONIOBusDesktopProjectEvent showInfoEvent = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
			showInfoEvent.addElementAsString(DSLAMBOIProject.PROJECT_NAME	, projectName);
			showInfoEvent.addElementAsBoolean(DSLAMBOIProject.IS_MODIFIED	, sectionIsModified);
			showInfoEvent.setProjectId(projectId);
			showInfoEvent.setFinalSectionId(projectFinalSectionId);
			showInfoEvent.setEventType(EVENT_TYPE.SHOW_PROJECT_INFO);
			getLogicalEventHandlerManager().fireEvent(showInfoEvent);

			//SHOW CONTENT
			openFinalSection(projectChange, projectId, projectFinalSectionId, finalSectionData);
		}
	}

	private CRONIOBusDesktopProjectsLayout getProjectsLayout() {
		return projectsLayout;
	}

	private void saveCurrentProjectInDB() {

		final String currentProjectId	= getContextDataController().getElementAsString(PROJECT_NAVIGATION_DATA_CURRENT_PROJECT_ID);

		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		String projectDataKey = sbKey.toString();

		AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
		getClientServerConnection().executeComm(projectData, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECT_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectData = dataResult.getCompositeElement(DSLAMBUIProjectBusinessServiceConstants.PROJECT_DATA);
					if (projectData != null) {
						updateProjectClientData(currentProjectId, projectData);
					}
				}

			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void updateProjectClientData(String currentProjectId, AEMFTMetadataElementComposite projectData) {
		StringBuilder sbKey = new StringBuilder();
		sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
		sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
		sbKey.append(currentProjectId);
		String projectKey = sbKey.toString();

		AEGWTLocalStorageEvent storageEvent = new AEGWTLocalStorageEvent(PROJECT_PRESENTER, getName());
		storageEvent.setFullKey(projectKey);
		storageEvent.addElementAsDataValue(projectData);
		storageEvent.setEventType(AEGWTLocalStorageEventTypes.LOCAL_STORAGE_TYPE.CHANGE_DATA_CONTEXT_EVENT);
		getLogicalEventHandlerManager().fireEvent(storageEvent);

		projectData = (AEMFTMetadataElementComposite) projectData.cloneObject();
		getContextDataController().setElement(projectKey, projectData);

		fireProjectSaved(currentProjectId);
	}

	private void saveModifiedProjectsInDB() {
		List<String> modifiedProjectIds = getProjectsLayout().getModifiedProjetIds();

		AEMFTMetadataElementComposite projectsData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		for (String projectId : modifiedProjectIds) {
			StringBuilder sbKey = new StringBuilder();
			sbKey.append(CRONIODesktopIAppControllerConstants.PROJECTS_DATA);
			sbKey.append(DSLAMBusCommonConstants.ELEMENT_SEPARATOR);
			sbKey.append(projectId);
			String projectDataKey = sbKey.toString();

			AEMFTMetadataElementComposite projectData = getContextDataController().getElementAsComposite(projectDataKey);
			projectsData.addElement(projectId, projectData);
		}
		
		AEMFTMetadataElementComposite contextIn = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		contextIn.addElement(DSLAMBUIProjectBusinessServiceConstants.PROJECTS_DATA, projectsData);
		getClientServerConnection().executeComm(contextIn, DSLAMBUIServiceIdConstant.CTE_DSLAM_BU_SRV_PROJECT_UPDATE_PROJECTS_ID, new AEGWTCommClientAsynchCallbackRequest<AEMFTMetadataElementComposite>(this) {

			@Override
			public void onResult(AEMFTMetadataElementComposite dataResult) {
				if (dataResult != null) {
					AEMFTMetadataElementComposite projectsData = dataResult.getCompositeElement(DSLAMBUIProjectBusinessServiceConstants.PROJECTS_DATA);
					List<AEMFTMetadataElement> projectsDataElements = projectsData.getElementList();
					for (AEMFTMetadataElement projectDataElement :projectsDataElements) {
						updateProjectClientData(projectDataElement.getKey(), (AEMFTMetadataElementComposite) projectDataElement);
					}
				}
			}

			@Override
			public void onError(Throwable th) {
				// TODO Auto-generated method stub

			}

		});



	}

	private void fireProjectSaved(String projectId) {
		CRONIOBusDesktopProjectEvent projectSavedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
		projectSavedEvt.setEventType(EVENT_TYPE.PROJECT_SAVED);
		projectSavedEvt.setProjectId(projectId);
		getLogicalEventHandlerManager().fireEvent(projectSavedEvt);
	}

	private void fireSectionModified(String projectId, String currentSectionId) {
		CRONIOBusDesktopProjectEvent sectionModifiedEvt = new CRONIOBusDesktopProjectEvent(PROJECT_PRESENTER, getName());
		sectionModifiedEvt.setEventType(EVENT_TYPE.SECTION_MODIFIED);
		sectionModifiedEvt.setProjectId(projectId);
		sectionModifiedEvt.setFinalSectionId(currentSectionId);
		getLogicalEventHandlerManager().fireEvent(sectionModifiedEvt);
	}

}
