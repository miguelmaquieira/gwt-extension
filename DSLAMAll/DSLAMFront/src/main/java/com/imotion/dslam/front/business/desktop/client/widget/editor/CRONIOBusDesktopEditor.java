package com.imotion.dslam.front.business.desktop.client.widget.editor;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.Timer;
import com.imotion.dslam.bom.DSLAMBOIFile;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.base.exe.core.appli.metadata.element.factory.AEMFTMetadataElementConstructorBasedFactory;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class CRONIOBusDesktopEditor extends AEGWTCompositePanel {

	public 		static final String	NAME 			= "CRONIOBusDesktopEditor";
	private		static final int		MILIS_TO_SAVE 	= 1000;

	private AceEditor								editor;
	private AEMFTMetadataElementComposite 			fileData;
	private Timer 									timer;
	private CRONIOBusDesktopEditorChangeHandler 	handler;
	
	public CRONIOBusDesktopEditor(CRONIOBusDesktopEditorChangeHandler handler)  {
		this();
		addEditorChangeHandler(handler);
	}

	public CRONIOBusDesktopEditor() {
		// create first AceEditor widget
		editor = new AceEditor();
		initWidget(editor);
		editor.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR_AREA);

		// start the first editor and set its theme and mode
		editor.startEditor(); // must be called before calling setTheme/setMode/etc.
		editor.setTheme(AceEditorTheme.ECLIPSE);
		editor.setAutoCompletionEnabled(true);
		editor.setShowPrintMargin(false);
		editor.setFontSize(14);
		editor.setVisible(false);

		editor.addOnChangeHandler(new AceEditorCallback() {

			@Override
			public void invokeAceCallback(JavaScriptObject obj) {
				handleChanges(false);
			}

		});

		editor.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				handleChanges(true);
			}
		});
	}

	public void addEditorChangeHandler(CRONIOBusDesktopEditorChangeHandler handler) {
		this.handler = handler;
	}

	/**
	 *	AEGWTCompositePanel 
	 */

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setData(AEMFTMetadataElementComposite data) {
		if (data != null) {
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
			fileData = data;
			String 	content		= getElementController().getElementAsString(DSLAMBOIFile.CONTENT, data);
			int		contentType	= getElementController().getElementAsInt(DSLAMBOIFile.CONTENT_TYPE, data);

			editor.setText(content);

			if (DSLAMBOIProject.PROJECT_MACHINE_TYPE_DSLAM == contentType) {
				editor.setMode(AceEditorMode.DSLAM);
			} else {
				editor.setMode(AceEditorMode.TEXT);
			}
		} else {
			editor.setMode(AceEditorMode.DSLAM);
			fileData = AEMFTMetadataElementConstructorBasedFactory.getMonoInstance().getComposite();
		}
		editor.setVisible(true);
	}

	/************************************************************************
	 *                        PRIVATE FUNCTIONS
	 ************************************************************************/

	private void handleChanges(boolean now) {
		if (handler != null) {
			String originalContent 			= getElementController().getElementAsString(DSLAMBOIFile.CONTENT, fileData);
			final String currentContent		= editor.getText();
			if (!currentContent.equals(originalContent)) {
				if (now) {
					if (timer != null) {
						timer.cancel();
						timer = null;
					}
					fireChangeEvent(currentContent);
				} else {
					if (timer == null) {
						timer = new Timer() {

							@Override
							public void run() {
								fireChangeEvent(currentContent);
							}

						};
					}
					timer.schedule(MILIS_TO_SAVE);
				}
			}
		}
	}

	private void fireChangeEvent(String currentContent) {
		fileData.addElement(DSLAMBOIFile.CONTENT, currentContent);
		fileData.addElement(DSLAMBOIFile.SAVED_TIME, new Date());
		handler.fireEvent(fileData);
	}

}
