lexer grammar FrgLexer;

SYNTAX      :   'syntax';
INFO        :   'info';
IMPORT      :   'import';
TYPE        :   'type';
ENUM        :   'enum';
SERVICE     :   'service';
RETURNS     :   'returns';
RPC         :   'rpc';
STRUCT      :   'struct';
MAP         :   'map';
ATTR        :   '@attr';
ATHANDLER   :   '@handler';
SECURITY    :   '@security';
EXTERNDEFS  :   '@externDefs';
AUTH        :   '@auth';

// other go keywords
BREAK                  : 'break';
DEFAULT                : 'default';
FUNC                   : 'func';
INTERFACE              : 'interface';
SELECT                 : 'select';
CASE                   : 'case';
DEFER                  : 'defer';
GO                     : 'go';
CHAN                   : 'chan';
ELSE                   : 'else';
GOTO                   : 'goto';
PACKAGE                : 'package';
SWITCH                 : 'switch';
CONST                  : 'const';
FALLTHROUGH            : 'fallthrough';
IF                     : 'if';
RANGE                  : 'range';
CONTINUE               : 'continue';
FOR                    : 'for';
RETURN                 : 'return';
VAR                    : 'var';
NIL_LIT                : 'nil';


// HTTP METHOD
GET    :    'get';
HEAD   :    'head';
POST:       'post';
PUT:        'put';
PATCH  :    'patch';
DELETE :    'delete';
CONNECT:    'connect';
OPTIONS:    'options';
TRACE  :    'trace';



// Whitespace and comments
WS          :   [ \t\r\n\u000C]+ -> channel(HIDDEN);
LINE_COMMENT:   '//' ~[\r\n]* -> channel(88);
STRING      :   '"' (~["\\] | EscapeSequence)* '"';

INT:    '0' | '-'?[1-9][0-9]*;

CAP_ID:     [A-Z] LetterOrDigit*;
ID:         Letter LetterOrDigit*;

fragment ExponentPart
    : [eE] [+-]? Digits
    ;

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;
fragment Digits
    : [0-9] ([0-9_]* [0-9])?
    ;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;
fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;