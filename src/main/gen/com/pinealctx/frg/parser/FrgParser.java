// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.pinealctx.frg.psi.FrgTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class FrgParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return frgFile(b, l + 1);
  }

  /* ********************************************************** */
  // (pointerType | qualifiedName) (tag)?
  public static boolean anonymousField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymousField")) return false;
    if (!nextTokenIs(b, "<anonymous field>", ID, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANONYMOUS_FIELD, "<anonymous field>");
    r = anonymousField_0(b, l + 1);
    r = r && anonymousField_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // pointerType | qualifiedName
  private static boolean anonymousField_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymousField_0")) return false;
    boolean r;
    r = pointerType(b, l + 1);
    if (!r) r = qualifiedName(b, l + 1);
    return r;
  }

  // (tag)?
  private static boolean anonymousField_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymousField_1")) return false;
    anonymousField_1_0(b, l + 1);
    return true;
  }

  // (tag)
  private static boolean anonymousField_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymousField_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tag(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACK RBRACK typeName
  public static boolean arrayType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayType")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    r = r && typeName(b, l + 1);
    exit_section_(b, m, ARRAY_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // AT_ATTR LPAREN keyValue* RPAREN
  public static boolean attrBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrBlock")) return false;
    if (!nextTokenIs(b, AT_ATTR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT_ATTR, LPAREN);
    r = r && attrBlock_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ATTR_BLOCK, r);
    return r;
  }

  // keyValue*
  private static boolean attrBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attrBlock_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!keyValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attrBlock_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // AT_AUTH
  public static boolean authMetadata(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "authMetadata")) return false;
    if (!nextTokenIs(b, AT_AUTH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_AUTH);
    exit_section_(b, m, AUTH_METADATA, r);
    return r;
  }

  /* ********************************************************** */
  // AT_DOC LPAREN keyValue* RPAREN
  public static boolean docMetadata(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docMetadata")) return false;
    if (!nextTokenIs(b, AT_DOC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT_DOC, LPAREN);
    r = r && docMetadata_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, DOC_METADATA, r);
    return r;
  }

  // keyValue*
  private static boolean docMetadata_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docMetadata_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!keyValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "docMetadata_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ENUM identifier LBRACE enumMember* RBRACE
  public static boolean enumDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDecl")) return false;
    if (!nextTokenIs(b, ENUM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENUM);
    r = r && identifier(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && enumDecl_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ENUM_DECL, r);
    return r;
  }

  // enumMember*
  private static boolean enumDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDecl_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumMember(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumDecl_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID (ASSIGN value)? (SEMI)?
  public static boolean enumMember(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumMember")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && enumMember_1(b, l + 1);
    r = r && enumMember_2(b, l + 1);
    exit_section_(b, m, ENUM_MEMBER, r);
    return r;
  }

  // (ASSIGN value)?
  private static boolean enumMember_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumMember_1")) return false;
    enumMember_1_0(b, l + 1);
    return true;
  }

  // ASSIGN value
  private static boolean enumMember_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumMember_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SEMI)?
  private static boolean enumMember_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumMember_2")) return false;
    consumeToken(b, SEMI);
    return true;
  }

  /* ********************************************************** */
  // keyValue+
  public static boolean externDef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "externDef")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = keyValue(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!keyValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "externDef", c)) break;
    }
    exit_section_(b, m, EXTERN_DEF, r);
    return r;
  }

  /* ********************************************************** */
  // AT_EXTERN_DEFS LBRACE externDef* RBRACE
  public static boolean externDefs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "externDefs")) return false;
    if (!nextTokenIs(b, AT_EXTERN_DEFS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT_EXTERN_DEFS, LBRACE);
    r = r && externDefs_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, EXTERN_DEFS, r);
    return r;
  }

  // externDef*
  private static boolean externDefs_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "externDefs_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!externDef(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "externDefs_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean frgFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "frgFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "frgFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // AT_HANDLER identifier
  public static boolean handlerMetadata(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "handlerMetadata")) return false;
    if (!nextTokenIs(b, AT_HANDLER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_HANDLER);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, HANDLER_METADATA, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT STRING_LITERAL
  public static boolean importDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importDecl")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, STRING_LITERAL);
    exit_section_(b, m, IMPORT_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // INFO LPAREN (keyValue)* RPAREN
  public static boolean infoBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infoBlock")) return false;
    if (!nextTokenIs(b, INFO)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INFO, LPAREN);
    r = r && infoBlock_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, INFO_BLOCK, r);
    return r;
  }

  // (keyValue)*
  private static boolean infoBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infoBlock_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!infoBlock_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "infoBlock_2", c)) break;
    }
    return true;
  }

  // (keyValue)
  private static boolean infoBlock_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infoBlock_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = keyValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // syntaxDecl | infoBlock | importDecl | externDefs | typeDecl | enumDecl | serviceDecl | COMMENT
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = syntaxDecl(b, l + 1);
    if (!r) r = infoBlock(b, l + 1);
    if (!r) r = importDecl(b, l + 1);
    if (!r) r = externDefs(b, l + 1);
    if (!r) r = typeDecl(b, l + 1);
    if (!r) r = enumDecl(b, l + 1);
    if (!r) r = serviceDecl(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // identifier (COLON | ASSIGN) value (COMMA)?
  public static boolean keyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyValue")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && keyValue_1(b, l + 1);
    r = r && value(b, l + 1);
    r = r && keyValue_3(b, l + 1);
    exit_section_(b, m, KEY_VALUE, r);
    return r;
  }

  // COLON | ASSIGN
  private static boolean keyValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyValue_1")) return false;
    boolean r;
    r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, ASSIGN);
    return r;
  }

  // (COMMA)?
  private static boolean keyValue_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyValue_3")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // SYNTAX | INFO | IMPORT | TYPE | ENUM | SERVICE | RETURNS | MAP | INTERFACE
  //                    | STRING | BOOL | INT | INT32 | INT64 | FLOAT | FLOAT32 | FLOAT64 | DOUBLE
  //                    | GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE
  //                    | AT_HANDLER | AT_DOC | AT_ATTR | AT_EXTERN_DEFS | AT_AUTH
  //                    | STRUCT | RPC
  static boolean keywords(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keywords")) return false;
    boolean r;
    r = consumeToken(b, SYNTAX);
    if (!r) r = consumeToken(b, INFO);
    if (!r) r = consumeToken(b, IMPORT);
    if (!r) r = consumeToken(b, TYPE);
    if (!r) r = consumeToken(b, ENUM);
    if (!r) r = consumeToken(b, SERVICE);
    if (!r) r = consumeToken(b, RETURNS);
    if (!r) r = consumeToken(b, MAP);
    if (!r) r = consumeToken(b, INTERFACE);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, INT32);
    if (!r) r = consumeToken(b, INT64);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, FLOAT32);
    if (!r) r = consumeToken(b, FLOAT64);
    if (!r) r = consumeToken(b, DOUBLE);
    if (!r) r = consumeToken(b, GET);
    if (!r) r = consumeToken(b, HEAD);
    if (!r) r = consumeToken(b, POST);
    if (!r) r = consumeToken(b, PUT);
    if (!r) r = consumeToken(b, DELETE);
    if (!r) r = consumeToken(b, PATCH);
    if (!r) r = consumeToken(b, CONNECT);
    if (!r) r = consumeToken(b, OPTIONS);
    if (!r) r = consumeToken(b, TRACE);
    if (!r) r = consumeToken(b, AT_HANDLER);
    if (!r) r = consumeToken(b, AT_DOC);
    if (!r) r = consumeToken(b, AT_ATTR);
    if (!r) r = consumeToken(b, AT_EXTERN_DEFS);
    if (!r) r = consumeToken(b, AT_AUTH);
    if (!r) r = consumeToken(b, STRUCT);
    if (!r) r = consumeToken(b, RPC);
    return r;
  }

  /* ********************************************************** */
  // MAP LBRACK primitiveType RBRACK typeName
  public static boolean mapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapType")) return false;
    if (!nextTokenIs(b, MAP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MAP, LBRACK);
    r = r && primitiveType(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    r = r && typeName(b, l + 1);
    exit_section_(b, m, MAP_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // identifier typeName (tag)?
  public static boolean normalField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalField")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NORMAL_FIELD, null);
    r = identifier(b, l + 1);
    r = r && typeName(b, l + 1);
    p = r; // pin = 2
    r = r && normalField_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (tag)?
  private static boolean normalField_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalField_2")) return false;
    normalField_2_0(b, l + 1);
    return true;
  }

  // (tag)
  private static boolean normalField_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "normalField_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tag(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (SLASH | SLASH_COLON) pathToken (MINUS pathToken)*
  static boolean pathSegment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pathSegment")) return false;
    if (!nextTokenIs(b, "", SLASH, SLASH_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pathSegment_0(b, l + 1);
    r = r && pathToken(b, l + 1);
    r = r && pathSegment_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SLASH | SLASH_COLON
  private static boolean pathSegment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pathSegment_0")) return false;
    boolean r;
    r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, SLASH_COLON);
    return r;
  }

  // (MINUS pathToken)*
  private static boolean pathSegment_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pathSegment_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!pathSegment_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "pathSegment_2", c)) break;
    }
    return true;
  }

  // MINUS pathToken
  private static boolean pathSegment_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pathSegment_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    r = r && pathToken(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier | ID | NUMBER | STRING_LITERAL | keywords
  static boolean pathToken(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pathToken")) return false;
    boolean r;
    r = identifier(b, l + 1);
    if (!r) r = consumeToken(b, ID);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING_LITERAL);
    if (!r) r = keywords(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STAR typeName
  public static boolean pointerType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pointerType")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    r = r && typeName(b, l + 1);
    exit_section_(b, m, POINTER_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // STRING | BOOL | INT | INT32 | INT64 | FLOAT | FLOAT32 | FLOAT64 | DOUBLE | INTERFACE
  public static boolean primitiveType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primitiveType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRIMITIVE_TYPE, "<primitive type>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, INT);
    if (!r) r = consumeToken(b, INT32);
    if (!r) r = consumeToken(b, INT64);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, FLOAT32);
    if (!r) r = consumeToken(b, FLOAT64);
    if (!r) r = consumeToken(b, DOUBLE);
    if (!r) r = consumeToken(b, INTERFACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID (DOT ID)*
  public static boolean qualifiedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && qualifiedName_1(b, l + 1);
    exit_section_(b, m, QUALIFIED_NAME, r);
    return r;
  }

  // (DOT ID)*
  private static boolean qualifiedName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!qualifiedName_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "qualifiedName_1", c)) break;
    }
    return true;
  }

  // DOT ID
  private static boolean qualifiedName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // typeName
  public static boolean requestType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "requestType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REQUEST_TYPE, "<request type>");
    r = typeName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // typeName
  public static boolean responseType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "responseType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESPONSE_TYPE, "<response type>");
    r = typeName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE) routePath (LPAREN requestType? RPAREN)? (RETURNS LPAREN responseType? RPAREN)?
  public static boolean route(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROUTE, "<route>");
    r = route_0(b, l + 1);
    r = r && routePath(b, l + 1);
    r = r && route_2(b, l + 1);
    r = r && route_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE
  private static boolean route_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_0")) return false;
    boolean r;
    r = consumeToken(b, GET);
    if (!r) r = consumeToken(b, HEAD);
    if (!r) r = consumeToken(b, POST);
    if (!r) r = consumeToken(b, PUT);
    if (!r) r = consumeToken(b, DELETE);
    if (!r) r = consumeToken(b, PATCH);
    if (!r) r = consumeToken(b, CONNECT);
    if (!r) r = consumeToken(b, OPTIONS);
    if (!r) r = consumeToken(b, TRACE);
    return r;
  }

  // (LPAREN requestType? RPAREN)?
  private static boolean route_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_2")) return false;
    route_2_0(b, l + 1);
    return true;
  }

  // LPAREN requestType? RPAREN
  private static boolean route_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && route_2_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // requestType?
  private static boolean route_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_2_0_1")) return false;
    requestType(b, l + 1);
    return true;
  }

  // (RETURNS LPAREN responseType? RPAREN)?
  private static boolean route_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_3")) return false;
    route_3_0(b, l + 1);
    return true;
  }

  // RETURNS LPAREN responseType? RPAREN
  private static boolean route_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RETURNS, LPAREN);
    r = r && route_3_0_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // responseType?
  private static boolean route_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "route_3_0_2")) return false;
    responseType(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (pathSegment)+
  public static boolean routePath(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "routePath")) return false;
    if (!nextTokenIs(b, "<route path>", SLASH, SLASH_COLON)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROUTE_PATH, "<route path>");
    r = routePath_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!routePath_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "routePath", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (pathSegment)
  private static boolean routePath_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "routePath_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pathSegment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RPC identifier LPAREN requestType? RPAREN RETURNS LPAREN responseType? RPAREN
  public static boolean rpcRoute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rpcRoute")) return false;
    if (!nextTokenIs(b, RPC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RPC);
    r = r && identifier(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && rpcRoute_3(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, RETURNS, LPAREN);
    r = r && rpcRoute_7(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, RPC_ROUTE, r);
    return r;
  }

  // requestType?
  private static boolean rpcRoute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rpcRoute_3")) return false;
    requestType(b, l + 1);
    return true;
  }

  // responseType?
  private static boolean rpcRoute_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rpcRoute_7")) return false;
    responseType(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // handlerMetadata | docMetadata | authMetadata | route | rpcRoute
  public static boolean serviceBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_BODY, "<service body>");
    r = handlerMetadata(b, l + 1);
    if (!r) r = docMetadata(b, l + 1);
    if (!r) r = authMetadata(b, l + 1);
    if (!r) r = route(b, l + 1);
    if (!r) r = rpcRoute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (attrBlock)? SERVICE ID? LBRACE serviceBody* RBRACE
  public static boolean serviceDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceDecl")) return false;
    if (!nextTokenIs(b, "<service decl>", AT_ATTR, SERVICE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_DECL, "<service decl>");
    r = serviceDecl_0(b, l + 1);
    r = r && consumeToken(b, SERVICE);
    r = r && serviceDecl_2(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && serviceDecl_4(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (attrBlock)?
  private static boolean serviceDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceDecl_0")) return false;
    serviceDecl_0_0(b, l + 1);
    return true;
  }

  // (attrBlock)
  private static boolean serviceDecl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceDecl_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attrBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID?
  private static boolean serviceDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceDecl_2")) return false;
    consumeToken(b, ID);
    return true;
  }

  // serviceBody*
  private static boolean serviceDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serviceDecl_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!serviceBody(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "serviceDecl_4", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SYNTAX (ASSIGN)? STRING_LITERAL
  public static boolean syntaxDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "syntaxDecl")) return false;
    if (!nextTokenIs(b, SYNTAX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYNTAX);
    r = r && syntaxDecl_1(b, l + 1);
    r = r && consumeToken(b, STRING_LITERAL);
    exit_section_(b, m, SYNTAX_DECL, r);
    return r;
  }

  // (ASSIGN)?
  private static boolean syntaxDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "syntaxDecl_1")) return false;
    consumeToken(b, ASSIGN);
    return true;
  }

  /* ********************************************************** */
  // STRING_LITERAL
  public static boolean tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag")) return false;
    if (!nextTokenIs(b, STRING_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL);
    exit_section_(b, m, TAG, r);
    return r;
  }

  /* ********************************************************** */
  // TYPE identifier (STRUCT)? LBRACE typeField* RBRACE
  public static boolean typeDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDecl")) return false;
    if (!nextTokenIs(b, TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPE);
    r = r && identifier(b, l + 1);
    r = r && typeDecl_2(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && typeDecl_4(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, TYPE_DECL, r);
    return r;
  }

  // (STRUCT)?
  private static boolean typeDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDecl_2")) return false;
    consumeToken(b, STRUCT);
    return true;
  }

  // typeField*
  private static boolean typeDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDecl_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!typeField(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "typeDecl_4", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // normalField | anonymousField
  public static boolean typeField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeField")) return false;
    if (!nextTokenIs(b, "<type field>", ID, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_FIELD, "<type field>");
    r = normalField(b, l + 1);
    if (!r) r = anonymousField(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // mapType | arrayType | pointerType | qualifiedName | primitiveType
  public static boolean typeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_NAME, "<type name>");
    r = mapType(b, l + 1);
    if (!r) r = arrayType(b, l + 1);
    if (!r) r = pointerType(b, l + 1);
    if (!r) r = qualifiedName(b, l + 1);
    if (!r) r = primitiveType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING_LITERAL | NUMBER
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    if (!nextTokenIs(b, "<value>", NUMBER, STRING_LITERAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = consumeToken(b, STRING_LITERAL);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
