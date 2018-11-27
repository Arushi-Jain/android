package com.tb.mapp;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Log.d("inside service", "Remote Message" + remoteMessage);
        Map<String, String> data = remoteMessage.getData();

        //you can get your text message here.
        String body= data.get("body");
        String title= data.get("title");
        Log.d("inside service", "Message data outside " + body);

    }
}
