package com.sophiafema.home_easylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Light extends Fragment implements View.OnClickListener
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    Room r;
    double brightness;
    boolean on;
    DatabaseAdapter db;


    public Fragment_Light(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    //TODO nach diesem Schema Raum übergeben
    //TODO alternativ kann man natürlich auch andere Werte übergeben wenn das besser passt

    //TODO veränderete Daten in der Datenbank abspeichern
    public static Fragment_Light newInstance(Room room) { return new Fragment_Light(room);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View light = inflater.inflate(R.layout.fragment_light_bath, container, false);

       if (r.getName().equals(Util.BATH))
        {
            light = inflater.inflate(R.layout.fragment_light_bath, container, false);

            brightness = r.getLights().get(0).getBrightness();
            light.findViewById(R.id.iVFLightBath).setOnClickListener(this);


        }

        else if (r.getName().equals(Util.HALLWAY))
        {
            light = inflater.inflate(R.layout.fragment_light_hallway, container, false);

            brightness = r.getLights().get(0).getBrightness();
            light.findViewById(R.id.iVFLightHallway1).setOnClickListener(this);
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);

            light.findViewById(R.id.iVFLightKitchen1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(0).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });

            light.findViewById(R.id.iVFLightKitchen2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(1).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });

            light.findViewById(R.id.iVFLightKitchen3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(2).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });
        }

        else if (r.getName().equals(Util.LIVING))
        {
            light = inflater.inflate(R.layout.fragment_light_living, container, false);

            brightness = r.getLights().get(0).getBrightness();
            light.findViewById(R.id.iVFLightLiving1).setOnClickListener(this);
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            light = inflater.inflate(R.layout.fragment_light_sleeping, container, false);

            brightness = r.getLights().get(0).getBrightness();
            light.findViewById(R.id.iVFLightSleeping1).setOnClickListener(this);
        }

       return light;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
        intent.putExtra(LIGHT_BRIGHTNESS, brightness);
        startActivity(intent);
    }

}
