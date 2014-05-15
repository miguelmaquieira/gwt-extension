package com.imotion.dslamstudio.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.imotion.dslamstudio.client.widget.EXTGWTDSLAMIXMLExamples;
import com.imotion.dslamstudio.client.widget.EXTGWTDSLAMToolBar;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EXTGWTDSLAMEntryPoint implements EntryPoint {

	private AceEditor				editor1;
	private InlineLabel				rowColLabel;
	private FlowPanel				editorPanel;
	private FlowPanel				consolePanel;
	private TextArea				console;

	private Map<String, Object> 	variables;

	private static final String DSLAM_TEXT =
			"//THIS IS A COMMENT \n\n"
					+ "$ID = 2845 \n"
					+ "$SAVE = 1 \n"
					+ "$NETWORK = 'network:4' \n\n"
					+ "show equipment protection-element xml \n"
					+ "configure equipment slot nt-b no unlock \n"
					+ "configure equipment protection-group 1 admin-status lock \n"
					+ "configure vlan id $ID mode residential-bridge name INET_PPP_RES protocol-filter pass-pppoe \n"
					+ "configure vlan id $ID pppoe-relay-tag configurable circuit-id-pppoe physical-id remote-id-pppoe customer-id \n"
					+ "configure vlan shub id $ID   mode residential-bridge name INET_PPP_RES \n"
					+ "show interface shub port xml \n"
					+ "configure vlan shub id $ID egress-port $NETWORK \n"
					+ "show equipment slot $ID \n\n"
					+ "$INIT   = 1; \n"
					+ "$END    = 12; \n"
					+ "FOR $i IN ($INIT..$END) { \n"
					+ "    configure vlan shub id $ID egress-port lt:1/1/$i \n"
					+ "} \n\n"
					+ "$j 	 = 15 \n"
					+ "$last = 20 \n"
					+ "WHILE ($j LEQ $last) \n"
					+ "   configure vlan shub id $ID egress-port lt:1/1/$j \n"
					+ "   $j = $j + 1; \n"
					+ "} \n\n"
					+ "configure pppox-relay cross-connect engine $ID mac-addr-conc name INET_PPP_RES \n"
					+ "configure equipment slot nt-b unlock \n"
					+ "configure equipment protection-group 1 admin-status unlock \n\n"
					+ "if ($SAVE) { \n"
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


		double consoleWidth = Window.getClientWidth() * 0.4;
		splitPanel.addEast(consolePanel, consoleWidth);
		splitPanel.add(editorPanel);
	}

	private void buildEditor() {
		editorPanel = new FlowPanel();
		editorPanel.setWidth("100%");
		editorPanel.setHeight("100%");
		editorPanel.getElement().getStyle().setFloat(Float.LEFT);

		//toolbar
		EXTGWTDSLAMToolBar toolbar = new EXTGWTDSLAMToolBar();
		editorPanel.add(toolbar);

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

		toolbar.addRunClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				runCode();
			}
		});
	}

	private void buidConsole() {
		consolePanel = new FlowPanel();
		consolePanel.setWidth("100%");
		consolePanel.setHeight("100%");
		consolePanel.getElement().getStyle().setFloat(Float.RIGHT);

		console = new TextArea();
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

	private void runCode() {
		console.setText("");

		final Iterator<String> resultIterator = parseCode().iterator();
		Timer timer = new Timer() {

			@Override
			public void run() {
				if (resultIterator.hasNext()) {
					String consoleText = console.getText();
					String currentLine = resultIterator.next();
					consoleText += currentLine + "\n";
					if (currentLine.startsWith("show")) {
						consoleText += "\n" + EXTGWTDSLAMIXMLExamples.SHOW_EXAMPLE + "\n";
					}
					console.setText(consoleText);
				}
			}
		};
		timer.scheduleRepeating(500);
	}

	private List<String> parseCode() {
		List<String> result = new ArrayList<>();
		variables = new HashMap();

		List<String> blocks = getBlocks();
		for (String block : blocks) {
			parseBlock(block);
		}
		return result;
	}
	
	private List<String> getBlocks() {
		List<String>	blocks		= new ArrayList<>();
		String			text		= editor1.getText();
		String[]		lines		= text.split("\\n");

		for (int i = 0; i < lines.length; i++) {
			String currentBlock = "";
			if (lines[i].startsWith("//")) {
				continue;
			}
			if (lines[i].contains("{")) {
				while (!lines[i].contains("}")) {
					currentBlock += lines[i];
					i++;
				}
				currentBlock += lines[i];
			} else {
				currentBlock = lines[i];
			}
			blocks.add(currentBlock);
		}

		return blocks;
	}

	private void parseBlock(String block) {
		if (block.startsWith("for") || block.startsWith("FOR")) {
			parseFor(block);
		} else if (block.startsWith("while") || block.startsWith("WHILE")) {
			parseWhile(block);
		} else if (block.startsWith("if") || block.startsWith("IF")) {
			parseIf(block);
		} else {
			parseSimpleInstruction(block);
		}
	}

	private void parseSimpleInstruction(String line) {
		if (line.startsWith("$")) {
			String[] variableDefinition = line.split("=");
			String variableName 		= variableDefinition[0].trim();
			String variableAsignment	= variableDefinition[1].trim();
			Object finalValue			= parseAsignment(variableAsignment);
			variables.put(variableName, finalValue);
		} else if (line.contains("$")) {

		} else {

		}
		replaceVariables(line);
		
	}

	private void parseIf(String block) {
		// TODO Auto-generated method stub
		
	}

	private void parseWhile(String block) {
		// TODO Auto-generated method stub
		
	}

	private void parseFor(String block) {
		String[]			lines				= block.split("\\n");
		List<String>		linesList			= Arrays.asList(lines);
		Map<String, Object>	currentVariables	= new HashMap<>();
		
		int conditionStart	= lines[0].indexOf("(");
		int conditionEnd	= lines[0].indexOf(")");
		String conditionStr = lines[0].substring(conditionStart, conditionEnd);
		
		for (String line : linesList) {
			if (line.startsWith("//")) {
				continue;
			}

			
		}
	}

	private Object parseAsignment(String asignment) {
		Object result = null;

		if (asignment.contains("\"") || asignment.contains("'")) {
			result = asignment;
		} else {
			result = parseIntegerAsignment(asignment);
		}

		return result;
	}

	private Integer parseIntegerAsignment(String asignment) {
		Integer integerResult = 0;
		String[] asignmentParts = asignment.split(" ");
		if (asignmentParts.length > 1) {
			String firstValue 	= asignmentParts[0];
			String operator		= asignmentParts[1];
			String secondValue 	= asignmentParts[2];

			Integer firstValueAsInt		= 0;
			Integer secondValueAsInt	= 0;

			if (firstValue.contains("$")) {
				firstValueAsInt = (Integer) variables.get(firstValue);
			} else {
				firstValueAsInt = Integer.parseInt(firstValue);
			}

			if (firstValue.contains("$")) {
				secondValueAsInt = (Integer) variables.get(secondValue);
			} else {
				secondValueAsInt = Integer.parseInt(secondValue);
			}

			if (operator.contains("+")) {
				integerResult = firstValueAsInt + secondValueAsInt;
			} else {
				integerResult = firstValueAsInt - secondValueAsInt;
			}

		} else {
			integerResult = Integer.parseInt(asignment);
		}
		return integerResult;
	}

	private String replaceVariables(String line) {
		String lineWithValues = line;
		if (line.contains("$")) {
			Set<String> keySet = variables.keySet();
			for (String key : keySet) {
				lineWithValues = lineWithValues.replace(key, variables.get(key).toString());
			}
		}
		return lineWithValues;
	}

}
