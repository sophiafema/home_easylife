package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.EventsRoom;


public class EventAddFunctionFragment extends Fragment {

    private EventsRoom room;
    ImageView iVLightAdd;
    ImageView iVThermostsAdd;
    ImageView iVShutterAdd;
    ImageView iVMusicAdd;

    public EventAddFunctionFragment() {
        // Required empty public constructor
    }

    public EventAddFunctionFragment(EventsRoom room) {
        this.room = room;
    }


    public static EventAddFunctionFragment newInstance(EventsRoom room) {
        EventAddFunctionFragment fragment = new EventAddFunctionFragment(room);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View event = inflater.inflate(R.layout.fragment_event_add_function, container, false);


        return event;


    }
}
