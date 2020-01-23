package com.usl.usl.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.adapter.AccountAdapter
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.account.Account
import com.usl.usl.network.response.account.AccountResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Helper
import com.usl.usl.utils.LocalRepositories
import com.usl.usl.utils.MyProgressDialog
import org.greenrobot.eventbus.Subscribe
import java.util.*

class AccountActivity :RegisterAbstractActivity(), View.OnClickListener {


    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    lateinit var tvHeading: TextView
    lateinit var ivBack: ImageView
    @BindView(R.id.rvAccount)
    lateinit var rvAccount: RecyclerView
    @BindView(R.id.tvNoAccount)
    lateinit var tvNoAccount: TextView
    var appUser = AppUser()
    var progressDialog: MyProgressDialog? = null

    var linearLayoutManager: LinearLayoutManager? = null
    var accountAdapter: AccountAdapter? = null
    var account = ArrayList<Account>()
    val ACTION_ACCOUNT:String="account"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()

        LocalRepositories().saveAppUser(applicationContext, appUser)
        progressDialog = MyProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(true)
        linearLayoutManager = LinearLayoutManager(this@AccountActivity, LinearLayoutManager.VERTICAL, false)
        rvAccount!!.layoutManager = linearLayoutManager

        apiAccount()
    }

    private fun apiAccount(){
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog!!.show()

            ApiCallService.action(applicationContext,ACTION_ACCOUNT)
        } else {
            Toast.makeText(this, "Please Check Your Internet", Toast.LENGTH_SHORT).show()
        }
    }


    @Subscribe
    fun account(response: AccountResponse) {
        progressDialog!!.dismiss()
        if (response.status == 200) {
            if (response.data!!.accounts!!.size > 0) {
                tvNoAccount!!.visibility = View.GONE
                account = response.data?.accounts as ArrayList<Account>
                accountAdapter = AccountAdapter(this@AccountActivity, account)
                rvAccount!!.adapter = accountAdapter
            } else {
                tvNoAccount!!.visibility = View.VISIBLE
            }
        } else {
            Helper().alert(this@AccountActivity, response.message, "USL")
        }
    }

    fun settoolbar(){
        tvHeading = toolbar!!.findViewById(R.id.tvHeading)
        tvHeading.setText("Account")
        ivBack = toolbar!!.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
    }


    override fun layoutId(): Int {
        return R.layout.activity_account
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.ivBack -> finish()
            else -> {
            }
        }
    }

    @Subscribe
    fun timeout(msg: String?) {
        //progressDialog!!.dismiss()
        Helper().alert(this, msg, "USL")
    }
}