package com.s.hkaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.ui.AbstractAccountFragment
import com.s.hkaccount.ui.AccountViewModel
import com.s.hkaccount.ui.PreviewFragment
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity(), PreviewFragment.Callback {

    companion object {
        private const val CONTENT_FRAGMENT_TAG = "account_fragment"
        private const val STATE_PREVIEW = 0
        private const val STATE_ADD_CUSTOMER = 1
    }
    private lateinit var accountViewModel: AccountViewModel

    private var mState = STATE_PREVIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = Injection.provideViewModelFactory(this)
        accountViewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel::class.java)

        next()
    }

    private fun getContentFragment() : AbstractAccountFragment? {
        return supportFragmentManager.findFragmentByTag(CONTENT_FRAGMENT_TAG) as AbstractAccountFragment?
    }

    private fun next() {
        when(mState) {
            STATE_PREVIEW -> {
                updateContentFragment(false)
            }
            STATE_ADD_CUSTOMER -> {
                MaterialDialog(this).show {
                    title(text = "输入客户姓名")
                    input(hint = "客户姓名")
                    cornerRadius(16f)
                    positiveButton(text = "确定") { dialog->
                        val name = dialog.getInputField().text.toString()
                        accountViewModel.newCustomer(name)
                            .subscribe(Consumer<Customer> {
                                Log.d("cjslog", "add customer:${it.id}, ${it.name}")
                                getContentFragment()?.addNewCustomer(it)
                            }, Consumer {
                                Log.d("cjslog", "insert error", it)
                            })
                    }
                    negativeButton(text = "取消", click = {
                        it.dismiss()
                    })
                }
            }
        }
    }

    private fun updateContentFragment(addToBackstack: Boolean) {
        val f: AbstractAccountFragment
        when(mState) {
            STATE_PREVIEW -> {
                f = PreviewFragment.newInstance()
            }
            else -> {
                throw IllegalStateException("Incorrect state $mState")
            }
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.customers, f, CONTENT_FRAGMENT_TAG)
        if (addToBackstack) {
            ft.addToBackStack(null)
        }
        ft.commit()

    }

    override fun getViewModel(): AccountViewModel {
        return accountViewModel
    }

    override fun addNewCustomer() {
        mState = STATE_ADD_CUSTOMER
        next()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.custom_menu, menu)
        return true
    }
}
