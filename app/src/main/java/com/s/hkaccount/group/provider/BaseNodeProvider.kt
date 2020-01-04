package com.s.hkaccount.group.provider

import android.view.View
import com.s.hkaccount.group.BaseNodeAdapter
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.listener.OnItemClickListener
import com.s.hkaccount.group.listener.OnNodeProviderItemClick
import com.s.hkaccount.group.viewholder.BaseViewHolder

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    var onNodeProviderItemClick: OnNodeProviderItemClick? = null

    override fun getAdapter(): BaseNodeAdapter? {
        return super.getAdapter() as? BaseNodeAdapter
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        onNodeProviderItemClick?.onProviderItemClick(helper, view, data, position)
    }
}