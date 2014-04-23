package com.imotion.gwt.stlviewer.client.utils;

public class EXTGWTSTLVSceneParameters {
	
	public		static final int 		CAMERA_VISION_ANGLE_DEGREES		= 35;
	private 	static final int 		CAMERA_POSITION_ANGLE_DEGREES	= 33;
	private 	static final double 	CAMERA_MIN_HEIGHT_PROPORTION	= 1d/2d;

	private double	 cameraPositionX;
	private double	 cameraPositionY;
	private double	 cameraPositionZ;
	private double	 lightPositionX;
	private double	 lightPositionY;
	private double	 lightPositionZ;
	private double height;
	private double width;
	private double cameraLookAtYAddition;
	
	public EXTGWTSTLVSceneParameters(double objectHeight, double objectWidth) {
		double maxSize 		= Math.max(objectHeight, objectWidth);
		
		height = objectHeight;
		width	 = objectWidth;
		
		cameraLookAtYAddition = CAMERA_MIN_HEIGHT_PROPORTION * height;
		
		cameraPositionX = ((maxSize / Math.tan(getCameraVisionAngleInRadians() / 2)) / Math.sqrt(2));
		cameraPositionZ = cameraPositionX;
		cameraPositionY = (( (maxSize / Math.tan(getCameraVisionAngleInRadians() / 2)) *   Math.tan(getCameraPositionAngleInRadians()) ) + height / 2 );
		
		lightPositionX = 0;
		lightPositionY = cameraPositionY;
		lightPositionZ = cameraPositionZ;
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

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCameraLookAtYAddition() {
		return cameraLookAtYAddition;
	}
	
	/**
	 * PRIVATE
	 */
	
	private double getCameraVisionAngleInRadians() {
		return Math.toRadians(CAMERA_VISION_ANGLE_DEGREES);
	}

	private double getCameraPositionAngleInRadians() {
		return Math.toRadians(CAMERA_POSITION_ANGLE_DEGREES);
	}

}
