package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID="channel1ID";
    public static final String channelName="channel 1"
    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }
    public void createChannels()
    {
        NotificationChannel channel1=new NotificationChannel(channel1ID,channelName, NotificationManager.IMPORTANCE_DEFAULT);
    }
}
