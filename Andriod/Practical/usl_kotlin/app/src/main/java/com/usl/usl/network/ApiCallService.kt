package com.usl.usl.network

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.usl.usl.apps.ThisApp
import com.usl.usl.network.response.account.AccountResponse
import com.usl.usl.network.response.sheets.SheetResponse
import com.usl.usl.network.response.sheetsupdate.SheetUpdateResponse
import com.usl.usl.network.response.upcominggame.UpcomingGameResponse
import com.usl.usl.network.response.user.UserResponsee
import com.usl.usl.network.response.usersheets.UserSheetResponse
import com.usl.usl.utils.AppUser
import com.usl.usl.utils.Cv
import com.usl.usl.utils.LocalRepositories
import com.usl.usl.utils.Preferences

class ApiCallService: IntentService{

    var api: Api? = null
    var appUser: AppUser? = null
    var cv = Cv()


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
        if (cv.ACTION_LOGIN == action) {
            api!!.login(appUser!!.login)!!.enqueue(ApiCallBack<UserResponsee?>())
        }else if(cv.ACTION_RESETPASSWORD==action){
            api!!.reset_password(appUser!!.reset)!!.enqueue(ApiCallBack<UserResponsee?>())
        }else if(cv.ACTION_UPCOMINGGAME==action){
            api!!.game()!!.enqueue(ApiCallBack<UpcomingGameResponse?>())
        }else if(cv.ACTION_ACCOUNT==action){
            api!!.account(appUser!!.type)!!.enqueue(ApiCallBack<AccountResponse?>())
        }else if(cv.ACTION_HISTORY==action){
            api!!.user_sheets(appUser!!.sheet_type)!!.enqueue(ApiCallBack<UserSheetResponse?>())
        }else if(cv.ACTION_MATCHEDBET==action){
            api!!.user_sheets(appUser!!.sheet_type)!!.enqueue(ApiCallBack<UserSheetResponse?>())
        }else if(cv.ACTION_SENDSHEET==action){
            api!!.sheets(appUser!!.sheets)!!.enqueue(ApiCallBack<SheetResponse?>())
        }else if(cv.ACTION_UPDATESHEET==action){
            api!!.update_sheets(appUser!!.sheets)!!.enqueue(ApiCallBack<SheetResponse?>())
        }else if(cv.ACTION_GETSHEETS==action){
            api!!.user_sheets()!!.enqueue(ApiCallBack<UserSheetResponse?>())
        }else if(cv.ACTION_SHEETCOLLECTION==action){
            api!!.sheets_update(appUser!!.submitSheet)!!.enqueue(ApiCallBack<SheetUpdateResponse?>())
        }else if(cv.ACTION_SHEETDELETE==action){
            api!!.sheets_delete(Preferences(this).getInstance(this)?.getsheetId()!!)!!.enqueue(ApiCallBack<SheetUpdateResponse?>())
        }
    }

}