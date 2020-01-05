package com.s.hkaccount.persistent

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Entity(tableName = "product")
data class Product(
    var customerId: Long, //客户id
    var name: String,
    var buy_count: Int,
    var date: String,
    var buying_price: Float,
    var bought: Boolean,
    var totalPrice: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}