package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.Date;

import org.goda.time.DateTime;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.imotion.dslam.front.business.desktop.client.common.DSLAMBusI18NTexts;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;

public class DSLAMBusDesktopEditorToolbarFileInfo extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopEditorToolbarFileInfo";
	
	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);
	
	private AEGWTLabel	fileNameLabel;
	private AEGWTLabel	lastSavedLabel;
	
	public DSLAMBusDesktopEditorToolbarFileInfo() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_FILE_INFO);
		
		//FILENAME
		SimplePanel fileNameZone = new SimplePanel();
		root.add(fileNameZone);
		fileNameZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_5);
		fileNameZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_FILE_INFO_FILE_NAME_ZONE);
		
		fileNameLabel = new AEGWTLabel();
		fileNameZone.add(fileNameLabel);
		
		//LASTSAVED
		SimplePanel lastSavedZone = new SimplePanel();
		root.add(lastSavedZone);
		lastSavedZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_5);
		lastSavedZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_FILE_INFO_LAST_SAVED_ZONE);
		
		lastSavedLabel = new AEGWTLabel();
		lastSavedZone.add(lastSavedLabel);
		
		//CLOSE
		SimplePanel closeZone = new SimplePanel();
		root.add(closeZone);
		closeZone.addStyleName(AEGWTIBoostrapConstants.COL_XS_2);
		closeZone.addStyleName(DSLAMBusDesktopIStyleConstants.TOOLBAR_FILE_INFO_CLOSE_ZONE);
		
		AEGWTBootstrapGlyphiconButton closeButton = new AEGWTBootstrapGlyphiconButton(AEGWTIBoostrapConstants.GLYPHICON_REMOVE, null, texts.exit());
		closeZone.add(closeButton);
	}
	
	public void setFileName(String fileName) {
		fileNameLabel.setText(fileName);
	}
	
	public void setLastSaved(Date date) {
		DateTime lastSavedDateTime = new DateTime(date.getTime());
		String lastSavedTimeStr = lastSavedDateTime.toString("dd/MM/yyyy hh:mm:ss");
		lastSavedTimeStr = texts.last_saved() + lastSavedTimeStr;
		lastSavedLabel.setText(lastSavedTimeStr);
	}
	
	public void setModified(boolean modified) {
		String currentText = fileNameLabel.getText();
		if (modified && !currentText.startsWith("*")) {
			currentText = "*" + currentText; 
		} else {
			currentText = currentText.replaceFirst("\\*", "");
		}
		fileNameLabel.setText(currentText);
	}

	/**
	 *  AEGWTICompositePanel
	 */
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
	}

}
