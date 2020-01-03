package com.s.hkaccount.persistent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Create by chenjunsheng on 2020/1/2
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM product")
    fun getProducts() : Single<List<Product>>

    @Query("SELECT * FROM customer")
    fun getCustomers() : Single<List<Customer>>

    @Insert(entity = Customer::class)
    fun insertCustomer(customer: Customer) : Single<Long>
}