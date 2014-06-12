package com.imotion.dslam.front.business.desktop.client.widget.editor;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlowPanel;
import com.imotion.dslam.front.business.desktop.client.DSLAMBusDesktopIStyleConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;
import com.selene.arch.exe.gwt.client.ui.widget.AEGWTCompositePanel;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class CRONIOBusDesktopScriptsEditor extends AEGWTCompositePanel {
	
	public static final String NAME = "CRONIBusDesktopScriptsEditor";
	
	private AceEditor editor;

	public CRONIOBusDesktopScriptsEditor() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR_CONTAINER);

		// create first AceEditor widget
		editor = new AceEditor();
		root.add(editor);
		editor.addStyleName(DSLAMBusDesktopIStyleConstants.SCRIPTS_EDITOR_AREA);


		// start the first editor and set its theme and mode
		editor.startEditor(); // must be called before calling setTheme/setMode/etc.
		editor.setTheme(AceEditorTheme.ECLIPSE);
		editor.setMode(AceEditorMode.DSLAM);
		editor.setAutoCompletionEnabled(true);
		editor.setShowPrintMargin(false);
		editor.setFontSize(14);
		editor.setVisible(false);

		editor.addOnChangeHandler(new AceEditorCallback() {

			@Override
			public void invokeAceCallback(JavaScriptObject obj) {
//				if (!toolbar.isModified()) {
//					toolbar.setModified(true);
//				}
			}
		});
		
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
		// TODO Auto-generated method stub

	}

}
