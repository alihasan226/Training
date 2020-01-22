package com.usl.usl.apps

import android.app.Application
import android.content.Context
import com.usl.usl.BuildConfig
import com.usl.usl.network.Api
import com.usl.usl.utils.Preferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ThisApp : Application() {


    var BASE_URL:String="http://192.168.1.51:3000/"
    operator fun get(ctx: Context): ThisApp? {
        return ctx.applicationContext as ThisApp
    }

    companion object{
        lateinit var mInstance: ThisApp
        fun getApi(ctx:Context):Api?{
            return ThisApp().get(ctx)!!.api
        }
    }


    private var api: Api? = null
    var str = ""

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        api = createApi()
    }


    @Synchronized
    fun getInstance(): ThisApp? {
        return mInstance
    }


    private fun createApi(): Api? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
        client.addInterceptor { chain: Interceptor.Chain ->
            val builder = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/vnd.usl_backend.v1")
            if (Preferences(applicationContext).getInstance(this)!!.getAuthToken()=="") {
                str = ""
                builder.addHeader("Authorization", "")
            } else {
                str = Preferences(applicationContext).getAuthToken().toString()
                builder.addHeader("Authorization", str)
            }
            //val token: String = LocalRepositories().getAppUser(this)!!.auth_token
            chain.proceed(builder.build())
        }
        if (BuildConfig.BUILD_TYPE == "debug") {
            client.addInterceptor(interceptor)
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }


}

//192.168.1.19 local
//68.183.92.181 Production