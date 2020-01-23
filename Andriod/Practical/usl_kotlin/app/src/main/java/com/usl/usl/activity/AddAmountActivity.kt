package com.usl.usl.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.apps.RegisterAbstractActivity
import java.util.*

class AddAmountActivity : RegisterAbstractActivity(), View.OnClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar:View
    lateinit var tvHeading:TextView
    lateinit var ivBack:ImageView
    @BindView(R.id.viewpagerAddItem)
    lateinit var viewPagerItem:ViewPager
    @BindView(R.id.tablayout)
    lateinit var tableLayout: TableLayout
    lateinit var btn_submit:Button

    var temp:Float=0.0f
    var temp1:Float=0.0f

    var mSection = HashMap<Int, String>()
    var dSection = HashMap<Int, String>()
    var hSection = HashMap<Int, String>()

    var m1Section = HashMap<Int, String>()
    var d1Section = HashMap<Int, String>()
    var h1Section = HashMap<Int, String>()
    var apihashMap = HashMap<String, Float>()
    var x:Int = 1
    var y:Int = 1

    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        bundle = Bundle()
        bundle = intent.extras
        settoolbar()
        viewPagerItem.adapter
    }


    fun settoolbar(){
        tvHeading=toolbar.findViewById(R.id.tvHeading)
        tvHeading.setText("Add Amount")
        ivBack=toolbar.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
        btn_submit=toolbar.findViewById(R.id.btn_Submit)
        btn_submit.visibility=View.GONE

    }
    override fun layoutId(): Int {
        return R.layout.activity_addamount
    }

    override fun onClick(item: View?) {
        when(item?.id){
            R.id.ivBack -> finish()
            else ->{

            }
        }
    }
}