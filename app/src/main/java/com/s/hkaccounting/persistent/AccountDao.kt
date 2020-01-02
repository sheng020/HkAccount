package com.s.hkaccounting.persistent

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM account")
    fun getAllData() : Flowable<Account>
}