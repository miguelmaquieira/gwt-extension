grammar DSLAM;

@header {
	package com.imotion.antlr;
}


//PARSER
program :	block+;
block   :	assign
			| exec;
assign: VARIABLE_SCRIPT '=' expression  ';';
expression : INTEGER							  #atom
			 | VARIABLE_SCRIPT					  #atom
			 | expression ('*' | '/') expression  #aritOp
			 | expression ('+' | '-') expression  #aritOp
			 ;
exec : 'execute' (StringCharacters (VARIABLE_SCRIPT)*)+ ';';

//LEXER
INTEGER: (DIGIT)+;
WS  :  [ \t\r\n\u000C]+ -> skip
    ;
VARIABLE_NAME : IDENT_CHAR ('_'| IDENT_CHAR)+ ;
VARIABLE_SCRIPT : '$' VARIABLE_NAME;

fragment	DIGIT 				: '0'..'9' ;
fragment	IDENT_CHAR 			: ('a'..'z'|'A'..'Z');
fragment	StringCharacter		:   [\u0000-\uFFFF];
fragment	StringCharacters	:   StringCharacter+;

StringLiteral:   '"' StringCharacters? '"';