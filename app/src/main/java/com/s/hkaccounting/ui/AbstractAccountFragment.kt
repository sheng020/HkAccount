package com.s.hkaccounting.ui

import androidx.fragment.app.Fragment

/**
 * Create by chenjunsheng on 2020/1/2
 */
abstract class AbstractAccountFragment : Fragment() {

    interface Callback {
        fun getViewModel() : AccountViewModel
    }
}