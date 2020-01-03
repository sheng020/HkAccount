package com.s.hkaccount.group.diff

/**
 * Create by chenjunsheng on 2020/1/3
 */
interface DifferImp<T> {
    fun addListListener(listChangeListener: ListChangeListener<T>)
}