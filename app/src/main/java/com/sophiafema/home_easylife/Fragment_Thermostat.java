package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Thermostat;
import com.sophiafema.home_easylife.view.Picker;

public class Fragment_Thermostat extends Fragment
{
    //Instanzvariablen
    Room r;
    double temperature;
    boolean power;
    DatabaseAdapter db;

    com.sophiafema.home_easylife.view.TemperaturePicker pFTermostat;
    Switch sFTermostat;

    //Konstruktor
    public Fragment_Thermostat(Room room) {
        // Required empty public constructor
        this.r = room;
    }
    public Fragment_Thermostat() {
        // Required empty public constructor
    }

    public static Fragment_Thermostat newInstance(Room room) { return new Fragment_Thermostat(room);}

    /*
        - View fürs swipen
        - sezt Temperatur, Power und ToggleButton
        - speichert neue Temperatur in der Datenbank
        - steuert den Toggle Button
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View thermostat = inflater.inflate(R.layout.fragment_thermostat, container, false);
        temperature = r.getThermo().getTemperature();
        power = r.getThermo().isPower();
        db = new DatabaseAdapter();

        sFTermostat = (Switch) thermostat.findViewById(R.id.sFTermostat);
        pFTermostat = (com.sophiafema.home_easylife.view.TemperaturePicker) thermostat.findViewById(R.id.pFTermostat);
        pFTermostat.setValueToPercent((float) temperature);
        pFTermostat.setWheelIsEnabled(power);
        sFTermostat.setChecked(power);

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

        return thermostat;
    }


}
