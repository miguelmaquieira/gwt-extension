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
			|	forEachStatement
			| 	execution;
assignStatement: VARIABLE_SCRIPT '=' (( (expression | stringExpr | listExp) ';') | execution);

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
			 
execution : 'execute' (dslamCommands | variable)+ ';';

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
			
dslamCommands : 	'cli'|'environment'|'configure'|'show'|'admin'|'equipment'
					|'interface'|'vlan'|'pppox-relay'|'software-mngt'
					|'slot'|'protection-group'|'protection-element'|'admin-status'
    				|'id'|'mode'|'name'|'protocol-filter'|'pppoe-relay-tag'|'circuit-id-pppoe'
    				|'remote-id-pppoe'|'customer-id'|'shub'|'port'|'egress-port'|'cross-connect'
    				|'engine'|'mac-addr-conc'|'unlock'|'database'|'save';

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
STRING_LITERAL:   '"' STRING_CHARACTERS? '"';

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
fragment	IDENT_CHAR 			: ('a'..'z'|'A'..'Z');
fragment	STRING_CHARACTERS	:   STRING_CHARACTER+;
fragment	STRING_CHARACTER	:   ~["\\];