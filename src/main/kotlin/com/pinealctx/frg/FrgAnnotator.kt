package com.pinealctx.frg

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.pinealctx.frg.psi.FrgHandlerMetadata
import com.pinealctx.frg.psi.FrgTypes

class FrgAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == FrgTypes.ID && element.parent is FrgHandlerMetadata) {
            // Highlight handler name
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
                .create()
        }
    }
    
    private val PsiElement.elementType get() = node.elementType
}
