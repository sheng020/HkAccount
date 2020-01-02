package com.s.hkaccounting

import android.content.Context
import com.s.hkaccounting.persistent.AccountDao
import com.s.hkaccounting.persistent.AccountDatabase
import com.s.hkaccounting.ui.ViewModelFactory

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