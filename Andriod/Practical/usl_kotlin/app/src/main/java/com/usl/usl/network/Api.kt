package com.usl.usl.network

import com.usl.usl.network.response.account.AccountResponse
import com.usl.usl.network.response.sheets.SheetResponse
import com.usl.usl.network.response.sheetsupdate.SheetUpdateResponse
import com.usl.usl.network.response.upcominggame.UpcomingGameResponse
import com.usl.usl.network.response.user.UserResponsee
import com.usl.usl.network.response.usersheets.UserSheetResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("api/login")
    fun login(@Body map: HashMap<Any,Any>): Call<UserResponsee?>?
    @PATCH("api/reset_password")
    fun resetPassword(@Body map: HashMap<Any,Any>): Call<UserResponsee?>?
    @GET("api/upcoming_games")
    fun game(): Call<UpcomingGameResponse?>?
    @GET("accounts")
    fun account(@Query("type") type:String?): Call<AccountResponse?>?
    @GET("sheets")
    fun userSheets(@Query("sheets_type") sheetTypes: String?): Call<UserSheetResponse?>?
    @POST("sheets")
    fun sheets(@Body map: HashMap<Any,Any>): Call<SheetResponse?>?
    @PUT("api/sheets")
    fun updateSheets(@Body map: HashMap<Any,Any>): Call<SheetResponse?>?
    @GET("sheets")
    fun userSheets(): Call<UserSheetResponse?>?
    @PATCH("agent_sheet_submit")
    fun sheetsUpdate(@Body map: HashMap<Any, Any>): Call<SheetUpdateResponse?>?
    @DELETE("sheets/{id}")
    fun sheetsDelete(@Path("id") id: Int): Call<SheetUpdateResponse?>?

   /*@GET("sheets")
    fun user_sheets(@Query("sheets_type") sheetTypes: String?): Call<UserSheetResponse?>?*/
}