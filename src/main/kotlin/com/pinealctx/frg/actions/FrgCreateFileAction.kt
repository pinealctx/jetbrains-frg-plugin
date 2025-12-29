package com.pinealctx.frg.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.pinealctx.frg.FrgFileType

class FrgCreateFileAction : CreateFileFromTemplateAction(
    "FRG File",
    "Creates a new FRG file",
    FrgFileType.icon
) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New FRG File")
            .addKind("FRG File", FrgFileType.icon, "Frg File")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String {
        return "Create FRG File $newName"
    }
}
