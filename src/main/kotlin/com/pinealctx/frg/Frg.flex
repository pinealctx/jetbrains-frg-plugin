package com.pinealctx.frg;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.pinealctx.frg.psi.FrgTypes;
import com.intellij.psi.TokenType;

%%

%class FrgLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("//")[^\r\n]*
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?

ID=[a-zA-Z_][a-zA-Z0-9_]*
NUMBER=[0-9]+
STRING_LITERAL=\"([^\\\"]|\\.)*\"|\`([^`])*\`

%%

<YYINITIAL> {
  {END_OF_LINE_COMMENT}      { return FrgTypes.COMMENT; }
  {BLOCK_COMMENT}            { return FrgTypes.COMMENT; }

  "syntax"                   { return FrgTypes.SYNTAX; }
  "info"                     { return FrgTypes.INFO; }
  "import"                   { return FrgTypes.IMPORT; }
  "type"                     { return FrgTypes.TYPE; }
  "enum"                     { return FrgTypes.ENUM; }
  "service"                  { return FrgTypes.SERVICE; }
  "returns"                  { return FrgTypes.RETURNS; }
  "map"                      { return FrgTypes.MAP; }
  "interface{}"              { return FrgTypes.INTERFACE; }
  
  "struct"                   { return FrgTypes.STRUCT; }
  "rpc"                      { return FrgTypes.RPC; }
  
  "string"                   { return FrgTypes.STRING; }
  "bool"                     { return FrgTypes.BOOL; }
  "int"                      { return FrgTypes.INT; }
  "int32"                    { return FrgTypes.INT32; }
  "int64"                    { return FrgTypes.INT64; }
  "float"                    { return FrgTypes.FLOAT; }
  "float32"                  { return FrgTypes.FLOAT32; }
  "float64"                  { return FrgTypes.FLOAT64; }
  "double"                   { return FrgTypes.DOUBLE; }

  "get"                      { return FrgTypes.GET; }
  "head"                     { return FrgTypes.HEAD; }
  "post"                     { return FrgTypes.POST; }
  "put"                      { return FrgTypes.PUT; }
  "delete"                   { return FrgTypes.DELETE; }
  "patch"                    { return FrgTypes.PATCH; }
  "connect"                  { return FrgTypes.CONNECT; }
  "options"                  { return FrgTypes.OPTIONS; }
  "trace"                    { return FrgTypes.TRACE; }

  "@handler"                 { return FrgTypes.AT_HANDLER; }
  "@doc"                     { return FrgTypes.AT_DOC; }
  "@attr"                    { return FrgTypes.AT_ATTR; }
  "@externDefs"              { return FrgTypes.AT_EXTERN_DEFS; }
  "@auth"                    { return FrgTypes.AT_AUTH; }

  "{"                        { return FrgTypes.LBRACE; }
  "}"                        { return FrgTypes.RBRACE; }
  "("                        { return FrgTypes.LPAREN; }
  ")"                        { return FrgTypes.RPAREN; }
  "["                        { return FrgTypes.LBRACK; }
  "]"                        { return FrgTypes.RBRACK; }
  ":"                        { return FrgTypes.COLON; }
  "/:"                       { return FrgTypes.SLASH_COLON; }
  "/"                        { return FrgTypes.SLASH; }
  "="                        { return FrgTypes.ASSIGN; }
  ";"                        { return FrgTypes.SEMI; }
  "."                        { return FrgTypes.DOT; }
  "*"                        { return FrgTypes.STAR; }
  "-"                        { return FrgTypes.MINUS; }
  ","                        { return FrgTypes.COMMA; }

  {ID}                       { return FrgTypes.ID; }
  {NUMBER}                   { return FrgTypes.NUMBER; }
  {STRING_LITERAL}           { return FrgTypes.STRING_LITERAL; }

  {WHITE_SPACE}+             { return TokenType.WHITE_SPACE; }

}

[^] { return TokenType.BAD_CHARACTER; }
