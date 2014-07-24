package com.imotion.dslam.front.business.desktop.client.widget.layout;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopHasPreferencesEventHandlers;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEvent;
import com.imotion.dslam.front.business.desktop.client.event.CRONIOBusDesktopPreferencesEventTypes.EVENT_TYPE;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenterConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.CRONIOBusDesktopPreferencesPresenter;
import com.imotion.dslam.front.business.desktop.client.presenter.preferences.connection.CRONIOBusDesktopPreferencesConnectionPresenter;
import com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences.DSLAMBusDesktopPreferencesMenu;
import com.imotion.dslam.front.business.desktop.client.widget.toolbar.DSLAMBusDesktopPreferencesToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElement;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.jquery.AEGWTJQueryPerfectScrollBar;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class CRONIOBusDesktopPreferencesLayout extends AEGWTCompositePanel implements CRONIOBusDesktopIsLayout, CRONIOBusDesktopHasPreferencesEventHandlers {

	public 		final	static String NAME 		= "CRONIOBusDesktopPreferencesLayout";
	private 	final	static String NAVIGATOR_ID	= "NAVIGATOR_ID";

	private static DSLAMBusI18NTexts TEXTS 	= GWT.create(DSLAMBusI18NTexts.class);
	
	private FlowPanel 										root;
	private DSLAMBusDesktopPreferencesToolbar				toolbar;
	private DSLAMBusDesktopPreferencesMenu					preferencesMenu;
	private CRONIOBusDesktopPreferencesLayoutItemHeader		sectionHeader;
	private FlowPanel										preferencesWorkZone;

	public CRONIOBusDesktopPreferencesLayout() {
		root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT);

		toolbar = new DSLAMBusDesktopPreferencesToolbar();
		root.add(toolbar);
		toolbar.setModified(false);
		
		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_BOTTOM_ZONE);

		//Bottom Zone - PreferencesMenu zone
		FlowPanel preferencesMenuZone = new FlowPanel();
		bottomZone.add(preferencesMenuZone);
		preferencesMenuZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_3);
		preferencesMenuZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_MENU_ZONE);
		
		FlowPanel menu = new FlowPanel();
		menu.addStyleName(DSLAMBusDesktopIStyleConstants.LIST);
		preferencesMenuZone.add(menu);
		
		//Header
		FlowPanel headerZone = new FlowPanel();
		menu.add(headerZone);
		headerZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_ZONE_HEADER);
				
		AEGWTLabel headerLabel = new AEGWTLabel(TEXTS.preferences());
		headerZone.add(headerLabel);

		preferencesMenu = new DSLAMBusDesktopPreferencesMenu();
		menu.add(preferencesMenu);

		//Bottom Zone - Right
		FlowPanel bottomRightZone = new FlowPanel();
		bottomZone.add(bottomRightZone);
		bottomRightZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_9);
		bottomRightZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROJECTS_LAYOUT_BOTTOM_ZONE_RIGHT);

		sectionHeader = new CRONIOBusDesktopPreferencesLayoutItemHeader();
		bottomRightZone.add(sectionHeader);
		sectionHeader.setVisible(false);

		preferencesWorkZone = new FlowPanel();
		bottomRightZone.add(preferencesWorkZone);
		preferencesWorkZone.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_WORK_ZONE);
	}

	/**
	 * CRONIOBusDesktopIsLayout
	 */

	@Override
	public void setLayoutContent(Widget content) {
		preferencesWorkZone.clear();
		preferencesWorkZone.add(content);
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
			AEMFTMetadataElementComposite machinePropertiesListData = getElementController().getElementAsComposite(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST, data);
			List<AEMFTMetadataElement> machinePropertiesList = machinePropertiesListData.getSortedElementList();
			for (AEMFTMetadataElement machineProperties : machinePropertiesList) {
				String connectionName = machineProperties.getKey();
				preferencesMenu.addConnection(connectionName);	
			}
		}
	}

	@Override
	public void postDisplay() {
		super.postDisplay();
		setHeightToDecrease(75);
		AEGWTJQueryPerfectScrollBar.addScrollToWidget(NAVIGATOR_ID, preferencesMenu, getCurrentHeight(), true);
	}

	/**
	 * CRONIOBusDesktopHasPreferencesEventHandlers
	 */
	@Override
	public void dispatchEvent(CRONIOBusDesktopPreferencesEvent evt) {
		String srcWindow 	= evt.getSourceWindow();
		EVENT_TYPE type		= evt.getEventType();
		if (CRONIOBusPreferencesBasePresenterConstants.PREFERENCES_PRESENTER.equals(srcWindow)) {
			String srcWidget	= evt.getSourceWidget();
			String sectionId	= evt.getFinalSectionId();
			String sectionPath	= evt.getFinalSectionPath();
			String[] sectionPathSplit = null;
			
			if (sectionPath != null) {
				sectionPathSplit = sectionPath.split("\\.");
				sectionId = sectionPathSplit[sectionPathSplit.length - 1];
			}
			
			if ((CRONIOBusDesktopPreferencesConnectionPresenter.NAME.equals(srcWidget) || CRONIOBusDesktopPreferencesPresenter.NAME.equals(srcWidget)) && EVENT_TYPE.SHOW_PREFERENCES_INFO.equals(type)) {
				AEMFTMetadataElementComposite sectionData = evt.getElementAsComposite(CRONIOBusPreferencesBasePresenterConstants.SECTION_DATA);
				
				String		machineName			= sectionPathSplit[1];
				boolean		sectionModified		= getElementController().getElementAsBoolean(CRONIOBOIPreferences.INFO_IS_MODIFIED, sectionData);
				
				toolbar.setSavePreferencesEnabled(sectionModified);
				sectionHeader.setMachineName(machineName);
				sectionHeader.setSectionNameFromId(sectionId);
				sectionHeader.setModified(sectionModified);
				sectionHeader.setVisible(true);
				setId(machineName);
				toolbar.setId(machineName);	
			} else if (EVENT_TYPE.SECTION_MODIFIED.equals(type)) {
				preferencesMenu.setPreferencesSectionModified(sectionPath);
				sectionHeader.setModified(true);
				toolbar.setSavePreferencesEnabled(true);
			} else if (EVENT_TYPE.PREFERENCES_SAVED.equals(type)) {
				sectionHeader.setModified(false);
				preferencesMenu.setPreferencesSaved();
				toolbar.setSavePreferencesEnabled(false);
			} else if (EVENT_TYPE.CONNECTION_CREATED.equals(type)) {
				AEMFTMetadataElementComposite connectionData = (AEMFTMetadataElementComposite) evt.getElementAsDataValue();
				String connectionName = getElementController().getElementAsString(CRONIOBOIMachineProperties.MACHINE_NAME, connectionData);
				preferencesMenu.addConnection(connectionName);
				toolbar.hideConnectionForm();
			}
		}
	}

	@Override
	public boolean isDispatchEventType(EVENT_TYPE type) {
		return 
				EVENT_TYPE.SHOW_PREFERENCES_INFO.equals(type)
				||
				EVENT_TYPE.PREFERENCES_SAVED.equals(type)
				||
				EVENT_TYPE.SECTION_MODIFIED.equals(type)
				||
				EVENT_TYPE.CONNECTION_CREATED.equals(type);
	}


}
