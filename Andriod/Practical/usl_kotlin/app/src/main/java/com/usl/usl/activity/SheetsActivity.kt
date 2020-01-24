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
import com.usl.usl.adapter.SheetsDataAdapter
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.sheetsupdate.SheetUpdateResponse
import com.usl.usl.network.response.usersheets.Sheet
import com.usl.usl.network.response.usersheets.UserSheetResponse
import com.usl.usl.utils.*
import org.greenrobot.eventbus.Subscribe

class SheetsActivity : RegisterAbstractActivity(), View.OnClickListener {


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
    var sheetDataAdapter: SheetsDataAdapter? = null
    var saveGameList= ArrayList<Sheet>()
    var linearLayoutManager: LinearLayoutManager? = null
    var submitPos:Int = 0
    var DeletePos:Int = 0
    val ACTION_GETSHEETS:String="get_sheet"
    val ACTION_SHEETCOLLECTION:String="sheet_collection"
    val ACTION_SHEETDELETE:String="sheet_delete"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()

        progressDialog = MyProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(true)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSheetData!!.layoutManager = linearLayoutManager

        apigetSheets()

    }


    private fun apigetSheets() {
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog!!.show()
            ApiCallService.action(applicationContext,ACTION_GETSHEETS)
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show()
        }
    }

    @Subscribe
    fun usersheetresponse(response:UserSheetResponse){
        if(response.status==200){
            if(response.data?.sheets?.size!! >0){
                tvNoSheet.visibility = View.GONE
                saveGameList=response.data!!.sheets as ArrayList<Sheet>
                sheetDataAdapter=SheetsDataAdapter(this@SheetsActivity, saveGameList as ArrayList<Sheet>){ view: View, i: Int, s: String ->
                    if(s=="Submit"){
                        submitPos=i
                        apiSheetCollection()
                    }else if(s=="Delete"){
                        DeletePos=i
                        apiSheetDelete()
                    }
                }
            }else{
                tvNoSheet.visibility=View.VISIBLE
            }
            rvSheetData!!.adapter=sheetDataAdapter
        }else{
            Helper().alert(this,response.message,"USL")
        }
    }

    private fun apiSheetCollection(){
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog!!.show()
            appUser = LocalRepositories().getAppUser(applicationContext)!!
            appUser.submitSheet.put("id",Preferences(applicationContext).getInstance(this)?.getsheetId()!!)
            LocalRepositories().saveAppUser(applicationContext, appUser)
            ApiCallService.action(applicationContext,ACTION_SHEETCOLLECTION)
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun apiSheetDelete() {
        if (ConnectivityReceiver().isConnected()) {
            //progressDialog!!.show()
            ApiCallService.action(applicationContext,ACTION_SHEETDELETE)
        } else {
            Toast.makeText(this, "Please Check Your Internet", Toast.LENGTH_SHORT).show()
        }
    }


    @Subscribe
    fun sheetupdate(response: SheetUpdateResponse) {
        progressDialog!!.dismiss()
        if (response.status == 200) {
            if (response.message.equals("Sheet deleted", ignoreCase = true)) {
                Preferences(applicationContext).getInstance(applicationContext)?.setLimit(response.data!!.limit.toString().toFloat())
                saveGameList.removeAt(DeletePos)
                sheetDataAdapter!!.notifyDataSetChanged()
            } else if (response.message.equals("Sheet submitted", ignoreCase = true)) {
                saveGameList.removeAt(submitPos)
                sheetDataAdapter!!.notifyDataSetChanged()
            }
            apigetSheets()
            Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT).show()
        } else {
            Helper().alert(this, response.message, "USL")
        }
    }

    fun settoolbar() {
        ivBack = toolbar!!.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
        tvHeading = toolbar!!.findViewById(R.id.tvHeading)
        tvHeading.text = "Sheets"
    }


    override fun layoutId(): Int {
        return R.layout.activity_sheetdata
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