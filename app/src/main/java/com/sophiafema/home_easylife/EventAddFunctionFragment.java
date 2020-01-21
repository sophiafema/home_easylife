package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventAddFunctionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventAddFunctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventAddFunctionFragment extends Fragment {

    public EventAddFunctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventAddFunctionFragment.
     */
    public static EventAddFunctionFragment newInstance(String param1, String param2) {
        EventAddFunctionFragment fragment = new EventAddFunctionFragment();
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
        return inflater.inflate(R.layout.fragment_event_add_function, container, false);
    }


}
