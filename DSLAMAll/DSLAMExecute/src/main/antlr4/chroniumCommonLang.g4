grammar chroniumCommonLang;

@header {
	package com.imotion.antlr.chronium;
}

expression :	'(' expression ')'									#parExp
				| left=expression op=('*'|'/') right=expression		#aritOp
   				| left=expression op=('+'|'-') right=expression 	#aritOp
     			| atom=value										#atomExpr
     			;
     			
value: 			INTEGER
				| STRING_LITERAL;


//LEXER
INTEGER: 			('-')?(DIGIT)+;
STRING_LITERAL:   	'"' STRING_CHARACTERS '"';

VARIABLE_SCRIPT: 	 '$' VARIABLE_NAME;
VARIABLE_PROCESS: 	 '#' VARIABLE_NAME;
VARIABLE_EXECUTION:  '@' VARIABLE_NAME;
VARIABLE_NAME: 		IDENT_CHAR ('_'| IDENT_CHAR | DIGIT)* ;


fragment DIGIT: 			('0'..'9')+ ;
fragment IDENT_CHAR: 		('a'..'z'|'A'..'Z');
fragment STRING_CHARACTERS: STRING_CHARACTER+;
fragment STRING_CHARACTER:
   	 		 				~["\\\r\n]
 							| '\\' EscapeSequence;
 							
fragment EscapeSequence: 
 							CharacterEscapeSequence
 							| '0' // no digit ahead! TODO
 							| HexEscapeSequence
 							| UnicodeEscapeSequence
 							;
 							
fragment HexEscapeSequence: 
 							'x' HexDigit HexDigit
 							;

fragment UnicodeEscapeSequence: 
							'u' HexDigit HexDigit HexDigit HexDigit
							| 'U' HexDigit HexDigit HexDigit HexDigit HexDigit HexDigit
							;
 							
fragment CharacterEscapeSequence: 
							SingleEscapeCharacter
 							| NonEscapeCharacter
 							;
 							
fragment NonEscapeCharacter: 
 							~['"\\bfnrtv0-9xu\r\n]
							;
							
fragment HexDigit: [0-9a-fA-F];
 							
fragment SingleEscapeCharacter: 
							['"\\nrtbfv]
							;
