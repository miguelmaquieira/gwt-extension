package com.imotion.dslam.antlr;

public interface CRONIOILangVisitor {

	public void pause() throws InterruptedException;
	
	public void next();
	
	public void stop();
	
	public void play();
	
}
