package com.s.hkaccount.group.listener

import android.view.View
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.viewholder.BaseViewHolder

/**
 * Create by chenjunsheng on 2020/1/4
 */
interface OnNodeProviderItemClick {

    fun onProviderItemClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int)
}