package com.s.hkaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s.hkaccount.R

/**
 * Create by chenjunsheng on 2020/1/4
 */
class ReviewProductFragment : AbstractAccountFragment() {

    companion object {
        fun newInstance(): ReviewProductFragment = ReviewProductFragment()
    }

    private var deliverData: DeliverData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.let {
            deliverData = it.getParcelable(DELIVER_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.review_product_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}