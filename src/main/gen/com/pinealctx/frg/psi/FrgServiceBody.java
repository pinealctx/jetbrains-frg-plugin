// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FrgServiceBody extends PsiElement {

  @Nullable
  FrgAuthMetadata getAuthMetadata();

  @Nullable
  FrgDocMetadata getDocMetadata();

  @Nullable
  FrgHandlerMetadata getHandlerMetadata();

  @Nullable
  FrgRoute getRoute();

  @Nullable
  FrgRpcRoute getRpcRoute();

}
