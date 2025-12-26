package com.pinealctx.frg

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.pinealctx.frg.psi.FrgEnumDecl
import com.pinealctx.frg.psi.FrgFile
import com.pinealctx.frg.psi.FrgTypeDecl
import com.pinealctx.frg.psi.FrgExternDefs
import java.util.*

object FrgUtil {
    fun findExternDefs(project: Project, key: String?): List<PsiElement> {
        val result: MutableList<PsiElement> = ArrayList()
        val virtualFiles = FileTypeIndex.getFiles(FrgFileType, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val frgFile = PsiManager.getInstance(project).findFile(virtualFile) as? FrgFile ?: continue
            val externDefsList = PsiTreeUtil.findChildrenOfType(frgFile, FrgExternDefs::class.java)
            for (externDefs in externDefsList) {
                val defs = externDefs.externDefList
                for (def in defs) {
                    val keyValues = def.keyValueList
                    for (kv in keyValues) {
                        val id = kv.identifier
                        if (id.text == "name") {
                            val value = kv.value
                            // value text includes quotes, e.g. "tex.JsInt64"
                            val text = value.text.trim('"', '`')
                            if (key == null || text == key) {
                                result.add(value)
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    fun findTypeDeclarations(project: Project, key: String?): List<FrgTypeDecl> {
        val result: MutableList<FrgTypeDecl> = ArrayList()
        val virtualFiles = FileTypeIndex.getFiles(FrgFileType, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val frgFile = PsiManager.getInstance(project).findFile(virtualFile) as? FrgFile ?: continue
            val typeDecls = PsiTreeUtil.findChildrenOfType(frgFile, FrgTypeDecl::class.java)
            for (typeDecl in typeDecls) {
                if (key == null || typeDecl.identifier.text == key) {
                    result.add(typeDecl)
                }
            }
        }
        return result
    }

    fun findEnumDeclarations(project: Project, key: String?): List<FrgEnumDecl> {
        val result: MutableList<FrgEnumDecl> = ArrayList()
        val virtualFiles = FileTypeIndex.getFiles(FrgFileType, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val frgFile = PsiManager.getInstance(project).findFile(virtualFile) as? FrgFile ?: continue
            val enumDecls = PsiTreeUtil.findChildrenOfType(frgFile, FrgEnumDecl::class.java)
            for (enumDecl in enumDecls) {
                if (key == null || enumDecl.identifier.text == key) {
                    result.add(enumDecl)
                }
            }
        }
        return result
    }

    fun findGoFiles(project: Project): Collection<VirtualFile> {
        // Simple search for all files ending with .go
        // In a real plugin, you might want to use GoFileType if the Go plugin is available
        return com.intellij.psi.search.FilenameIndex.getAllFilesByExt(project, "go", GlobalSearchScope.allScope(project))
    }
}
