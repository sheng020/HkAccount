package com.s.hkaccount.ui

import android.widget.CheckBox
import com.s.hkaccount.R
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.provider.BaseNodeProvider
import com.s.hkaccount.group.viewholder.BaseViewHolder

/**
 * Create by chenjunsheng on 2020/1/2
 */
class CustomerItemProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 1

    override val layoutId: Int
        get() = R.layout.customer_buy_detail2

    override fun convert(helper: BaseViewHolder, data: BaseNode?) {
        val productNode = data as? ProductNode ?: return
        helper.setText(R.id.name_tv, productNode.product.name)
        helper.setText(R.id.hk_price_tv, productNode.product.buying_price.toString())
        helper.setText(R.id.buy_count_tv, productNode.product.buy_count.toString())
        helper.getView<CheckBox>(R.id.bought).isChecked = productNode.product.bought
    }
}