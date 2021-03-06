package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sophiafema.home_easylife.models.Room;


public class Fragment_Events extends Fragment
{
   Room r;

    public Fragment_Events() {
        // Required empty public constructor
    }

   public Fragment_Events(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Events newInstance(Room room) {
        return new Fragment_Events(room);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View events = inflater.inflate(R.layout.fragment_events, container, false);
        return events;
    }
}
