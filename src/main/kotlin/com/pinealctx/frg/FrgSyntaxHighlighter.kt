package com.pinealctx.frg

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.pinealctx.frg.psi.FrgTypes

class FrgSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = FrgLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            FrgTypes.SYNTAX, FrgTypes.INFO, FrgTypes.IMPORT, FrgTypes.TYPE, FrgTypes.ENUM, FrgTypes.SERVICE, FrgTypes.RETURNS, FrgTypes.MAP, FrgTypes.INTERFACE -> KEYWORD_KEYS
            FrgTypes.STRING, FrgTypes.BOOL, FrgTypes.INT, FrgTypes.INT32, FrgTypes.INT64, FrgTypes.FLOAT, FrgTypes.FLOAT32, FrgTypes.FLOAT64, FrgTypes.DOUBLE -> TYPE_KEYS
            FrgTypes.STRING_LITERAL -> STRING_KEYS
            FrgTypes.NUMBER -> NUMBER_KEYS
            FrgTypes.COMMENT -> COMMENT_KEYS
            FrgTypes.AT_HANDLER, FrgTypes.AT_DOC -> METADATA_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey("FRG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = TextAttributesKey.createTextAttributesKey("FRG_STRING", DefaultLanguageHighlighterColors.STRING)
        val NUMBER = TextAttributesKey.createTextAttributesKey("FRG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val COMMENT = TextAttributesKey.createTextAttributesKey("FRG_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        // Use KEYWORD color for types to match user expectations (blue instead of black)
        val TYPE = TextAttributesKey.createTextAttributesKey("FRG_TYPE", DefaultLanguageHighlighterColors.KEYWORD)
        val METADATA = TextAttributesKey.createTextAttributesKey("FRG_METADATA", DefaultLanguageHighlighterColors.METADATA)
        val DOC_TAG = TextAttributesKey.createTextAttributesKey("FRG_DOC_TAG", DefaultLanguageHighlighterColors.DOC_COMMENT_TAG)
        val DOC_VALUE = TextAttributesKey.createTextAttributesKey("FRG_DOC_VALUE", DefaultLanguageHighlighterColors.DOC_COMMENT_TAG_VALUE)

        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val STRING_KEYS = arrayOf(STRING)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val TYPE_KEYS = arrayOf(TYPE)
        private val METADATA_KEYS = arrayOf(METADATA)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}
