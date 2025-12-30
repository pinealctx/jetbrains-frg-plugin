// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FrgRoute extends PsiElement {

  @Nullable
  FrgRequestType getRequestType();

  @Nullable
  FrgResponseType getResponseType();

  @NotNull
  FrgRoutePath getRoutePath();

}
