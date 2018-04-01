grammar SimLanguageGrammar;

/*
  Author : Shashikant Mhatre
  
  Created On : October 12, 2013
  
  Description : This document defines the basic syntax of the SIM (Simple) Language. Any modification to this language syntax will result in modification of the
  language walker and language evaluators
  
  Note : Please contact Shashikant Mhatre before you modify the language syntax
*/

options {
  language = Java;
  output=AST;  
  ASTLabelType=CommonTree;
}

tokens {
  EQUALS;
  NOTEQUALS;
  DIV;
  NEGATION;
  SUBTRACT;
  NOT;
  AND;
  OR;
  STARTS;
}

@header {
  package com.mmpnc.simlanguage.parse;
}

@lexer::header {
  package com.mmpnc.simlanguage.parse;
}

block : 
    statements EOF!;

statements :
        statement statement*
        ;
 
statement :
        assignment | ifStatement | loopStatement | function | executeProgram
        ;

function : 
        IDENTS^ '('! ('Using'!?) (arithmeticExp (',' arithmeticExp)*)? ')'!;


domainTable : 
        IDENTS^ '{'! (atom (',' atom)*)? '}'!;

executeProgram :
        programType^ '('! ( IDENTS | xpath ) ')'!;

specialFunction : 
            SPECIALFUNCTION^ '('! arithmeticExp ')'! ;

rateFunction : 
            'RateTable'^ ':'! function;
        
assignment:
        (IDENTS | varType | xpath) '='^ arithmeticExp ;
        
ifStatement: 
        'IF'^ (not)? '('! booleanExp ')'! ('THEN'!)? statements 
        ('ELSE' statements)?    
        'END IF'!
        ;

loopStatement :
        'LOOP THROUGH'^ STRING
        statements
        'END LOOP'!   
        ;
            
atom : ( IDENTS | function | expression | INTEGER | STRING | xpath | rateFunction | varType | domainTable);

negation :
        (not^)? atom
        ;

not :
  ('NOT') -> NOT
  ;

unary :
    (('+'! | negative^))* negation
    ;
    
multi :
    unary (( '*'^ | '/'^ | 'mod'^ ) unary)*
    ;
  
arithmeticExp :
    multi (( '+'^ | subtraction^ ) multi )*
    ;

relation :
    arithmeticExp (( equals^ | notequals^ | '<'^ | '<='^ | '>'^ | '>='^ | startsOperator^ )  arithmeticExp )*
    ;
    
equals:
    ('Is Equal To' | '==') -> EQUALS
    ;

notequals:
    ('Not Equal To'| '!=') -> NOTEQUALS
    ;

negative :
    ('-'|'Negative') -> NEGATION
    ;
    
subtraction :
    ('-'|'Subtract') -> SUBTRACT
    ;

andOperator :
    ('And' | '&&') -> AND
    ;
    
orOperator :
    ('Or' | '||') -> OR
   ;
   
startsOperator :
    ('StartsWith') -> STARTS
   ;
   
booleanExp : relation (( andOperator^ | orOperator^ ) relation )*;

expression 
    : (SPECIALFUNCTION '(' arithmeticExp ')')=> specialFunction
    | '('! booleanExp ')'!;

xpath : XPATHCONSTANT ':' STRING ;

varType : VARCONSTANT ':' (IDENTS | STRING);

programType : 'Execute' | 'Attach' ;

XPATHCONSTANT : 'XPATH' | 'XPATH_STRING' | 'XPATH_NUMERIC';
VARCONSTANT : 'COLUMN_NUMERIC' | 'COLUMN_STRING' | 'UI' | 'CONSTANT' | 'LOCAL' | 'LV_NUMERIC' | 'LV_STRING' | 'LV_DOUBLE' | 'LV_INTEGER' | 'LV_BOOLEAN' | 'LV_TIMESPAN' | 'LV_DATE' | 'LV_VALUE';
SPECIALFUNCTION : 'RoundUpDollar' | 'RoundUpHundredth' | 'RoundUpHundredThousandth' | 'RoundUpThousandth' | 'RoundDollar' | 'RoundHundredth' | 'RoundHundredThousandth' | 'RoundThousandth' | 'RoundTenThousandth' | 'Ceiling';
IDENTS : ('a'..'z' | 'A'..'Z' | '_' | '/' | '.')('a'..'z' | 'A'..'Z' | '_' | '0'..'9' | '/' | '.' | '*' )*;
INTEGER : ('0'..'9')+('.' ('0'..'9')* (EXPONENT)?)?;
EXPONENT: ('e'|'E') ('+'|'-')? ('0'..'9')+;
STRING : '"' .* '"';
WS : (' '| '\n'| '\r'| '\t')* {$channel = HIDDEN;};
COMMENTS : '//' .* ('\n'|'\r') {$channel = HIDDEN;};
MULTI_COMMENT : '/*' .* '*/' {$channel = HIDDEN;};

