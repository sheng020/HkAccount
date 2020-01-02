package com.s.hkaccounting.group.provider

import com.s.hkaccounting.group.BaseNodeAdapter
import com.s.hkaccounting.group.entity.node.BaseNode

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    override fun getAdapter(): BaseNodeAdapter? {
        return super.getAdapter() as? BaseNodeAdapter
    }

}