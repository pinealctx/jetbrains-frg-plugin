package com.pinealctx.frg

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.pinealctx.frg.psi.FrgEnumDecl
import com.pinealctx.frg.psi.FrgHandlerMetadata
import com.pinealctx.frg.psi.FrgIdentifier
import com.pinealctx.frg.psi.FrgQualifiedName
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgTypes

class FrgAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType != FrgTypes.ID) return

        val parent = element.parent
        if (parent is FrgHandlerMetadata) {
            // Highlight handler name
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
                .create()
        } else if (parent is FrgQualifiedName) {
            // Highlight type reference
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(FrgSyntaxHighlighter.TYPE)
                .create()
        } else if (parent is FrgIdentifier) {
            val grandParent = parent.parent
            if (grandParent is FrgTypeDecl || grandParent is FrgEnumDecl) {
                // Highlight type declaration
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(FrgSyntaxHighlighter.TYPE)
                    .create()
            }
        }
    }
    
    private val PsiElement.elementType get() = node.elementType
}
