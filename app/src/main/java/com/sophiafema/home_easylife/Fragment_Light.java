package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Light extends Fragment
{
    Room r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View light = null;

        if (r.getName() == Util.BATH)
        {
            light = inflater.inflate(R.layout.fragment_light_bath, container, false);
        }

        else if (r.getName() == Util.HALLWAY)
        {
            light = inflater.inflate(R.layout.fragment_light_hallway, container, false);
        }

        else if (r.getName() == Util.KITCHEN)
        {
            light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);
        }

        else if (r.getName() == Util.LIVING)
        {
            light = inflater.inflate(R.layout.fragment_light_living, container, false);
        }

        else if (r.getName() == Util.SLEEPING)
        {
            light = inflater.inflate(R.layout.fragment_light_sleeping, container, false);
        }

       return light;

    }
}
