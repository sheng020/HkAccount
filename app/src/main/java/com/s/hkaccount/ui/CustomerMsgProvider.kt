package com.s.hkaccount.ui

import android.util.Log
import android.view.View
import com.s.hkaccount.R
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.provider.BaseNodeProvider
import com.s.hkaccount.group.viewholder.BaseViewHolder

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerMsgProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 0

    override val layoutId: Int
        get() = R.layout.customer_msg_layout

    override fun convert(helper: BaseViewHolder, data: BaseNode?) {
        val rootCustomerNode = data as? RootCustomerNode
        Log.d("cjslog", "on base view holde")
        helper.setText(R.id.name, rootCustomerNode?.customer?.name ?: "")
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()?.expandOrCollapse(position)
    }
}