package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Thermostat;
import com.sophiafema.home_easylife.view.Picker;

public class Fragment_Thermostat extends Fragment implements View.OnClickListener
{
    Room r;
    double temperature;
    boolean power;
    DatabaseAdapter db;
    com.sophiafema.home_easylife.view.TemperaturePicker pFTermostat;


    public Fragment_Thermostat(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Thermostat newInstance(Room room) { return new Fragment_Thermostat(room);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View thermostat = inflater.inflate(R.layout.fragment_thermostat, container, false);
        temperature = r.getThermo().getTemperature();
        power = r.getThermo().isPower();
        //dba erstellen

        pFTermostat = (com.sophiafema.home_easylife.view.TemperaturePicker) thermostat.findViewById(R.id.pFTermostat);
        pFTermostat.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int color) {
                //color == temperatur
                r.getThermo().setTemperature(color);
                //dba set
            }
        });

        return thermostat;
    }

    //TODO Wo bekomme ich die Rückgabewerte vom Picker her?
    @Override
    public void onClick(View v) {

        db = new DatabaseAdapter();
       //db.setTemperature(r.getName(), )
        //
        // für sitch .setThermostatPower(r.getName(), );

    }
}
