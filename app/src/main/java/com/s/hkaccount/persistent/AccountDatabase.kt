package com.s.hkaccount.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Database(entities = [Product::class, Customer::class], version = 1)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountDao() : AccountDao

    companion object {

        @Volatile private var INSTANCE : AccountDatabase? = null

        fun getInstance(context: Context) : AccountDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        fun buildDatabase(context: Context) : AccountDatabase =
            Room.databaseBuilder(context.applicationContext,
                AccountDatabase::class.java, "Account.db")
                .build()
    }
}