package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sophiafema.home_easylife.models.Room;


public class Fragment_Music extends Fragment
{
    Room r;
    public Fragment_Music(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Music newInstance(Room room) {
        return new Fragment_Music(room);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View music = inflater.inflate(R.layout.fragment_music, container, false);
        return music;
    }
}
