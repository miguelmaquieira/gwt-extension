package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusProjectBasePresenterConstants;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopHasProjectEventHandlers;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEvent;
import com.imotion.dslam.front.business.desktop.client.view.event.CRONIOBusDesktopProjectEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.DSLAMBusDesktopProjectNavigator;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

public class CRONIOBusDesktopProjectsLayout extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayout, CRONIOBusDesktopHasProjectEventHandlers {

	public 		final static String 	NAME 			= "CRONIOBusDesktopProjectsLayout";
	public	 	final static String	NO_PROJECT_ID 	= "NO_PROJECT_ID";

	private FlowPanel 									root;
	private DSLAMBusDesktopToolbar						toolbar;
	private DSLAMBusDesktopProjectNavigator				projectListNavigator;
	private CRONIOBusDesktopProjectsLayoutItemHeader	sectionHeader;
	private FlowPanel									projectWorkZone;

	public CRONIOBusDesktopProjectsLayout() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT);

		toolbar = new DSLAMBusDesktopToolbar();
		root.add(toolbar);
		toolbar.setModified(false);
		toolbar.setId(NO_PROJECT_ID);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE);

		//Bottom Zone - Projectlist zone
		FlowPanel projectListZone = new FlowPanel();
		bottomZone.add(projectListZone);
		projectListZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		projectListZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_LIST_ZONE);

		projectListNavigator = new DSLAMBusDesktopProjectNavigator();
		projectListZone.add(projectListNavigator);

		//Bottom Zone - Right
		FlowPanel bottomRightZone = new FlowPanel();
		bottomZone.add(bottomRightZone);
		bottomRightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		bottomRightZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT);

		sectionHeader = new CRONIOBusDesktopProjectsLayoutItemHeader();
		bottomRightZone.add(sectionHeader);
		sectionHeader.setVisible(false);

		projectWorkZone = new FlowPanel();
		bottomRightZone.add(projectWorkZone);
		projectWorkZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_WORK_ZONE);
	}

	public List<String> getModifiedProjetIds() {
		return projectListNavigator.getModifiedProjectIds();
	}

	/**
	 * CRONIOBusDesktopIsLayout
	 */

	@Override
	public void setLayoutContent(Widget content) {
		projectWorkZone.clear();
		projectWorkZone.add(content);
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
				String		projectName			= evt.getElementAsString(DSLAMBOIProject.PROJECT_NAME);
				boolean		sectionModified		= evt.getElementAsBoolean(DSLAMBOIProject.IS_MODIFIED);
				List<String> modifiedProjects	= getModifiedProjetIds();
				boolean projectModified = modifiedProjects.contains(projectId);
				toolbar.setSaveProjectEnabled(projectModified);
				sectionHeader.setProyectName(projectName);
				sectionHeader.setSectionNameFromId(sectionId);
				sectionHeader.setModified(sectionModified);
				sectionHeader.setVisible(true);
				setId(projectId);
				toolbar.setId(projectId);
				if (DSLAMBOIProject.PROJECT_EXECUTION_LOG.equals(sectionId)) {
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
			}
		}
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return EVENT_TYPE.SHOW_PROJECT_INFO.equals(type)
				||
				EVENT_TYPE.PROJECT_SAVED.equals(type)
				||
				EVENT_TYPE.SECTION_MODIFIED.equals(type)
				||
				EVENT_TYPE.PROJECT_CREATED.equals(type);
	}


}
