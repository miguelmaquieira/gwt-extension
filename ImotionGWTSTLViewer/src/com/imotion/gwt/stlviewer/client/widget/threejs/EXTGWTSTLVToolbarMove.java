package com.imotion.gwt.stlviewer.client.widget.threejs;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;

public class EXTGWTSTLVToolbarMove extends Composite {
	
	private EXTGWTSTLVSpinner spinnerHorizontal;
	private EXTGWTSTLVSpinner spinnerVertical;
	
	private EXTGWTSTLILoaderDisplay stlvDisplay;
	
	public EXTGWTSTLVToolbarMove(EXTGWTSTLILoaderDisplay stlvDisplay) {
		this.stlvDisplay = stlvDisplay;
		FlowPanel contentPanel = new FlowPanel();
		initWidget(contentPanel);
		contentPanel.addStyleName("viewer-toolbarMove");
		
		// Horizontal toolbar
		EXTGWTSTLVSpinnerListener horizontalMove = new EXTGWTSTLVSpinnerListener() {
			
			@Override
			public void onSpinning(long value, int step) {
				EXTGWTSTLVToolbarMove.this.stlvDisplay.moveObject(step, 0, step);
			}
		};
		spinnerHorizontal = new EXTGWTSTLVSpinner(horizontalMove, 0, -1000, 1000, 1, 5, true);
		HorizontalPanel toolbarHPanel = new HorizontalPanel();
		contentPanel.add(toolbarHPanel);
		toolbarHPanel.addStyleName("viewer-toolbarMoveHP");
		Image imageLeft = spinnerHorizontal.getDecrementArrow();
		imageLeft.addStyleName("arrow");
		imageLeft.addStyleName("arrow-left");
		Image imageRight = spinnerHorizontal.getIncrementArrow();
		imageRight.addStyleName("arrow");
		imageRight.addStyleName("arrow-right");
		toolbarHPanel.add(imageLeft);
		toolbarHPanel.setCellHorizontalAlignment(imageLeft, HasHorizontalAlignment.ALIGN_LEFT);
		toolbarHPanel.add(imageRight);
		toolbarHPanel.setCellHorizontalAlignment(imageRight, HasHorizontalAlignment.ALIGN_RIGHT);
		
		// Horizontal toolbar
		EXTGWTSTLVSpinnerListener verticalMove = new EXTGWTSTLVSpinnerListener() {
					
			@Override
			public void onSpinning(long value, int step) {
				EXTGWTSTLVToolbarMove.this.stlvDisplay.moveObject(0, step, 0);
			}
		};
		spinnerVertical = new EXTGWTSTLVSpinner(verticalMove, 0, -1000, 1000, 1, 5, true);
		VerticalPanel toolbarVPanel = new VerticalPanel();
		contentPanel.add(toolbarVPanel);
		toolbarVPanel.addStyleName("viewer-toolbarMoveVP");
		Image imageDown = spinnerVertical.getDecrementArrow();
		imageDown.addStyleName("arrow");
		imageDown.addStyleName("arrow-down");
		Image imageUp = spinnerVertical.getIncrementArrow();
		imageUp.addStyleName("arrow");
		imageUp.addStyleName("arrow-up");
		toolbarVPanel.add(imageUp);
		toolbarVPanel.setCellVerticalAlignment(imageUp, HasVerticalAlignment.ALIGN_TOP);
		toolbarVPanel.add(imageDown);
		toolbarVPanel.setCellVerticalAlignment(imageDown, HasVerticalAlignment.ALIGN_BOTTOM);
	}
}
