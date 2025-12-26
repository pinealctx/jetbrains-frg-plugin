package com.pinealctx.frg

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.pinealctx.frg.psi.FrgEnumDecl
import com.pinealctx.frg.psi.FrgTypeDecl
import java.util.ArrayList

class FrgTypeReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement>(element, textRange) {

    private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    private val GO_BUILTINS = setOf(
        "bool", "string", "int", "int8", "int16", "int32", "int64",
        "uint", "uint8", "uint16", "uint32", "uint64", "uintptr",
        "byte", "rune", "float32", "float64", "complex64", "complex128",
        "interface{}", "any", "error"
    )

    override fun resolve(): PsiElement? {
        // println("Resolving key: $key")
        if (GO_BUILTINS.contains(key)) {
            return null
        }
        val project = myElement.project
        val types = FrgUtil.findTypeDeclarations(project, key)
        if (types.isNotEmpty()) {
            return types[0].identifier
        }
        val enums = FrgUtil.findEnumDeclarations(project, key)
        if (enums.isNotEmpty()) {
            return enums[0].identifier
        }
        val externs = FrgUtil.findExternDefs(project, key)
        if (externs.isNotEmpty()) {
            return externs[0]
        }
        return null
    }

    override fun getVariants(): Array<Any> {
        val project = myElement.project
        val variants: MutableList<LookupElement> = ArrayList()
        
        for (builtin in GO_BUILTINS) {
             variants.add(LookupElementBuilder.create(builtin).withIcon(FrgFileType.icon))
        }

        val types = FrgUtil.findTypeDeclarations(project, null)
        for (type in types) {
            if (type.identifier.text.isNotEmpty()) {
                variants.add(
                    LookupElementBuilder.create(type).withIcon(FrgFileType.icon)
                        .withTypeText(type.containingFile.name)
                )
            }
        }
        
        val enums = FrgUtil.findEnumDeclarations(project, null)
        for (enumDecl in enums) {
             if (enumDecl.identifier.text.isNotEmpty()) {
                variants.add(
                    LookupElementBuilder.create(enumDecl).withIcon(FrgFileType.icon)
                        .withTypeText(enumDecl.containingFile.name)
                )
            }
        }

        val externs = FrgUtil.findExternDefs(project, null)
        for (ext in externs) {
             val name = ext.text.trim('"', '`')
             if (name.isNotEmpty()) {
                 variants.add(LookupElementBuilder.create(ext, name).withIcon(FrgFileType.icon).withTypeText("Extern"))
             }
        }

        return variants.toTypedArray()
    }
}
