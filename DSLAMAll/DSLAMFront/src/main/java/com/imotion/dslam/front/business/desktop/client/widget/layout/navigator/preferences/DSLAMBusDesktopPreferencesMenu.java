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
	private CRONIOBusDesktopPreferencesNavigatorTreeMenuItem 			menuMachines;
	private CRONIOBusDesktopPreferencesNavigatorTreeMenuItem 			menuUser;

	public DSLAMBusDesktopPreferencesMenu() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PREFERENCES_LAYOUT_MENU);

		//MENU
		menu 				= new AEGWTBootstrapTreeMenu();
		root.add(menu);

		//MENU -> Machines
		menuMachines 		= new CRONIOBusDesktopPreferencesNavigatorTreeMenuItem(SECTION_TYPE_MACHINE_PREFERENCES, TEXTS.machines(), this);
		menuMachines.setId(CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST);
		menuMachines.setCloseMenu();
		menu.addWidget(menuMachines);
		
		//MENU -> User
		menuUser 		= new CRONIOBusDesktopPreferencesNavigatorTreeMenuItem(SECTION_TYPE_USER_PREFERENCES, TEXTS.user_label(), this);
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
		CRONIOBusDesktopPreferencesNavigatorTreeMenuItem connection = new CRONIOBusDesktopPreferencesNavigatorTreeMenuItem(connectionName, connectionName, this);
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
		menuUser.setModified(false);
		setResetModifiedUserSection(menuUser);
	}
	
	public void setResetModifiedMachinesSection (AEGWTBootstrapTreeMenuItem section) {
		Iterator<Widget> menuMachineList = section.iterator();
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
	
	public void setResetModifiedUserSection (AEGWTBootstrapTreeMenuItem section) {
		Iterator<Widget> userSectionList = section.iterator();
		while (userSectionList.hasNext()) {
			AEGWTBootstrapTreeMenuFinalItem userSection = (AEGWTBootstrapTreeMenuFinalItem) userSectionList.next();
			userSection.setModified(false);
		}
	}
	
	public void resetSectionSelected() {
		resetMachinesSectionSelected();
		resetUserSectionSelected();
	}
	
	public void resetUserSectionSelected() {
		menuUser.setSelected(false);
		Iterator<Widget> userSectionList = menuUser.iterator();
		while (userSectionList.hasNext()) {
			AEGWTBootstrapTreeMenuFinalItem userSection = (AEGWTBootstrapTreeMenuFinalItem) userSectionList.next();
			userSection.setSelected(false);
		}
	}
	
	public void resetMachinesSectionSelected() {
		menuMachines.setSelected(false);
		Iterator<Widget> menuMachineList = menuMachines.iterator();
		while (menuMachineList.hasNext()) {
			AEGWTBootstrapTreeMenuItem machineSection = (AEGWTBootstrapTreeMenuItem) menuMachineList.next();
			machineSection.setSelected(false);
			Iterator<Widget> machineFinalSections = machineSection.iterator();
			while (machineFinalSections.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem machineFinalSection = (AEGWTBootstrapTreeMenuFinalItem) machineFinalSections.next();
				machineFinalSection.setSelected(false);
			}
		}
	}
	
	public void setSectionSelected(String sectionId) {
		if (SECTION_TYPE_MACHINE_PREFERENCES.equals(sectionId)) {
			menuMachines.setSelected(true);
		} else if (SECTION_TYPE_USER_PREFERENCES.equals(sectionId)) {
			menuUser.setSelected(true);
		} else {
			Iterator<Widget> machineList = menuMachines.iterator();
			while (machineList.hasNext()) {
				CRONIOBusDesktopPreferencesNavigatorTreeMenuItem machineSection = (CRONIOBusDesktopPreferencesNavigatorTreeMenuItem) machineList.next();
				if(machineSection.getId().equals(sectionId)) {
					machineSection.setSelected(true);
				}
			}
		}
	}
	
	public void setFinalSectionSelected(String mainSectionId, String sectionPath) {
		String[] sectionPathSplit = sectionPath.split("\\.");
		if ( CRONIOBOIPreferences.PREFERENCES_MACHINE_PROPERTIES_LIST.equals(mainSectionId)) {
			String machine 		= sectionPathSplit[1];
			Iterator<Widget> menuMachineList = menuMachines.iterator();
			while (menuMachineList.hasNext()) {
				AEGWTBootstrapTreeMenuItem machineSection = (AEGWTBootstrapTreeMenuItem) menuMachineList.next();
				if (machineSection.getId().equals(machine)) {
					Iterator<Widget> machineFinalSections = machineSection.iterator();
					while (machineFinalSections.hasNext()) {
						AEGWTBootstrapTreeMenuFinalItem machineFinalSection = (AEGWTBootstrapTreeMenuFinalItem) machineFinalSections.next();
						if (machineFinalSection.getId().equals(sectionPath)) {
						machineFinalSection.setSelected(true);
						}
					}
				}
			}
		} else if (CRONIOBOIPreferences.PREFERENCES_USER_PROPERTIES.equals(mainSectionId)) {
			Iterator<Widget> userSectionList = menuUser.iterator();
			while (userSectionList.hasNext()) {
				AEGWTBootstrapTreeMenuFinalItem userSection = (AEGWTBootstrapTreeMenuFinalItem) userSectionList.next();
				userSection.setSelected(true);
			}
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		// TODO Auto-generated method stub	
	}

}
