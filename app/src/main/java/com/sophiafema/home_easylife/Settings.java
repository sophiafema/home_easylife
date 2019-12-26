package com.sophiafema.home_easylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sophiafema.home_easylife.R;

public class Settings extends AppCompatActivity implements
        View.OnClickListener {



    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        // Buttons
        findViewById(R.id.btnSettingsLogOut).setOnClickListener(this);
        //findViewById(R.id.signOutButton).setOnClickListener(this);
        //findViewById(R.id.verifyEmailButton).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }



    private void updateUI(FirebaseUser user) {
        //hideProgressBar();
        if (user == null) {
            //mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail(), user.isEmailVerified()));

            Intent intent = new Intent(Settings.this, LogInActivity.class);
            startActivity(intent);

        } else {
            //mStatusTextView.setText("Abgemeldet");
            //mDetailTextView.setText(null);


        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSettingsLogOut) {
            signOut();
        }
    }
}