package com.s.hkaccounting.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.s.hkaccounting.persistent.AccountDao

/**
 * Create by chenjunsheng on 2020/1/2
 */
class ViewModelFactory(private val dataSource: AccountDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}