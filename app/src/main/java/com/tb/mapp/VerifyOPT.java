package com.tb.mapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOPT extends AppCompatActivity {

    private final int TIMER_DISPLAY_LENGTH = 60000;
    private final int TIMER_ONE_SECOND = 1000;
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;
    private TextView timerVerify, numbervisible2, numbervisible, onetext, twotext, threetext, fourtext, fivetext, sixtext, seventext, eighttext, ninetext, zerotext;
    private LinearLayout erasetext;
    private String numberString = "";
    private TextView wrongNumber, resendVerify;
    private boolean sentVerification = true;
    private String currentTimerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_opt);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        editText = findViewById(R.id.editTextCode);

        final String phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);

//
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
        numbervisible = findViewById(R.id.eee);
        numbervisible2 = findViewById(R.id.numberText);
        resendVerify = findViewById(R.id.resendVerification);
        wrongNumber = findViewById(R.id.wrongNumber);
        timerVerify = findViewById(R.id.timer);
        resendVerify.setAlpha((float) 0.50);


        numbervisible2.setText(phonenumber);
        numbervisible.setText(phonenumber);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                sentVerification = false;
                resendVerify.setClickable(true);
            }
        }, TIMER_DISPLAY_LENGTH);

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerVerify.setText(" " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                timerVerify.setText("0");
                sentVerification = false;
                resendVerify.setAlpha((float) 1.00);
                resendVerify.setClickable(true);
            }

        }.start();

        // On Click Listners

        resendVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sentVerification)
                {
                    Toast.makeText(VerifyOPT.this, "Wait for resend timer", Toast.LENGTH_LONG).show();
                }
                else
                {
                    sendVerificationCode(phonenumber);
                    sentVerification = true;
                    resendVerify.setAlpha((float) 0.50);
                    new CountDownTimer(60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timerVerify.setText(" " + millisUntilFinished / 1000);
                            //here you can have your logic to set text to edittext
                        }

                        public void onFinish() {
                            sentVerification = false;
                            resendVerify.setClickable(true);
                        }

                    }.start();
                }
            }
        });

        wrongNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });

//        erasetext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(numberString.isEmpty())
//                {
//                    Toast.makeText(VerifyOPT.this, "Number is Empty", Toast.LENGTH_LONG).show();
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

        findViewById(R.id.verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(VerifyOPT.this, SignSuccess.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);

                        } else {
                            Toast.makeText(VerifyOPT.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }


    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Log.d("Inside Callback", "onCodeSent:" + s);
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Log.d("Inside Callback", "onVerificationCompleted:" + phoneAuthCredential);
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d("Inside Callback", "onVerificationFailed:" + e);
            Toast.makeText(VerifyOPT.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}
