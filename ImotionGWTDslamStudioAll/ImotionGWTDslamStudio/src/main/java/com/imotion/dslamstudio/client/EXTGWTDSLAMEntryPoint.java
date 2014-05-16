package com.imotion.dslamstudio.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
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
	private AceEditor				console;

	private Map<String, Object> 	variables;
	private EXTGWTDSLAMToolBar toolbar;

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
					+ "$INIT   = 1 \n"
					+ "$END    = 12 \n"
					+ "FOR $i IN ($INIT..$END) { \n"
					+ "    configure vlan shub id $ID egress-port lt:1/1/$i \n"
					+ "} \n\n"
					+ "$j 	 = 15 \n"
					+ "$last = 20 \n"
					+ "WHILE ($j LEQ $last) { \n"
					+ "   configure vlan shub id $ID egress-port lt:1/1/$j \n"
					+ "   $j = $j + 1 \n"
					+ "} \n\n"
					+ "configure pppox-relay cross-connect engine $ID mac-addr-conc name INET_PPP_RES \n"
					+ "configure equipment slot nt-b unlock \n"
					+ "configure equipment protection-group 1 admin-status unlock \n\n"
					+ "if ($SAVE EQU 1) { \n"
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
		toolbar = new EXTGWTDSLAMToolBar();
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

		// create first AceEditor widget
		console = new AceEditor();
		consolePanel.add(console);

		// Label to display current row/column
		rowColLabel = new InlineLabel("");
		consolePanel.add(rowColLabel);

		// start the first editor and set its theme and mode
		console.startEditor(); // must be called before calling setTheme/setMode/etc.
		console.setReadOnly(true);
		console.setTheme(AceEditorTheme.MONOKAI);
		console.setMode(AceEditorMode.DSLAM);
		console.setWidth("100%");
		console.setHeight("100%");
		console.setShowGutter(false);
		console.setShowPrintMargin(false);
	}

	private void updateEditor1CursorPosition() {
		rowColLabel.setText(editor1.getCursorPosition().toString());
	}

	private void runCode() {
		console.setText("");
		toolbar.setButtonEnabled(false);
		final Iterator<String> resultIterator = parseCode().iterator();
		Timer timer = new Timer() {

			@Override
			public void run() {
				if (resultIterator.hasNext()) {
					String currentLine = resultIterator.next();
					String consoleText = currentLine + "\n";
					if (currentLine.startsWith("show")) {
						consoleText += "\n" + EXTGWTDSLAMIXMLExamples.SHOW_EXAMPLE + "\n";
					}
					console.insertAtCursor(consoleText);
				} else {
					this.cancel();
					toolbar.setButtonEnabled(true);
				}
			}
		};
		timer.scheduleRepeating(1500);
	}

	protected int countConsoleLines() {
		String consoleText = console.getText();
		String[] lines = consoleText.split("\r\n|\r|\n");
		return  lines.length;
	}

	private List<String> parseCode() {
		List<String> resultList = new ArrayList<>();
		variables = new HashMap<>();
		List<String> blocks = getBlocks();
		for (String block : blocks) {
			String result = parseBlock(block);
			if (!result.isEmpty()) {
				String[] lines = getLines(result);
				for (int i= 0; i < lines.length; i++) {
					String line = lines[i];
					resultList.add(line);
				}
			}
		}
		return resultList;
	}

	private List<String> getBlocks() {
		List<String>	blocks		= new ArrayList<>();
		String			text		= editor1.getText();
		text = text.replace("\t", "");
		String[]		lines		= getLines(text);

		for (int i = 0; i < lines.length; i++) {
			String currentBlock = "";
			String currentLine	= lines[i];
			if (lines[i].startsWith("//") || currentLine.isEmpty()) {
				continue;
			}
			if (currentLine.contains("{")) {
				while (!currentLine.contains("}")) {
					currentLine = lines[i] + "\n";
					currentBlock += currentLine;
					i++;
				}
				if (currentLine.contains("ELSE") || currentLine.contains("else")) {
					//TODO:
				}
			} else {
				currentBlock = currentLine;
			}
			blocks.add(currentBlock);
		}

		return blocks;
	}

	private String parseBlock(String block) {
		String result = "";
		if (block.startsWith("for") || block.startsWith("FOR")) {
			result = parseFor(block);
		} else if (block.startsWith("while") || block.startsWith("WHILE")) {
			result = parseWhile(block);
		} else if (block.startsWith("if") || block.startsWith("IF")) {
			result = parseIf(block);
		} else {
			result = parseSimpleInstruction(block, null);
		}
		return result;
	}

	private String parseSimpleInstruction(String line, Map<String, Object> localVariables) {
		String result = "";
		line = line.trim();
		if (line.startsWith("$")) {
			String[] variableDefinition = line.split("=");
			String variableName 		= variableDefinition[0].trim();
			String asigment				= variableDefinition[1].trim();
			Object finalValue			= parseAsignment(asigment, localVariables);
			if (localVariables == null || (localVariables != null && variables.containsKey(variableName))) {
				variables.put(variableName, finalValue);
			} else {
				localVariables.put(variableName, finalValue);
			}
		} else {
			result = replaceVariables(line	, localVariables);
		}
		if (!result.isEmpty()) {
			result += "\n";
		}
		return result;
	}

	private String parseIf(String block) {
		String		result 				= "";
		String[]	lines 				= getLines(block);
		String		firstLine 			= lines[0];
		int			conditionInit 		= firstLine.indexOf("(") + 1;
		int			conditionEnd 		= firstLine.indexOf(")");
		String		conditionStr		= firstLine.substring(conditionInit, conditionEnd);
		String[]	conditionElements	= conditionStr.split(" ");
		String		elementOne			= conditionElements[0];
		String		comparator			= conditionElements[1];
		String		elementTwo			= conditionElements[2];

		boolean conditionIsValid = checkIntegerCondition(elementOne, comparator, elementTwo);
		if (conditionIsValid) {
			for (int k = 1; k < lines.length - 1; k++ ) {
				result += parseSimpleInstruction(lines[k], null);
			}
		}
		return result;
	}

	private String parseWhile(String block) {
		String				result				= "";
		String[]			lines				= getLines(block);
		Map<String, Object>	currentVariables	= new HashMap<>();

		String 		firstLine 		= lines[0];
		int 		conditionStart	= firstLine.indexOf("(") + 1;
		int 		conditionEnd	= firstLine.indexOf(")");
		String 		conditionStr 	= firstLine.substring(conditionStart, conditionEnd);

		String[]	conditionElements	= conditionStr.split(" ");
		String		elementOne			= conditionElements[0];
		String		comparator			= conditionElements[1];
		String		elementTwo			= conditionElements[2];

		while (checkIntegerCondition(elementOne, comparator, elementTwo)) {
			for (int j = 1; j < lines.length - 1; j++) {
				result += parseSimpleInstruction(lines[j], currentVariables);
			}
		}
		return result;
	}

	private String parseFor(String block) {
		String				result				= "";
		String[]			lines				= getLines(block);
		Map<String, Object>	currentVariables	= new HashMap<>();

		String 		firstLine 			= lines[0];
		int 		conditionStart		= firstLine.indexOf("(") + 1;
		int 		conditionEnd		= firstLine.indexOf(")");
		String 		conditionStr 		= firstLine.substring(conditionStart, conditionEnd);
		String[] 	intervalParts		= conditionStr.split("\\.\\.");
		String		initStr				= intervalParts[0].trim();
		String		endStr				= intervalParts[1].trim();
		int			init				= getIntValueFromText(initStr);
		int			end					= getIntValueFromText(endStr);
		int			iteratorIndex		= firstLine.indexOf("$");
		int			iteratorIndexEnd	= firstLine.indexOf(" IN");
		String		iteratorStr			= firstLine.substring(iteratorIndex, iteratorIndexEnd).trim();
		for (int i = init; i <= end; i++) {
			currentVariables.put(iteratorStr, i);
			for (int j = 1; j < (lines.length - 1); j++) {
				String currentLine = lines[j];
				result = result + parseSimpleInstruction(currentLine, currentVariables);
			}
		}
		return result;
	}

	private Object parseAsignment(String asignment, Map<String, Object> localVariables) {
		Object result = null;

		if (asignment.contains("\"") || asignment.contains("'")) {
			result = asignment;
		} else {
			result = parseIntegerAsignment(asignment, localVariables);
		}

		return result;
	}

	private Integer parseIntegerAsignment(String asignment, Map<String, Object> localVariables) {

		Map<String , Object> allVariables = new HashMap<>();
		allVariables.putAll(variables);
		if (localVariables != null) {
			allVariables.putAll(localVariables);
		}
		Integer integerResult = 0;
		String[] asignmentParts = asignment.split(" ");
		if (asignmentParts.length > 1) {
			String firstValue 	= asignmentParts[0];
			String operator		= asignmentParts[1];
			String secondValue 	= asignmentParts[2];

			Integer firstValueAsInt		= 0;
			Integer secondValueAsInt	= 0;

			if (firstValue.contains("$")) {
				firstValueAsInt = (Integer) allVariables.get(firstValue);
			} else {
				firstValueAsInt = Integer.parseInt(firstValue);
			}

			if (secondValue.contains("$")) {
				secondValueAsInt = (Integer) allVariables.get(secondValue);
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

	private String replaceVariables(String line, Map<String, Object> localVariables) {
		String lineWithValues = line;
		if (line.contains("$")) {
			Map<String, Object> allVariables = new HashMap<>();
			allVariables.putAll(variables);
			if (localVariables != null) {
				allVariables.putAll(localVariables);
			}
			Set<String> keySet = allVariables.keySet();
			for (String key : keySet) {
				if (lineWithValues.contains(key)) {
					String value = null;
					if (localVariables != null && localVariables.get(key) != null) {
						value = localVariables.get(key).toString();
					} else {
						value = variables.get(key).toString();
					}
					lineWithValues = lineWithValues.replace(key, value);
				}
			}
		}
		return lineWithValues;
	}

	private boolean checkIntegerCondition(String valueA, String comparator, String valueB) {
		boolean result = false;
		int	 valueaAsInt = getIntValueFromText(valueA);
		int	 valuebAsInt = getIntValueFromText(valueB);
		if (comparator.equals("EQU")) {
			result = valueaAsInt == valuebAsInt;
		} else if (comparator.equals("NEQ")) {
			result = valueaAsInt != valuebAsInt;
		} else if (comparator.equals("LSS")) {
			result = valueaAsInt < valuebAsInt;
		} else if (comparator.equals("LEQ")) {
			result = valueaAsInt <= valuebAsInt;
		} else if (comparator.equals("GTR")) {
			result = valueaAsInt > valuebAsInt;
		} else if (comparator.equals("GEQ")) {
			result = valueaAsInt >= valuebAsInt;
		}
		return result;
	}

	private String[] getLines(String block) {
		return block.split("\\n");
	}

	private int getIntValueFromText(String text) {
		int value = -1;
		if (text.startsWith("$")) {
			value = (Integer) variables.get(text);
		} else {
			value = Integer.parseInt(text);
		}
		return value;
	}

}
