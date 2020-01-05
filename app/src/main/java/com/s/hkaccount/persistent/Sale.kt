package com.s.hkaccount.persistent

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by chenjunsheng on 2020/1/4
 */
@Entity(tableName = "sale")
data class Sale(var customerId: Long,
                var productId: Int,
                var rmbPrice: Float,
                var saleCount: Int) {
    @PrimaryKey
    var id: Int = 0
}