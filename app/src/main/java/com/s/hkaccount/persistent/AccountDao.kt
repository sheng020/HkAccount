package com.s.hkaccount.persistent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    @Insert(entity = Product::class)
    fun insertProduct(product: Product) : Single<Long>

    @Update(entity = Product::class)
    fun updateProduct(product: Product) : Single<Int>
}