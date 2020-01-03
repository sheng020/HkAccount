package com.s.hkaccount.ui

import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product

/**
 * Create by chenjunsheng on 2020/1/3
 */

fun transformRootCustomerNode(customer: Customer) : RootCustomerNode {
    return RootCustomerNode(null, customer)
}

fun transformProductNode(product: Product) : ProductNode {
    return ProductNode(product.name, product.buying_price, product.bought)
}

fun transformTreeNode(customers: List<Customer>, products: List<Product>) : MutableList<BaseNode> {
    val trees = arrayListOf<BaseNode>()
    customers.forEach { customer: Customer ->
        val childNode = arrayListOf<BaseNode>()
        products.forEach { products->
            if (products.id == customer.id) {
                childNode.add(transformProductNode(products))
            }
        }
        val customerNode = transformRootCustomerNode(customer)
        trees.add(customerNode)
    }
    return trees
}