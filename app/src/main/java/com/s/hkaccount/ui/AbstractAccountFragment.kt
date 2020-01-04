package com.s.hkaccount.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import java.lang.UnsupportedOperationException

/**
 * Create by chenjunsheng on 2020/1/2
 */
abstract class AbstractAccountFragment : Fragment() {

    protected var accountViewModel: AccountViewModel? = null

    companion object {
        const val DELIVER_DATA = "deliver_data"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val callback = activity as Callback
        accountViewModel = callback.getViewModel()
    }

    interface Callback {
        fun getViewModel() : AccountViewModel
    }

    open fun addNewCustomer(customer: Customer) {
        throw UnsupportedOperationException()
    }
}