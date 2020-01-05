package com.s.hkaccount.ui

import androidx.lifecycle.ViewModel
import com.s.hkaccount.persistent.AccountDao
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Create by chenjunsheng on 2020/1/2
 */
class AccountViewModel(private val dataSource: AccountDao) : ViewModel() {

    fun getCustomers() : Single<List<Customer>> {
        return dataSource.getCustomers()
            .compose(applySchedulersForSingle())
    }

    fun getProducts() : Single<List<Product>> {
        return dataSource.getProducts()
            .compose(applySchedulersForSingle())
    }

    fun newCustomer(name: String) : Single<Customer> {
        val customer = Customer(name)
        return dataSource.insertCustomer(customer)
            .flatMap(Function<Long, SingleSource<Customer>> { id->
                customer.id = id
                return@Function Single.create { singleEmitter->
                    singleEmitter.onSuccess(customer)
                }
            })
            .compose(applySchedulersForSingle())
    }

    fun newProduct(product: Product) : Single<Long> {
        return dataSource.insertProduct(product)
            .compose(applySchedulersForSingle())
    }

    fun updateProduct(product: Product) : Single<Int> {
        return dataSource.updateProduct(product)
            .compose(applySchedulersForSingle())
    }

    private fun <T> applySchedulersForSingle() : SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}