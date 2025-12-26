package com.pinealctx.frg

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object FrgFileType : LanguageFileType(FrgLanguage) {
    override fun getName(): String = "FRG File"
    override fun getDescription(): String = "FRG language file"
    override fun getDefaultExtension(): String = "frg"
    override fun getIcon(): Icon? = null
}
