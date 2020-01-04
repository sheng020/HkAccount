package com.s.hkaccount.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.s.hkaccount.R
import com.s.hkaccount.group.BaseNodeAdapter
import com.s.hkaccount.group.BaseQuickAdapter
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.group.listener.OnItemChildClickListener
import com.s.hkaccount.group.listener.OnNodeProviderItemClick
import com.s.hkaccount.group.viewholder.BaseViewHolder
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import java.text.SimpleDateFormat
import java.util.*

/**
 * Create by chenjunsheng on 2020/1/2
 */
class PreviewFragment : AbstractAccountFragment(),
    View.OnClickListener, OnItemChildClickListener,
    OnNodeProviderItemClick {

    companion object {
        fun newInstance(): PreviewFragment = PreviewFragment()
    }

    interface Callback : AbstractAccountFragment.Callback {
        fun addNewCustomer()

        fun reviewProduct(customer: Customer, product: Product)
    }

    private var fab: FloatingActionButton? = null
    private var preview: RecyclerView? = null
    private var adapter: CustomerNodeAdapter? = null
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.preview_main_layout, container, false)
        fab = view.findViewById(R.id.fab)
        preview = view.findViewById(R.id.preview)

        fab?.setOnClickListener(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = CustomerNodeAdapter()
            .apply {
                setOnItemChildClickListener(this@PreviewFragment)
                addChildClickViewIds(R.id.add_commodity)
                addFullSpanNodeProvider(CustomerMsgProvider().apply {
                    onNodeProviderItemClick = this@PreviewFragment
                })
                addNodeProvider(CustomerItemProvider()
                    .apply {
                        onNodeProviderItemClick = this@PreviewFragment
                    })
            }

        preview?.layoutManager = GridLayoutManager(activity, 3)
        preview?.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        accountViewModel?.let { viewModel->
            disposable.add(Single.zip(viewModel.getCustomers(),
                viewModel.getProducts(),
                BiFunction<List<Customer>,
                        List<Product>,
                        MutableList<BaseNode>> { customers, products ->
                    return@BiFunction transformTreeNode(customers, products)
                }
            ).subscribe(
                Consumer<MutableList<BaseNode>> {
                    adapter?.setNewData(it)
                },
                Consumer<Throwable> { t ->
                    Log.d("cjslog", "preview load fragment fail", t) }))
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.fab -> {
                val callback = activity as Callback
                callback.addNewCustomer()
            }
        }
    }

    override fun onProviderItemClick(
        helper: BaseViewHolder,
        view: View,
        data: BaseNode,
        position: Int
    ) {
        val parentPos = adapter?.findParentNode(data) ?: -1
        //如果没有父节点，则为商品
        if (parentPos == -1) {
            adapter?.expandOrCollapse(position)
        } else {
            val parentNode = adapter?.getItem(parentPos) as? RootCustomerNode ?: return
            val product = (data as ProductNode).product
            val callback = activity as Callback
            callback.reviewProduct(parentNode.customer, product)
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when(view.id) {
            R.id.add_commodity -> {
                val rootCustomerNode = adapter.getItem(position) as? RootCustomerNode ?: return
                MaterialDialog(activity ?: return, BottomSheet()).show {
                    title(text = rootCustomerNode.customer.name)
                    cornerRadius(16f)
                    customView(R.layout.add_new_product)
                    negativeButton(text = "取消", click = {
                        it.dismiss()
                    })
                    positiveButton(text = "确定") { dialog ->
                        cornerRadius(16f)
                        val customView = dialog.getCustomView()
                        val name = customView.findViewById<EditText>(R.id.name_et).text.toString()
                        val buyCount = customView.findViewById<EditText>(R.id.count_et).text.toString().toInt()
                        val hkPrice = customView.findViewById<EditText>(R.id.hk_price_et).text.toString().toFloat()
                        val rmbPrice = customView.findViewById<EditText>(R.id.rmb_price_et).text.toString().toFloat()
                        val bought = customView.findViewById<CheckBox>(R.id.bought).isChecked
                        val id = rootCustomerNode.customer.id
                        val sfd = SimpleDateFormat.getDateInstance()
                        val today = sfd.format(Date())
                        val product = Product(id, name, buyCount, today, hkPrice, rmbPrice, bought)
                        val productNode = ProductNode(product)
                        (adapter as BaseNodeAdapter).nodeAddData(rootCustomerNode, 0, productNode)
                        accountViewModel?.let {
                            disposable.add(it.newProduct(product)
                                .subscribe())
                        }
                    }
                }
            }
        }
    }

    override fun addNewCustomer(customer: Customer) {
        val rootCustomerNode = transformRootCustomerNode(customer)
        adapter?.addData(0, rootCustomerNode)
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}