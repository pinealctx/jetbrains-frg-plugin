package com.pinealctx.frg

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import com.intellij.openapi.util.TextRange
import com.pinealctx.frg.psi.FrgEnumDecl
import com.pinealctx.frg.psi.FrgHandlerMetadata
import com.pinealctx.frg.psi.FrgIdentifier
import com.pinealctx.frg.psi.FrgQualifiedName
import com.pinealctx.frg.psi.FrgServiceDecl
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgTypes
import java.util.regex.Pattern

class FrgAnnotator : Annotator {
    private val propertyPattern = Pattern.compile("^//\\s*([a-zA-Z0-9_]+):\\s*(.*)")
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == FrgTypes.COMMENT) {
            annotateComment(element, holder)
            return
        }
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
    
    private fun annotateComment(element: PsiElement, holder: AnnotationHolder) {
        // Only annotate comments inside Service definitions (routes)
        if (element.parent !is FrgServiceDecl) return

        val text = element.text
        val matcher = propertyPattern.matcher(text)
        if (matcher.find()) {
            val startOffset = element.textRange.startOffset
            
            // Group 1: Key (e.g. "summary")
            val keyStart = matcher.start(1)
            val keyEnd = matcher.end(1)
            if (keyStart != -1 && keyEnd != -1) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(TextRange(startOffset + keyStart, startOffset + keyEnd))
                    .textAttributes(FrgSyntaxHighlighter.DOC_TAG)
                    .create()
            }

            // Group 2: Value (e.g. "Get user")
            val valueStart = matcher.start(2)
            val valueEnd = matcher.end(2)
            if (valueStart != -1 && valueEnd != -1) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(TextRange(startOffset + valueStart, startOffset + valueEnd))
                    .textAttributes(FrgSyntaxHighlighter.DOC_VALUE)
                    .create()
            }
        }
    }
    
    private val PsiElement.elementType get() = node.elementType
}
