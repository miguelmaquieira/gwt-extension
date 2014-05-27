package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.imotion.dslam.front.business.client.DSLAMBusI18NTexts;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.AEGWTIBoostrapConstants;
import com.selene.arch.exe.gwt.client.business.ui.utils.AEGWTBusinessUtils;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;
import com.selene.arch.exe.gwt.client.ui.widget.bootstrap.AEGWTBootstrapGlyphiconButton;
import com.selene.arch.exe.gwt.client.ui.widget.label.AEGWTLabel;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEvent;
import com.selene.arch.exe.gwt.mvp.event.logic.AEGWTLogicalEventTypes.LOGICAL_TYPE;

public class DSLAMBusDesktopEditorToolbarFileInfo extends AEGWTCompositePanel {

	public static final String NAME = "DSLAMBusDesktopEditorToolbarFileInfo";

	private DSLAMBusI18NTexts texts = GWT.create(DSLAMBusI18NTexts.class);

	private AEGWTLabel	fileNameLabel;
	private AEGWTLabel	lastSavedLabel;

	private boolean modified;

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

		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!isModified() || (isModified() && Window.confirm(texts.exit_without_save()))) {
					AEGWTLogicalEvent closeEvent = new AEGWTLogicalEvent(getWindowName(), getName());
					closeEvent.setSourceWidgetId(getId());
					closeEvent.setEventType(LOGICAL_TYPE.CLOSE_EVENT);
					getLogicalEventHandlerManager().fireEvent(closeEvent);
				}
			}
		});
	}

	public void setFileName(String fileName) {
		fileNameLabel.setText(fileName);
	}

	public void setLastSaved(Date date) {
		if (date == null) {
			lastSavedLabel.setText("");
		} else {
			String lastSavedTimeStr = AEGWTBusinessUtils.getFormattedTimeMessage(date, "dd/MM/yyyy HH:mm:ss");
			lastSavedTimeStr = texts.last_saved() + lastSavedTimeStr;
			lastSavedLabel.setText(lastSavedTimeStr);
		}
	}

	public void setModified(boolean modified) {
		this.modified = modified;
		String currentText = fileNameLabel.getText();
		if (modified && !currentText.startsWith("*")) {
			currentText = "*" + currentText; 
		} else {
			currentText = currentText.replaceFirst("\\*", "");
		}
		fileNameLabel.setText(currentText);
	}

	public boolean isModified() {
		return modified;
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
