package com.example.retrofitexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class broadcastreceiver extends BroadcastReceiver {

    //private static boolean isConnected;
    public static boolean isConnected;
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("API123", "" + intent.getAction());

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (intent.getAction().equals("Connectivity Check")) {
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
        }

    }

    public static boolean isConnect()
    {
        boolean temp=false;
        if(isConnected==true)
        {
            temp=false;
        }
        return temp;
    }


}
