package com.tb.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.time.Clock;

public class CreateProfile extends AppCompatActivity {
    private Button button;
    private EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        button = (Button) findViewById(R.id.next);
        editText1 = findViewById(R.id.fullName);
        editText2 = findViewById(R.id.city);
        Log.d("Inside craeteprofile", "outside button listener:" + button);
        System.out.println("Inside craeteprofile" + "outside button listener:" + button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Inside craeteprofile", "inside button listener:" + button);
                String name = editText1.getText().toString().trim();
                String city = editText2.getText().toString().trim();
                Intent intent = new Intent(CreateProfile.this, Details.class);

                Bundle extras = new Bundle();
                extras.putString("name", name);
                extras.putString("city", city);

                Log.d("Inside craeteprofile", "outside button listener before extras:" + extras);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
