package com.usl.usl.network

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.usl.usl.apps.ThisApp
import com.usl.usl.network.response.account.AccountResponse
import com.usl.usl.network.response.upcominggame.UpcomingGameResponse
import com.usl.usl.network.response.user.UserResponsee
import com.usl.usl.network.response.usersheets.UserSheetResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.LocalRepositories

class ApiCallService: IntentService{


    val ACTION_LOGIN:String="login"
    val ACTION_RESETPASSWORD:String="reset_password"
    val ACTION_UPCOMINGGAME:String="upcoming_game"
    val ACTION_ACCOUNT:String="account"
    val ACTION_HISTORY:String="history"
    val ACTION_MATCHEDBET:String="matched_bet"

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
            api!!.login(appUser!!.login)!!.enqueue(ApiCallBack<UserResponsee?>())
        }else if(ACTION_RESETPASSWORD==action){
            api!!.reset_password(appUser!!.reset)!!.enqueue(ApiCallBack<UserResponsee?>())
        }else if(ACTION_UPCOMINGGAME==action){
            api!!.game()!!.enqueue(ApiCallBack<UpcomingGameResponse?>())
        }else if(ACTION_ACCOUNT==action){
            api!!.account()!!.enqueue(ApiCallBack<AccountResponse?>())
        }else if(ACTION_HISTORY==action){
            api!!.user_sheets(appUser!!.sheet_type)!!.enqueue(ApiCallBack<UserSheetResponse?>())
        }else if(ACTION_MATCHEDBET==action){
            api!!.user_sheets(appUser!!.sheet_type)!!.enqueue(ApiCallBack<UserSheetResponse?>())
        }
    }

}