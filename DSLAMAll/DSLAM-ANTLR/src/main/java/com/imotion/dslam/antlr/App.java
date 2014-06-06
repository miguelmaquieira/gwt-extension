package com.imotion.dslam.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import com.imotion.antlr.DSLAMLexer;
import com.imotion.antlr.DSLAMParser;


/**
 * Hello world!
 *
 */
public class App {

	private static final String DSLAM_TEXT = 
												  "$a = 2845;"
												+ "$b = 3000;"
												+ "if ($a < #a) {"
												+ "	$c = 5 + $a;"
												+ "} else {"
												+ " $c = 0;"
												+ "	$b = 9;"
												+ "}"
												+ "for $j in (1..3) {"
												+ "	$p = 3 * ($j + 4);"
												+ "}"
												+ "$k = 1;"
												+ "while ($k < 3) {"
												+ "	$k = $k + 1;"
												+ "}";

	public static void main( String[] args ) {
		DSLAMLexer lexer = new DSLAMLexer(new ANTLRInputStream(DSLAM_TEXT));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DSLAMParser parser = new DSLAMParser(tokens);

		ParserRuleContext tree = parser.program(); // parse

		System.out.println(tree.toStringTree(parser));
		
//		Visitor visitor = new Visitor();
//		List<String> commands = visitor.visit(tree);
//		System.out.println(commands.toString());
	}
}
