grammar ImoLangPrecompiler;

@header {
	package com.imotion.antlr;
}

//PARSER
program :	instruction+;
//
instruction: function;

//assignStatement: ~(function) INTRUCTION_END;

function : (execution | readUntil | match | rollback | tagBlockCode) (variable|noVariable)+ INTRUCTION_END;
		 
execution: 		'>' 	; 
readUntil : 	'read'	;
match:  		'match'	;
rollback:		'rb'	;
tagBlockCode:	'tag'	;

noVariable: ~(VARIABLE_SCRIPT| VARIABLE_PROCESS|VARIABLE_EXTERNAL);

variable : 	VARIABLE_SCRIPT
			| VARIABLE_PROCESS
			|VARIABLE_EXTERNAL;
			
			
//LEXER
INTRUCTION_END: ~('{'|'}') NEWLINE+;
VARIABLE_SCRIPT : 	'$' VARIABLE_NAME;
VARIABLE_PROCESS : 	'%' VARIABLE_NAME;
VARIABLE_EXTERNAL : '#' VARIABLE_NAME;
VARIABLE_NAME : IDENT_CHAR ('_'| IDENT_CHAR | DIGIT)* ;

fragment	DIGIT 		: '0'..'9' ;
fragment	IDENT_CHAR 	: ('a'..'z'|'A'..'Z');
fragment 	NEWLINE   	: '\r' '\n' | '\n' | '\r';
fragment	STRING_CHARACTERS	:   STRING_CHARACTER+;
fragment	STRING_CHARACTER	:   ~["\\];
