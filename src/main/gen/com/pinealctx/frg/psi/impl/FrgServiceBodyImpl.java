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

public class FrgServiceBodyImpl extends ASTWrapperPsiElement implements FrgServiceBody {

  public FrgServiceBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FrgVisitor visitor) {
    visitor.visitServiceBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FrgVisitor) accept((FrgVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FrgAuthMetadata getAuthMetadata() {
    return findChildByClass(FrgAuthMetadata.class);
  }

  @Override
  @Nullable
  public FrgDocMetadata getDocMetadata() {
    return findChildByClass(FrgDocMetadata.class);
  }

  @Override
  @Nullable
  public FrgHandlerMetadata getHandlerMetadata() {
    return findChildByClass(FrgHandlerMetadata.class);
  }

  @Override
  @Nullable
  public FrgRoute getRoute() {
    return findChildByClass(FrgRoute.class);
  }

  @Override
  @Nullable
  public FrgRpcRoute getRpcRoute() {
    return findChildByClass(FrgRpcRoute.class);
  }

}
