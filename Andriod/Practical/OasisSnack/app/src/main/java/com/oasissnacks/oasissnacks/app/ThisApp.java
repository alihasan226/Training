package com.oasissnacks.oasissnacks.app;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiManager;

import com.oasissnacks.oasissnacks.BuildConfig;
import com.oasissnacks.oasissnacks.network.Api;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by suraj on 2/12/2019.
 */

public class ThisApp extends Application {

    public static ThisApp get(Context ctx) {
        return (ThisApp) ctx.getApplicationContext();
    }

    public static Api getApi(Context ctx) {
        return ThisApp.get(ctx).api;
    }

    private static ThisApp mInstance;
    private Api api;
    public String str = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        api = createApi();
    }

    public static synchronized ThisApp getInstance() {
        return mInstance;
    }

    private Api createApi() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS);



        client.addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/vnd.oasissnacks_backend.v1");
            if (Preferences.getInstance(this).getAuthToken().equalsIgnoreCase("")) {
                str = "";
                builder .addHeader("Authorization", "");

            } else {

                str = "Bearer " + Preferences.getInstance(this).getAuthToken();
                builder  .addHeader("Authorization", str);

            }


            String token = LocalRepositories.getAppUser(this).auth_token;

          /*  if (null != token) {
                builder.addHeader("Authorization", token);
            }*/
            return chain.proceed(builder.build());
        });


        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            client.addInterceptor(interceptor);
        }

        return new Retrofit.Builder()
                .baseUrl(Cv.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
