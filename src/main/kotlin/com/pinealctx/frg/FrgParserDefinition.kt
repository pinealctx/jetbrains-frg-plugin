package com.pinealctx.frg

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.pinealctx.frg.parser.FrgParser
import com.pinealctx.frg.psi.FrgFile
import com.pinealctx.frg.psi.FrgTypes

class FrgParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer = FrgLexerAdapter()
    override fun createParser(project: Project): PsiParser = FrgParser()
    override fun getFileNodeType(): IFileElementType = FILE
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getWhitespaceTokens(): TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun createElement(node: ASTNode): PsiElement = FrgTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = FrgFile(viewProvider)

    companion object {
        val FILE = IFileElementType(FrgLanguage)
        val COMMENTS = TokenSet.create(FrgTypes.COMMENT)
    }
}
