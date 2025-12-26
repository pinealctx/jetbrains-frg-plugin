package com.pinealctx.frg.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.pinealctx.frg.FrgFileType
import com.pinealctx.frg.FrgLanguage

class FrgFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FrgLanguage) {
    override fun getFileType(): FileType = FrgFileType
    override fun toString(): String = "FRG File"
}
