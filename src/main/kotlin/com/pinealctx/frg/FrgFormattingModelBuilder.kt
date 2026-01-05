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
            // Blank lines around top-level definitions
            .between(FrgTypes.COMMENT, FrgTypes.TYPE_DECL).spacing(0, 0, 1, true, 0)
            .around(FrgTypes.TYPE_DECL).blankLines(1)
            .after(FrgTypes.SYNTAX_DECL).blankLines(1)
            .before(FrgTypes.LBRACE).spaceIf(true)
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
            .between(FrgTypes.ID, FrgTypes.ASSIGN).spaces(1)
            .between(FrgTypes.ASSIGN, FrgTypes.VALUE).spaces(1)
            .before(FrgTypes.COMMENT).spaces(1)
            // Keep comments attached to the following metadata (Must be before generic .before rules)
            .between(FrgTypes.COMMENT, FrgTypes.HANDLER_METADATA).spacing(0, 0, 1, true, 0)
            .between(FrgTypes.COMMENT, FrgTypes.DOC_METADATA).spacing(0, 0, 1, true, 0)
            .between(FrgTypes.COMMENT, FrgTypes.AUTH_METADATA).spacing(0, 0, 1, true, 0)
            // Enforce blank lines between interface definitions
            .before(FrgTypes.HANDLER_METADATA).blankLines(1)
            .before(FrgTypes.DOC_METADATA).blankLines(1)
            .before(FrgTypes.AUTH_METADATA).blankLines(1)
            // Ensure blank lines between routes if no metadata is present
            .between(FrgTypes.ROUTE, FrgTypes.ROUTE).blankLines(1)
            .between(FrgTypes.ROUTE, FrgTypes.RPC_ROUTE).blankLines(1)
            .between(FrgTypes.RPC_ROUTE, FrgTypes.ROUTE).blankLines(1)
            .between(FrgTypes.RPC_ROUTE, FrgTypes.RPC_ROUTE).blankLines(1)
    }
}
