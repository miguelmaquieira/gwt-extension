package com.imotion.dslam.antlr;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class DSLAMAST extends CommonTree {

	public Symbol symbol;
	public String expType = "";
	public String expSecType = "";
	
	public DSLAMAST(Token t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

}
