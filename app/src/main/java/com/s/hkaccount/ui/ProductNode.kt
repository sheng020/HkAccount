package com.s.hkaccount.ui

import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.persistent.Product

/**
 * Create by chenjunsheng on 2020/1/2
 */
class ProductNode(val product: Product) : BaseNode() {

    override val childNode: MutableList<BaseNode>?
        get() = null


}