package com.sophiafema.home_easylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Light extends Fragment
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    Room r;
    double brightness;

    public Fragment_Light(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    //TODO nach diesem Schema Raum übergeben
    //TODO alternativ kann man natürlich auch andere Werte übergeben wenn das besser passt
    public static Fragment_Light newInstance(Room room) {
        return new Fragment_Light(room);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View light = inflater.inflate(R.layout.fragment_light_bath, container, false);
        Log.e("fragment_light", r.getName() );

        //TODO Name vergleichen mit equals
       if (r.getName().equals(Util.BATH))
        {
            light = inflater.inflate(R.layout.fragment_light_bath, container, false);

            brightness = r.getLights().get(0).getBrightness();
            light.findViewById(R.id.iVFLightBath1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);

                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });
        }

        else if (r.getName().equals(Util.HALLWAY))
        {
            light = inflater.inflate(R.layout.fragment_light_hallway, container, false);
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);
        }

        else if (r.getName().equals(Util.LIVING))
        {
            light = inflater.inflate(R.layout.fragment_light_living, container, false);
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            light = inflater.inflate(R.layout.fragment_light_sleeping, container, false);
        }

       return light;

    }
}
