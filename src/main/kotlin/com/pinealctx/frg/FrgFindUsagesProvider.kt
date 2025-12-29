package com.pinealctx.frg

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import com.pinealctx.frg.psi.FrgEnumDecl
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgTypes

class FrgFindUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            FrgLexerAdapter(),
            TokenSet.create(FrgTypes.ID),
            TokenSet.create(FrgTypes.COMMENT),
            TokenSet.create(FrgTypes.STRING_LITERAL)
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is FrgTypeDecl -> "Type"
            is FrgEnumDecl -> "Enum"
            else -> ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return if (element is PsiNamedElement) {
            element.name ?: ""
        } else {
            ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return if (element is PsiNamedElement) {
            element.name ?: ""
        } else {
            ""
        }
    }
}
