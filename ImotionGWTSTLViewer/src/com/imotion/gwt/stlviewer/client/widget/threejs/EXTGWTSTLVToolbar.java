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
	
	
	public EXTGWTSTLVToolbar(EXTGWTSTLILoaderDisplay stlvDisplay) {
		this.stlvDisplay = stlvDisplay;
		
		HorizontalPanel hrContentPanel = new HorizontalPanel();
		initWidget(hrContentPanel);
		hrContentPanel.addStyleName("viewer-toolbar");
		
		// Zoom in
		zoom_in = new Image();
		zoom_in.setUrl("images/empty_background.png");
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
		zoom_out.setUrl("images/empty_background.png");
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
		ground.setUrl("images/empty_background.png");
		ground.addStyleName("action");		
		ground.addStyleName("ground_visibility_off");
		hrContentPanel.add(ground);
		ground.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean groundVisible = EXTGWTSTLVToolbar.this.stlvDisplay.isGroundVisible();
				EXTGWTSTLVToolbar.this.stlvDisplay.groundVisibility(!groundVisible);
				groundDisplayStyle(!groundVisible);
			}
		});
	
		// Gyre
		gyre = new Image();
		gyre.setUrl("images/empty_background.png");
		gyre.addStyleName("action");
		gyre.addStyleName("gyre_on");
		hrContentPanel.add(gyre);
		gyre.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean captureEvents = EXTGWTSTLVToolbar.this.stlvDisplay.isRotationMouseEvents();
				captureEvents = !captureEvents;
				EXTGWTSTLVToolbar.this.stlvDisplay.captureRotationMouseEvents(captureEvents);
				gyreDisplayStyle(captureEvents);
				
				// Ground display
				EXTGWTSTLVToolbar.this.stlvDisplay.groundVisibility(!captureEvents);
				groundDisplayStyle(!captureEvents);
			}
		});
	}
	
	private void groundDisplayStyle(boolean groundVisible) {
		if (groundVisible) {
			ground.addStyleName("ground_visibility_off");
			ground.removeStyleName("ground_visibility_on");			
		} else {
			ground.addStyleName("ground_visibility_on");
			ground.removeStyleName("ground_visibility_off");
		}
	}
	
	private void gyreDisplayStyle(boolean gyreAction) {
		if (gyreAction) {
			gyre.addStyleName("gyre_off");
			gyre.removeStyleName("gyre_on");
		} else {
			gyre.addStyleName("gyre_on");
			gyre.removeStyleName("gyre_off");
		}
	}
}
