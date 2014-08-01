package com.imotion.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import com.imotion.antlr.ImoLangParser.ProgramContext;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorImpl;
import com.imotion.dslam.antlr.CRONIOInterpreterVisitorValue;
import com.imotion.dslam.conn.CRONIOConnectionImpl;
import com.imotion.dslam.conn.CRONIOIConnection;

import static org.junit.Assert.*;

public class ImoLanLexerTest {
	
	@Test
	public void variableScript() {
		// Asing
		String varScript = "$a = 1;";
		
		// Action
		ImoLangLexer lexer = new ImoLangLexer(new ANTLRInputStream(varScript));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ImoLangParser parser = new ImoLangParser(tokens);
		ProgramContext tree = parser.program();
		
		String lexerText = lexer.getText();
		CRONIOIConnection connection = new CRONIOConnectionImpl(0, null, null);
		
		//ImoLangVisitor<CRONIOInterpreterVisitorValue> interpreter = new CRONIOInterpreterVisitorImpl(connection, variables, rollbackConditionRegEx, rollbackVisitor, rollbackTree)
////		
//		Map<String, Object> variables = new HashMap<>();
//		DSLAMInterpreterVisitorImpl visitor = new DSLAMInterpreterVisitorImpl(connection, variables);
//		visitor.visit(tree);

		// Assert
		assertEquals(varScript, lexerText);
		
	}

}
