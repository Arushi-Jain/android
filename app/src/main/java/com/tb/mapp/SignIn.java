package com.tb.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText editText;
    private TextView onetext, twotext, threetext, fourtext, fivetext, sixtext, seventext, eighttext, ninetext, zerotext;
    private LinearLayout erasetext;
    private String numberString = "";
    private Spinner countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Adding full screen feature to screen

        // Initializing the TextView
        editText = findViewById(R.id.number);
//        onetext = findViewById(R.id.one);
//        twotext = findViewById(R.id.two);
//        threetext = findViewById(R.id.three);
//        fourtext = findViewById(R.id.four);
//        fivetext = findViewById(R.id.five);
//        sixtext = findViewById(R.id.six);
//        seventext = findViewById(R.id.seven);
//        eighttext = findViewById(R.id.eight);
//        ninetext = findViewById(R.id.nine);
//        zerotext = findViewById(R.id.zero);
//        erasetext = (LinearLayout) findViewById(R.id.erase);
        countryCode = (Spinner) findViewById(R.id.spinnerCountryCode);

        // On Click Listners


//        erasetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(numberString.isEmpty())
//                {
//                    Toast.makeText(SignIn.this, "Number is Empty", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    numberString = removeLastChar(numberString);
//                }
//                editText.setText(numberString);
//            }
//        });
//
//        onetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numberString = numberString + "1";
//                editText.setText(numberString);
//            }
//        });
//
//        twotext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "2";
//                editText.setText(numberString);
//            }
//        });
//
//        threetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "3";
//                editText.setText(numberString);
//            }
//        });
//
//        fourtext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "4";
//                editText.setText(numberString);
//            }
//        });
//
//        fivetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "5";
//                editText.setText(numberString);
//            }
//        });
//
//        sixtext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "6";
//                editText.setText(numberString);
//            }
//        });
//
//        seventext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "7";
//                editText.setText(numberString);
//            }
//        });
//
//        eighttext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "8";
//                editText.setText(numberString);
//            }
//        });
//
//        ninetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "9";
//                editText.setText(numberString);
//            }
//        });
//
//        zerotext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numberString = numberString + "0";
//                editText.setText(numberString);
//            }
//        });


        findViewById(R.id.nextSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10 || number.length() > 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }
                String countryNewCode = countryCode.getSelectedItem().toString();
                String phoneNumber = countryNewCode + number;

                Intent intent = new Intent(SignIn.this, VerifyOPT.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);

            }
        });
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, SignSuccess.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
