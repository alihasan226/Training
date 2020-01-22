package com.usl.usl.network

import com.usl.usl.network.response.user.UserResponsee
import retrofit2.Call
import retrofit2.http.*

interface Api {


    @POST("login")
    fun login(@Body map: HashMap<Any,Any>): Call<UserResponsee?>?
    @PATCH("reset_password")
    fun reset_password(@Body map: HashMap<Any,Any>): Call<UserResponsee?>?
   /* @GET("upcoming_games")
    fun game(): Call<UpcomingGamesResponse?>?

    @POST("sheets")
    fun sheets(@Body map: Map<*, *>?): Call<SheetResponse?>?

    @GET("sheets")
    fun user_sheets(): Call<UserSheetResponse?>?

    @PATCH("agent_sheet_submit")
    fun sheets_update(@Body map: Map<*, *>?): Call<SheetUpdateResponse?>?

    @PUT("sheets")
    fun update_sheets(@Body map: Map<*, *>?): Call<SheetResponse?>?

    @GET("sheets")
    fun user_sheets(@Query("sheets_type") sheetTypes: String?): Call<UserSheetResponse?>?

    @DELETE("sheets/{id}")
    fun sheets_delete(@Path("id") id: Int): Call<SheetUpdateResponse?>?

    @PATCH("reset_password")
    fun reset_password(@Body map: Map<*, *>?): Call<UserResponse?>?

    @GET("account")
    fun account(): Call<AccountResponse?>?*/

}