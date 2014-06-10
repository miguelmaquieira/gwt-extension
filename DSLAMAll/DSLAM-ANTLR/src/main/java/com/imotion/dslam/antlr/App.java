package com.imotion.dslam.antlr;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.imotion.antlr.DSLAMLexer;
import com.imotion.antlr.DSLAMParser;
import com.imotion.antlr.DSLAMParser.ProgramContext;


/**
 * Hello world!
 *
 */
public class App {

	private static final String DSLAM_TEXT = 
												  "$a = 1;"
												+ "$b = 2;"
												+ "if ($a < $b) {"
												+ "	$a = $a + $b;"
												+ "} else {"
												+ " $a = 0;"
												+ "	$b = 9;"
												+ "}"
												+ "$p = 0;"
												+ "for $j in (1..3) {"
												+ "	$p = 2 * ($j + $j);"
												+ "}"
												+ "$k = 1;"
												+ "while ($k <= 3) {"
												+ "	$k = $k + 1;"
												+ "}"
												+ "execute configure admin $k;";

	public static void main( String[] args ) {
		DSLAMLexer lexer = new DSLAMLexer(new ANTLRInputStream(DSLAM_TEXT));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		DSLAMParser parser = new DSLAMParser(tokens);
		ProgramContext tree = parser.program();
		
		DSLAMIConnection connection = new DSLAMConnectionImpl();
		
		Map<String, Object> variables = new HashMap<>();
		DSLAMVisitorImpl visitor = new DSLAMVisitorImpl(connection, variables);
		visitor.visit(tree);
	}
}
