package com.s.hkaccount.ui

import android.os.Parcelable
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Create by chenjunsheng on 2020/1/4
 */
@Parcelize
data class DeliverData(var customer: @RawValue Customer,
                       var product: @RawValue Product) : Parcelable