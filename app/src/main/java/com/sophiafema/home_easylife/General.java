package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class General extends AppCompatActivity implements View.OnClickListener{

    ImageView iVGeneralMenue;

    TextView tVGeneralHeading;

    ImageView iVGeneralLight;
    ImageView iVGeneralTemperature;
    ImageView iVGeneralShutters;
    ImageView iVGeneralMusic;

    Switch swGeneralLight;
    Switch swGeneralTemperature;
    Switch swGeneralMusic;

    ImageView iVGeneralShuttersUp;
    ImageView iVGeneralShuttersDown;

    TextView tVGeneralLight;
    TextView tVGeneralTemperature;
    TextView tVGeneralMusic;
    TextView tVGeneralShutters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        iVGeneralMenue = (ImageView) findViewById(R.id.iVGeneralMenue);

        tVGeneralHeading = (TextView) findViewById(R.id.tVGeneralHeading);
        tVGeneralHeading.setOnClickListener(this);

        iVGeneralLight = (ImageView) findViewById(R.id.iVGeneralLight);
        iVGeneralTemperature = (ImageView) findViewById(R.id.iVGeneralTemperature);
        iVGeneralShutters = (ImageView) findViewById(R.id.iVGeneralShutters);
        iVGeneralMusic = (ImageView) findViewById(R.id.iVGeneralMusic);

        swGeneralLight = (Switch) findViewById(R.id.swGeneralLight);
        swGeneralTemperature = (Switch) findViewById(R.id.swGeneralTemperature);
        swGeneralMusic = (Switch) findViewById(R.id.swGeneralMusic);


        iVGeneralShuttersUp = (ImageView) findViewById(R.id.iVGeneralShuttersUp);
        iVGeneralShuttersDown = (ImageView) findViewById(R.id.iVGeneralShuttersDown);

        tVGeneralLight = (TextView) findViewById(R.id.tVGeneralLight);
        tVGeneralTemperature = (TextView) findViewById(R.id.tVGeneralTemperature);
        tVGeneralMusic = (TextView) findViewById(R.id.tVGeneralMusic);
        tVGeneralShutters = (TextView) findViewById(R.id.tVGeneralShutters);


        //Status Text Licht
        swGeneralLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    tVGeneralLight.setText("Licht an");
                }else{
                    tVGeneralLight.setText("Licht aus");
                }

            }
        });

        //Status Text Temperatur
        swGeneralTemperature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    tVGeneralTemperature.setText("Thermostat an");
                }else{
                    tVGeneralTemperature.setText("Thermostat aus");
                }

            }
        });

        //Status Text Musik
        swGeneralMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    tVGeneralMusic.setText("Musik an");
                }else{
                    tVGeneralMusic.setText("Musik aus");
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
