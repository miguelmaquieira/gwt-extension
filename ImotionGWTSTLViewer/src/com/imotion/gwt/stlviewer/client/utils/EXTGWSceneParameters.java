package com.imotion.gwt.stlviewer.client.utils;

public class EXTGWSceneParameters {

	private int	 cameraTargetSize;
	private double cameraVisionAngle;
	private double cameraPositionAngle;
	private int	 cameraPositionX;
	private int	 cameraPositionY;
	private int	 cameraPositionZ;
	private int	 lightPositionX;
	private int	 lightPositionY;
	private int	 lightPositionZ;
	
	public EXTGWSceneParameters(int objectHeight, int objectWidth) {
		int maxSize = Math.max(objectHeight, objectWidth);
		this.cameraTargetSize 		= 2 * maxSize;
		this.cameraVisionAngle		= 0.610865238; //35º
		this.cameraPositionAngle	= Math.PI / 6; //30º
		
		//SOLVE cameraPositionX  cameraPositionY  cameraPositionZ
		
		this.lightPositionX = 0;
		this.lightPositionY = cameraPositionY;
		this.lightPositionZ = cameraPositionZ;
		
	}

}
