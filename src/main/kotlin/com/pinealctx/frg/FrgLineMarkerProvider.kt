package com.pinealctx.frg

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.pinealctx.frg.psi.FrgHandlerMetadata
import com.pinealctx.frg.psi.FrgTypes

class FrgLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        // Ensure we only run for the ID inside HandlerMetadata
        if (element.node.elementType == FrgTypes.ID && element.parent is FrgHandlerMetadata) {
            val project = element.project
            val handlerName = element.text
            
            // Reuse the logic from FrgHandlerReference to find the file
            // Ideally this logic should be in FrgUtil
            val goFiles = FrgUtil.findGoFiles(project)
            val pascalWord = handlerName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            val regex = Regex("func\\s+.*${pascalWord}\\s*\\(")

            val targets = goFiles.mapNotNull { virtualFile ->
                val psiFile = com.intellij.psi.PsiManager.getInstance(project).findFile(virtualFile)
                if (psiFile != null && regex.containsMatchIn(psiFile.text)) {
                    psiFile
                } else {
                    null
                }
            }

            if (targets.isNotEmpty()) {
                val builder = NavigationGutterIconBuilder.create(AllIcons.General.ArrowRight)
                    .setTargets(targets)
                    .setTooltipText("Navigate to Go Handler")
                result.add(builder.createLineMarkerInfo(element))
            }
        }
    }
}
