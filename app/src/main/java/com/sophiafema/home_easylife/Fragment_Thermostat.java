package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sophiafema.home_easylife.models.Room;

public class Fragment_Thermostat extends Fragment
{
    Room r;

    public Fragment_Thermostat(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Thermostat newInstance(Room room) { return new Fragment_Thermostat(room);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View thermostat = inflater.inflate(R.layout.fragment_thermostat, container, false);
        return thermostat;
    }
}
