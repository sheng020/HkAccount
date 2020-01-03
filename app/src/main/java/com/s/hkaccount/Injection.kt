package com.s.hkaccount

import android.content.Context
import com.s.hkaccount.persistent.AccountDao
import com.s.hkaccount.persistent.AccountDatabase
import com.s.hkaccount.ui.ViewModelFactory

/**
 * Create by chenjunsheng on 2020/1/2
 */
object Injection {

    fun provideAccountDataSource(context: Context) : AccountDao {
        val database = AccountDatabase.getInstance(context)
        return database.accountDao()
    }

    fun provideViewModelFactory(context: Context) : ViewModelFactory {
        val dataSource = provideAccountDataSource(context)
        return ViewModelFactory(dataSource)
    }
}