package com.s.hkaccounting.ui

import android.view.View
import com.s.hkaccounting.R
import com.s.hkaccounting.group.entity.node.BaseNode
import com.s.hkaccounting.group.provider.BaseNodeProvider
import com.s.hkaccounting.group.viewholder.BaseViewHolder

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerMsgProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 0

    override val layoutId: Int
        get() = R.layout.customer_msg_layout

    override fun convert(helper: BaseViewHolder, data: BaseNode?) {
        helper.setText(R.id.name, "大神升")
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()?.expandOrCollapse(position)
    }
}