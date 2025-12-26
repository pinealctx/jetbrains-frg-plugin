package com.pinealctx.frg.psi.impl

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.pinealctx.frg.FrgFileType
import com.pinealctx.frg.psi.FrgFile
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgTypes

object FrgElementFactory {
    fun createIdentifier(project: Project, name: String): PsiElement {
        val file = createFile(project, name)
        // The file content is "type Name struct {}".
        // We want to extract "Name".
        // FrgFile -> TypeDecl -> Identifier
        val typeDecl = file.firstChild as FrgTypeDecl
        return typeDecl.identifier
    }

    fun createFile(project: Project, text: String): FrgFile {
        val name = "dummy.frg"
        val content = "type $text struct {}"
        return PsiFileFactory.getInstance(project).createFileFromText(name, FrgFileType, content) as FrgFile
    }
}

