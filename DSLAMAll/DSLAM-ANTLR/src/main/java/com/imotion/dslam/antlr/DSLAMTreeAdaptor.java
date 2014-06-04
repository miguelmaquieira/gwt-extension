package com.imotion.dslam.antlr;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class DSLAMTreeAdaptor extends CommonTreeAdaptor {
	
	@Override
	public Object create(Token payload) {
		return new DSLAMAST(payload);
	}

}
