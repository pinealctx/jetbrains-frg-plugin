package com.pinealctx.frg.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.pinealctx.frg.psi.FrgNamedElement
import com.pinealctx.frg.psi.FrgTypes

abstract class FrgNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), FrgNamedElement {
    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement {
        val identifier = nameIdentifier
        if (identifier != null) {
            val newIdentifier = FrgElementFactory.createIdentifier(project, name)
            identifier.replace(newIdentifier)
        }
        return this
    }

    override fun getNameIdentifier(): PsiElement? {
        // The identifier rule in BNF creates a node that wraps the ID token.
        // We need to find that child.
        // Since we don't have easy access to the generated class here (circular dependency if we use FrgIdentifier class),
        // we look for the child that is an identifier.
        // In our BNF: typeDecl ::= TYPE identifier ...
        // So we look for the child of type IDENTIFIER (the rule name becomes the element type)
        return findChildByType(FrgTypes.IDENTIFIER)
    }
}
