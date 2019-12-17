package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_Light extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);
        return light;

    }


    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0: return new Fragment_Light();
            case 1: return new Fragment_Thermostat();

        }
        return null;
    }
}
