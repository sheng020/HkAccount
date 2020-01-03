package com.s.hkaccount.ui

import com.s.hkaccount.group.entity.node.BaseExpandNode
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.persistent.Customer

/**
 * Create by chenjunsheng on 2020/1/2
 */
class RootCustomerNode(private val children: MutableList<BaseNode>?,
                       val customer: Customer) : BaseExpandNode() {

    override val childNode: MutableList<BaseNode>?
        get() = children
}