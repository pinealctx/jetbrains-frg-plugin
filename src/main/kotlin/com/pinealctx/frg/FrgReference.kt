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
    PsiReferenceBase<PsiElement>(element, TextRange(0, element.textLength)) { // Adjust range relative to element

    private val key: String = element.text

    private val GO_BUILTINS = setOf(
        "bool", "string", "int", "int8", "int16", "int32", "int64",
        "uint", "uint8", "uint16", "uint32", "uint64", "uintptr",
        "byte", "rune", "float32", "float64", "complex64", "complex128",
        "interface{}", "any", "error"
    )

    override fun resolve(): PsiElement? {
        // LOG.info("FrgTypeReference: Resolving '$key'")
        if (GO_BUILTINS.contains(key)) {
            return myElement
        }
        val project = myElement.project
        val types = FrgUtil.findTypeDeclarations(project, key)
        if (types.isNotEmpty()) {
            // LOG.info("FrgTypeReference: Found type declaration for '$key'")
            // Return the identifier element (which wraps the ID)
            return types[0].identifier
        }
        val enums = FrgUtil.findEnumDeclarations(project, key)
        if (enums.isNotEmpty()) {
            // LOG.info("FrgTypeReference: Found enum declaration for '$key'")
            return enums[0].identifier
        }
        val externs = FrgUtil.findExternDefs(project, key)
        if (externs.isNotEmpty()) {
            // LOG.info("FrgTypeReference: Found extern def for '$key'")
            return externs[0]
        }
        // LOG.info("FrgTypeReference: Could not resolve '$key'")
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

class FrgHandlerReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement>(element, TextRange(0, element.textLength)) {

    private val handlerName: String = element.text

    override fun resolve(): PsiElement? {
        // This is a simplified implementation. 
        // Ideally we should parse Go files to find the exact function.
        // Here we just try to find a Go file that *might* contain the handler, 
        // or just return null but allow navigation via other means if we implemented a Go parser.
        
        // Since we don't have a Go parser here, we will try to find the file text content.
        // But resolve() must return a PsiElement.
        // We can return a PsiFile if we find a file that seems relevant, 
        // or we can scan all Go files and regex match the function.
        
        val project = myElement.project
        val goFiles = FrgUtil.findGoFiles(project)
        
        // Convert camelCase to PascalCase for Go function name convention
        val pascalWord = handlerName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val regex = Regex("func\\s+.*${pascalWord}\\s*\\(")

        for (virtualFile in goFiles) {
            val psiFile = PsiManager.getInstance(project).findFile(virtualFile) ?: continue
            val text = psiFile.text
            if (regex.containsMatchIn(text)) {
                // If we found the file, we can return the file itself as the target.
                // Or better, try to find the offset and return a PsiElement at that offset.
                // Since we can't easily create a PsiElement for a random offset in a non-supported language file without the plugin,
                // we will just return the PsiFile. 
                // The user will jump to the beginning of the file.
                // To jump to specific line, we would need to implement a custom navigation or use a FakePsiElement.
                return psiFile
            }
        }
        
        return null
    }

    override fun getVariants(): Array<Any> {
        return emptyArray()
    }
}
