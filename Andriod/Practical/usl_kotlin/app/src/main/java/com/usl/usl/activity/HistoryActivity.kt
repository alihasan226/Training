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
import com.usl.usl.adapter.HistoryAdapter
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.usersheets.Sheet
import com.usl.usl.network.response.usersheets.UserSheetResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Helper
import com.usl.usl.utils.LocalRepositories
import com.usl.usl.utils.MyProgressDialog
import org.greenrobot.eventbus.Subscribe
import java.util.*

class HistoryActivity : RegisterAbstractActivity() , View.OnClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    lateinit var tvHeading: TextView
    lateinit var ivBack: ImageView
    @BindView(R.id.rvHistory)
    lateinit var rvHistory: RecyclerView
    @BindView(R.id.tvHistory)
    lateinit var tvHistory: TextView
    var appUser = AppUser()
    var progressDialog: MyProgressDialog? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var historyList=ArrayList<Sheet>()
    var historyAdapter: HistoryAdapter? = null
    val ACTION_HISTORY:String="history"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()
        LocalRepositories().saveAppUser(applicationContext, appUser)
        progressDialog = MyProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(true)
        linearLayoutManager = LinearLayoutManager(this@HistoryActivity, LinearLayoutManager.VERTICAL, false)
        rvHistory.layoutManager = linearLayoutManager

        apiHistory()
    }

    private fun apiHistory(){
        if (ConnectivityReceiver().isConnected()) {
            progressDialog!!.show()
            appUser = LocalRepositories().getAppUser(applicationContext)!!
            appUser.sheet_type = "history"
            LocalRepositories().saveAppUser(applicationContext, appUser)
            ApiCallService.action(applicationContext,ACTION_HISTORY)
        } else {
            Toast.makeText(this, "Please Check Your Internet.", Toast.LENGTH_SHORT).show()
        }
    }

    @Subscribe
    fun usersheetresponse(response: UserSheetResponse) {
        progressDialog!!.dismiss()
        if (response.status == 200) {
            if (response.data!!.sheets!!.size > 0) {
                tvHistory.visibility = View.GONE
                historyList = response.data!!.sheets as ArrayList<Sheet>
                historyAdapter = HistoryAdapter(this@HistoryActivity, historyList)
                rvHistory.adapter = historyAdapter
            } else {
                tvHistory.visibility = View.VISIBLE
            }
        } else {
            Helper().alert(this@HistoryActivity, response.message, "USL")
        }
    }

    private fun settoolbar(){
        tvHeading = toolbar!!.findViewById(R.id.tvHeading)
        tvHeading.text = "History"
        ivBack = toolbar!!.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.ivBack -> finish()
            else -> {

            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_history
    }

    @Subscribe
    fun timeout(msg: String?) {
        progressDialog!!.dismiss()
        Helper().alert(this, msg, "USL")
    }
}