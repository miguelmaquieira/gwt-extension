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
	
	private boolean mouseRotationInteraction;
	private boolean mouseMoveInteraction;
	private boolean mouseDragAction;
	
	private int sceneWidth;
	private int sceneHeight;
	
	
	public EXTGWTSTLVMouseEventHandler(EXTGWTSTLILoaderDisplay renderer, boolean mouseInteraction, int sceneWidth, int sceneHeight) {
		this.stlvDisplay = renderer;
		this.mouseRotationInteraction = mouseInteraction;
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;
		mouseTransition = new EXTGWTSTLVTransition(0, 0);
	}

	@Override
	public void onMouseWheel(MouseWheelEvent event) {
		if (mouseRotationInteraction) {
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
		if (mouseRotationInteraction) {
			if (mouseDragAction) {
				mouseTransition.update(event.getX(), event.getY());
				int xTransition = mouseTransition.getXTransition();
				int yTransition = mouseTransition.getYTransition();
				
				// zGyre
				stlvDisplay.zGyreSpeed((float)xTransition / (sceneWidth * 10));
				
				// x,y, Gyre
				stlvDisplay.xGyreSpeed((float)yTransition / (sceneHeight * 10));
				stlvDisplay.yGyreSpeed((float)yTransition / (sceneHeight * 10));
				
				System.out.println(mouseTransition.toString());
				System.out.println("Transition X: " + xTransition);
				System.out.println("Transition Y: " + yTransition + "\n");
			}
		} else if (mouseMoveInteraction) {
			if (mouseDragAction) {
				mouseTransition.update(event.getX(), event.getY());
				int xTransition = mouseTransition.getXTransition();
				int yTransition = mouseTransition.getYTransition();
				stlvDisplay.moveObject(xTransition, -yTransition, xTransition);
			}
		}
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (mouseRotationInteraction || mouseMoveInteraction) {
			mouseDragAction = true;
			mouseTransition.init(event.getX(), event.getY());
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		if (mouseRotationInteraction || mouseMoveInteraction) {
			mouseDragAction = false;
			initGyreSpeed();
		}
	}
	
	public void captureRotationMouseEvents(boolean mouseInteraction) {
		this.mouseRotationInteraction = mouseInteraction;
		if (mouseInteraction) {
			initGyreSpeed();
			if (mouseTransition == null) {
				mouseTransition = new EXTGWTSTLVTransition(0, 0);
			}
		}
	}
	
	public void captureMoveMouseEvents(boolean mouseInteraction) {
		this.mouseMoveInteraction = mouseInteraction;
		if (mouseInteraction) {
			if (mouseTransition == null) {
				mouseTransition = new EXTGWTSTLVTransition(0, 0);
			}
		}
	}
	
	public boolean isRotationMouseEvents() {
		return this.mouseRotationInteraction;
	}
	
	public boolean isMoveMouseEvents() {
		return this.mouseMoveInteraction;
	}
	
	
	private void initGyreSpeed() {
		stlvDisplay.setGyreZSpeed(0.0);
		stlvDisplay.setGyreXSpeed(0.0);
		stlvDisplay.setGyreYSpeed(0.0);
	}

}
