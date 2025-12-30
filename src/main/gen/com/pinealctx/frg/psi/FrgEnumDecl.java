// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FrgEnumDecl extends FrgNamedElement {

  @NotNull
  List<FrgEnumMember> getEnumMemberList();

  @NotNull
  FrgIdentifier getIdentifier();

  @Nullable String getName();

  @NotNull PsiElement setName(@NotNull String name);

  @Nullable PsiElement getNameIdentifier();

}
