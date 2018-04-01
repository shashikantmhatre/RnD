grammar isoalgorithmimport;

/*
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

options {
  language = Java;
  output = AST;
  ASTLabelType = CommonTree;
}

@header {
  package com.mmpnc.rating.iso.algorithm.dataimport;
}

@lexer::header {
  package com.mmpnc.rating.iso.algorithm.dataimport;
}

block : (program | statement | classnode);

classnode : 'CLASS'^ '{'! keyvalues childnode* '}'! ;

childnode : ( rating | err | stat | formAttachment | classnode );

rating : 'RATING'^ '{'! keyvalues '}'!;

err : 'ERROR'^ '{'! keyvalues '}'!;

stat : 'STAT'^ '{'! keyvalues '}'!;

formAttachment : 'FORMATTACHMENT'^ '{'! keyvalues '}'!;

statement : ( assign | iif | loop );

program: 'program'^ '{'! keyvalues  statement* '}'! ;

iif : 'IF'^ '{'! condition then eelse? '}'! ;

condition : 'CONDITION'^ '{'! expression '}'!;

then : 'THEN'^ '{'! statement* '}'!;

eelse : 'ELSE'^ '{'! statement* '}'!;

loop : 'LOOP'^ '{'! keyvalue statement* '}'!;

assign : 'ASSIGN'^ '{'! keyvalues expression '}'!;

bracket : 'BRACKET'^ '{'! expression '}'!;

expression : 'EXPRESSION'^ '{'! keyvalues ( expression | bracket | ratetable | function )* '}'!;

ratetable : 'RATETABLE'^ '{'! keyvalue arg* '}'!;

function : 'FUNCTION'^ '{'! keyvalue arg* '}'!;

arg : 'ARG'^ '{'! keyvalues '}'!;

keyvalues : keyvalue (','! keyvalue )*;

keyvalue : (IDENTS^ ':'! STRING);

IDENTS : ('a'..'z' | 'A'..'Z' | '_' | '/' | '.')('a'..'z' | 'A'..'Z' | '_' | '0'..'9' | '/' | '.' | '*' )*;
INTEGER : ('0'..'9')+('.' ('0'..'9')* (EXPONENT)?)?;
EXPONENT: ('e'|'E') ('+'|'-')? ('0'..'9')+;
STRING : '"' .* '"';
WS : (' '| '\n'| '\r'| '\t')* {$channel = HIDDEN;};
COMMENTS : '//' .* ('\n'|'\r') {$channel = HIDDEN;};
MULTI_COMMENT : '/*' .* '*/' {$channel = HIDDEN;};