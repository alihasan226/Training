package com.usl.usl.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.usl.usl.R
import com.usl.usl.apps.ConnectivityReceiver
import com.usl.usl.apps.RegisterAbstractActivity
import com.usl.usl.network.ApiCallService
import com.usl.usl.network.response.user.UserResponsee
import com.usl.usl.utils.*
import org.greenrobot.eventbus.Subscribe
import java.util.*

class ResetPasswordActivity : RegisterAbstractActivity(), View.OnClickListener {


    @BindView(R.id.toolbar)
    lateinit var toolbar: View
    lateinit var tvHeading:TextView
    lateinit var ivBack:ImageView
    @BindView(R.id.etPassword)
    lateinit var etPassword:EditText
    @BindView(R.id.etConfirmPassword)
    lateinit var etConfirmPassword:EditText
    var appUser=AppUser()
    var progressDialog: MyProgressDialog? = null
    var cv = Cv()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        settoolbar()
        progressDialog = MyProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(true)
    }

    private fun settoolbar(){
        tvHeading=toolbar.findViewById(R.id.tvHeading)
        tvHeading.setText("Reset Password")
        ivBack=toolbar.findViewById(R.id.ivBack)
        ivBack.setImageResource(R.drawable.ic_arrow_back)
        ivBack.setOnClickListener(this)

    }

    @OnClick(R.id.btn_reset)
    fun reset(){
        if(validation()){
            appUser = LocalRepositories().getAppUser(applicationContext)!!
            val user: MutableMap<String,String> = HashMap<String,String>()
            user.put("id",Preferences(applicationContext).getInstance(applicationContext)!!.getId().toString())
            user.put("password", etPassword.text.toString())
            user.put("password_confirmation",etConfirmPassword.text.toString())
            appUser.reset.put("user", user)
            LocalRepositories().saveAppUser(applicationContext, appUser)
            Preferences(applicationContext).getInstance(applicationContext)!!.setPassword(etConfirmPassword.text.toString())
            val isConnected: Boolean = ConnectivityReceiver().isConnected()
            if (isConnected) {
                progressDialog!!.show()
                ApiCallService.action(this@ResetPasswordActivity,cv.ACTION_RESETPASSWORD)
            } else {
                Helper().alert(this, "No Internet Connection", "USL")
            }
        }
    }


    @Subscribe
    fun reserPassword(response:UserResponsee){
        progressDialog!!.dismiss()
        if (response.status=== 200) {
            appUser.id = response.data?.user?.id.toString()
            appUser.name = response.data?.user?.name.toString()
            appUser.user_id = response.data?.user?.user_id.toString()
            appUser.auth_token =response.data?.user?.auth_token.toString()
            Preferences(applicationContext).getInstance(applicationContext)?.setLimit(response.data?.user?.balance)
            Preferences(applicationContext).getInstance(this)?.setId(response.data?.user?.id)
            Preferences(applicationContext).getInstance(this)?.setAuthToken(response.data?.user?.auth_token)
            LocalRepositories().saveAppUser(applicationContext, appUser)
            val intent = Intent(this@ResetPasswordActivity, LandingPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        } else {
            Helper().alert(this, response.message, "USL")
        }
    }


    private fun validation():Boolean{
        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Required Field")
            etPassword.requestFocus()
            return false
        } else if (TextUtils.isEmpty(etConfirmPassword.getText())) {
            etConfirmPassword.setError("Required Field")
            etConfirmPassword.requestFocus()
            return false
        } else if (etPassword.getText().toString() != etConfirmPassword.getText().toString()) {
            Toast.makeText(applicationContext, "Password Not Matches.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    override fun layoutId(): Int {
        return R.layout.activity_resetpassword
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.ivBack ->
                finish()
            else ->{

            }
        }
    }

    @Subscribe
    fun timeout(msg: String?) {
        progressDialog!!.dismiss()
        Helper().alert(this, msg, "USL")
    }

}