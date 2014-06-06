grammar DSLAM;

@header {
	package com.imotion.antlr;
}

//PARSER
program :	statement+;
statement   :	assignStatement
			|   ifStatement
			|	whileStatement
			|	forStatement
			| 	execution;
assignStatement: VARIABLE_SCRIPT '=' (expression | execution)  ';';

ifStatement: 	'if' condition '{' statement+ '}' ('else' '{' statement+ '}')?;
whileStatement: 'while' condition '{' statement+ '}';
forStatement: 	'for' VARIABLE_SCRIPT 'in' '(' integerValue '..' integerValue ')' '{' statement+ '}';

expression :	'(' expression ')'								#parentExp
				| left=expression op=('*'|'/') right=expression #aritOp
   				| left=expression op=('+'|'-') right=expression #aritOp
     			| atom=integerValue								#atomExpr
     			;
			 
execution : 'execute' ('a' | 'b')+ ;

condition : '(' (integerValue) (LOGICAL_COMPARATOR (integerValue))? ')';

integerValue: 	INTEGER
				|	variable;

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
VARIABLE_NAME : IDENT_CHAR ('_'| IDENT_CHAR)* ;
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