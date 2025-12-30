package com.pinealctx.frg

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.pinealctx.frg.psi.FrgTypes

class FrgFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        collectDescriptors(root.node, descriptors)
        return descriptors.toTypedArray()
    }

    private fun collectDescriptors(node: ASTNode, descriptors: MutableList<FoldingDescriptor>) {
        val type = node.elementType
        
        if (type == FrgTypes.TYPE_DECL || 
            type == FrgTypes.ENUM_DECL || 
            type == FrgTypes.SERVICE_DECL || 
            type == FrgTypes.EXTERN_DEFS) {
            
            val lBrace = node.findChildByType(FrgTypes.LBRACE)
            val rBrace = node.findChildByType(FrgTypes.RBRACE)
            
            if (lBrace != null && rBrace != null) {
                val range = TextRange(lBrace.startOffset, rBrace.startOffset + rBrace.textLength)
                descriptors.add(FoldingDescriptor(node, range))
            }
        }
        
        for (child in node.getChildren(null)) {
            collectDescriptors(child, descriptors)
        }
    }

    override fun getPlaceholderText(node: ASTNode): String {
        return "{...}"
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }
}
