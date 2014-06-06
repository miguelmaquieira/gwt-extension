grammar DSLAM;

@header {
	package com.imotion.antlr;
}


//PARSER
program :	statement+;
statement   :	assignStatement
			|   ifStatement
			|	whileStatement
			|	forStatement ;
//			| exec;
assignStatement: VARIABLE_SCRIPT '=' expression  ';';

ifStatement: 	'if' condition '{' statement+ '}' ('else' '{' statement+ '}')?;
whileStatement: 'while' condition '{' statement+ '}';
forStatement: 	'for' VARIABLE_SCRIPT 'in' '(' (INTEGER|VARIABLE_SCRIPT|VARIABLE_PROCESS|VARIABLE_EXTERNAL) '..' (INTEGER|VARIABLE_SCRIPT|VARIABLE_PROCESS|VARIABLE_EXTERNAL) ')' '{' statement+ '}';

expression : INTEGER							  #atom
			 | VARIABLE_SCRIPT					  #atom
			 | expression ('*' | '/') expression  #aritOp
			 | expression ('+' | '-') expression  #aritOp
			 ;
//exec : 'execute' COMMAND_ITEM+ ';';
condition : '(' (INTEGER|variable) (LOGICAL_COMPARATOR (INTEGER|variable))? ')';

variable : 	VARIABLE_SCRIPT
			| VARIABLE_PROCESS
			|VARIABLE_EXTERNAL;

//LEXER
INTEGER: (DIGIT)+;
WS  :  [ \t\r\n\u000C]+ -> skip
    ;
VARIABLE_SCRIPT : 	'$' VARIABLE_NAME;
VARIABLE_PROCESS : 	'%' VARIABLE_NAME;
VARIABLE_EXTERNAL : '#' VARIABLE_NAME;
VARIABLE_NAME : IDENT_CHAR ('_'| IDENT_CHAR)+ ;
COMMAND_ITEM	: (IDENT_CHAR)(IDENT_CHAR | ':' | '_' |'-' | '/')+;
LOGICAL_COMPARATOR :   '=='
					 | '!='
					 | '<'
					 | '<='
					 | '>'
					 | '>=';
STRING_LITERAL:   '"' STRING_CHARACTERS? '"';

fragment	DIGIT 				: '0'..'9' ;
fragment	IDENT_CHAR 			: ('a'..'z'|'A'..'Z');
fragment	STRING_CHARACTERS	:   STRING_CHARACTER+;
fragment	STRING_CHARACTER	:   ~["\\];