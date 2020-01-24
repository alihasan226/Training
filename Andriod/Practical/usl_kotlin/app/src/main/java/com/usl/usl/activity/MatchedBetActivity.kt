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
import com.usl.usl.adapter.MatchedBetAdapter
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

class MatchedBetActivity : RegisterAbstractActivity() , View.OnClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    @BindView(R.id.tvNoSheet)
    lateinit var tvNoSheet: TextView
    @BindView(R.id.rvSheetData)
    lateinit var rvSheetData: RecyclerView
    lateinit var ivBack: ImageView
    lateinit var tvHeading: TextView
    var progressDialog: MyProgressDialog? = null
    var appUser = AppUser()
    var matchedBetAdapter: MatchedBetAdapter? = null
    var saveGameList: List<Sheet> = ArrayList()
    var linearLayoutManager: LinearLayoutManager? = null
    val ACTION_MATCHEDBET:String="matched_bet"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()
        progressDialog = MyProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(true)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSheetData!!.layoutManager = linearLayoutManager

        apiMatchedBet()

    }


    fun apiMatchedBet() {
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog!!.show()
            appUser.sheet_type = "ready_for_collection"
            LocalRepositories().saveAppUser(applicationContext, appUser)
            ApiCallService.action(applicationContext,ACTION_MATCHEDBET)
        } else {
            Toast.makeText(this, "Please Check Your Internet", Toast.LENGTH_SHORT).show()
        }
    }


    @Subscribe
    fun usersheetresponse(response: UserSheetResponse) {
        progressDialog!!.dismiss()
        if (response.status == 200) {
            if (response.data!!.sheets!!.size > 0) {
                tvNoSheet!!.visibility = View.GONE
                saveGameList = response.data!!.sheets!!
                matchedBetAdapter = MatchedBetAdapter(this@MatchedBetActivity, saveGameList)
                rvSheetData!!.adapter = matchedBetAdapter
            } else {
                tvNoSheet!!.visibility = View.VISIBLE
            }
        } else {
            Helper().alert(this@MatchedBetActivity, response.message, "USL")
        }
    }


    fun settoolbar(){
        ivBack = toolbar!!.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
        tvHeading = toolbar!!.findViewById(R.id.tvHeading)
        tvHeading.setText("Matched Bets")
    }

    override fun layoutId(): Int {
        return R.layout.activity_matchedbet
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