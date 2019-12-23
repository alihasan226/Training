package com.example.usl.Apps;

import android.app.Application;
import android.content.Context;

import com.example.usl.BuildConfig;
import com.example.usl.Network.Api;
import com.example.usl.Utils.Cv;
import com.example.usl.Utils.LocalRepositories;

import java.util.concurrent.TimeUnit;
import com.example.usl.Utils.Preferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                    .addHeader("Accept", "application/vnd.usl_backend.v1");
            if (Preferences.getInstance(this).getAuthToken().equalsIgnoreCase("")) {
                str = "";
                builder .addHeader("Authorization", "");

            } else {

                str = "Bearer " + Preferences.getInstance(this).getAuthToken();
                builder  .addHeader("Authorization", str);

            }

            //String token = LocalRepositories.getAppUser(this).auth_token;

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
