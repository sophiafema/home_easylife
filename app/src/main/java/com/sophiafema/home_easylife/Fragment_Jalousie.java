package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Jalousie extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View jalousie = inflater.inflate(R.layout.fragment_jalousie, container, false);
        return jalousie;

    }
}