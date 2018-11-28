package com.tb.mapp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Map<String, String> data = remoteMessage.getData();

        //you can get your text message here.
        String body= data.get("body");
        String title= data.get("title");
        Log.d("inside service", "Message data body " + body);
        Log.d("inside service", "Message data title " + title);

     if(body.indexOf("successful")>0) {
         Intent i = new Intent(this, VerifiedProfile.class);
         startActivity(i);
     } else {
         Log.w("Inside firebase", "Error finding successful" + body.indexOf("successful"));
     }
     }

    }
