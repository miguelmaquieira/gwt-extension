package com.imotion.dslam.front.business.desktop.client.view.processpage;

import java.util.Date;

import org.goda.time.DateTime;

import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.bom.DSLAMBOIFileDataConstants;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.presenter.processpage.DSLAMBusDesktopProcessPageDisplay;
import com.imotion.dslam.front.business.desktop.client.view.DSLAMBusDesktopPanelBaseView;
import com.imotion.dslam.front.business.desktop.client.widget.navigator.DSLAMBusDesktopNavigatorList;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopConnectionToolbar;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopNavigatorProcessList;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopProcessConfigure;
import com.imotion.dslam.front.business.desktop.client.widget.proccesspage.DSLAMBusDesktopProcessToolbar;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.composite.AEMFTMetadataElementCompositeRecordSetListRegroup;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;

public class DSLAMBusDesktopProcessPageScreenView extends DSLAMBusDesktopPanelBaseView implements DSLAMBusDesktopProcessPageDisplay {

	public static final String NAME = "DSLAMBusDesktopProcessPageScreenView";

	private FlowPanel 							root;
	private DSLAMBusDesktopProcessToolbar		toolbar;
	private DSLAMBusDesktopConnectionToolbar	connectionToolbar;
	private DSLAMBusDesktopNavigatorList		processList;
	private DSLAMBusDesktopProcessConfigure		processOptions;
	
	public DSLAMBusDesktopProcessPageScreenView() {
		root = new FlowPanel();
		initContentPanel(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW);

		toolbar = new DSLAMBusDesktopProcessToolbar();
		root.add(toolbar);
		toolbar.setMainTitleText("script1");
		toolbar.addStyleName(AEGWTIBoostrapConstants.ROW);
		toolbar.setModified(false);
		toolbar.setLastSaved(new Date());
		
		connectionToolbar = new DSLAMBusDesktopConnectionToolbar();
		root.add(connectionToolbar);
		connectionToolbar.addStyleName(AEGWTIBoostrapConstants.ROW);

		//Bottom Zone
		FlowPanel bottomZone = new FlowPanel();
		root.add(bottomZone);
		bottomZone.addStyleName(DSLAMBusDesktopIStyleConstants.PROCESSES_VIEW_BOTTOM_ZONE);
		bottomZone.addStyleName(AEGWTIBoostrapConstants.ROW);

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
		processList.postDisplay();
		processOptions.postDisplay();
		
		//TEST
		buildExample();
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
	}

	/************************************************************************
	 *                        PROTECTED FUNCTIONS
	 ************************************************************************/


	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/
	private void buildExample() {
		AEMFTMetadataElementCompositeRecordSetListRegroup fileListData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getCompositeListRegroup();

		for (int i = 0; i < 20 ; i++) {
			AEMFTMetadataElementComposite fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
			DateTime fileDate = new DateTime();
			fileDate = fileDate.minusDays(i);

			getElementController().setElement(DSLAMBOIFileDataConstants.FILE_ID, fileData, "A-" + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.FILE_NAME, fileData, "file-" + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.CONTENT, fileData, "sdfsdfsfd \n aakskaskask \n\n scsdcscc -- " + i);
			getElementController().setElement(DSLAMBOIFileDataConstants.SAVED_TIME, fileData, fileDate.toDate());

			fileListData.addElement(fileData);
		}
		processList.setData(fileListData);
	}


}
