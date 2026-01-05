// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.pinealctx.frg.psi.impl.*;

public interface FrgTypes {

  IElementType ANONYMOUS_FIELD = new FrgElementType("ANONYMOUS_FIELD");
  IElementType ARRAY_TYPE = new FrgElementType("ARRAY_TYPE");
  IElementType ATTR_BLOCK = new FrgElementType("ATTR_BLOCK");
  IElementType AUTH_METADATA = new FrgElementType("AUTH_METADATA");
  IElementType DOC_METADATA = new FrgElementType("DOC_METADATA");
  IElementType ENUM_DECL = new FrgElementType("ENUM_DECL");
  IElementType ENUM_MEMBER = new FrgElementType("ENUM_MEMBER");
  IElementType EXTERN_DEF = new FrgElementType("EXTERN_DEF");
  IElementType EXTERN_DEFS = new FrgElementType("EXTERN_DEFS");
  IElementType HANDLER_METADATA = new FrgElementType("HANDLER_METADATA");
  IElementType IDENTIFIER = new FrgElementType("IDENTIFIER");
  IElementType IMPORT_DECL = new FrgElementType("IMPORT_DECL");
  IElementType INFO_BLOCK = new FrgElementType("INFO_BLOCK");
  IElementType KEY_VALUE = new FrgElementType("KEY_VALUE");
  IElementType MAP_TYPE = new FrgElementType("MAP_TYPE");
  IElementType NORMAL_FIELD = new FrgElementType("NORMAL_FIELD");
  IElementType POINTER_TYPE = new FrgElementType("POINTER_TYPE");
  IElementType PRIMITIVE_TYPE = new FrgElementType("PRIMITIVE_TYPE");
  IElementType QUALIFIED_NAME = new FrgElementType("QUALIFIED_NAME");
  IElementType REQUEST_TYPE = new FrgElementType("REQUEST_TYPE");
  IElementType RESPONSE_TYPE = new FrgElementType("RESPONSE_TYPE");
  IElementType ROUTE = new FrgElementType("ROUTE");
  IElementType ROUTE_PATH = new FrgElementType("ROUTE_PATH");
  IElementType RPC_ROUTE = new FrgElementType("RPC_ROUTE");
  IElementType SERVICE_DECL = new FrgElementType("SERVICE_DECL");
  IElementType SYNTAX_DECL = new FrgElementType("SYNTAX_DECL");
  IElementType TAG = new FrgElementType("TAG");
  IElementType TYPE_DECL = new FrgElementType("TYPE_DECL");
  IElementType TYPE_FIELD = new FrgElementType("TYPE_FIELD");
  IElementType TYPE_NAME = new FrgElementType("TYPE_NAME");
  IElementType VALUE = new FrgElementType("VALUE");

