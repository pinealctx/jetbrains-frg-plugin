package com.pinealctx.frg

import com.intellij.openapi.diagnostic.Logger
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.pinealctx.frg.psi.FrgHandlerMetadata
import com.pinealctx.frg.psi.FrgIdentifier
import com.pinealctx.frg.psi.FrgQualifiedName
import com.pinealctx.frg.psi.FrgTypes
import com.intellij.psi.util.PsiTreeUtil

class FrgReferenceContributor : PsiReferenceContributor() {
    private val LOG = Logger.getInstance(FrgReferenceContributor::class.java)

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        // Generic provider for ID tokens
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(FrgTypes.ID),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    // LOG.info("FrgReferenceContributor: Checking element '${element.text}'")
                    
                    // Debug: Print parent chain
                    // var parent = element.parent
                    // while (parent != null) {
                    //    LOG.info("  Parent: ${parent.javaClass.simpleName}")
                    //    parent = parent.parent
                    // }

                    // Check if this ID is part of a QualifiedName (Type usage)
                    val qualifiedName = PsiTreeUtil.getParentOfType(element, FrgQualifiedName::class.java)
                    if (qualifiedName != null) {
                        // LOG.info("FrgReferenceContributor: Found QualifiedName parent for '${element.text}'")
                        return arrayOf(FrgTypeReference(element, element.textRange))
                    }
                    
                    // Check if this ID is part of a HandlerMetadata (Handler usage)
                    // HandlerMetadata -> Identifier -> ID
                    val identifier = PsiTreeUtil.getParentOfType(element, FrgIdentifier::class.java)
                    if (identifier != null) {
                        val handlerMetadata = PsiTreeUtil.getParentOfType(identifier, FrgHandlerMetadata::class.java)
                        if (handlerMetadata != null) {
                             LOG.info("FrgReferenceContributor: Found HandlerMetadata parent for '${element.text}'")
                             return arrayOf(FrgHandlerReference(element, element.textRange))
                        }
                    }

                    return PsiReference.EMPTY_ARRAY
                }
            }
        )
    }
}
