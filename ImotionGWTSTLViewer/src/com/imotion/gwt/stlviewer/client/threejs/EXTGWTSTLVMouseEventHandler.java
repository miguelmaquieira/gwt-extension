package com.imotion.gwt.stlviewer.client.threejs;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.imotion.gwt.stlviewer.client.widget.EXTGWTSTLILoaderDisplay;

public class EXTGWTSTLVMouseEventHandler implements MouseWheelHandler, MouseUpHandler, MouseDownHandler, MouseMoveHandler {
	
	private EXTGWTSTLILoaderDisplay stlvDisplay;
	private EXTGWTSTLVTransition	mouseTransition;
	
	private boolean mouseInteraction; 	
	private boolean mouseDragAction;
	
	private int sceneWidth;
	private int sceneHeight;
	
	
	public EXTGWTSTLVMouseEventHandler(EXTGWTSTLILoaderDisplay renderer, boolean mouseInteraction, int sceneWidth, int sceneHeight) {
		this.stlvDisplay = renderer;
		this.mouseInteraction = mouseInteraction;
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
	}

	@Override
	public void onMouseWheel(MouseWheelEvent event) {
		if (mouseInteraction) {
			if (event.isNorth()) {
				stlvDisplay.zoomIn();
			} else if (event.isSouth()) {
				stlvDisplay.zoomOut();
			}
			event.preventDefault();
		}
		
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (mouseInteraction) {
			if (mouseDragAction) {
				mouseTransition.update(event.getX(), event.getY());
				int xTransition = mouseTransition.getXTransition();
				int yTransition = mouseTransition.getYTransition();
				if (xTransition > 0) {
					stlvDisplay.increaseZGyreSpeed((float)xTransition / (sceneWidth * 10));
				} else {
					stlvDisplay.decreaseZGyreSpeed((float)(-xTransition) / (sceneWidth * 10)) ;
				}
				
				if (yTransition > 0) {
					stlvDisplay.increaseXGyreSpeed((float)yTransition / (sceneHeight * 10));
					stlvDisplay.increaseYGyreSpeed((float)yTransition / (sceneHeight * 10));
				} else {
					stlvDisplay.decreaseXGyreSpeed((float)(-yTransition) / (sceneHeight * 10)) ;
					stlvDisplay.decreaseYGyreSpeed((float)(-yTransition) / (sceneHeight * 10)) ;
				}
				System.out.println(mouseTransition.toString());
				System.out.println("Transition X: " + xTransition);
				System.out.println("Transition Y: " + yTransition + "\n");
			}
		}
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		mouseDragAction = true;
		mouseTransition.init(event.getX(), event.getY());
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		mouseDragAction = false;
		stlvDisplay.setGyreZSpeed(0.0);
		stlvDisplay.setGyreXSpeed(0.0);
		stlvDisplay.setGyreYSpeed(0.0);
		
	}
	
	public void captureMouseEvents(boolean mouseInteraction) {
		this.mouseInteraction = mouseInteraction;
		if (mouseInteraction) {
			stlvDisplay.setGyreZSpeed(0.0);
			if (mouseTransition == null) {
				mouseTransition = new EXTGWTSTLVTransition(0, 0);
			}
		}
	}

}
