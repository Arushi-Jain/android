package com.tb.mapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

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

    }

    void submitlistener (View view){

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

        // Log.d("Inside submitDetails", "Final extras passed to firebase:" + extras);
        submitToFirebase(extras);
        Intent i = new Intent(Options.this, ValidateProfile.class);
        startActivity(i);

    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }

    private static Map<String, String> convertBundleToMap(Bundle extras) {
        Map<String, String> map = new HashMap<>();
        for (String key : extras.keySet()) {
            map.put(key, extras.getString(key));
        }
        Log.d("Inside converttomap", "Final map returned:" + map);
        return map;
    }

    void submitToFirebase(Bundle extras) {
        Map<String, String> data = convertBundleToMap(extras);
        data.put("InstanceId", FirebaseInstanceId.getInstance().getToken());
        Log.d("Inside firebase", "Final data passed to firebase: " + data);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);


        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                .collection("ProfileDetails").document("profile")
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Inside firebase", "DocumentSnapshot added with ID: " + aVoid);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Inside firebase", "Error adding document", e);
                    }
                });

    }
}
