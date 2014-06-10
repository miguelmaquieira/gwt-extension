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
assignStatement: VARIABLE_SCRIPT '=' (( (expression | STRING_LITERAL) ';') | execution);

ifStatement: 	'if' condition '{' ifBlock '}' ('else' '{' elseBlock '}')?;
ifBlock: statement+;
elseBlock: statement+;
whileStatement: 'while' condition '{' statement+ '}';
forStatement: 	'for' VARIABLE_SCRIPT 'in' '(' integerValue '..' integerValue ')' '{' statement+ '}';

expression :	'(' expression ')'								#parExp
				| left=expression op=('*'|'/') right=expression #aritOp
   				| left=expression op=('+'|'-') right=expression #aritOp
     			| atom=integerValue								#atomExpr
     			;
			 
execution : 'execute' (dslamCommands | variable)+ ';';

condition : '(' (integerValue) (LOGICAL_COMPARATOR (integerValue))? ')';

integerValue: 	INTEGER
				|	variable;

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