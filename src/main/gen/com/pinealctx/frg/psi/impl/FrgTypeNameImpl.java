// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.pinealctx.frg.psi.FrgTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.pinealctx.frg.psi.*;

public class FrgTypeNameImpl extends ASTWrapperPsiElement implements FrgTypeName {

  public FrgTypeNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FrgVisitor visitor) {
    visitor.visitTypeName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FrgVisitor) accept((FrgVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FrgArrayType getArrayType() {
    return findChildByClass(FrgArrayType.class);
  }

  @Override
  @Nullable
  public FrgMapType getMapType() {
    return findChildByClass(FrgMapType.class);
  }

  @Override
  @Nullable
  public FrgPointerType getPointerType() {
    return findChildByClass(FrgPointerType.class);
  }

  @Override
  @Nullable
  public FrgPrimitiveType getPrimitiveType() {
    return findChildByClass(FrgPrimitiveType.class);
  }

  @Override
  @Nullable
  public FrgQualifiedName getQualifiedName() {
    return findChildByClass(FrgQualifiedName.class);
  }

}
