package com.s.hkaccounting.persistent

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Entity(tableName = "account")
data class Account(
    @PrimaryKey
    var id: Int,
    var name: String,
    var products: String,

    var date: Int,
    var buying_price: Float,
    var sale_price: Float
)