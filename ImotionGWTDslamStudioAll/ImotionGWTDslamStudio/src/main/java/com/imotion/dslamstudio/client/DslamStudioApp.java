package com.imotion.dslamstudio.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DslamStudioApp implements EntryPoint {
	
	private AceEditor editor1;
	private InlineLabel rowColLabel;
	private FlowPanel editorPanel;
	private FlowPanel consolePanel;

	private static final String DSLAM_TEXT =
			"//THIS IS A COMMENT \n"
					+ "$ID = 2845 \n"
					+ "show equipment protection-element xml \n"
					+ "configure equipment slot nt-b no unlock \n"
					+ "configure equipment protection-group 1 admin-status lock \n"
					+ "configure vlan id $ID mode residential-bridge name INET_PPP_RES protocol-filter pass-pppoe \n"
					+ "configure vlan id $ID pppoe-relay-tag configurable circuit-id-pppoe physical-id remote-id-pppoe customer-id \n"
					+ "configure vlan shub id $ID   mode residential-bridge name INET_PPP_RES \n"
					+ "show interface shub port xml \n"
					+ "configure vlan shub id $ID egress-port #NETWORK \n"
					+ "show equipment slot $ID \n"
					+ "$INIT   = 1; \n"
					+ "$END    = 12; \n"
					+ "FOR $i IN ($INIT..$END) { \n"
					+ "    configure vlan shub id $ID egress-port lt:1/1/$i \n"
					+ "} \n"
					+ "$j = 13 \n"
					+ "WHILE ($j LEQ #MAXNODES) \n"
					+ "   configure vlan shub id $ID egress-port lt:1/1/$j \n"
					+ "   $j = $j + 1; \n"
					+ "} \n"
					+ "configure pppox-relay cross-connect engine $ID mac-addr-conc name INET_PPP_RES \n"
					+ "if (%ALL_RIGHT) { \n"
					+ "    configure equipment slot nt-b unlock \n"
					+ "    configure equipment protection-group 1 admin-status unlock \n"
					+ "    admin software-mngt shub database save \n"
					+ "} \n";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		buildEditor();
		buidConsole();
		
		SplitLayoutPanel splitPanel = new SplitLayoutPanel(5);
		RootLayoutPanel.get().add(splitPanel);
		
		splitPanel.addEast(consolePanel, 300);
		splitPanel.add(editorPanel);
	}

	private void buildEditor() {
		editorPanel = new FlowPanel();
		editorPanel.setWidth("100%");
		editorPanel.setHeight("100%");
		editorPanel.getElement().getStyle().setFloat(Float.LEFT);

		// create first AceEditor widget
		editor1 = new AceEditor();
		editorPanel.add(editor1);

		// Label to display current row/column
		rowColLabel = new InlineLabel("");
		editorPanel.add(rowColLabel);

		// start the first editor and set its theme and mode
		editor1.startEditor(); // must be called before calling setTheme/setMode/etc.
		editor1.setTheme(AceEditorTheme.ECLIPSE);
		editor1.setMode(AceEditorMode.DSLAM);

		// use cursor position change events to keep a label updated
		// with the current row/col
		editor1.addOnCursorPositionChangeHandler(new AceEditorCallback() {
			
			public void invokeAceCallback(JavaScriptObject obj) {
				updateEditor1CursorPosition();
			}
		});
		updateEditor1CursorPosition(); // initial update

		// set some initial text in editor 1
		editor1.setText(DSLAM_TEXT);

		// add some annotations
		//				editor1.addAnnotation(0, 1, "What's up?", AceAnnotationType.WARNING);
		//				editor1.addAnnotation(2, 1, "This code is lame", AceAnnotationType.ERROR);
		//				editor1.setAnnotations();
		editor1.setWidth("100%");
		editor1.setHeight("95%");
	}

	private void buidConsole() {
		consolePanel = new FlowPanel();
		consolePanel.setWidth("100%");
		consolePanel.setHeight("100%");
		consolePanel.getElement().getStyle().setFloat(Float.RIGHT);

		TextArea console = new TextArea();
		consolePanel.add(console);
		console.setSize("100%", "100%");
		console.getElement().getStyle().setBackgroundColor("black");
		console.getElement().getStyle().setColor("white");
//		console.setReadOnly(true);
		console.getElement().getStyle().setOverflow(Overflow.AUTO);
	}

	private void updateEditor1CursorPosition() {
		rowColLabel.setText(editor1.getCursorPosition().toString());
	}
	
}
