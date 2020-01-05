package com.s.hkaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BasicGridItem
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.GridItem
import com.afollestad.materialdialogs.bottomsheets.gridItems
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.s.hkaccount.persistent.Customer
import com.s.hkaccount.persistent.Product
import com.s.hkaccount.ui.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), PreviewFragment.Callback,
    ReviewProductFragment.Callback {

    companion object {
        private const val CONTENT_FRAGMENT_TAG = "account_fragment"
        private const val STATE_PREVIEW = 0
        private const val STATE_ADD_CUSTOMER = 1
        private const val STATE_ADD_PRODUCT = 2
        private const val STATE_REVIEW_PRODUCT = 3
    }
    private lateinit var accountViewModel: AccountViewModel
    private var deliverData: DeliverData? = null
    private val disposable = CompositeDisposable()

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
                    title(text = "输入商场名")
                    input(hint = "商场名")
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
            STATE_REVIEW_PRODUCT -> {
                updateContentFragment(false)
            }
        }
    }

    private fun updateContentFragment(addToBackstack: Boolean) {
        val f: AbstractAccountFragment
        when(mState) {
            STATE_PREVIEW -> {
                f = PreviewFragment.newInstance()
            }
            STATE_REVIEW_PRODUCT -> {
                f = ReviewProductFragment.newInstance()
                val bundle = Bundle()
                bundle.putParcelable(AbstractAccountFragment.DELIVER_DATA, deliverData)
                f.arguments = bundle
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

    override fun reviewProduct(customer: Customer, product: Product) {
        mState = STATE_REVIEW_PRODUCT
        deliverData = DeliverData(customer, product)
        next()
    }

    override fun modifyProductDetail(product: Product) {
        mState = STATE_PREVIEW
        disposable.add(
            accountViewModel.updateProduct(product)
                .subscribe(Consumer {
                    //supportFragmentManager.popBackStack()
                    next()
                }, Consumer {
                    Log.d("cjslog", "update product fail", it)
                })
        )

    }

    override fun reviewBackwards() {
        mState = STATE_PREVIEW
        //supportFragmentManager.popBackStack()
        next()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (getContentFragment()?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }

    override fun addSaleRecord(product: Product) {

    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}
