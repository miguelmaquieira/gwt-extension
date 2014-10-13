package test.com.imotion.gwt.stlviewer.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class TestExtGWTSTLVParameter extends Composite {
	
	private FlowPanel content;
	private TextBox textValue;
	private Button action;
	
	public TestExtGWTSTLVParameter(String labelText, String defaultValue, String textWidth, String buttonWidth) {
		content = new FlowPanel();
		initWidget(content);
		content.setWidth("150px");
		
		// Title
		Label titleLabel = new Label(labelText);
		content.add(titleLabel);
		
		// TextPanel
		HorizontalPanel hrPanel = new HorizontalPanel();
		content.add(hrPanel);
		
		textValue = new TextBox();
		textValue.setText(defaultValue);
		textValue.setWidth(textWidth);
		hrPanel.add(textValue);
		
		// Load button
		action = new Button("SET");
		hrPanel.add(action);
		action.setWidth(buttonWidth);
	}
	
	public String getValue() {
		return textValue.getText();
	}
	
	public void addActionHandler(ClickHandler handler) {
		action.addClickHandler(handler);
	}
}