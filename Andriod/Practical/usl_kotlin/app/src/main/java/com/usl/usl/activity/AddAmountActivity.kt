package com.usl.usl.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.tabs.TabLayout
import com.usl.usl.R
import com.usl.usl.adapter.ViewPagerAdapter
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.sheets.SheetResponse
import com.usl.usl.utils.*
import org.greenrobot.eventbus.Subscribe
import java.util.*

class AddAmountActivity : RegisterAbstractActivity(), View.OnClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar:View
    lateinit var tvHeading:TextView
    lateinit var ivBack:ImageView
    @BindView(R.id.viewpagerAddItem)
    lateinit var viewPagerItem:ViewPager
    @BindView(R.id.tablayout)
    lateinit var tabLayout:TabLayout
    lateinit var btn_submit:Button
    var appUser = AppUser()
    var cv = Cv()

    var mSection = HashMap<Int?, String?>()
    var dSection = HashMap<Int?, String?>()
    var hSection = HashMap<Int?, String?>()

    var m1Section = HashMap<Int?, String?>()
    var d1Section = HashMap<Int?, String?>()
    var h1Section = HashMap<Int?, String?>()
    var apihashMap = HashMap<String, Float>()
    var x:Int = 1
    var y:Int = 1
    var M_COUNT=Array(10){FloatArray(10)}
    var D_COUNT=FloatArray(10)
    var H_COUNT=FloatArray(10)
    var M_TOTAL:Float=0.0f
    var D_TOTAL:Float=0.0f
    var H_TOTAL:Float=0.0f
    var GRAND_TOTAL:Float=0.0f
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        bundle = Bundle()
        bundle = intent.extras
        settoolbar()
        viewPagerItem.adapter = ViewPagerAdapter(supportFragmentManager, bundle)
        tabLayout.setupWithViewPager(viewPagerItem)
        clearPreferences()
    }


    fun settoolbar(){
        tvHeading=toolbar.findViewById(R.id.tvHeading)
        tvHeading.setText("Add Amount")
        ivBack=toolbar.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
        btn_submit=toolbar.findViewById(R.id.btn_Submit)
        btn_submit.visibility=View.VISIBLE
        if (bundle != null) {
            if (bundle!!.getString("GETSHEET")=="sheet_get") {
                btn_submit.text="Update"
            }
        }
        btn_submit.setOnClickListener(this)
    }


    override fun layoutId(): Int {
        return R.layout.activity_addamount
    }

    override fun onClick(item: View?) {
        when(item?.id){
            R.id.ivBack -> {
                clearPreferences()
                finish()
            }
            R.id.btn_Submit -> {
                x=1
                y=1
                sendData()
                clearPreferences()
            }
            else ->{
            }
        }
    }

    fun sendData() {
        mSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("MSection")!!
        dSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("DColumn")!!
        hSection = if (Preferences(applicationContext).getInstance(applicationContext)?.getHCounter() == 2) {
            Preferences(applicationContext).getInstance(applicationContext)?.getNewSheet("HSHEET")!!
        } else {
            Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("HColumn")!!
        }
        for (i in 1..100) {
            m1Section[i] = "0.0"
            if (mSection[i] != null) {
                m1Section[i] = mSection[i]!!
            }
        }
        for (i in 1..10) {
            d1Section[i] = "0.0"
            if (dSection[i] != null) {
                d1Section[i] = dSection[i]!!
            }
        }
        for (i in 1..10) {
            h1Section[i] = "0.0"
            if (hSection[i] != null) {
                h1Section[i] = hSection[i]!!
            }
        }
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(m1Section, "MSection")
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(d1Section, "DColumn")
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(h1Section, "HColumn")
        mSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("MSection")!!
        dSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("DColumn")!!
        hSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("HColumn")!!

        for (i in 0..9) {
            for (j in 0..9) {
                M_COUNT[i][j] = 0.0f
            }
        }

        for(i in 0..9){
            D_COUNT[i] = 0.0f
            H_COUNT[i] = 0.0f
        }

        for (i in 0..9) {
            y = x
            for (j in 0..11) {
                if (j < 10) {
                    if (!mSection[y].equals("0.0", ignoreCase = true)) {
                        apihashMap[y.toString()] = mSection[y]!!.toFloat()
                        M_COUNT[i][j]=mSection[y]!!.toFloat()
                    }
                }
                if (j == 10) {
                    if (!dSection[x].equals("0.0", ignoreCase = true)) {
                        apihashMap[y.toString()] = dSection[x]!!.toFloat()
                        D_COUNT[x]=dSection[x]!!.toFloat()
                    }
                }
                if (j == 11) {
                    if (!hSection[x].equals("0.0", ignoreCase = true)) {
                        apihashMap[y.toString()] = hSection[x]!!.toFloat()
                        H_COUNT[x]=hSection[x]!!.toFloat()
                    }
                }
                y = y + 10
            }
            x = x + 1
        }

        for (i in 0..9) {
            for (j in 0..9) {
                if(M_COUNT[i][j]>0.0f){
                    M_TOTAL+=M_COUNT[i][j]
                }
            }
        }

        for(i in 0..9){
            if(D_COUNT[i]>0.0f){
                D_TOTAL+=D_COUNT[i]
            }
            if(H_COUNT[i]>0.0f){
                H_TOTAL+=H_COUNT[i]
            }
        }

        GRAND_TOTAL=M_TOTAL+D_TOTAL+H_TOTAL

        if (bundle != null && bundle!!.getString("GETSHEET")=="sheet_get") {
            apiUpdateSheet()
        } else {
            apiSendSheet()
        }
    }

    private fun apiSendSheet(){
        if(apihashMap.size>0){
            if(ConnectivityReceiver().isConnected()){
                appUser = LocalRepositories().getAppUser(applicationContext)!!
                val sheet:HashMap<String,Any> = HashMap<String,Any>()
                sheet.put("game_id",Preferences(applicationContext).getInstance(applicationContext)?.getGameId()?.toInt()!!)
                sheet.put("cell_amounts",apihashMap)
                sheet.put("sheet_type","Sheet")
                sheet.put("m_total",M_TOTAL)
                sheet.put("d_total",D_TOTAL)
                sheet.put("h_total",H_TOTAL)
                sheet.put("grand_total",GRAND_TOTAL)
                appUser.sheets.put("sheet",sheet)
                LocalRepositories().saveAppUser(applicationContext,appUser)
                ApiCallService.action(applicationContext,cv.ACTION_SENDSHEET)
            }else{
                Toast.makeText(this, "Please Check Your Internet.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Please Enter Some Amount", Toast.LENGTH_SHORT).show()
        }
    }

    private fun apiUpdateSheet(){
            if(ConnectivityReceiver().isConnected()){
                appUser = LocalRepositories().getAppUser(applicationContext)!!
                val sheet:HashMap<String,Any> = HashMap<String,Any>()
                sheet.put("cell_amounts",apihashMap)
                sheet.put("m_total",M_TOTAL)
                sheet.put("d_total",D_TOTAL)
                sheet.put("h_total",H_TOTAL)
                sheet.put("grand_total",GRAND_TOTAL)
                sheet.put("id",Preferences(applicationContext).getInstance(applicationContext)?.getsheetId()!!)
                appUser.sheets.put("sheet",sheet)
                LocalRepositories().saveAppUser(applicationContext,appUser)
                ApiCallService.action(applicationContext,cv.ACTION_UPDATESHEET)
            }else{
                Toast.makeText(this, "Please Check Your Internet.", Toast.LENGTH_SHORT).show()
            }
    }

    @Subscribe
    fun getSeetResponse(response: SheetResponse) {
        //progressDialog.dismiss()
        if (response.status == 201 || response.status == 200) {
            Preferences(applicationContext).getInstance(applicationContext)?.setLimit(response.data?.balance?.toFloat())
            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LandingPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        } else {
            Helper().alert(this, response.message, "USL")
        }
    }

    @Subscribe
    fun timeout(msg: String?) {
        //progressDialog!!.dismiss()
        Helper().alert(this, msg, "USL")
    }

    fun clearPreferences() {
        mSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("MSection")!!
        dSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("DColumn")!!
        hSection = Preferences(applicationContext).getInstance(applicationContext)?.getHashMap("HColumn")!!
        mSection.clear()
        dSection.clear()
        hSection.clear()
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(mSection, "MSection")
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(dSection, "DColumn")
        Preferences(applicationContext).getInstance(applicationContext)?.storeHashMap(hSection, "HColumn")
    }
}