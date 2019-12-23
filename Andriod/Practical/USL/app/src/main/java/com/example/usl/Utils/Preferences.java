package com.example.usl.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "usl";
    private static final String  AUTH_TOKEN ="auth_token";
    private static Preferences instance;

    private Preferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    public void setAuthToken(String authToken){
        editor.putString(AUTH_TOKEN,authToken);
        editor.commit();
    }

    public String getAuthToken(){
        return pref.getString(AUTH_TOKEN,"");

    }
}
