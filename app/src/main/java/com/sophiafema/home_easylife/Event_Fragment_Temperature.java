package com.sophiafema.home_easylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.view.Picker;

import androidx.appcompat.app.AppCompatActivity;

public class Event_Fragment_Temperature extends AppCompatActivity {

    Room r;
    double temperature;
    boolean power;
    DatabaseAdapter db;

    com.sophiafema.home_easylife.view.TemperaturePicker pFTermostat;
    Switch sFTermostat;

    TextView save;
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_thermostat);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            r = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
        }



        if(r == null) {
            r = new EventsRoom(Util.LIVING, 0);
        }

        System.out.println(r.getName());

        if(r.getThermo() == null) {
            db = new DatabaseAdapter();
            r.setThermo(db.getThermostat(r.getName()));
        }

        temperature = r.getThermo().getTemperature();
        power = r.getThermo().isPower();
        db = new DatabaseAdapter();

        sFTermostat = (Switch) findViewById(R.id.sFTermostat);
        pFTermostat = (com.sophiafema.home_easylife.view.TemperaturePicker) findViewById(R.id.pFTermostat);
        pFTermostat.setValueToPercent((float) temperature);
        pFTermostat.setWheelIsEnabled(power);
        sFTermostat.setChecked(power);

        save = findViewById(R.id.tVEventsThermostat_Save);
        cancel = findViewById(R.id.tVEventsThermostat_Cancel);


        setBackground();

        //setPercentFromTemp

        pFTermostat.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = temperature

                //Übergabe Temperatur in Raum und Datenbank
                r.getThermo().setTemperature(color);
                db.setTemperature(r.getName(), color);
            }
        });

        sFTermostat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    power = true;
                    pFTermostat.setWheelIsEnabled(true);
                }
                else
                {
                    power = false;
                    pFTermostat.setWheelIsEnabled(false);
                }
                //Übergabe Power in Raum und Datenbank
                r.getThermo().setPower(power);
                db.setThermostatPower(r.getName(),power);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Util.EVENTSROOM, r);
                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public void setBackground()
    {
        ImageView iVLightSettingBackground = (ImageView) findViewById(R.id.iVThermostatSettingBackground);

        if(r.getName().equals(Util.LIVING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);}
        else if(r.getName().equals(Util.BATH)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);}
        else if(r.getName().equals(Util.HALLWAY)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_flur_raum_v2);}
        else if(r.getName().equals(Util.KITCHEN)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_kueche_raum);}
        else if(r.getName().equals(Util.SLEEPING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);}
    }
}
