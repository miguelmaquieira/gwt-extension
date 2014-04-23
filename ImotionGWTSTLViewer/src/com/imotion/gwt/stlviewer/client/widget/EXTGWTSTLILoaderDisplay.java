package com.imotion.gwt.stlviewer.client.widget;

import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.user.client.ui.IsWidget;

public interface EXTGWTSTLILoaderDisplay extends AnimationCallback, IsWidget {
	
	double DEFAULT_ZOOM_PCTG 	= 10;
	double DEFAULT_GYRE_SPEED 	= 0.05d;

	void loadModel(String url);

	double increaseGyreSpeed(double radiansPerIteration);

	double decreaseGyreSpeed(double radiansPerIteration);

	void zoomIn();

	void zoomOut();

	void setZoomPercentage(double zoomPercentage);

	void setGyreSpeed(double radiansPerIteration);

}
