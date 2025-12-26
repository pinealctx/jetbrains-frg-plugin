grammar FrgParser;

import FrgLexer;

@lexer::members{
    const COMEMNTS = 88
}


tree:
    syntax infoSpec? importSpec* externDef? typeSpec* serviceSpec* EOF
    ;

// syntax
syntax:
    SYNTAX '=' STRING;

// info
infoSpec:
    INFO '(' kvLit+ ')';

// import
importSpec:
    IMPORT value=STRING;

typeSpec:
    typeStruct
    |enum
    ;

externDef:
    EXTERNDEFS '{' externDefItem+ '}';

externDefItem:
    'name' ':' STRING ',' 'swaggerType' ':' STRING ',' 'importPath' ':' STRING (',' 'csharpType' ':' STRING)?;

enum:
    ENUM enumName=CAP_ID '{' (intEnum+ | stringEnum+)'}' ;

intEnum:
    name=goId '=' value=INT ';'?;

stringEnum:
    name=goId '=' value=STRING ';'?;

typeStruct:
    TYPE structName=CAP_ID STRUCT? '{' field* '}';

field:
    normalField
    |anonymousFiled ;

normalField:
    fieldName=goId dataType tag?;

tag:
    '`' (goId ':' STRING)+ '`';

anonymousFiled:
    star='*'? qualifiedId;

dataType:
    qualifiedId
    |mapType
    |arrayType
    |inter='interface{}'
    |pointerType
    ;
pointerType:
    star='*' qualifiedId;

mapType:
    MAP '[' key=goId ']' value=dataType;

arrayType:
    '[' ']' dataType;

qualifiedId:
    goId ('.' goId)?;

// service
serviceSpec:
    attr? SERVICE name=goId? '{' serviceRoute* '}';

attr:
    ATTR '(' kvLit+ ')';

serviceRoute:
    httpRoute
    |rpcDef;

rpcDef:
    RPC rpcHandler=goId request=body RETURNS response=body;

httpRoute:
    ATHANDLER handler=goId AUTH?
    httpMethod path request=body RETURNS response=body;

body:
    '(' dataType? ')';

// kv
kvLit:
    key=ident ':' value=STRING;

path:
    (('/' (ident ('-' ident)*))|('/:' (ident ('-' ident)?)))+;

httpMethod:
    GET
    |HEAD
    |POST
    |PUT
    |PATCH
    |DELETE
    |CONNECT
    |OPTIONS
    |TRACE
    ;

goId:
    httpMethod
    |CAP_ID
    |SYNTAX
    |INFO
    |ENUM
    |SERVICE
    |RETURNS
    |RPC
    |ID;

ident:
    goId
    |BREAK
    |DEFAULT
    |FUNC
    |INTERFACE
    |SELECT
    |CASE
    |DEFER
    |GO
    |CHAN
    |ELSE
    |GOTO
    |PACKAGE
    |SWITCH
    |CONST
    |FALLTHROUGH
    |IF
    |RANGE
    |CONTINUE
    |FOR
    |RETURN
    |VAR
    |NIL_LIT
    ;