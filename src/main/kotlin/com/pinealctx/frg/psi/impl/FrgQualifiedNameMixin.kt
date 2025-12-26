package com.pinealctx.frg.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiReference
import com.pinealctx.frg.FrgTypeReference
import com.pinealctx.frg.psi.FrgTypes
import java.util.ArrayList

abstract class FrgQualifiedNameMixin(node: ASTNode) : ASTWrapperPsiElement(node) {
    override fun getReferences(): Array<PsiReference> {
        val references = ArrayList<PsiReference>()
        var child = firstChild
        while (child != null) {
            if (child.node.elementType == FrgTypes.ID) {
                // 为每个 ID 子节点创建一个引用
                val range = TextRange(child.startOffsetInParent, child.startOffsetInParent + child.textLength)
                references.add(FrgTypeReference(this, range))
            }
            child = child.nextSibling
        }
        return references.toTypedArray()
    }
}
