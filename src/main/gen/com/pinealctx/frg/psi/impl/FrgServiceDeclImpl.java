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
  public List<FrgServiceBody> getServiceBodyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FrgServiceBody.class);
  }

}
