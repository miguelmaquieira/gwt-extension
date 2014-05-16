package com.imotion.dslamstudio.client.widget;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class EXTGWTDSLAMToolBar extends Composite {

	private Button runButton;

	public EXTGWTDSLAMToolBar() {
		FlowPanel root = new FlowPanel();
		initWidget(root);
		root.setWidth("100%");
		root.getElement().getStyle().setBackgroundColor("#F2F1F0");
		root.getElement().getStyle().setPadding(5d, Unit.PX);
		root.getElement().getStyle().setBorderColor("grey");
		root.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		root.getElement().getStyle().setBorderWidth(1, Unit.PX);
		
		runButton = new Button("RUN ►");
		root.add(runButton);
	}
	
	public void addRunClickHandler(ClickHandler handler) {
		runButton.addClickHandler(handler);
	}
	
	public void setButtonEnabled(boolean enabled) {
		runButton.setEnabled(enabled);
	}

}
