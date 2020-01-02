package com.s.hkaccounting.ui

import com.s.hkaccounting.R
import com.s.hkaccounting.group.entity.node.BaseNode
import com.s.hkaccounting.group.provider.BaseNodeProvider
import com.s.hkaccounting.group.viewholder.BaseViewHolder

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerItemProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 1

    override val layoutId: Int
        get() = R.layout.customer_buy_detail

    override fun convert(helper: BaseViewHolder, data: BaseNode?) {
        helper.setText(R.id.name, "11111")
    }
}