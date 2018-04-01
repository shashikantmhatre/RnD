grammar isodataimport;

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
  package com.mmpnc.rating.iso.content.dataimport;
}

@lexer::header {
  package com.mmpnc.rating.iso.content.dataimport;
}

data : records EOF!;

records : record record*;

record : (ratebook | ratetable | domaintable ) ;

ratebook : 'Ratebook'^ '{'! keyvalue (','! keyvalue )* '}'!
  ;

ratetable : 'RateTable'^ '{'!
              keyvalue (','! keyvalue )*
            '}'! ;

ratetablekey : 'RateTableKey'^ '{'!
              keyvalue (','! keyvalue )*
            '}'!;
            
ratetablevalue : 'RateTableValue'^ '{'!
              keyvalue (','! keyvalue )*
           '}'!;   

ratetableentry : 'RateTableEntry'^ '{'!
              keyvalue (','! keyvalue )*
            '}'!;  
            
domaintable : 'DomainTable'^ '{'!
              keyvalue (','! keyvalue )*
            '}'! ;            
            
domaintablekey : 'DomainTableKey'^ '{'!
              keyvalue (','! keyvalue )*
            '}'!;
            
domaintablevalue : 'DomainTableValue'^ '{'!
              keyvalue (','! keyvalue )*
           '}'!;   

domaintableentry : 'DomainTableEntry'^ '{'!
              keyvalue (','! keyvalue )*
            '}'!;              
            
key : 'Key'^ '{'!
            keyvalue (','! keyvalue )*
            '}'!;

value : 'Value'^ '{'!
        keyvalue (','! keyvalue )*
        '}'!;

keyvalue : (IDENTS^ ':'! STRING | ratebook | ratetablekey | ratetablevalue | ratetableentry | domaintablekey | domaintablevalue | domaintableentry | key | value );

IDENTS : ('a'..'z' | 'A'..'Z' | '_' | '/' | '.')('a'..'z' | 'A'..'Z' | '_' | '0'..'9' | '/' | '.' | '*' )*;
INTEGER : ('0'..'9')+('.' ('0'..'9')* (EXPONENT)?)?;
EXPONENT: ('e'|'E') ('+'|'-')? ('0'..'9')+;
STRING : '"' .* '"';
WS : (' '| '\n'| '\r'| '\t')* {$channel = HIDDEN;};
COMMENTS : '//' .* ('\n'|'\r') {$channel = HIDDEN;};
MULTI_COMMENT : '/*' .* '*/' {$channel = HIDDEN;};