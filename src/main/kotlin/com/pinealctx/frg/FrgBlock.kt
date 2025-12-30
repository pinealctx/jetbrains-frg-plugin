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
                var alignmentForChild = alignmentMap?.get(childType)

                if (childType == FrgTypes.COMMENT && alignmentForChild != null) {
                    if (!isEndOfLineComment(child)) {
                        alignmentForChild = null
                    }
                }
                
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

    private fun isEndOfLineComment(node: ASTNode): Boolean {
        val prev = node.treePrev ?: return false
        if (prev.elementType == TokenType.WHITE_SPACE) {
            return !prev.textContains('\n')
        }
        return true
    }

    private fun createChildAlignmentMap(): Map<IElementType, Alignment>? {
        if (myNode.elementType == FrgTypes.TYPE_DECL) {
            val typeAlignment = Alignment.createAlignment(true)
            val tagAlignment = Alignment.createAlignment(true)
            val commentAlignment = Alignment.createAlignment(true)
            return mapOf(
                FrgTypes.TYPE_NAME to typeAlignment,
                FrgTypes.TAG to tagAlignment,
                FrgTypes.COMMENT to commentAlignment,
                // For anonymous fields
                FrgTypes.POINTER_TYPE to typeAlignment,
                FrgTypes.QUALIFIED_NAME to typeAlignment,
                FrgTypes.MAP_TYPE to typeAlignment,
                FrgTypes.ARRAY_TYPE to typeAlignment,
                FrgTypes.PRIMITIVE_TYPE to typeAlignment
            )
        }
        if (myNode.elementType == FrgTypes.ENUM_DECL) {
            val assignAlignment = Alignment.createAlignment(true)
            val commentAlignment = Alignment.createAlignment(true)
            return mapOf(
                FrgTypes.ASSIGN to assignAlignment,
                FrgTypes.COMMENT to commentAlignment
            )
        }
        return childAlignments
    }


    override fun getIndent(): Indent? {
        val elementType = myNode.elementType
        val parentType = myNode.treeParent?.elementType

        if (elementType == FrgTypes.TYPE_FIELD ||
            elementType == FrgTypes.ENUM_MEMBER ||
            elementType == FrgTypes.SERVICE_BODY ||
            elementType == FrgTypes.EXTERN_DEF
        ) {
            return Indent.getNormalIndent()
        }

        if (elementType == FrgTypes.KEY_VALUE) {
            if (parentType == FrgTypes.INFO_BLOCK ||
                parentType == FrgTypes.ATTR_BLOCK ||
                parentType == FrgTypes.DOC_METADATA
            ) {
                return Indent.getNormalIndent()
            }
        }

        if (elementType == FrgTypes.COMMENT) {
            if (parentType == FrgTypes.TYPE_DECL ||
                parentType == FrgTypes.ENUM_DECL ||
                parentType == FrgTypes.SERVICE_DECL ||
                parentType == FrgTypes.INFO_BLOCK ||
                parentType == FrgTypes.EXTERN_DEFS ||
                parentType == FrgTypes.ATTR_BLOCK ||
                parentType == FrgTypes.DOC_METADATA
            ) {
                return Indent.getNormalIndent()
            }
        }

        return Indent.getNoneIndent()
    }

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        if (myNode.elementType == FrgTypes.TYPE_DECL ||
            myNode.elementType == FrgTypes.ENUM_DECL ||
            myNode.elementType == FrgTypes.SERVICE_DECL ||
            myNode.elementType == FrgTypes.EXTERN_DEFS ||
            myNode.elementType == FrgTypes.INFO_BLOCK ||
            myNode.elementType == FrgTypes.ATTR_BLOCK ||
            myNode.elementType == FrgTypes.DOC_METADATA) {
            return ChildAttributes(Indent.getNormalIndent(), null)
        }
        return ChildAttributes(Indent.getNoneIndent(), null)
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}
