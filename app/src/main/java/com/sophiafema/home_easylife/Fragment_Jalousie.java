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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View jalousie = null;

        if (r.getName() == Util.BATH)
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_bath, container, false);
        }

        else if (r.getName() == Util.HALLWAY)
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_hallway, container, false);
        }

        else if (r.getName() == Util.KITCHEN)
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_kitchen, container, false);
        }

        else if (r.getName() == Util.LIVING)
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_living, container, false);
        }

        else if (r.getName() == Util.SLEEPING)
        {
            jalousie = inflater.inflate(R.layout.fragment_jalousie_sleeping, container, false);
        }

        return jalousie;




    }
}
