package com.imotion.gwt.stlviewer.client.widget;

import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.imotion.gwt.stlviewer.client.exception.EXTGWTSTLExceptionCallback;

public interface EXTGWTSTLILoaderDisplay extends AnimationCallback, IsWidget {
	
	double DEFAULT_ZOOM_PCTG 	= 10;
	double DEFAULT_GYRE_SPEED 	= 0.05d;

	void loadModel(String url, EXTGWTSTLExceptionCallback exceptionCallback);
	void loadModel(String url, boolean startAnimation, EXTGWTSTLExceptionCallback exceptionCallback);

	double increaseZGyreSpeed(double radiansPerIteration);
	double decreaseZGyreSpeed(double radiansPerIteration);
	double increaseXGyreSpeed(double radiansPerIteration);
	double decreaseXGyreSpeed(double radiansPerIteration);
	double increaseYGyreSpeed(double radiansPerIteration);
	double decreaseYGyreSpeed(double radiansPerIteration);
	
	void setGyreZSpeed(double radiansPerIteration);
	void setGyreXSpeed(double radiansPerIteration);
	void setGyreYSpeed(double radiansPerIteration);

	void zoomIn();
	void zoomOut();
	void setZoomPercentage(double zoomPercentage);
	
	void captureRotationMouseEvents(boolean mouseRotationFlag);
	boolean isRotationMouseEvents();
	
	void captureMoveMouseEvents(boolean mouseMoveFlag);
	boolean isMoveMouseEvents();
	
	void moveObject(MouseMoveEvent mouseMoveEvent);
	
	void groundVisibility(boolean visibility);
	boolean isGroundVisible();

}
