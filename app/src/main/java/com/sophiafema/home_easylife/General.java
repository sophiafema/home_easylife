package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    ImageView iVGeneralLightIcon;
    ImageView iVGeneralTemperatureIcon;
    ImageView iVGeneralShuttersIcon;
    ImageView iVGeneralMusicIcon;


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
        double shutterPosition = dba.getAllShutterPosition();

        //initialised view
        iVGeneralMenue = (ImageView) findViewById(R.id.iVGeneralMenue);

        tVGeneralHeading = (TextView) findViewById(R.id.tVGeneralHeading);
        tVGeneralHeading.setOnClickListener(this);

        iVGeneralLight = (ImageView) findViewById(R.id.iVGeneralLight);
        iVGeneralTemperature = (ImageView) findViewById(R.id.iVGeneralTemperature);
        iVGeneralShutters = (ImageView) findViewById(R.id.iVGeneralShutters);
        iVGeneralMusic = (ImageView) findViewById(R.id.iVGeneralMusic);

        iVGeneralLightIcon = (ImageView) findViewById(R.id.iVGeneralLightIcon);
        iVGeneralTemperatureIcon = (ImageView) findViewById(R.id.iVGeneralTemperatureIcon);
        iVGeneralShuttersIcon = (ImageView) findViewById(R.id.iVGeneralShuttersIcon);
        iVGeneralMusicIcon = (ImageView) findViewById(R.id.iVGeneralMusicIcon);

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
        setTextShuttersUp(isShutterUp(shutterPosition));


        //checkchanged listener
        swGeneralLight.setOnCheckedChangeListener(this);
        swGeneralTemperature.setOnCheckedChangeListener(this);
        swGeneralMusic.setOnCheckedChangeListener(this);
    }

    //Status Licht
    public void setTextLightOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralLight.setText(R.string.light_on);
            iVGeneralLightIcon.setImageResource(R.drawable.ic_gluehbirne_weiss_an);
        }else{
            tVGeneralLight.setText(R.string.light_off);
            iVGeneralLightIcon.setImageResource(R.drawable.ic_gluehbirne_weiss_aus);
        }
    }

    public void setLightOn(boolean isChecked) {
        if(isChecked) {
            dba.setAllLightsPower(true);
        }else{
            dba.setAllLightsPower(false);
        }
    }

    //Status Text Temperatur
    public void setTextTemperatureOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralTemperature.setText(R.string.thermo_on);
        }else{
            tVGeneralTemperature.setText(R.string.thermo_off);
        }
    }
    public void setTemperatureOn(boolean isChecked) {
        if(isChecked) {
            dba.setAllThermoPower(true);
        }else{
            dba.setAllThermoPower(false);
        }
    }

    //Status Text Musik
    public void setTextMusicOn(boolean isChecked) {
        if(isChecked) {
            tVGeneralMusic.setText(R.string.music_on);
        }else{
            tVGeneralMusic.setText(R.string.music_off);
        }
    }
    public void setMusicOn(boolean isChecked) {
        if(isChecked) {
            dba.setAllMusicPower(true);
        }else{
            dba.setAllMusicPower(false);
        }
    }

    //Status Jalousie
    public void setTextShuttersUp(boolean isChecked)
    {
        if(isChecked) {
            tVGeneralShutters.setText(R.string.shutter_up);
        }else{
            tVGeneralShutters.setText(R.string.shutter_down);
        }
    }

    public void setShuttersUp(boolean isChecked)
    {
        if(isChecked) {
            dba.setAllShutterPosition(0.0);
        }else{
            dba.setAllShutterPosition(1.0);
        }
    }

    private boolean isShutterUp(double shutterPosition) {
        return shutterPosition < 50;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tVGeneralHeading) {
            this.finish();
        }
        else if(view.getId() == R.id.iVGeneralShuttersUp) {
            setTextShuttersUp(true);
            setShuttersUp(true);
            iVGeneralShuttersUp.setColorFilter(getResources().getColor(R.color.colorAccent));
            iVGeneralShuttersDown.setColorFilter(Color.TRANSPARENT);
        }
        else if(view.getId() == R.id.iVGeneralShuttersDown) {
            setTextShuttersUp(false);
            setShuttersUp(false);
            iVGeneralShuttersDown.setColorFilter(getResources().getColor(R.color.colorAccent));
            iVGeneralShuttersUp.setColorFilter(Color.TRANSPARENT);

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.swGeneralLight) {
            setTextLightOn(isChecked);
            setLightOn(isChecked);
        }
        else if(buttonView.getId() == R.id.swGeneralTemperature) {
            setTextTemperatureOn(isChecked);
            setTemperatureOn(isChecked);
        }
        else if(buttonView.getId() == R.id.swGeneralMusic) {
            setTextMusicOn(isChecked);
            setMusicOn(isChecked);
        }
    }
}
