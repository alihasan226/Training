package com.usl.usl.network

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.usl.usl.apps.ThisApp
import com.usl.usl.network.response.user.UserResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.LocalRepositories

class ApiCallService: IntentService{


    var ACTION_LOGIN:String="login"
    var api: Api? = null
    var appUser: AppUser? = null


    constructor() : super("NetworkingService") {}

    companion object{
        fun action(ctx: Context, action: String?) {
            val intent = Intent(ctx, ApiCallService::class.java)
            intent.action = action
            ctx.startService(intent)
        }
    }


    override fun onHandleIntent(intent: Intent?) {
        val action = intent!!.action
        api = ThisApp.getApi(this)
        appUser = LocalRepositories().getAppUser(applicationContext)
        if (ACTION_LOGIN == action) {
            api!!.login(appUser!!.login)!!.enqueue(ApiCallBack<UserResponse?>())
        }
    }

}