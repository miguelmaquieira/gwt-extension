package com.imotion.gwt.stlviewer.client.widget.threejs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;

public class EXTGWTSTLVToolbar extends Composite {
	
	private EXTGWTSTLILoaderDisplay stlvDisplay;
	
	private Image zoom_in;
	private Image zoom_out;
	private Image ground;
	private Image gyre;
	private Image move;
	
	
	public EXTGWTSTLVToolbar(EXTGWTSTLILoaderDisplay stlvDisplay) {
		this.stlvDisplay = stlvDisplay;
		
		HorizontalPanel hrContentPanel = new HorizontalPanel();
		initWidget(hrContentPanel);
		hrContentPanel.addStyleName("viewer-toolbar");
		
		// Zoom in
		zoom_in = new Image();
		zoom_in.addStyleName("action");
		zoom_in.addStyleName("zoom_in_action");
		hrContentPanel.add(zoom_in);
		
		zoom_in.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EXTGWTSTLVToolbar.this.stlvDisplay.zoomIn();
			}
		});
		
		// Zoom out
		zoom_out = new Image();
		zoom_out.addStyleName("action");
		zoom_out.addStyleName("zoom_out_action");
		hrContentPanel.add(zoom_out);
		
		zoom_out.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EXTGWTSTLVToolbar.this.stlvDisplay.zoomOut();
			}
		});
		
		// Ground visibility
		ground = new Image();
		ground.addStyleName("action");
		ground.addStyleName("ground_visibility_on");
		hrContentPanel.add(ground);
		ground.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean groundVisible = EXTGWTSTLVToolbar.this.stlvDisplay.isGroundVisible();
				EXTGWTSTLVToolbar.this.stlvDisplay.groundVisibility(!groundVisible);
				if (groundVisible) {
					ground.addStyleName("ground_visibility_off");
					ground.removeStyleName("ground_visibility_on");
				} else {
					ground.addStyleName("ground_visibility_on");
					ground.removeStyleName("ground_visibility_off");
				}
			}
		});
		
		// Gyre
		gyre = new Image();
		gyre.addStyleName("action");
		gyre.addStyleName("gyre_on");
		hrContentPanel.add(gyre);
		gyre.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean captureEvents = EXTGWTSTLVToolbar.this.stlvDisplay.isRotationMouseEvents();
				EXTGWTSTLVToolbar.this.stlvDisplay.captureRotationMouseEvents(!captureEvents);
				if (captureEvents) {
					gyre.addStyleName("gyre_on");
					gyre.removeStyleName("gyre_off");
				} else {
					gyre.addStyleName("gyre_off");
					gyre.removeStyleName("gyre_on");
				}
			}
		});
		
		// Move
		move = new Image();
		move.addStyleName("action");
		move.addStyleName("move_on");
		hrContentPanel.add(move);
		move.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean captureEvents = EXTGWTSTLVToolbar.this.stlvDisplay.isMoveMouseEvents();
				EXTGWTSTLVToolbar.this.stlvDisplay.captureMoveMouseEvents(!captureEvents);
				if (captureEvents) {
					move.addStyleName("move_on");
					move.removeStyleName("move_off");
				} else {
					move.addStyleName("move_off");
					move.removeStyleName("move_on");
				}
			}
		});
	}
}
