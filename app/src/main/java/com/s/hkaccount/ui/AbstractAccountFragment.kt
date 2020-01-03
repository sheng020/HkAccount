package com.s.hkaccount.ui

import androidx.fragment.app.Fragment
import com.s.hkaccount.persistent.Customer
import java.lang.UnsupportedOperationException

/**
 * Create by chenjunsheng on 2020/1/2
 */
abstract class AbstractAccountFragment : Fragment() {

    interface Callback {
        fun getViewModel() : AccountViewModel
    }

    open fun addNewCustomer(customer: Customer) {
        throw UnsupportedOperationException()
    }
}