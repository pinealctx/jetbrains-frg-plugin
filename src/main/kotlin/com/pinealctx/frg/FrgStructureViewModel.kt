package com.pinealctx.frg

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile
import com.pinealctx.frg.psi.FrgFile
import com.pinealctx.frg.psi.FrgServiceDecl
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgEnumDecl

class FrgStructureViewModel(psiFile: PsiFile) : 
    StructureViewModelBase(psiFile, FrgStructureViewElement(psiFile)), StructureViewModel.ElementInfoProvider {

    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        val value = element.value
        return value !is FrgFile && 
               value !is FrgServiceDecl && 
               value !is FrgTypeDecl && 
               value !is FrgEnumDecl
    }
}
