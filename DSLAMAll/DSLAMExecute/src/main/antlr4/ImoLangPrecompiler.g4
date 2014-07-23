grammar ImoLangPrecompiler;

@header {
	package com.imotion.antlr;
}

//PARSER
program :	line+;
//
line: .  ; 
			
//LEXER
//space: ('-')?(DIGIT)+;
