package com.pinealctx.frg

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.pinealctx.frg.psi.*
import java.util.ArrayList

class FrgStructureViewElement(private val element: NavigatablePsiElement) : StructureViewTreeElement, SortableTreeElement {

    override fun getValue(): Any = element

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = element.canNavigate()

    override fun canNavigateToSource(): Boolean = element.canNavigateToSource()

    override fun getAlphaSortKey(): String {
        val name = element.name
        return name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        val presentation = element.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (element is FrgFile) {
            val treeElements = ArrayList<TreeElement>()
            
            // Add Services
            val services = PsiTreeUtil.findChildrenOfType(element, FrgServiceDecl::class.java)
            for (service in services) {
                treeElements.add(FrgStructureViewElement(service as NavigatablePsiElement))
            }

            // Add Types
            val types = PsiTreeUtil.findChildrenOfType(element, FrgTypeDecl::class.java)
            for (type in types) {
                treeElements.add(FrgStructureViewElement(type as NavigatablePsiElement))
            }

            // Add Enums
            val enums = PsiTreeUtil.findChildrenOfType(element, FrgEnumDecl::class.java)
            for (enum in enums) {
                treeElements.add(FrgStructureViewElement(enum as NavigatablePsiElement))
            }
            
            return treeElements.toTypedArray()
        } else if (element is FrgServiceDecl) {
            val treeElements = ArrayList<TreeElement>()
            val serviceBodies = element.serviceBodyList
            for (body in serviceBodies) {
                val handlerMetadata = body.handlerMetadata
                if (handlerMetadata != null) {
                    treeElements.add(FrgStructureViewElement(handlerMetadata as NavigatablePsiElement))
                }
            }
            return treeElements.toTypedArray()
        } else if (element is FrgTypeDecl) {
             val treeElements = ArrayList<TreeElement>()
             val fields = element.typeFieldList
             for (field in fields) {
                 treeElements.add(FrgStructureViewElement(field as NavigatablePsiElement))
             }
             return treeElements.toTypedArray()
        }
        return emptyArray()
    }
}
