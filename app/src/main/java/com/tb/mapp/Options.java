package com.tb.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;

public class Options extends AppCompatActivity {
    private CheckBox chkbox1, chkbox2, chkbox3, chkbox4;
    private TextView text1, text2, text3, text4;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        chkbox1 = findViewById(R.id.check1);
        chkbox2 = findViewById(R.id.check2);
        chkbox3 = findViewById(R.id.check3);
        chkbox4 = findViewById(R.id.check4);

        text1 = findViewById(R.id.item1);
        text2 = findViewById(R.id.item2);
        text3 = findViewById(R.id.item3);
        text4 = findViewById(R.id.item4);
        btn = findViewById(R.id.submit);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();

                if (chkbox1.isChecked()) {
                    extras.putString("item1",text1.getText().toString());
                }
                if (chkbox2.isChecked()) {
                    extras.putString("item2",text2.getText().toString());
                }
                if (chkbox3.isChecked()) {
                    extras.putString("item3",text3.getText().toString());
                }
                if (chkbox4.isChecked()) {
                    extras.putString("item4",text4.getText().toString());
                }

                Log.d("Inside submitDetails", "Final extras passed to firebase:" + extras);

                Serializable result = extras.getSerializable("data");
                HashMap<String,String> output = (HashMap<String, String>) result;

                submitToFirebase(output);

            }
        });

    }

    void submitToFirebase(HashMap extras) {
        Log.d("Inside submitDetails", "Final extras passed to firebase:" + extras);
    }
}
