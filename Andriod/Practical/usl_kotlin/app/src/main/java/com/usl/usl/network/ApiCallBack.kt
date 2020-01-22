package com.usl.usl.network

import com.usl.usl.utils.Cv
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallBack<T>: Cv(),Callback<T>{

    var t: T? = null
    fun ApiCallBack(t: T) {
        this.t = t
    }

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        if (response.code() == 200) {
            val body: T? = response.body()
            EventBus.getDefault().post(body)
        } else {
            EventBus.getDefault().post(super.TIMEOUT)
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        EventBus.getDefault().post(super.TIMEOUT)
    }

}