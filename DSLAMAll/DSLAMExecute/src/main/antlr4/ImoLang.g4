grammar ImoLang;

@header {
	package com.imotion.antlr;
}

//PARSER
program :	statement+; 
statement   :	assignStatement
			|   ifStatement
			|	whileStatement
			|	forStatement
			|	forEachStatement
			| 	function;
			
assignStatement: VARIABLE_SCRIPT '=' (( (expression | stringExpr | listExp | function) ';'));

function : (execution | readUntil | match | rollback | tagBlockCode) ';';
		 
execution: 		'execute' 	stringExpr ; 
readUntil : 	'read'		stringExpr ;
match:  		'match'		stringExpr ;
rollback:		'rollback'	stringExpr ;
tagBlockCode:	'tag'		stringExpr ;

command : STRING_CHARACTERS;

ifStatement: 	'if' condition '{' ifBlock '}' ('else' '{' elseBlock '}')?;
ifBlock: statement+;
elseBlock: statement+;
whileStatement: 'while' condition '{' statement+ '}';
forStatement: 	'for' VARIABLE_SCRIPT 'in' '(' value '..' value ')' '{' statement+ '}';
forEachStatement: 	'foreach' '(' VARIABLE_SCRIPT ':' variable ')' '{' statement+ '}';

expression :	'(' expression ')'									#parExp
				| left=expression op=('*'|'/') right=expression		#aritOp
   				| left=expression op=('+'|'-') right=expression 	#aritOp
     			| atom=value												#atomExpr
     			;
     			
stringExpr : value ('.' value)*;

condition : '(' (value) (LOGICAL_COMPARATOR (value))? ')';

listExp: '{' value (',' value)* '}';

listItem: variable'['value']';

value: 			INTEGER
				| STRING_LITERAL
				| variable
				| listItem;

variable : 	VARIABLE_SCRIPT
			| VARIABLE_PROCESS
			|VARIABLE_EXTERNAL;
			
//LEXER
INTEGER: ('-')?(DIGIT)+;
VARIABLE_SCRIPT : 	'$' VARIABLE_NAME;
VARIABLE_PROCESS : 	'%' VARIABLE_NAME;
VARIABLE_EXTERNAL : '#' VARIABLE_NAME;
VARIABLE_NAME : IDENT_CHAR ('_'| IDENT_CHAR | DIGIT)* ;
LOGICAL_COMPARATOR :   '=='
					 | '!='
					 | '<'
					 | '<='
					 | '>'
					 | '>=';
STRING_LITERAL:   '"' STRING_CHARACTERS '"';

//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

fragment	DIGIT 				: '0'..'9' ;
fragment	IDENT_CHAR 			: ('a'..'z'|'A'..'Z' | '>');
fragment	STRING_CHARACTERS	:   STRING_CHARACTER+;
fragment	STRING_CHARACTER	:   ~["\\];