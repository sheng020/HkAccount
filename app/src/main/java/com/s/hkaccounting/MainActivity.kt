package com.s.hkaccounting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.s.hkaccounting.ui.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PreviewFragment.Callback{

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

    private fun next() {
        when(mState) {
            STATE_PREVIEW -> {
                updateContentFragment(false)
            }
            STATE_ADD_CUSTOMER -> {
                updateContentFragment(true)
            }
        }
    }

    private fun updateContentFragment(addToBackstack: Boolean) {
        val f: AbstractAccountFragment
        when(mState) {
            STATE_PREVIEW -> {
                f = PreviewFragment.newInstance()
            }
            STATE_ADD_CUSTOMER -> {
                f = NewCustomerFragment.newInstance()
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
}
