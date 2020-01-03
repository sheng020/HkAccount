package com.s.hkaccount.group.diff

/**
 * Create by chenjunsheng on 2020/1/3
 */
interface ListChangeListener<T> {
    /**
     * Called after the current List has been updated.
     *
     * @param previousList The previous list.
     * @param currentList The new current list.
     */
    fun onCurrentListChanged(
        previousList: List<T>,
        currentList: List<T>
    )
}