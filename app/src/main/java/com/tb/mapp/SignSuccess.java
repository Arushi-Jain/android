package com.tb.mapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignSuccess extends AppCompatActivity {
    private final int DISPLAY_LENGTH = 2500;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_success);
//        button = (Button) findViewById(R.id.logout);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), SignIn.class));
//            }
//        });
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SignSuccess.this,CreateProfile.class);
                SignSuccess.this.startActivity(mainIntent);
                SignSuccess.this.finish();
            }
        }, DISPLAY_LENGTH);
    }
}
