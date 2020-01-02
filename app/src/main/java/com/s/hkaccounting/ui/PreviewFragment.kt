package com.s.hkaccounting.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.s.hkaccounting.R
import com.s.hkaccounting.group.entity.node.BaseNode

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.preview_main_layout, null)
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

        val a = ProductNode("1", 1.0f, false)
        val list = ArrayList<BaseNode>()
        val adapterList = ArrayList<BaseNode>()
        list.add(a)
        val b = RootCustomerNode(list)
        adapterList.add(b)
        adapter?.setNewData(adapterList)
    }

    override fun onClick(v: View) {
        Log.d("cjslog", "onclick")
        when(v.id) {
            R.id.fab -> {
                val callback = activity as Callback
                callback.addNewCustomer()
            }
        }
    }
}