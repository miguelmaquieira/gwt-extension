package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.core.Color;

public class EXTGWTSTLVColor extends Color {

	protected EXTGWTSTLVColor() {
		super();
	}
	
	public final native void setHSL(double h,double s,double l)/*-{
		this.setHSL(h,s,l);
	}-*/;

}
