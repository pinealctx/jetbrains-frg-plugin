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
            if (child.elementType !== TokenType.WHITE_SPACE && child.elementType !== FrgTypes.NEWLINE) {
                val childType = child.elementType
                var alignmentForChild = alignmentMap?.get(childType)

                if (childType == FrgTypes.COMMENT && alignmentForChild != null) {
                    if (!isEndOfLineComment(child)) {
                        alignmentForChild = null
                    }
                }

                // Disable alignment for anonymous fields (e.g. "User" in "type UserDetail { User ... }")
                // We only want to align types in NormalFields (e.g. "*Org" in "Org *Org")
                if (myNode.elementType == FrgTypes.ANONYMOUS_FIELD) {
                    alignmentForChild = null
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
        var prev = node.treePrev
        while (prev != null && prev.elementType == TokenType.WHITE_SPACE) {
            if (prev.textContains('\n')) return false
            prev = prev.treePrev
        }
        if (prev == null) return false
        if (prev.elementType == FrgTypes.NEWLINE) return false
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
                // For cases where typeName might be transparent or we want to align specific types
                FrgTypes.POINTER_TYPE to typeAlignment,
                FrgTypes.QUALIFIED_NAME to typeAlignment,
                FrgTypes.MAP_TYPE to typeAlignment,
                FrgTypes.ARRAY_TYPE to typeAlignment,
                FrgTypes.PRIMITIVE_TYPE to typeAlignment,
                // Add primitive type tokens directly in case the rule is collapsed
                FrgTypes.STRING to typeAlignment,
                FrgTypes.BOOL to typeAlignment,
                FrgTypes.INT to typeAlignment,
                FrgTypes.INT32 to typeAlignment,
                FrgTypes.INT64 to typeAlignment,
                FrgTypes.FLOAT to typeAlignment,
                FrgTypes.FLOAT32 to typeAlignment,
                FrgTypes.FLOAT64 to typeAlignment,
                FrgTypes.DOUBLE to typeAlignment,
                FrgTypes.INTERFACE to typeAlignment,
                // Add STRING_LITERAL for tags in case the tag rule is collapsed
                FrgTypes.STRING_LITERAL to tagAlignment
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
            elementType == FrgTypes.EXTERN_DEF
        ) {
            return Indent.getNormalIndent()
        }

        // Fallback for when wrapper nodes are transparent/collapsed
        if (parentType == FrgTypes.TYPE_DECL && (
            elementType == FrgTypes.NORMAL_FIELD || 
            elementType == FrgTypes.ANONYMOUS_FIELD
        )) {
            return Indent.getNormalIndent()
        }

        if (parentType == FrgTypes.SERVICE_DECL && (
            elementType == FrgTypes.HANDLER_METADATA ||
            elementType == FrgTypes.DOC_METADATA ||
            elementType == FrgTypes.AUTH_METADATA ||
            elementType == FrgTypes.ROUTE ||
            elementType == FrgTypes.RPC_ROUTE
        )) {
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
        if (myNode.elementType == FrgTypes.NORMAL_FIELD ||
            myNode.elementType == FrgTypes.ANONYMOUS_FIELD) {
            return Spacing.createSpacing(1, 1, 0, false, 0)
        }

        val type1 = (child1 as? AbstractBlock)?.node?.elementType
        val type2 = (child2 as? AbstractBlock)?.node?.elementType

        // Enforce blank line between Route and next Comment (if not trailing)
        if ((type1 == FrgTypes.ROUTE || type1 == FrgTypes.RPC_ROUTE) && type2 == FrgTypes.COMMENT) {
            val node2 = (child2 as? AbstractBlock)?.node
            if (node2 != null && !isEndOfLineComment(node2)) {
                return Spacing.createSpacing(0, 0, 2, true, 0)
            }
        }

        if (type2 == FrgTypes.COMMENT) {
            return Spacing.createSpacing(1, 1, 0, true, 0)
        }
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}
