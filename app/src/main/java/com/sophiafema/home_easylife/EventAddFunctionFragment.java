package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.EventsRoom;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventAddFunctionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventAddFunctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventAddFunctionFragment extends Fragment {

    private EventsRoom room;

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
        return inflater.inflate(R.layout.fragment_event_add_function, container, false);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
