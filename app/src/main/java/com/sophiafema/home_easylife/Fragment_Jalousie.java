package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Jalousie extends Fragment
{
    Room r;

    public Fragment_Jalousie(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Jalousie newInstance(Room room) {
        return new Fragment_Jalousie(room);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View jalousie = inflater.inflate(R.layout.fragment_jalousie_bath, container, false);

        if (r.getName().equals(Util.BATH) )
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_bath, container, false);
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_kitchen, container, false);
        }

        else if (r.getName().equals(Util.LIVING))
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_living, container, false);
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_sleeping, container, false);
        }

        return jalousie;




    }
}
