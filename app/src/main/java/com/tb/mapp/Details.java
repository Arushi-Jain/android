package com.tb.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Details extends AppCompatActivity {
    private Button button;
    private EditText editText1, editText2, editText3;
    private Spinner mySpinner1,mySpinner2,mySpinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mySpinner1 = findViewById(R.id.options);
        mySpinner2 = findViewById(R.id.state);
        mySpinner3 = findViewById(R.id.city);
        button =  findViewById(R.id.next2);
        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.email);
        editText3 = findViewById(R.id.address);

    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }

    void detailsNext (View view) {
        String option = mySpinner1.getSelectedItem().toString();
        String state = mySpinner2.getSelectedItem().toString();
        String city = mySpinner3.getSelectedItem().toString();

        String name = editText1.getText().toString().trim();
        String email = editText2.getText().toString().trim();
        String address= editText3.getText().toString().trim();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        extras.putString("option",option);
        extras.putString("state",state);
        extras.putString("cityDetail",city);
        extras.putString("nameDetail",name);
        extras.putString("email",email);
        extras.putString("address",address);
        Log.d("Inside profileDetails", "Final extras passed in intent:" + extras);

        Intent intent1 = new Intent(Details.this, Options.class);
        intent1.putExtras(extras);
        startActivity(intent1);
    }
}
