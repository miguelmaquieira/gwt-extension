package com.imotion.dslam.front.business.desktop.client.widget.layout.navigator.preferences;

import java.util.Iterator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.imotion.dslam.bom.CRONIOBOIMachinePropertiesDataConstants;
import com.imotion.dslam.bom.CRONIOBOIPreferences;
import com.imotion.dslam.front.business.client.DSLAMBusCommonConstants;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.CRONIOBusPreferencesBasePresenterConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenu;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuFinalItem;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapTreeMenuItem;

public class DSLAMBusDesktopPreferencesMenu extends AEGWTCompositePanel implements CRONIOBusPreferencesBasePresenterConstants {

	public static final String NAME = "DSLAMBusDesktopPreferencesMenu";

	private static DSLAMBusI18NTexts TEXTS = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTBootstrapTreeMenu 				menu;
	private AEGWTBootstrapTreeMenuItem 			menuMachines;
	private AEGWTBootstrapTreeMenuItem 			menuUser;

	public DSLAMBusDesktopPreferencesMenu() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_MENU);
		//setId(projectId);

		//MENU
		menu 				= new AEGWTBootstrapTreeMenu();
		root.add(menu);

		//MENU -> Machines
		menuMachines 		= new AEGWTBootstrapTreeMenuItem(TEXTS.machines());
		menuMachines.setId(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
		menuMachines.setCloseMenu();
		menu.addWidget(menuMachines);
		
		//MENU -> Machines
		menuUser 		= new AEGWTBootstrapTreeMenuItem(TEXTS.user_label());
		menuUser.setId(CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES);
		menuUser.setCloseMenu();
		menu.addWidget(menuUser);
		
		String menuPathUser = menuUser.getId() ;
		
		//MENU -> User -> Config
		CRONIOBusDesktopPreferencesMenuFinalItem userConfig 	= new CRONIOBusDesktopPreferencesMenuFinalItem(menuPathUser, CRONIOBOIPreferences.USER_CONFIG		,TEXTS.config(), this);
		menuUser.add(userConfig);
	}

	public void addConnection(String connectionName) {
		//MENU -> Machines -> DSLAM
		AEGWTBootstrapTreeMenuItem connection = new AEGWTBootstrapTreeMenuItem(connectionName);
		menuMachines.add(connection);
		connection.setContainerId(menuMachines.getId());
		connection.setId(connectionName);
		connection.setCloseMenu();


		String menuPathMachine = connection.getContainerId() + DSLAMBusCommonConstants.ELEMENT_SEPARATOR + connection.getId(); 

		//MENU -> Machines -> DSLAM --> Connection Script
		CRONIOBusDesktopPreferencesMenuFinalItem connectionDslamScript 		= new CRONIOBusDesktopPreferencesMenuFinalItem(menuPathMachine, CRONIOBOIMachinePropertiesDataConstants.MACHINE_CONNECTION_SCRIPT		,TEXTS.connection_script(), this);
		connection.add(connectionDslamScript);

		//MENU -> Machines -> DSLAM --> Disconnection Script
		CRONIOBusDesktopPreferencesMenuFinalItem disconnectionDslamScript 	= new CRONIOBusDesktopPreferencesMenuFinalItem(menuPathMachine, CRONIOBOIMachinePropertiesDataConstants.MACHINE_DISCONNECTION_SCRIPT	,TEXTS.disconnection_script(), this);
		connection.add(disconnectionDslamScript);

		//MENU -> Machines -> DSLAM --> Machine Variables
		CRONIOBusDesktopPreferencesMenuFinalItem variablesMachine 			= new CRONIOBusDesktopPreferencesMenuFinalItem(menuPathMachine, CRONIOBOIMachinePropertiesDataConstants.MACHINE_VARIABLES				,TEXTS.variables(), this);
		connection.add(variablesMachine);

		//MENU -> Machines -> DSLAM --> Connection Config
		CRONIOBusDesktopPreferencesMenuFinalItem connectionConfig 			= new CRONIOBusDesktopPreferencesMenuFinalItem(menuPathMachine, CRONIOBOIMachinePropertiesDataConstants.MACHINE_CONNECTION_CONFIG		,TEXTS.config(), this);
		connection.add(connectionConfig);
	}

	public void setPreferencesSectionModified(String sectionPath) {

		String[] 	sectionPathSplit 		= sectionPath.split("\\.");
		String 		mainSection 			= sectionPathSplit[1];
		String 		finalSection 			= sectionPathSplit[sectionPathSplit.length-1];

		if (CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST.equals(mainSection)) {
			menuMachines.setModified(true);
			Iterator<Widget> sectionList = menuMachines.iterator();
			String machine = sectionPathSplit[2];
			while (sectionList.hasNext()) {
				AEGWTBootstrapTreeMenuItem machineMenu = (AEGWTBootstrapTreeMenuItem) sectionList.next();
				String machineMenuId = machineMenu.getId();
				if (machineMenuId.equals(machine)) {
					machineMenu.setModified(true);
					Iterator<Widget> machineMenuList = machineMenu.iterator();
					while (machineMenuList.hasNext()) {
						AEGWTBootstrapTreeMenuFinalItem finalMachineSection = (AEGWTBootstrapTreeMenuFinalItem) machineMenuList.next();
						String 		finalMachinePathId 		= finalMachineSection.getId();
						String[] 	finalMachinePathIdSplit	= finalMachinePathId.split("\\.");
						String 		finalMachineSectionId 	= finalMachinePathIdSplit[finalMachinePathIdSplit.length - 1];
						if (finalMachineSectionId.equals(finalSection)) {
							finalMachineSection.setModified(true);
						}
					}
				}	
			}
		} else if (CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES.equals(mainSection)) {
			menuUser.setModified(true);
			Iterator<Widget> finalUserSectionList = menuUser.iterator();
			while (finalUserSectionList.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem finalUserSection = (AEGWTBootstrapTreeMenuFinalItem) finalUserSectionList.next();
				String 		finalUserPathId 		= finalUserSection.getId();
				String[] 	finalUserPathIdSplit	= finalUserPathId.split("\\.");
				String 		finalUserSectionId 	= finalUserPathIdSplit[finalUserPathIdSplit.length - 1];
				if (finalUserSectionId.equals(finalSection)) {
					finalUserSection.setModified(true);
				}
			}
		}
	}
	
	public void setPreferencesSaved() {
		menuMachines.setModified(false);
		setResetModifiedMachinesSection(menuMachines);
		
	}
	
	public void setResetModifiedMachinesSection (AEGWTBootstrapTreeMenuItem section) {
		Iterator<Widget> menuMachineList = menuMachines.iterator();
		while (menuMachineList.hasNext()) {
			AEGWTBootstrapTreeMenuItem machineSection = (AEGWTBootstrapTreeMenuItem) menuMachineList.next();
			machineSection.setModified(false);
			Iterator<Widget> machineFinalSections = machineSection.iterator();
			while (machineFinalSections.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem machineFinalSection = (AEGWTBootstrapTreeMenuFinalItem) machineFinalSections.next();
				machineFinalSection.setModified(false);
			}
		}
	}

	//	public boolean isModified() {
	//		return menuProject.isModified();
	//	}	
		
	//
	//	public AEMFTMetadataElementComposite getData() {
	//		return null;
	//	}
	//
	//	public String getElementName() {
	//		return menuProject.getLabelText();
	//	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub	
	}

}
