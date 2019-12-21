package com.example.usl.Network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.usl.Apps.ThisApp;
import com.example.usl.Utils.AppUser;
import com.example.usl.Utils.Cv;
import com.example.usl.Utils.LocalRepositories;

import java.util.HashMap;
import java.util.Map;

public class ApiCallService extends IntentService {

    Api api;
    AppUser appUser;

    public ApiCallService() {
        super(Cv.SERVICE_NAME);

    }

    public ApiCallService(String name) {
        super(name);
    }

    public static void action(Context ctx, String action) {
        Intent intent = new Intent(ctx, ApiCallService.class);
        intent.setAction(action);
        ctx.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        api = ThisApp.getApi(this);
        appUser = LocalRepositories.getAppUser(getApplicationContext());
        /*if (Cv.ACTION_LOGIN.equals(action)) {
            api.login(appUser.login).enqueue(new ApiCallBack<>(new UserResponse()));
        }*/

    }
}
