package com.s.hkaccount.group.provider

import com.s.hkaccount.group.BaseNodeAdapter
import com.s.hkaccount.group.entity.node.BaseNode

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    override fun getAdapter(): BaseNodeAdapter? {
        return super.getAdapter() as? BaseNodeAdapter
    }

}