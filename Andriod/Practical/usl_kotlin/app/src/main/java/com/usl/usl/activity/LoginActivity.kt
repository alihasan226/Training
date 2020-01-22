package com.usl.usl.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.usl.usl.R
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.user.UserResponse
import com.usl.usl.utils.*
import org.greenrobot.eventbus.Subscribe

class LoginActivity : RegisterAbstractActivity(){

    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    lateinit var tvHeading:TextView
    lateinit var ivBack:ImageView
    @BindView(R.id.etUserID)
    lateinit var etUserID:EditText
    @BindView(R.id.etPassword)
    lateinit var etPassword:EditText
    var appUser = AppUser()
    lateinit var myProgressDialog:MyProgressDialog
    val ACTION_LOGIN:String="login"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this);
        settoolbar();
        LocalRepositories().saveAppUser(applicationContext, appUser)
        myProgressDialog= MyProgressDialog(context = applicationContext)
        myProgressDialog.setCancelable(true)
        myProgressDialog.setCanceledOnTouchOutside(true)

    }


    @OnClick(R.id.btn_login)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_login ->
                if (validation()) {
                appUser = LocalRepositories().getAppUser(applicationContext)!!
                val user:HashMap<String,String> = HashMap<String,String>()
                user.put("user_id",etUserID.text.toString())
                user.put("password",etPassword.text.toString())
                appUser.login.put("user", user)
                LocalRepositories().saveAppUser(applicationContext, appUser)
                Preferences(applicationContext).getInstance(applicationContext)!!.setUserID(etUserID.text.toString())
                Preferences(applicationContext).getInstance(applicationContext)!!.setPassword(etPassword.text.toString())
                val isConnected: Boolean = ConnectivityReceiver().isConnected()
                if (isConnected) {
                    myProgressDialog.show()
                    ApiCallService.action(this@LoginActivity,ACTION_LOGIN)
                } else {
                    Helper().alert(this@LoginActivity, "No Internet Connection", "USL")
                }
            }
            else -> {
            }
        }
    }

    @Subscribe
    fun user(response:UserResponse){
        if(response.equals(" ")){

        }
    }

    private fun validation(): Boolean {
        if (TextUtils.isEmpty(etUserID.text)) {
            etUserID.error = "Required Field"
            etUserID.requestFocus()
            return false
        } else if (etUserID.text.toString().length > 6) {
            etUserID.error = "Invalid User ID"
            etUserID.requestFocus()
            return false
        } else if (TextUtils.isEmpty(etPassword.text)) {
            etPassword.error = "Required Field"
            etPassword.requestFocus()
            return false
        }
        return true
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    private fun settoolbar(){
        tvHeading=toolbar.findViewById(R.id.tvHeading)
        tvHeading.setText("Login")
        ivBack=toolbar.findViewById(R.id.ivBack)
        ivBack.visibility=View.GONE
    }
}