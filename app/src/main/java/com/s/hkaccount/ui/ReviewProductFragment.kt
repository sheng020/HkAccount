package com.s.hkaccount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.hkaccount.R
import com.s.hkaccount.persistent.Product
import com.yanzhenjie.recyclerview.SwipeRecyclerView

/**
 * Create by chenjunsheng on 2020/1/4
 */
class ReviewProductFragment : AbstractAccountFragment(), View.OnClickListener {

    companion object {
        fun newInstance(): ReviewProductFragment = ReviewProductFragment()
    }

    interface Callback : AbstractAccountFragment.Callback {
        fun modifyProductDetail(product: Product)

        fun reviewBackwards()

        fun addSaleRecord(product: Product)
    }

    private var deliverData: DeliverData? = null
    private var productName: EditText? = null
    private var hkPrice: EditText? = null
    private var buyCount: EditText? = null
    private var bought: CheckBox? = null
    private var buyDate: TextView? = null
    private var customerName: TextView? = null
    private var confirm: Button? = null
    private var cancel: Button? = null
    private var delete: Button? = null
    private var reference: TextView? = null
    private var exchange: TextView? = null
    private var saleBtn: Button? = null

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
        productName = view.findViewById(R.id.name_et)
        hkPrice = view.findViewById(R.id.hk_price_et)
        buyCount = view.findViewById(R.id.buy_count_et)
        bought = view.findViewById(R.id.bought)
        buyDate = view.findViewById(R.id.buy_date_tv)
        customerName = view.findViewById(R.id.customer_name_tv)
        confirm = view.findViewById(R.id.confirm)
        cancel = view.findViewById(R.id.cancel)
        delete = view.findViewById(R.id.delete)
        reference = view.findViewById(R.id.reference_price_tv)
        exchange = view.findViewById(R.id.exchange_tv)
        saleBtn = view.findViewById(R.id.sale)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deliverData?.let {
            val product = it.product
            val customer = it.customer
            productName?.setText(product.name)
            hkPrice?.setText(product.buying_price.toString())
            buyCount?.setText(product.buy_count.toString())
            bought?.isChecked = product.bought
            buyDate?.text = product.date
            customerName?.setText(customer.name)
        }
        confirm?.setOnClickListener(this)
        cancel?.setOnClickListener(this)
        delete?.setOnClickListener(this)
        saleBtn?.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val callback = activity as Callback
        when(v.id) {
            R.id.confirm -> {
                deliverData?.let { deliver->
                    val product = deliver.product
                    product.bought = bought?.isChecked ?: false
                    product.buy_count = buyCount?.text?.toString()?.toInt() ?: 0
                    product.name = productName?.text?.toString() ?: ""
                    product.buying_price = hkPrice?.text?.toString()?.toFloat() ?: 0f
                    callback.modifyProductDetail(product)
                }
            }
            R.id.cancel -> {
                callback.reviewBackwards()
            }
            R.id.delete -> {

            }
            R.id.sale -> {
                deliverData?.let { deliver->
                    val product = deliver.product
                    product.bought = bought?.isChecked ?: false
                    product.buy_count = buyCount?.text?.toString()?.toInt() ?: 0
                    product.name = productName?.text?.toString() ?: ""
                    product.buying_price = hkPrice?.text?.toString()?.toFloat() ?: 0f
                    accountViewModel?.updateProduct(product)
                    callback.addSaleRecord(product)
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        val callback = activity as Callback
        callback.reviewBackwards()
        return true
    }
}