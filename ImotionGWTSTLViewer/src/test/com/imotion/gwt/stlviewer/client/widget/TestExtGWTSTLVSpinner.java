package test.com.imotion.gwt.stlviewer.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TestExtGWTSTLVSpinner extends Composite {

	private Button increaseButton;
	private Button decreaseButton;
	
	public TestExtGWTSTLVSpinner(String title) {
		VerticalPanel root = new VerticalPanel();
		initWidget(root);
		
		//Title
		Label titleLabel = new Label(title);
		root.add(titleLabel);
		
		//Buttons
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		root.add(buttonsPanel);
		
		decreaseButton = new Button("-");
		buttonsPanel.add(decreaseButton);
		decreaseButton.setWidth("50px");
		
		increaseButton = new Button("+");
		buttonsPanel.add(increaseButton);
		increaseButton.setWidth("50px");
	}
	
	public void addIncreaseClickHandler(ClickHandler clickHandler) {
		increaseButton.addClickHandler(clickHandler);
	}
	
	public void addDecraseClickHandler(ClickHandler clickHandler) {
		decreaseButton.addClickHandler(clickHandler);
	}

}
