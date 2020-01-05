package com.s.hkaccount.ui

import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product

/**
 * Create by chenjunsheng on 2020/1/3
 */

fun transformRootCustomerNode(customer: Customer): RootCustomerNode {
    return RootCustomerNode(arrayListOf(), customer)
}

fun transformProductNode(product: Product): ProductNode {
    return ProductNode(product)
}

fun transformTreeNode(customers: List<Customer>, products: List<Product>): MutableList<BaseNode> {
    val trees = arrayListOf<BaseNode>()
    customers.forEach { customer: Customer ->
        val childNode = arrayListOf<BaseNode>()
        products.forEach { products ->
            if (products.customerId == customer.id) {
                childNode.add(transformProductNode(products))
            }
        }
        val customerNode = transformRootCustomerNode(customer)
            .apply {
                addProductNode(childNode)
            }
        trees.add(customerNode)
    }
    return trees
}

fun findProductPosition(data: List<BaseNode>, product: Product): Int {
    data.forEachIndexed { index, baseNode ->
        if (baseNode is RootCustomerNode) {
            if (baseNode.customer.id == product.customerId) { //找到所属客户
                baseNode.childNode?.forEachIndexed { index1, baseNode1 ->
                    //找到客户下面的商品位置
                    if (baseNode1 is ProductNode) {
                        if (baseNode1.product.id == product.id) {
                            return index + index1 + 1
                        }
                    }
                }

            }
        }
    }
    return -1
}