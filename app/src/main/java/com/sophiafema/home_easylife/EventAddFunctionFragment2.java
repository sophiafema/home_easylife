package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventAddFunctionFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventAddFunctionFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventAddFunctionFragment2 extends Fragment {

    public EventAddFunctionFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventAddFunctionFragment2.
     */
    public static EventAddFunctionFragment2 newInstance(String param1, String param2) {
        EventAddFunctionFragment2 fragment = new EventAddFunctionFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_add_function_fragment2, container, false);
    }


}
