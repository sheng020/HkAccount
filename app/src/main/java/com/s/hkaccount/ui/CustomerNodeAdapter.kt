package com.s.hkaccount.ui

import com.s.hkaccount.group.BaseNodeAdapter
import com.s.hkaccount.group.entity.node.BaseNode

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerNodeAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(CustomerMsgProvider())
        addNodeProvider(CustomerItemProvider())
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val baseNode = data[position]
        return if (baseNode is RootCustomerNode) {
            0
        } else {
            1
        }
    }
}