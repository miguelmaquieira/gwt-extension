package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOINodeList;
import com.imotion.dslam.bom.CRONIOBOIProject;
import com.imotion.dslam.bom.CRONIOBOIProjectDataConstants;
import com.imotion.dslam.front.business.client.CRONIOBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.CRONIOBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.CRONIOBusDesktopProjectNavigator;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.CRONIOBusDesktopProjectsToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopProjectsLayout extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayout, CRONIOBusDesktopHasProjectEventHandlers {

	public 		final static String 	NAME 			= "CRONIOBusDesktopProjectsLayout";
	public	 	final static String	NO_PROJECT_ID 	= "NO_PROJECT_ID";

	private CRONIOBusI18NTexts TEXTS = GWT.create(CRONIOBusI18NTexts.class);

	private FlowPanel 									root;
	private CRONIOBusDesktopProjectsToolbar				toolbar;
	private CRONIOBusDesktopProjectNavigator			projectListNavigator;
	private CRONIOBusDesktopProjectsLayoutItemHeader	sectionHeader;
	private FlowPanel									projectWorkZone;

	private List<String>								modifyNodeLists;

	public CRONIOBusDesktopProjectsLayout() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT);

		toolbar = new CRONIOBusDesktopProjectsToolbar();
		root.add(toolbar);
		toolbar.setModified(false);
		toolbar.setId(NO_PROJECT_ID);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE);

		//Bottom Zone - Projectlist zone
		FlowPanel projectListZone = new FlowPanel();
		bottomZone.add(projectListZone);
		projectListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		projectListZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_LIST_ZONE);

		projectListNavigator = new CRONIOBusDesktopProjectNavigator();
		projectListZone.add(projectListNavigator);

		//Bottom Zone - Right
		FlowPanel bottomRightZone = new FlowPanel();
		bottomZone.add(bottomRightZone);
		bottomRightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		bottomRightZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT);

		sectionHeader = new CRONIOBusDesktopProjectsLayoutItemHeader();
		bottomRightZone.add(sectionHeader);

		projectWorkZone = new FlowPanel();
		bottomRightZone.add(projectWorkZone);
		projectWorkZone.addStyleName(CRONIOBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE);
	}

	public List<String> getModifiedProjetIds() {
		return projectListNavigator.getModifiedProjectIds();
	}

	public void addExecution(String projectId, long executionId, String executionDateStr) {
		projectListNavigator.addExecution(projectId, executionId, executionDateStr);
	}

	public void addNodeList(String projectId, String nodeListName) {
		projectListNavigator.addNodeList(projectId, nodeListName);
	}

	/**
	 * CRONIOBusDesktopIsLayout
	 */

	@Override
	public void setLayoutContent(Widget content) {
		projectWorkZone.clear();
		projectWorkZone.add(content);
	}

	@Override
	public void setvisibleLayoutItemHeader(boolean visible) {
		sectionHeader.setVisible(visible);
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
		if (data != null) {
			projectListNavigator.setData(data);
		}
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		setHeightToDecrease(75);
		projectListNavigator.postDisplay();
		if (modifyNodeLists == null) {
			modifyNodeLists = new ArrayList<>();
		}
	}

	/**
	 * CRONIOBusDesktopHasProjectEventHandlers
	 */
	@Override
	public void dispatchEvent(CRONIOBusDesktopProjectEvent evt) {
		String srcWindow 	= evt.getSourceWindow();
		EVENT_TYPE type		= evt.getEventType();
		if (CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER.equals(srcWindow)) {
			String projectId	= evt.getProjectId();
			String sectionId	= evt.getFinalSectionId();
			if (EVENT_TYPE.SHOW_PROJECT_INFO.equals(type)) {
				String		projectName			= evt.getElementAsString(CRONIOBOIProject.PROJECT_NAME);
				boolean		sectionModified		= evt.getElementAsBoolean(CRONIOBOIProject.INFO_IS_MODIFIED);
				List<String> modifiedProjects	= getModifiedProjetIds();
				boolean projectModified = modifiedProjects.contains(projectId);

				toolbar.setSaveProjectEnabled(projectModified);
				sectionHeader.setProyectName(projectName);
				sectionHeader.setSectionNameFromId(sectionId);
				sectionHeader.setModified(sectionModified);
				setId(projectId);
				toolbar.setId(projectId);
				if (CRONIOBOIProject.PROJECT_EXECUTION_CONSOLE.equals(sectionId)) {
					toolbar.setExecuteEnabled(true);
				} else {
					toolbar.setExecuteEnabled(false);
				}	
			} else if (EVENT_TYPE.SECTION_MODIFIED.equals(type)) {
				projectListNavigator.setProjectSectionModified(projectId, sectionId);
				if (projectId.equals(getId())) {
					sectionHeader.setModified(true);
					toolbar.setSaveProjectEnabled(true);
				}
				toolbar.setSaveAllProjectsEnabled(true);
			} else if (EVENT_TYPE.PROJECT_SAVED.equals(type)) {
				sectionHeader.setModified(false);
				projectListNavigator.setProjectSaved(projectId);
				toolbar.setSaveProjectEnabled(false);
			} else if (EVENT_TYPE.PROJECT_CREATED.equals(type)) {
				AEMFTMetadataElementComposite projectData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
				projectListNavigator.addElement(projectData);
				toolbar.hideProjectForm();
			} else if (EVENT_TYPE.NODELIST_CREATED.equals(type)) {
				String nodeListProjectId =evt.getElementAsString(CRONIOBOIProject.PROJECT_ID);
				projectListNavigator.hideAddNodeListForm(nodeListProjectId);
			} else if (EVENT_TYPE.SECTION_SELECTED.equals(type)) {
				projectListNavigator.removeProjectSectionSelected();
				if (sectionId == null) {
					sectionId = evt.getMainSectionId();
				}
				projectListNavigator.setProjectSectionSeleted(projectId, sectionId);
			} else if (EVENT_TYPE.GET_PROCESS_NODELISTS.equals(type)) {
				Serializable nodeListsData = evt.getElementAsSerializableDataValue();
				List<String> nodeLists = (List<String>) nodeListsData;
				toolbar.addNodeListsToExecuteForm(nodeLists);
			} else if (EVENT_TYPE.DUPLICATE_NODELIST_ERROR.equals(type)) {
				EVENT_TYPE eventType = evt.getEventType();
				if(EVENT_TYPE.DUPLICATE_NODELIST_ERROR.equals(eventType)) {
					AEMFTMetadataElement errorData = evt.getElementAsDataValue();
					long currentProjectId 	= getElementController().getElementAsLong(CRONIOBOIProjectDataConstants.PROJECT_ID, errorData);
					String nodeListName = getElementController().getElementAsString(CRONIOBOINodeList.NODELIST_NAME, errorData);
					projectListNavigator.showDuplicateNodeListNameError(currentProjectId, nodeListName);
				}
			} else if (EVENT_TYPE.GET_MODIFY_NODELISTS_ID.equals(evt.getEventType())) {
				evt.stopPropagation();
				CRONIOBusDesktopProjectEvent 	getModifyNodelistsEvt 	= new CRONIOBusDesktopProjectEvent(CRONIOBusProjectBasePresenterConstants.PROJECT_PRESENTER, getName());
				getModifyNodelistsEvt.setEventType(EVENT_TYPE.SAVE_PROJECT);
				getModifyNodelistsEvt.addElementAsSerializableDataValue((Serializable) modifyNodeLists);
				getLogicalEventHandlerManager().fireEvent(getModifyNodelistsEvt);
			} else if (EVENT_TYPE.ADD_MODIFY_NODELISTS_ID.equals(evt.getEventType())) {
				List<String> modifyNodeLists = (List<String>) evt.getElementAsSerializableDataValue();
				this.modifyNodeLists = modifyNodeLists;
			}			
		} 
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.SHOW_PROJECT_INFO.equals(type)||
				EVENT_TYPE.PROJECT_SAVED.equals(type)||
				EVENT_TYPE.SECTION_MODIFIED.equals(type)||
				EVENT_TYPE.SECTION_SELECTED.equals(type)||
				EVENT_TYPE.PROJECT_CREATED.equals(type)||
				EVENT_TYPE.NODELIST_CREATED.equals(type)||
				EVENT_TYPE.GET_PROCESS_NODELISTS.equals(type)||
				EVENT_TYPE.DUPLICATE_NODELIST_ERROR.equals(type)||
				EVENT_TYPE.GET_MODIFY_NODELISTS_ID.equals(type)||
				EVENT_TYPE.ADD_MODIFY_NODELISTS_ID.equals(type);
	}
}
