package com.s.hkaccount.persistent

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Create by chenjunsheng on 2020/1/3
 */
@Entity(
    tableName = "customer",
    indices = [Index(value = ["name"], unique = true)]
)
data class Customer(
    @ColumnInfo(defaultValue = "")
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}