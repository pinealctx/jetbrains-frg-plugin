// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FrgTypeName extends PsiElement {

  @Nullable
  FrgArrayType getArrayType();

  @Nullable
  FrgMapType getMapType();

  @Nullable
  FrgPointerType getPointerType();

  @Nullable
  FrgPrimitiveType getPrimitiveType();

  @Nullable
  FrgQualifiedName getQualifiedName();

}
