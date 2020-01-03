package com.s.hkaccount.ui

import com.s.hkaccount.R
import com.s.hkaccount.group.BaseNodeAdapter
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.listener.OnItemChildClickListener

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerNodeAdapter : BaseNodeAdapter() {

    init {
        addFullSpanNodeProvider(CustomerMsgProvider())
        addNodeProvider(CustomerItemProvider())
        addChildClickViewIds(R.id.add_commodity)
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