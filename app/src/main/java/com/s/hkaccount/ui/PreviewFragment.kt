package com.s.hkaccount.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.s.hkaccount.R
import com.s.hkaccount.group.entity.node.BaseNode
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer

/**
 * Create by chenjunsheng on 2020/1/2
 */
class PreviewFragment : AbstractAccountFragment(), View.OnClickListener {

    companion object {
        fun newInstance(): PreviewFragment = PreviewFragment()
    }

    interface Callback : AbstractAccountFragment.Callback {
        fun addNewCustomer()
    }

    private var fab: FloatingActionButton? = null
    private var preview: RecyclerView? = null
    private var accountViewModel: AccountViewModel? = null
    private var adapter: CustomerNodeAdapter? = null
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.preview_main_layout, container, false)
        fab = view.findViewById(R.id.fab)
        preview = view.findViewById(R.id.preview)

        fab?.setOnClickListener(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val callback = activity as Callback
        accountViewModel = callback.getViewModel()

        adapter = CustomerNodeAdapter()

        preview?.layoutManager = GridLayoutManager(activity, 3)
        preview?.adapter = adapter

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

    override fun addNewCustomer(customer: Customer) {
        val rootCustomerNode = transformRootCustomerNode(customer)
        adapter?.addData(0, rootCustomerNode)
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}