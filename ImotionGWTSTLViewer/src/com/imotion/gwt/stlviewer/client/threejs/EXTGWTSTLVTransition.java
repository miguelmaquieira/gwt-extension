package com.imotion.gwt.stlviewer.client.threejs;

public class EXTGWTSTLVTransition {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int iteration;
	
	public EXTGWTSTLVTransition(int x, int y) {
		init(x, y);
	}
	
	public void update(int x, int y) {
		this.x1 = this.x2;
		this.y1 = this.y2;
		this.x2 = x;
		this.y2 = y;
		this.iteration++;
	}
	
	public void init(int x, int y) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
		this.iteration = 0;
	}
	
	public int getXTransition() {
		return x2 - x1;
	}
	
	public int getYTransition() {
		return y2 - y1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Iteraction: ").append(iteration).append("\n");
		sb.append("(X1,Y1): (").append(x1).append(",").append(y1).append(")\n");
		sb.append("(X2,Y2): (").append(x2).append(",").append(y2).append(")");
		return sb.toString();
	}
}
