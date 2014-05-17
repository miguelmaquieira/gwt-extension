package com.imotion.dslamstudio.client.widget;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class EXTGWTDSLAMToolBar extends Composite {

	private Button runButton;
	private Button stepButton;
	private Button restartButton;

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
		runButton.getElement().getStyle().setMarginRight(5d, Unit.PX);
		
		stepButton = new Button("STEP ►");
		root.add(stepButton);
		
		restartButton = new Button("RESTART");
		root.add(restartButton);
	}
	
	public void addRunClickHandler(ClickHandler handler) {
		runButton.addClickHandler(handler);
	}
	
	public void addStepClickHandler(ClickHandler handler) {
		stepButton.addClickHandler(handler);
	}
	
	public void addRestartClickHandler(ClickHandler handler) {
		restartButton.addClickHandler(handler);
	}
	
	public void setButtonEnabled(boolean enabled) {
		runButton.setEnabled(enabled);
	}

}
