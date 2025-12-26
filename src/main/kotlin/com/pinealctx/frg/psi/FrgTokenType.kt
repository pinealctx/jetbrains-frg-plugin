package com.pinealctx.frg.psi

import com.intellij.psi.tree.IElementType
import com.pinealctx.frg.FrgLanguage
import org.jetbrains.annotations.NonNls

class FrgTokenType(@NonNls debugName: String) : IElementType(debugName, FrgLanguage) {
    override fun toString(): String = "FrgTokenType." + super.toString()
}
