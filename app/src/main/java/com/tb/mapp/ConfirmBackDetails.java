package com.tb.mapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmBackDetails extends AppCompatActivity {

    private TextView tv1, tv2, tv3 , tv4, tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_back_details);
        tv1 = findViewById(R.id.bankname);
        tv2 = findViewById(R.id.acctno);
        tv3 = findViewById(R.id.holdername);
        tv4 = findViewById(R.id.ifsc);
        tv5 = findViewById(R.id.gstn);
    }
     void confirmdetails(View view){
         String bankname = tv1.getText().toString().trim();
         String acctno = tv2.getText().toString().trim();
         String holdername = tv3.getText().toString().trim();
         String ifsc = tv4.getText().toString().trim();
         String bankgstnname = tv5.getText().toString().trim();
     }
}
