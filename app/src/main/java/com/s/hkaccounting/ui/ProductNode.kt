package com.s.hkaccounting.ui

import com.s.hkaccounting.group.entity.node.BaseNode

/**
 * Create by chenjunsheng on 2020/1/2
 */
class ProductNode(val productName: String,
                  val hkDollar: Float,
                  val bought: Boolean) : BaseNode() {

    override val childNode: MutableList<BaseNode>?
        get() = null


}