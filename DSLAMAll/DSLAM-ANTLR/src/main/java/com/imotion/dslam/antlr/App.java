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

	private static final String DSLAM_TEXT = "$ID = 2845;"
												+ "$KEY = 3000;"
												+ "execute lalal";

	public static void main( String[] args ) {
		DSLAMLexer lexer = new DSLAMLexer(new ANTLRInputStream(DSLAM_TEXT));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DSLAMParser parser = new DSLAMParser(tokens);

		ParserRuleContext tree = parser.program(); // parse

		Visitor visitor = new Visitor();
//		List<String> commands = visitor.visit(tree);
//		System.out.println(commands.toString());
	}
}
