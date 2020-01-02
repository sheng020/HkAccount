package com.s.hkaccounting.ui

import com.s.hkaccounting.group.entity.node.BaseExpandNode
import com.s.hkaccounting.group.entity.node.BaseNode

/**
 * Create by chenjunsheng on 2020/1/2
 */
class RootCustomerNode(private val children: MutableList<BaseNode>) : BaseExpandNode() {

    override val childNode: MutableList<BaseNode>?
        get() = children
}