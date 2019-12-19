package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class General extends AppCompatActivity implements View.OnClickListener{

    ImageView iVGeneralMenue;
    TextView tVGeneralMenue;

    TextView tVGeneralHeading;

    ImageView iVGeneralLight;
    ImageView iVGeneralTemperature;
    ImageView iVGeneralShutters;
    ImageView iVGeneralMusic;

    Switch swGeneralLight;
    Switch swGeneralTemperature;
    Switch swGeneralMusic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        iVGeneralMenue = (ImageView) findViewById(R.id.iVGeneralMenue);
        tVGeneralMenue = (TextView) findViewById(R.id.tVGeneralMenue);
        tVGeneralMenue.setOnClickListener(this);

        tVGeneralHeading = (TextView) findViewById(R.id.tVGeneralHeading);

        iVGeneralLight = (ImageView) findViewById(R.id.iVGeneralLight);
        iVGeneralTemperature = (ImageView) findViewById(R.id.iVGeneralTemperature);
        iVGeneralShutters = (ImageView) findViewById(R.id.iVGeneralShutters);
        iVGeneralMusic = (ImageView) findViewById(R.id.iVGeneralMusic);

        swGeneralLight = (Switch) findViewById(R.id.swGeneralLight);
        swGeneralTemperature = (Switch) findViewById(R.id.swGeneralTemperature);
        swGeneralMusic = (Switch) findViewById(R.id.swGeneralMusic);


    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