  IElementType ASSIGN = new FrgTokenType("ASSIGN");
  IElementType AT_ATTR = new FrgTokenType("AT_ATTR");
  IElementType AT_AUTH = new FrgTokenType("AT_AUTH");
  IElementType AT_DOC = new FrgTokenType("AT_DOC");
  IElementType AT_EXTERN_DEFS = new FrgTokenType("AT_EXTERN_DEFS");
  IElementType AT_HANDLER = new FrgTokenType("AT_HANDLER");
  IElementType BOOL = new FrgTokenType("BOOL");
  IElementType COLON = new FrgTokenType("COLON");
  IElementType COMMA = new FrgTokenType("COMMA");
  IElementType COMMENT = new FrgTokenType("COMMENT");
  IElementType CONNECT = new FrgTokenType("CONNECT");
  IElementType DELETE = new FrgTokenType("DELETE");
  IElementType DOT = new FrgTokenType("DOT");
  IElementType DOUBLE = new FrgTokenType("DOUBLE");
  IElementType ENUM = new FrgTokenType("ENUM");
  IElementType FLOAT = new FrgTokenType("FLOAT");
  IElementType FLOAT32 = new FrgTokenType("FLOAT32");
  IElementType FLOAT64 = new FrgTokenType("FLOAT64");
  IElementType GET = new FrgTokenType("GET");
  IElementType HEAD = new FrgTokenType("HEAD");
  IElementType ID = new FrgTokenType("ID");
  IElementType IMPORT = new FrgTokenType("IMPORT");
  IElementType INFO = new FrgTokenType("INFO");
  IElementType INT = new FrgTokenType("INT");
  IElementType INT32 = new FrgTokenType("INT32");
  IElementType INT64 = new FrgTokenType("INT64");
  IElementType INTERFACE = new FrgTokenType("INTERFACE");
  IElementType LBRACE = new FrgTokenType("LBRACE");
  IElementType LBRACK = new FrgTokenType("LBRACK");
  IElementType LPAREN = new FrgTokenType("LPAREN");
  IElementType MAP = new FrgTokenType("MAP");
  IElementType MINUS = new FrgTokenType("MINUS");
  IElementType NEWLINE = new FrgTokenType("NEWLINE");
  IElementType NUMBER = new FrgTokenType("NUMBER");
  IElementType OPTIONS = new FrgTokenType("OPTIONS");
  IElementType PATCH = new FrgTokenType("PATCH");
  IElementType POST = new FrgTokenType("POST");
  IElementType PUT = new FrgTokenType("PUT");
  IElementType RBRACE = new FrgTokenType("RBRACE");
  IElementType RBRACK = new FrgTokenType("RBRACK");
  IElementType RETURNS = new FrgTokenType("RETURNS");
  IElementType RPAREN = new FrgTokenType("RPAREN");
  IElementType RPC = new FrgTokenType("RPC");
  IElementType SEMI = new FrgTokenType("SEMI");
  IElementType SERVICE = new FrgTokenType("SERVICE");
  IElementType SLASH = new FrgTokenType("SLASH");
  IElementType SLASH_COLON = new FrgTokenType("SLASH_COLON");
  IElementType STAR = new FrgTokenType("STAR");
  IElementType STRING = new FrgTokenType("STRING");
  IElementType STRING_LITERAL = new FrgTokenType("STRING_LITERAL");
  IElementType STRUCT = new FrgTokenType("STRUCT");
  IElementType SYNTAX = new FrgTokenType("SYNTAX");
  IElementType TRACE = new FrgTokenType("TRACE");
  IElementType TYPE = new FrgTokenType("TYPE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANONYMOUS_FIELD) {
        return new FrgAnonymousFieldImpl(node);
      }
      else if (type == ARRAY_TYPE) {
        return new FrgArrayTypeImpl(node);
      }
      else if (type == ATTR_BLOCK) {
        return new FrgAttrBlockImpl(node);
      }
      else if (type == AUTH_METADATA) {
        return new FrgAuthMetadataImpl(node);
      }
      else if (type == DOC_METADATA) {
        return new FrgDocMetadataImpl(node);
      }
      else if (type == ENUM_DECL) {
        return new FrgEnumDeclImpl(node);
      }
      else if (type == ENUM_MEMBER) {
        return new FrgEnumMemberImpl(node);
      }
      else if (type == EXTERN_DEF) {
        return new FrgExternDefImpl(node);
      }
      else if (type == EXTERN_DEFS) {
        return new FrgExternDefsImpl(node);
      }
      else if (type == HANDLER_METADATA) {
        return new FrgHandlerMetadataImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new FrgIdentifierImpl(node);
      }
      else if (type == IMPORT_DECL) {
        return new FrgImportDeclImpl(node);
      }
      else if (type == INFO_BLOCK) {
        return new FrgInfoBlockImpl(node);
      }
      else if (type == KEY_VALUE) {
        return new FrgKeyValueImpl(node);
      }
      else if (type == MAP_TYPE) {
        return new FrgMapTypeImpl(node);
      }
      else if (type == NORMAL_FIELD) {
        return new FrgNormalFieldImpl(node);
      }
      else if (type == POINTER_TYPE) {
        return new FrgPointerTypeImpl(node);
      }
      else if (type == PRIMITIVE_TYPE) {
        return new FrgPrimitiveTypeImpl(node);
      }
      else if (type == QUALIFIED_NAME) {
        return new FrgQualifiedNameImpl(node);
      }
      else if (type == REQUEST_TYPE) {
        return new FrgRequestTypeImpl(node);
      }
      else if (type == RESPONSE_TYPE) {
        return new FrgResponseTypeImpl(node);
      }
      else if (type == ROUTE) {
        return new FrgRouteImpl(node);
      }
      else if (type == ROUTE_PATH) {
        return new FrgRoutePathImpl(node);
      }
      else if (type == RPC_ROUTE) {
        return new FrgRpcRouteImpl(node);
      }
      else if (type == SERVICE_DECL) {
        return new FrgServiceDeclImpl(node);
      }
      else if (type == SYNTAX_DECL) {
        return new FrgSyntaxDeclImpl(node);
      }
      else if (type == TAG) {
        return new FrgTagImpl(node);
      }
      else if (type == TYPE_DECL) {
        return new FrgTypeDeclImpl(node);
      }
      else if (type == TYPE_FIELD) {
        return new FrgTypeFieldImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new FrgTypeNameImpl(node);
      }
      else if (type == VALUE) {
        return new FrgValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
