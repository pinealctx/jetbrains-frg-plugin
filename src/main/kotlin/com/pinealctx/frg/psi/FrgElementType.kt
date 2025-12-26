package com.pinealctx.frg.psi

import com.intellij.psi.tree.IElementType
import com.pinealctx.frg.FrgLanguage
import org.jetbrains.annotations.NonNls

class FrgElementType(@NonNls debugName: String) : IElementType(debugName, FrgLanguage)
