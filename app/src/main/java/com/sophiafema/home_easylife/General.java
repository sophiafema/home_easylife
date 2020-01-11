package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;

public class General extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener {

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

    DatabaseAdapter dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);


        //getData from database
        dba = new DatabaseAdapter();
        boolean lightIsOn = dba.getAllLightsPower();
        boolean temperatureIsOn = dba.getAllThermostatPower();
        boolean musicIsOn = dba.getAllMusicPower();
        double shuttersUp = dba.getAllShutterPosition();

        //initialised view
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
        iVGeneralShuttersUp.setOnClickListener(this);
        iVGeneralShuttersDown = (ImageView) findViewById(R.id.iVGeneralShuttersDown);
        iVGeneralShuttersDown.setOnClickListener(this);

        tVGeneralLight = (TextView) findViewById(R.id.tVGeneralLight);
        tVGeneralTemperature = (TextView) findViewById(R.id.tVGeneralTemperature);
        tVGeneralMusic = (TextView) findViewById(R.id.tVGeneralMusic);
        tVGeneralShutters = (TextView) findViewById(R.id.tVGeneralShutters);



        //set checked
        swGeneralLight.setChecked(lightIsOn);
        swGeneralTemperature.setChecked(temperatureIsOn);
        swGeneralMusic.setChecked(musicIsOn);

        //setText
        setTextLightOn(lightIsOn);
        setTextTemperatureOn(temperatureIsOn);
        setTextMusicOn(musicIsOn);


        //checkchanged listener
        swGeneralLight.setOnCheckedChangeListener(this);
        swGeneralTemperature.setOnCheckedChangeListener(this);
        swGeneralMusic.setOnCheckedChangeListener(this);
    }

    //Status Text Licht
    public void setTextLightOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralLight.setText("Licht an");
            dba.setAllLightsPower(true);
        }else{
            tVGeneralLight.setText("Licht aus");
            dba.setAllLightsPower(false);
        }
    }

    //Status Text Temperatur
    public void setTextTemperatureOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralTemperature.setText("Thermostat an");
            dba.setAllThermoPower(true);
        }else{
            tVGeneralTemperature.setText("Thermostat aus");
            dba.setAllThermoPower(false);
        }
    }

    //Status Text Musik
    public void setTextMusicOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralMusic.setText("Musik an");
            dba.setAllMusicPower(true);
        }else{
            tVGeneralMusic.setText("Musik aus");
            dba.setAllMusicPower(false);
        }
    }

    //Status Text Jalousie
    public void setTextShuttersUp(boolean isChecked)
    {
        if(isChecked) {
            tVGeneralShutters.setText("Jalousie oben");
            dba.setAllShutterPosition(0.0);
        }else{
            tVGeneralShutters.setText("Jalousie unten");
            dba.setAllShutterPosition(100.0);
        }
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tVGeneralHeading) {
            this.finish();
        }
        else if(view.getId() == R.id.iVGeneralShuttersUp) {
            setTextShuttersUp(true);
            iVGeneralShuttersUp.setColorFilter(Color.CYAN);
            iVGeneralShuttersDown.setColorFilter(Color.TRANSPARENT);
        }
        else if(view.getId() == R.id.iVGeneralShuttersDown) {
            setTextShuttersUp(false);
            iVGeneralShuttersDown.setColorFilter(Color.CYAN);
            iVGeneralShuttersUp.setColorFilter(Color.TRANSPARENT);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.swGeneralLight) {
            setTextLightOn(isChecked);
        }
        else if(buttonView.getId() == R.id.swGeneralTemperature) {
            setTextTemperatureOn(isChecked);
        }
        else if(buttonView.getId() == R.id.swGeneralMusic) {
            setTextMusicOn(isChecked);
        }
    }
}
