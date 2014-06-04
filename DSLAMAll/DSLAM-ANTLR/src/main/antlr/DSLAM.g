grammar DSLAM;

options {
	output=AST;
	ASTLabelType=DSLAMAST;
	language=Java;
}

tokens {
    PROGRAMA;
	DECLARACION;
	DECLARACIONPARAM;
	LISTADECLARACIONESAPI;
	DECLARACIONAPI;
	ASIGNACION;
	MENOSUNARIO;
	LISTAINSTRUCCIONES;
	LLAMADA;
	LISTAEXPRESIONES;
	LISTAPARAMETROS;
}

//*************************************************************
// LEXER
//*************************************************************

@lexer::header {
package com.imotion.antlr;
}

fragment LETRA : 'a'..'z'|'A'..'Z' ;
fragment DIGITO : '0'..'9' ;

LIT_ENTERO : DIGITO+ ;

LIT_REAL : LIT_ENTERO '.' LIT_ENTERO ;

LIT_CADENA : '"' (~('"'|'\n'|'\r'|'\t'))* '"' ;

LIT_LOGICO : 'true'|'false' ;

IDENT : (LETRA|'_')(LETRA|DIGITO|'_')* ;

COMENTARIO : '//' (~('\n'|'\r'))* '\r'? '\n' {$channel=HIDDEN;};

WS : (' '|'\r'|'\n'|'\t')+ {$channel=HIDDEN;} ;

/*************************************************************
// PARSER
//*************************************************************

@parser::header {
package  com.imotion.antlr;
}