package com.usl.usl.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.usl.usl.R
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.utils.Helper
import org.greenrobot.eventbus.Subscribe

class ContactUsActivity : RegisterAbstractActivity() , View.OnClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar:View
    lateinit var tvHeading:TextView
    lateinit var ivBack:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()
    }

    fun settoolbar(){
        tvHeading=toolbar.findViewById(R.id.tvHeading)
        tvHeading.setText("Conact US")
        ivBack=toolbar.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_contactus
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.ivBack -> finish()
            else -> {

            }
        }
    }

    @Subscribe
    fun timeout(msg: String?) {
        //progressDialog.dismiss()
        Helper().alert(this, msg, "USL")
    }


}