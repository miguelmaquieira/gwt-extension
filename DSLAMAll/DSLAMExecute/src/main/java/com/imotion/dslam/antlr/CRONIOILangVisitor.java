package com.imotion.dslam.antlr;

public interface CRONIOILangVisitor {

	public void pauseExecution() throws InterruptedException;
	
	public void nextInstruction();
	
	public void stopExecution();
	
	public void continueExecution();
	
}
