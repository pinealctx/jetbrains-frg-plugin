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

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return frgFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // (pointerType | qualifiedName) (tag)?
  public static boolean anonymousField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anonymousField")) return false;
    if (!nextTokenIs(builder_, "<anonymous field>", ID, STAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ANONYMOUS_FIELD, "<anonymous field>");
    result_ = anonymousField_0(builder_, level_ + 1);
    result_ = result_ && anonymousField_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // pointerType | qualifiedName
  private static boolean anonymousField_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anonymousField_0")) return false;
    boolean result_;
    result_ = pointerType(builder_, level_ + 1);
    if (!result_) result_ = qualifiedName(builder_, level_ + 1);
    return result_;
  }

  // (tag)?
  private static boolean anonymousField_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anonymousField_1")) return false;
    anonymousField_1_0(builder_, level_ + 1);
    return true;
  }

  // (tag)
  private static boolean anonymousField_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anonymousField_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = tag(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACK RBRACK typeName
  public static boolean arrayType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayType")) return false;
    if (!nextTokenIs(builder_, LBRACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LBRACK, RBRACK);
    result_ = result_ && typeName(builder_, level_ + 1);
    exit_section_(builder_, marker_, ARRAY_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // AT_ATTR LPAREN (keyValue | NEWLINE)* RPAREN
  public static boolean attrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attrBlock")) return false;
    if (!nextTokenIs(builder_, AT_ATTR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, AT_ATTR, LPAREN);
    result_ = result_ && attrBlock_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, ATTR_BLOCK, result_);
    return result_;
  }

  // (keyValue | NEWLINE)*
  private static boolean attrBlock_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attrBlock_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attrBlock_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "attrBlock_2", pos_)) break;
    }
    return true;
  }

  // keyValue | NEWLINE
  private static boolean attrBlock_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attrBlock_2_0")) return false;
    boolean result_;
    result_ = keyValue(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // AT_AUTH
  public static boolean authMetadata(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "authMetadata")) return false;
    if (!nextTokenIs(builder_, AT_AUTH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AT_AUTH);
    exit_section_(builder_, marker_, AUTH_METADATA, result_);
    return result_;
  }

  /* ********************************************************** */
  // AT_DOC LPAREN (keyValue | NEWLINE)* RPAREN
  public static boolean docMetadata(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "docMetadata")) return false;
    if (!nextTokenIs(builder_, AT_DOC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, AT_DOC, LPAREN);
    result_ = result_ && docMetadata_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, DOC_METADATA, result_);
    return result_;
  }

  // (keyValue | NEWLINE)*
  private static boolean docMetadata_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "docMetadata_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!docMetadata_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "docMetadata_2", pos_)) break;
    }
    return true;
  }

  // keyValue | NEWLINE
  private static boolean docMetadata_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "docMetadata_2_0")) return false;
    boolean result_;
    result_ = keyValue(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // ENUM identifier LBRACE (enumMember | NEWLINE)* RBRACE
  public static boolean enumDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDecl")) return false;
    if (!nextTokenIs(builder_, ENUM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENUM);
    result_ = result_ && identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && enumDecl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, ENUM_DECL, result_);
    return result_;
  }

  // (enumMember | NEWLINE)*
  private static boolean enumDecl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDecl_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!enumDecl_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enumDecl_3", pos_)) break;
    }
    return true;
  }

  // enumMember | NEWLINE
  private static boolean enumDecl_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDecl_3_0")) return false;
    boolean result_;
    result_ = enumMember(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // ID (ASSIGN value)? (SEMI)?
  public static boolean enumMember(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumMember")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    result_ = result_ && enumMember_1(builder_, level_ + 1);
    result_ = result_ && enumMember_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, ENUM_MEMBER, result_);
    return result_;
  }

  // (ASSIGN value)?
  private static boolean enumMember_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumMember_1")) return false;
    enumMember_1_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN value
  private static boolean enumMember_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumMember_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (SEMI)?
  private static boolean enumMember_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumMember_2")) return false;
    consumeToken(builder_, SEMI);
    return true;
  }

  /* ********************************************************** */
  // keyValue+
  public static boolean externDef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDef")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = keyValue(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!keyValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "externDef", pos_)) break;
    }
    exit_section_(builder_, marker_, EXTERN_DEF, result_);
    return result_;
  }

  /* ********************************************************** */
  // AT_EXTERN_DEFS LBRACE (externDef | NEWLINE)* RBRACE
  public static boolean externDefs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDefs")) return false;
    if (!nextTokenIs(builder_, AT_EXTERN_DEFS)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, AT_EXTERN_DEFS, LBRACE);
    result_ = result_ && externDefs_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, EXTERN_DEFS, result_);
    return result_;
  }

  // (externDef | NEWLINE)*
  private static boolean externDefs_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDefs_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!externDefs_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "externDefs_2", pos_)) break;
    }
    return true;
  }

  // externDef | NEWLINE
  private static boolean externDefs_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDefs_2_0")) return false;
    boolean result_;
    result_ = externDef(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // (item_ | NEWLINE)*
  static boolean frgFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "frgFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!frgFile_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "frgFile", pos_)) break;
    }
    return true;
  }

  // item_ | NEWLINE
  private static boolean frgFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "frgFile_0")) return false;
    boolean result_;
    result_ = item_(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // AT_HANDLER identifier
  public static boolean handlerMetadata(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "handlerMetadata")) return false;
    if (!nextTokenIs(builder_, AT_HANDLER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AT_HANDLER);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, HANDLER_METADATA, result_);
    return result_;
  }

  /* ********************************************************** */
  // ID
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    exit_section_(builder_, marker_, IDENTIFIER, result_);
    return result_;
  }

  /* ********************************************************** */
  // IMPORT STRING_LITERAL
  public static boolean importDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importDecl")) return false;
    if (!nextTokenIs(builder_, IMPORT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IMPORT, STRING_LITERAL);
    exit_section_(builder_, marker_, IMPORT_DECL, result_);
    return result_;
  }

  /* ********************************************************** */
  // INFO LPAREN (keyValue | NEWLINE)* RPAREN
  public static boolean infoBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infoBlock")) return false;
    if (!nextTokenIs(builder_, INFO)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, INFO, LPAREN);
    result_ = result_ && infoBlock_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, INFO_BLOCK, result_);
    return result_;
  }

  // (keyValue | NEWLINE)*
  private static boolean infoBlock_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infoBlock_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!infoBlock_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "infoBlock_2", pos_)) break;
    }
    return true;
  }

  // keyValue | NEWLINE
  private static boolean infoBlock_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infoBlock_2_0")) return false;
    boolean result_;
    result_ = keyValue(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // syntaxDecl | infoBlock | importDecl | externDefs | typeDecl | enumDecl | serviceDecl
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_;
    result_ = syntaxDecl(builder_, level_ + 1);
    if (!result_) result_ = infoBlock(builder_, level_ + 1);
    if (!result_) result_ = importDecl(builder_, level_ + 1);
    if (!result_) result_ = externDefs(builder_, level_ + 1);
    if (!result_) result_ = typeDecl(builder_, level_ + 1);
    if (!result_) result_ = enumDecl(builder_, level_ + 1);
    if (!result_) result_ = serviceDecl(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // identifier (COLON | ASSIGN) value (COMMA)?
  public static boolean keyValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyValue")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && keyValue_1(builder_, level_ + 1);
    result_ = result_ && value(builder_, level_ + 1);
    result_ = result_ && keyValue_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, KEY_VALUE, result_);
    return result_;
  }

  // COLON | ASSIGN
  private static boolean keyValue_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyValue_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, ASSIGN);
    return result_;
  }

  // (COMMA)?
  private static boolean keyValue_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keyValue_3")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // SYNTAX | INFO | IMPORT | TYPE | ENUM | SERVICE | RETURNS | MAP | INTERFACE
  //                    | STRING | BOOL | INT | INT32 | INT64 | FLOAT | FLOAT32 | FLOAT64 | DOUBLE
  //                    | GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE
  //                    | AT_HANDLER | AT_DOC | AT_ATTR | AT_EXTERN_DEFS | AT_AUTH
  //                    | STRUCT | RPC
  static boolean keywords(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keywords")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SYNTAX);
    if (!result_) result_ = consumeToken(builder_, INFO);
    if (!result_) result_ = consumeToken(builder_, IMPORT);
    if (!result_) result_ = consumeToken(builder_, TYPE);
    if (!result_) result_ = consumeToken(builder_, ENUM);
    if (!result_) result_ = consumeToken(builder_, SERVICE);
    if (!result_) result_ = consumeToken(builder_, RETURNS);
    if (!result_) result_ = consumeToken(builder_, MAP);
    if (!result_) result_ = consumeToken(builder_, INTERFACE);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, BOOL);
    if (!result_) result_ = consumeToken(builder_, INT);
    if (!result_) result_ = consumeToken(builder_, INT32);
    if (!result_) result_ = consumeToken(builder_, INT64);
    if (!result_) result_ = consumeToken(builder_, FLOAT);
    if (!result_) result_ = consumeToken(builder_, FLOAT32);
    if (!result_) result_ = consumeToken(builder_, FLOAT64);
    if (!result_) result_ = consumeToken(builder_, DOUBLE);
    if (!result_) result_ = consumeToken(builder_, GET);
    if (!result_) result_ = consumeToken(builder_, HEAD);
    if (!result_) result_ = consumeToken(builder_, POST);
    if (!result_) result_ = consumeToken(builder_, PUT);
    if (!result_) result_ = consumeToken(builder_, DELETE);
    if (!result_) result_ = consumeToken(builder_, PATCH);
    if (!result_) result_ = consumeToken(builder_, CONNECT);
    if (!result_) result_ = consumeToken(builder_, OPTIONS);
    if (!result_) result_ = consumeToken(builder_, TRACE);
    if (!result_) result_ = consumeToken(builder_, AT_HANDLER);
    if (!result_) result_ = consumeToken(builder_, AT_DOC);
    if (!result_) result_ = consumeToken(builder_, AT_ATTR);
    if (!result_) result_ = consumeToken(builder_, AT_EXTERN_DEFS);
    if (!result_) result_ = consumeToken(builder_, AT_AUTH);
    if (!result_) result_ = consumeToken(builder_, STRUCT);
    if (!result_) result_ = consumeToken(builder_, RPC);
    return result_;
  }

  /* ********************************************************** */
  // MAP LBRACK primitiveType RBRACK typeName
  public static boolean mapType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mapType")) return false;
    if (!nextTokenIs(builder_, MAP)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, MAP, LBRACK);
    result_ = result_ && primitiveType(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACK);
    result_ = result_ && typeName(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifier typeName (tag)?
  public static boolean normalField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "normalField")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NORMAL_FIELD, null);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && typeName(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && normalField_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (tag)?
  private static boolean normalField_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "normalField_2")) return false;
    normalField_2_0(builder_, level_ + 1);
    return true;
  }

  // (tag)
  private static boolean normalField_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "normalField_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = tag(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (SLASH | SLASH_COLON) pathToken (MINUS pathToken)*
  static boolean pathSegment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pathSegment")) return false;
    if (!nextTokenIs(builder_, "", SLASH, SLASH_COLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = pathSegment_0(builder_, level_ + 1);
    result_ = result_ && pathToken(builder_, level_ + 1);
    result_ = result_ && pathSegment_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SLASH | SLASH_COLON
  private static boolean pathSegment_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pathSegment_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SLASH);
    if (!result_) result_ = consumeToken(builder_, SLASH_COLON);
    return result_;
  }

  // (MINUS pathToken)*
  private static boolean pathSegment_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pathSegment_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!pathSegment_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "pathSegment_2", pos_)) break;
    }
    return true;
  }

  // MINUS pathToken
  private static boolean pathSegment_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pathSegment_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MINUS);
    result_ = result_ && pathToken(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifier | ID | NUMBER | STRING_LITERAL | keywords
  static boolean pathToken(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pathToken")) return false;
    boolean result_;
    result_ = identifier(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ID);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    if (!result_) result_ = consumeToken(builder_, STRING_LITERAL);
    if (!result_) result_ = keywords(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // STAR typeName
  public static boolean pointerType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pointerType")) return false;
    if (!nextTokenIs(builder_, STAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STAR);
    result_ = result_ && typeName(builder_, level_ + 1);
    exit_section_(builder_, marker_, POINTER_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING | BOOL | INT | INT32 | INT64 | FLOAT | FLOAT32 | FLOAT64 | DOUBLE | INTERFACE
  public static boolean primitiveType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primitiveType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PRIMITIVE_TYPE, "<primitive type>");
    result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, BOOL);
    if (!result_) result_ = consumeToken(builder_, INT);
    if (!result_) result_ = consumeToken(builder_, INT32);
    if (!result_) result_ = consumeToken(builder_, INT64);
    if (!result_) result_ = consumeToken(builder_, FLOAT);
    if (!result_) result_ = consumeToken(builder_, FLOAT32);
    if (!result_) result_ = consumeToken(builder_, FLOAT64);
    if (!result_) result_ = consumeToken(builder_, DOUBLE);
    if (!result_) result_ = consumeToken(builder_, INTERFACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ID (DOT ID)*
  public static boolean qualifiedName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualifiedName")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ID);
    result_ = result_ && qualifiedName_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, QUALIFIED_NAME, result_);
    return result_;
  }

  // (DOT ID)*
  private static boolean qualifiedName_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualifiedName_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!qualifiedName_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "qualifiedName_1", pos_)) break;
    }
    return true;
  }

  // DOT ID
  private static boolean qualifiedName_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualifiedName_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DOT, ID);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // typeName
  public static boolean requestType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "requestType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REQUEST_TYPE, "<request type>");
    result_ = typeName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // typeName
  public static boolean responseType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "responseType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RESPONSE_TYPE, "<response type>");
    result_ = typeName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE) routePath (LPAREN requestType? RPAREN)? (RETURNS LPAREN responseType? RPAREN)?
  public static boolean route(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ROUTE, "<route>");
    result_ = route_0(builder_, level_ + 1);
    result_ = result_ && routePath(builder_, level_ + 1);
    result_ = result_ && route_2(builder_, level_ + 1);
    result_ = result_ && route_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // GET | HEAD | POST | PUT | DELETE | PATCH | CONNECT | OPTIONS | TRACE
  private static boolean route_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, GET);
    if (!result_) result_ = consumeToken(builder_, HEAD);
    if (!result_) result_ = consumeToken(builder_, POST);
    if (!result_) result_ = consumeToken(builder_, PUT);
    if (!result_) result_ = consumeToken(builder_, DELETE);
    if (!result_) result_ = consumeToken(builder_, PATCH);
    if (!result_) result_ = consumeToken(builder_, CONNECT);
    if (!result_) result_ = consumeToken(builder_, OPTIONS);
    if (!result_) result_ = consumeToken(builder_, TRACE);
    return result_;
  }

  // (LPAREN requestType? RPAREN)?
  private static boolean route_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_2")) return false;
    route_2_0(builder_, level_ + 1);
    return true;
  }

  // LPAREN requestType? RPAREN
  private static boolean route_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && route_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // requestType?
  private static boolean route_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_2_0_1")) return false;
    requestType(builder_, level_ + 1);
    return true;
  }

  // (RETURNS LPAREN responseType? RPAREN)?
  private static boolean route_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_3")) return false;
    route_3_0(builder_, level_ + 1);
    return true;
  }

  // RETURNS LPAREN responseType? RPAREN
  private static boolean route_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, RETURNS, LPAREN);
    result_ = result_ && route_3_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // responseType?
  private static boolean route_3_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "route_3_0_2")) return false;
    responseType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (pathSegment)+
  public static boolean routePath(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "routePath")) return false;
    if (!nextTokenIs(builder_, "<route path>", SLASH, SLASH_COLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ROUTE_PATH, "<route path>");
    result_ = routePath_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!routePath_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "routePath", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (pathSegment)
  private static boolean routePath_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "routePath_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = pathSegment(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // RPC identifier LPAREN requestType? RPAREN RETURNS LPAREN responseType? RPAREN
  public static boolean rpcRoute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rpcRoute")) return false;
    if (!nextTokenIs(builder_, RPC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RPC);
    result_ = result_ && identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LPAREN);
    result_ = result_ && rpcRoute_3(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, RPAREN, RETURNS, LPAREN);
    result_ = result_ && rpcRoute_7(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, RPC_ROUTE, result_);
    return result_;
  }

  // requestType?
  private static boolean rpcRoute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rpcRoute_3")) return false;
    requestType(builder_, level_ + 1);
    return true;
  }

  // responseType?
  private static boolean rpcRoute_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "rpcRoute_7")) return false;
    responseType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // handlerMetadata | docMetadata | authMetadata | route | rpcRoute
  static boolean serviceBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceBody")) return false;
    boolean result_;
    result_ = handlerMetadata(builder_, level_ + 1);
    if (!result_) result_ = docMetadata(builder_, level_ + 1);
    if (!result_) result_ = authMetadata(builder_, level_ + 1);
    if (!result_) result_ = route(builder_, level_ + 1);
    if (!result_) result_ = rpcRoute(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // (attrBlock)? NEWLINE* SERVICE ID? LBRACE (serviceBody | NEWLINE)* RBRACE
  public static boolean serviceDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SERVICE_DECL, "<service decl>");
    result_ = serviceDecl_0(builder_, level_ + 1);
    result_ = result_ && serviceDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SERVICE);
    result_ = result_ && serviceDecl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && serviceDecl_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (attrBlock)?
  private static boolean serviceDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_0")) return false;
    serviceDecl_0_0(builder_, level_ + 1);
    return true;
  }

  // (attrBlock)
  private static boolean serviceDecl_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attrBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE*
  private static boolean serviceDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "serviceDecl_1", pos_)) break;
    }
    return true;
  }

  // ID?
  private static boolean serviceDecl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_3")) return false;
    consumeToken(builder_, ID);
    return true;
  }

  // (serviceBody | NEWLINE)*
  private static boolean serviceDecl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!serviceDecl_5_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "serviceDecl_5", pos_)) break;
    }
    return true;
  }

  // serviceBody | NEWLINE
  private static boolean serviceDecl_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "serviceDecl_5_0")) return false;
    boolean result_;
    result_ = serviceBody(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // SYNTAX (ASSIGN)? STRING_LITERAL
  public static boolean syntaxDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "syntaxDecl")) return false;
    if (!nextTokenIs(builder_, SYNTAX)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SYNTAX);
    result_ = result_ && syntaxDecl_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, SYNTAX_DECL, result_);
    return result_;
  }

  // (ASSIGN)?
  private static boolean syntaxDecl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "syntaxDecl_1")) return false;
    consumeToken(builder_, ASSIGN);
    return true;
  }

  /* ********************************************************** */
  // STRING_LITERAL
  public static boolean tag(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tag")) return false;
    if (!nextTokenIs(builder_, STRING_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_LITERAL);
    exit_section_(builder_, marker_, TAG, result_);
    return result_;
  }

  /* ********************************************************** */
  // (typeField | NEWLINE)*
  static boolean typeBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeBody")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!typeBody_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeBody", pos_)) break;
    }
    return true;
  }

  // typeField | NEWLINE
  private static boolean typeBody_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeBody_0")) return false;
    boolean result_;
    result_ = typeField(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    return result_;
  }

  /* ********************************************************** */
  // TYPE identifier (STRUCT)? LBRACE typeBody RBRACE
  public static boolean typeDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeDecl")) return false;
    if (!nextTokenIs(builder_, TYPE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TYPE);
    result_ = result_ && identifier(builder_, level_ + 1);
    result_ = result_ && typeDecl_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && typeBody(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, TYPE_DECL, result_);
    return result_;
  }

  // (STRUCT)?
  private static boolean typeDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeDecl_2")) return false;
    consumeToken(builder_, STRUCT);
    return true;
  }

  /* ********************************************************** */
  // normalField | anonymousField
  public static boolean typeField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeField")) return false;
    if (!nextTokenIs(builder_, "<type field>", ID, STAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_FIELD, "<type field>");
    result_ = normalField(builder_, level_ + 1);
    if (!result_) result_ = anonymousField(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // mapType | arrayType | pointerType | qualifiedName | primitiveType
  public static boolean typeName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeName")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_NAME, "<type name>");
    result_ = mapType(builder_, level_ + 1);
    if (!result_) result_ = arrayType(builder_, level_ + 1);
    if (!result_) result_ = pointerType(builder_, level_ + 1);
    if (!result_) result_ = qualifiedName(builder_, level_ + 1);
    if (!result_) result_ = primitiveType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // STRING_LITERAL | NUMBER
  public static boolean value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "value")) return false;
    if (!nextTokenIs(builder_, "<value>", NUMBER, STRING_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VALUE, "<value>");
    result_ = consumeToken(builder_, STRING_LITERAL);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

}
