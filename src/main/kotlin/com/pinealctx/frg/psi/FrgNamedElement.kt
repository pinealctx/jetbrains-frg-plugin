package com.pinealctx.frg.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface FrgNamedElement : PsiNameIdentifierOwner {
    override fun getName(): String?
    override fun setName(name: String): PsiElement
    override fun getNameIdentifier(): PsiElement?
}
