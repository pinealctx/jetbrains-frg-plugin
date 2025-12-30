package com.pinealctx.frg

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.pinealctx.frg.psi.FrgTypes

class FrgFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings
        return FormattingModelProvider
            .createFormattingModelForPsiFile(
                formattingContext.psiElement.containingFile,
                FrgBlock(
                    formattingContext.node,
                    Wrap.createWrap(WrapType.NONE, false),
                    null,
                    createSpaceBuilder(codeStyleSettings),
                    null
                ),
                codeStyleSettings
            )
    }

    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
        return SpacingBuilder(settings, FrgLanguage)
            .before(FrgTypes.LBRACE).spaceIf(true)
            .after(FrgTypes.LBRACE).lineBreakInCode()
            .before(FrgTypes.RBRACE).lineBreakInCode()
            .before(FrgTypes.LPAREN).spaceIf(false)
            .after(FrgTypes.COMMA).spaceIf(true)
            .before(FrgTypes.COMMA).spaceIf(false)
            .around(FrgTypes.ASSIGN).spaceIf(true)
            .after(FrgTypes.SYNTAX).spaceIf(true)
            .after(FrgTypes.IMPORT).spaceIf(true)
            .before(FrgTypes.TYPE).spaceIf(false)
            .after(FrgTypes.TYPE).spaceIf(true)
            .before(FrgTypes.ENUM).spaceIf(false)
            .after(FrgTypes.ENUM).spaceIf(true)
            .after(FrgTypes.SERVICE).spaceIf(true)
            .after(FrgTypes.STRUCT).spaceIf(true)
            .after(FrgTypes.RPC).spaceIf(true)
            .after(FrgTypes.RETURNS).spaceIf(true)
            .after(FrgTypes.MAP).spaceIf(false) // map[string]int
            .between(FrgTypes.IDENTIFIER, FrgTypes.TYPE_NAME).spaces(1)
            .between(FrgTypes.TYPE_NAME, FrgTypes.TAG).spaces(1)
            .between(FrgTypes.ID, FrgTypes.ASSIGN).spaces(1)
            .between(FrgTypes.ASSIGN, FrgTypes.VALUE).spaces(1)
            .before(FrgTypes.COMMENT).spaces(1)
    }
}
