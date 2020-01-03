package com.s.hkaccount.persistent

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Entity(tableName = "product",
    foreignKeys = [ForeignKey(entity = Customer::class,
        parentColumns = ["id"],
        childColumns = ["id"])])
data class Product(
    @PrimaryKey
    var id: Long, //客户id
    var name: String,

    var date: Int,
    var buying_price: Float,
    var sale_price: Float,
    var bought: Boolean
)