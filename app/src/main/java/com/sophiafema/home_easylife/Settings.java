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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sophiafema.home_easylife.R;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    ImageView iVSettingsMenue;
    TextView tVSettingsHeading;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //Menü-Leiste
        iVSettingsMenue = (ImageView) findViewById(R.id.iVSettingsMenue);
        iVSettingsMenue.setOnClickListener(this);
        tVSettingsHeading = (TextView) findViewById(R.id.tVSettingsHeading);
        tVSettingsHeading.setOnClickListener(this);


        // Buttons
        findViewById(R.id.btnSettingsLogOut).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
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

        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSettingsLogOut) {
            signOut();
        }

        else if(i == R.id.tVSettingsHeading | i == R.id.iVSettingsMenue)
        {
            finish();
        }
    }
}