package com.imotion.dslam.antlr.executor;

import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.imotion.antlr.DSLAMLexer;
import com.imotion.antlr.DSLAMParser;
import com.imotion.antlr.DSLAMParser.ProgramContext;
import com.imotion.dslam.antlr.DSLAMInterpreterVisitorImpl;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProject;
import com.imotion.dslam.conn.CRONIOConnectionFactory;
import com.imotion.dslam.conn.CRONIOIConnection;

public class CRONIOExecutorDSLAM extends CRONIOExecutorBase {

	public CRONIOExecutorDSLAM(DSLAMBOIProject project) throws Exception {
		super(project);
	}
	
	@Override
	protected void executeInNode(long processId, CRONIOBOINode node, String scriptCode, Map<String, Object> allVariables) {
		DSLAMLexer			lexer	= new DSLAMLexer(new ANTLRInputStream(scriptCode));
		CommonTokenStream	tokens	= new CommonTokenStream(lexer);
		
		DSLAMParser 	parser 	= new DSLAMParser(tokens);
		ProgramContext 	tree 	= parser.program();
		
		CRONIOIConnection 			connection 	= CRONIOConnectionFactory.getConnection(processId, node, getLogger());
		DSLAMInterpreterVisitorImpl visitor 	= new DSLAMInterpreterVisitorImpl(connection, allVariables);
		visitor.visit(tree);
		CRONIOConnectionFactory.releaseConnection(connection);
	}

}
