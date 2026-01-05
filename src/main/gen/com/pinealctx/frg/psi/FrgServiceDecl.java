// This is a generated file. Not intended for manual editing.
package com.pinealctx.frg.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FrgServiceDecl extends PsiElement {

  @Nullable
  FrgAttrBlock getAttrBlock();

  @NotNull
  List<FrgAuthMetadata> getAuthMetadataList();

  @NotNull
  List<FrgDocMetadata> getDocMetadataList();

  @NotNull
  List<FrgHandlerMetadata> getHandlerMetadataList();

  @NotNull
  List<FrgRoute> getRouteList();

  @NotNull
  List<FrgRpcRoute> getRpcRouteList();

}
