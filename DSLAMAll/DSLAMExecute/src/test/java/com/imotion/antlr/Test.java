package com.imotion.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.imotion.antlr.ImoLangLexer;



/**
 * Hello world!
 *
 */
public class Test {

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
	
	private static final String DSLAM_TEXT_2 = 	"$a = \"hola\";"
													+ "$b = \" que tal\";"
													+ "$c = $a . $b . \"!!\";"; 

	private static final String DSLAM_TEXT_3 = DSLAM_TEXT_2 + DSLAM_TEXT;
	
	private static final String DSLAM_LISTS	= 	"$a = {1,2,3,4};"
													+ "$b = {\"hola\", \" que\", \" tal\"};"
													+ "$c = 1; $d = 2; $e = 4;"
													+ "$listInt = {$c, $d, $e, 49};"
													+ "$stringA = \"Hola que ase\";"
													+ "$stringB = \" estoy muy bien\";"
													+ "$stringList = {$stringA, $stringB, \" Literal\"};"
													+ "$holaItem = $stringList[0];"
													+ "$estoyBien = $stringList[1];"
													+ "$index = 1;"
													+ "$estoyBien2 = $stringList[$index];";
	
	private static final String DSLAM_FULL = DSLAM_TEXT_2 + DSLAM_TEXT + DSLAM_LISTS;
	
	private static final String FOR_EACH_EXAMPLE = 	"$list = {\"¿ hola\", \" que\", \" tal\", \" estás\", \" ?\"};"
														+ "foreach ($item : $list) {"
														+ "	execute admin $item ;"
														+ "}";
	
	public static void main( String[] args ) {
		ANTLRInputStream is = new ANTLRInputStream("asdasd");
		//ImoLangLexer lexer = new ImoLangLexer("adasdsa");
//		DSLAMLexer lexer = new DSLAMLexer(new ANTLRInputStream(FOR_EACH_EXAMPLE));
//		CommonTokenStream tokens = new CommonTokenStream(lexer);
//		
//		DSLAMParser parser = new DSLAMParser(tokens);
//		ProgramContext tree = parser.program();
//		
//		CRONIOIConnection connection = new CRONIOConnectionDSLAM();
//		
//		Map<String, Object> variables = new HashMap<>();
//		DSLAMInterpreterVisitorImpl visitor = new DSLAMInterpreterVisitorImpl(connection, variables);
//		visitor.visit(tree);
	}
}
