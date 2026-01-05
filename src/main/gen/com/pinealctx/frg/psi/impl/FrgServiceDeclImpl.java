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

public class FrgServiceDeclImpl extends ASTWrapperPsiElement implements FrgServiceDecl {

  public FrgServiceDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull FrgVisitor visitor) {
    visitor.visitServiceDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof FrgVisitor) accept((FrgVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public FrgAttrBlock getAttrBlock() {
    return findChildByClass(FrgAttrBlock.class);
  }

  @Override
  @NotNull
  public List<FrgAuthMetadata> getAuthMetadataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgAuthMetadata.class);
  }

  @Override
  @NotNull
  public List<FrgDocMetadata> getDocMetadataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgDocMetadata.class);
  }

  @Override
  @NotNull
  public List<FrgHandlerMetadata> getHandlerMetadataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgHandlerMetadata.class);
  }

  @Override
  @NotNull
  public List<FrgRoute> getRouteList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgRoute.class);
  }

  @Override
  @NotNull
  public List<FrgRpcRoute> getRpcRouteList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgRpcRoute.class);
  }

}
