package com.pinealctx.frg

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.tree.IElementType
import com.pinealctx.frg.psi.FrgTypes

class FrgBlock(
    node: ASTNode,
    wrap: Wrap?,
    alignment: Alignment?,
    private val spacingBuilder: SpacingBuilder,
    private val childAlignments: Map<IElementType, Alignment>? = null
) : AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): List<Block> {
        val blocks = ArrayList<Block>()
        
        val alignmentMap = createChildAlignmentMap()
        
        var child = myNode.firstChildNode
        while (child != null) {
            if (child.elementType !== TokenType.WHITE_SPACE) {
                val childType = child.elementType
                val alignmentForChild = alignmentMap?.get(childType)
                
                val block = FrgBlock(
                    child,
                    Wrap.createWrap(WrapType.NONE, false),
                    alignmentForChild,
                    spacingBuilder,
                    alignmentMap
                )
                blocks.add(block)
            }
            child = child.treeNext
        }
        return blocks
    }

    private fun createChildAlignmentMap(): Map<IElementType, Alignment>? {
        if (myNode.elementType == FrgTypes.TYPE_DECL) {
            val typeAlignment = Alignment.createAlignment(true)
            val tagAlignment = Alignment.createAlignment(true)
            return mapOf(
                FrgTypes.TYPE_NAME to typeAlignment,
                FrgTypes.TAG to tagAlignment,
                // For anonymous fields
                FrgTypes.POINTER_TYPE to typeAlignment,
                FrgTypes.QUALIFIED_NAME to typeAlignment,
                FrgTypes.MAP_TYPE to typeAlignment,
                FrgTypes.ARRAY_TYPE to typeAlignment,
                FrgTypes.PRIMITIVE_TYPE to typeAlignment
            )
        }
        return childAlignments
    }


    override fun getIndent(): Indent? {
        val psi = myNode.psi
        val parent = psi.parent
        
        // Top level elements should have no indent (force column 0)
        if (parent is com.intellij.psi.PsiFile) {
            return Indent.getAbsoluteNoneIndent()
        }

        val elementType = myNode.elementType
        val parentType = myNode.treeParent?.elementType

        if (parentType == FrgTypes.TYPE_DECL ||
            parentType == FrgTypes.ENUM_DECL ||
            parentType == FrgTypes.SERVICE_DECL ||
            parentType == FrgTypes.INFO_BLOCK ||
            parentType == FrgTypes.EXTERN_DEFS ||
            parentType == FrgTypes.ATTR_BLOCK ||
            parentType == FrgTypes.DOC_METADATA
        ) {
            if (elementType == FrgTypes.LBRACE || elementType == FrgTypes.RBRACE ||
                elementType == FrgTypes.LPAREN || elementType == FrgTypes.RPAREN
            ) {
                return Indent.getNoneIndent()
            }
            return Indent.getNormalIndent()
        }
        
        return Indent.getNoneIndent()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}
