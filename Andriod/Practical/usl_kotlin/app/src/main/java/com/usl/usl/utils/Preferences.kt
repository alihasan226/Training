package com.usl.usl.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Preferences {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var _context: Context? = null
    var PRIVATE_MODE = 0
    private val LIMIT = "limit"
    private val HOUR = "hour"
    private val MINUTES = "minutes"
    private val SHEETID = "sheet_id"
    private val USERID = "userID"
    private val PASSWORD = "password"
    private val CURRENTDATE = "current_date"
    private val PLAYERID = "player_id"
    private val GAMEID = "game_id"
    private val PREF_NAME = "usl"
    private val AUTH_TOKEN = "auth_token"
    private val HCOUNTER = "hCounter"
    private var instance: Preferences? = null

    constructor(context: Context) {
        _context = context
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref!!.edit()
    }

    fun getInstance(context: Context?): Preferences? {
        if (instance == null) {
            instance = Preferences(context!!)
        }
        return instance
    }

    fun setAuthToken(authToken: String?) {
        editor!!.putString(AUTH_TOKEN, authToken)
        editor!!.commit()
    }

    fun getAuthToken(): String? {
        return pref!!.getString(AUTH_TOKEN, "")
    }

    fun setId(id: Int?) {
        editor!!.putInt(PLAYERID, id!!)
        editor!!.commit()
    }

    fun getId(): Int {
        return pref!!.getInt(PLAYERID, 0)
    }

    fun setGameId(id: Int?) {
        editor!!.putInt(GAMEID, id!!)
        editor!!.commit()
    }

    fun getGameId(): Int {
        return pref!!.getInt(GAMEID, 0)
    }


    fun setDate(date: String?) {
        editor!!.putString(CURRENTDATE, date)
        editor!!.commit()
    }

    fun getDate(): String? {
        return pref!!.getString(CURRENTDATE, "")
    }


    fun setUserID(userID: String?) {
        editor!!.putString(USERID, userID)
        editor!!.commit()
    }

    fun getuserID(): String? {
        return pref!!.getString(USERID, "")
    }

    fun setPassword(password: String?) {
        editor!!.putString(PASSWORD, password)
        editor!!.commit()
    }

    fun getPassword(): String? {
        return pref!!.getString(PASSWORD, "")
    }

    fun setsheetId(sheetId: Int?) {
        editor!!.putInt(SHEETID, sheetId!!)
        editor!!.commit()
    }

    fun getsheetId(): Int? {
        return pref!!.getInt(SHEETID, 0)
    }


    fun setHour(hour: Int?) {
        editor!!.putInt(HOUR, hour!!)
        editor!!.commit()
    }

    fun getHour(): Int? {
        return pref!!.getInt(HOUR, 0)
    }

    fun setMinute(minute: Int?) {
        editor!!.putInt(MINUTES, minute!!)
        editor!!.commit()
    }

    fun getMinute(): Int? {
        return pref!!.getInt(MINUTES, 0)
    }


    fun setLimit(limit: Float?) {
        editor!!.putFloat(LIMIT, limit!!)
        editor!!.commit()
    }

    fun getLimit(): Float? {
        return pref!!.getFloat(LIMIT, 0.0f)
    }

    fun setHCounter(hCounter: Int?) {
        editor!!.putInt(HCOUNTER, hCounter!!)
        editor!!.commit()
    }

    fun getHCounter(): Int? {
        return pref!!.getInt(HCOUNTER, 0)
    }


    fun storeHashMap(
        jsonMap: HashMap<Int?, String?>?,
        Key: String?
    ) {
        val jsonString = Gson().toJson(jsonMap)
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Key, jsonString)
        editor.apply()
    }

    fun getHashMap(Key: String?): HashMap<Int, String>? {
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val defValue = Gson().toJson(HashMap<Int, String>())
        val json = sharedPreferences.getString(Key, defValue)
        val token: TypeToken<HashMap<Int?, String?>?> =
            object : TypeToken<HashMap<Int?, String?>?>() {}
        return Gson().fromJson<HashMap<Int, String>>(json, token.type)
    }


    fun storeSheet(
        jsonMap: HashMap<String?, String?>?,
        Key: String?
    ) {
        val jsonString = Gson().toJson(jsonMap)
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Key, jsonString)
        editor.apply()
    }

    fun getSheet(Key: String?): HashMap<String, String>? {
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val defValue =
            Gson().toJson(HashMap<String, String>())
        val json = sharedPreferences.getString(Key, defValue)
        val token: TypeToken<HashMap<String?, String?>?> =
            object : TypeToken<HashMap<String?, String?>?>() {}
        return Gson().fromJson<HashMap<String, String>>(json, token.type)
    }


    fun storeNewSheet(
        jsonMap: HashMap<Int?, String?>?,
        Key: String?
    ) {
        val jsonString = Gson().toJson(jsonMap)
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Key, jsonString)
        editor.apply()
    }

    fun getNewSheet(Key: String?): HashMap<Int, String>? {
        val sharedPreferences =
            _context!!.getSharedPreferences(Key, Context.MODE_PRIVATE)
        val defValue = Gson().toJson(HashMap<Int, String>())
        val json = sharedPreferences.getString(Key, defValue)
        val token: TypeToken<HashMap<Int?, String?>?> =
            object : TypeToken<HashMap<Int?, String?>?>() {}
        return Gson().fromJson<HashMap<Int, String>>(json, token.type)
    }

}