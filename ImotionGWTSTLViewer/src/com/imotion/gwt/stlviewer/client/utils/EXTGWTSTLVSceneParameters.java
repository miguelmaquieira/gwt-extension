package com.imotion.gwt.stlviewer.client.utils;

public class EXTGWTSTLVSceneParameters {
	
	private static final double DESIRED_SIZE					= 200d;
	private static final double CAMERA_VISION_ANGLE			= 0.610865238; //35 º
	private static final double CAMERA_POSITION_ANGLE		= Math.PI / 6; //30 º
	private static final double CAMERA_LOOK_AT_Y_PROPORTION	= 3/4;

	private double	 cameraPositionX;
	private double	 cameraPositionY;
	private double	 cameraPositionZ;
	private double	 lightPositionX;
	private double	 lightPositionY;
	private double	 lightPositionZ;
	private double	 scale;
	private double heightScaled;
	private double widthScaled;
	private double cameraLookAtYAddition;
	
	public EXTGWTSTLVSceneParameters(double objectHeight, double objectWidth) {
		double maxSize 		= Math.max(objectHeight, objectWidth);
		
		scale =  1 / (DESIRED_SIZE / maxSize);
		
		heightScaled = objectHeight / scale;
		widthScaled	 = objectWidth / scale;
		
		cameraLookAtYAddition = CAMERA_LOOK_AT_Y_PROPORTION * heightScaled;
		
		cameraPositionX = ((DESIRED_SIZE / Math.tan(CAMERA_VISION_ANGLE / 2)) / Math.sqrt(2));
		cameraPositionZ = cameraPositionX;
		cameraPositionY = (( (DESIRED_SIZE / Math.tan(CAMERA_VISION_ANGLE / 2)) *   Math.tan(CAMERA_POSITION_ANGLE) ) + heightScaled / 2 );
		
		lightPositionX = 0;
		lightPositionY = cameraPositionY;
		lightPositionZ = cameraPositionZ;
	}

	public double getCameraVisionAngle() {
		return CAMERA_VISION_ANGLE;
	}

	public double getCameraPositionAngle() {
		return CAMERA_POSITION_ANGLE;
	}

	public double getCameraPositionX() {
		return cameraPositionX;
	}

	public double getCameraPositionY() {
		return cameraPositionY;
	}

	public double getCameraPositionZ() {
		return cameraPositionZ;
	}

	public double getLightPositionX() {
		return lightPositionX;
	}

	public double getLightPositionY() {
		return lightPositionY;
	}

	public double getLightPositionZ() {
		return lightPositionZ;
	}

	public double getScale() {
		return scale;
	}

	public double getHeightScaled() {
		return heightScaled;
	}

	public double getWidthScaled() {
		return widthScaled;
	}

	public double getCameraLookAtYAddition() {
		return cameraLookAtYAddition;
	}

}
