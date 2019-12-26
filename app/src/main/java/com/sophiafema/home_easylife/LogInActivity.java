package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity{

    private EditText eTMainName;
    private EditText eTMainPasswort;
    private Button btnMainLogIn;
    private TextView tVMainInfo;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        eTMainName = (EditText) findViewById(R.id.eTMainName);
        eTMainPasswort = (EditText) findViewById(R.id.eTMainPasswort);
        tVMainInfo = (TextView) findViewById(R.id.tVMainInfo);
        btnMainLogIn = (Button) findViewById(R.id.btnMainLogIn);


        tVMainInfo.setText("Anzahl fehlerhafter Anmeldeversuche: 5");


        btnMainLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate(eTMainName.getText().toString(), eTMainPasswort.getText().toString());
            }
        });

    }

    private void validate(String nutzerName, String nutzerPasswort)
    {
        if((nutzerName.equals("a")) && (nutzerPasswort.equals("1")))
        {
            Intent intent = new Intent(LogInActivity.this, Home.class);
            startActivity(intent);
        }
        else
        {
            counter--;

            tVMainInfo.setText("Anzahl fehlerhafter Anmeldeversuche: " + String.valueOf(counter));

            if(counter == 0)
            {
                btnMainLogIn.setEnabled(false);
            }
        }
    }
}
