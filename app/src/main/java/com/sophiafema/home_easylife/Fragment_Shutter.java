package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Shutter extends Fragment
{
    Room r;
    double position;
    double [] positionlist = new double [2];
    DatabaseAdapter db;

    com.sophiafema.home_easylife.view.Shutters shutterFBath;
    com.sophiafema.home_easylife.view.Shutters shutterFKitchen1;
    com.sophiafema.home_easylife.view.Shutters shutterFKitchen2;
    com.sophiafema.home_easylife.view.Shutters shutterFLiving;
    com.sophiafema.home_easylife.view.Shutters shutterFSleeping;

    ImageView iVFBathShuttersUp;
    ImageView iVFKitchenShuttersUp;
    ImageView iVFLivingShuttersUp;
    ImageView iVFSleepingShuttersUp;
    ImageView iVFBathShuttersDown;
    ImageView iVFKitchenShuttersDown;
    ImageView iVFLivingShuttersDown;
    ImageView iVFSleepingShuttersDown;


    public Fragment_Shutter(Room room) {
        // Required empty public constructor
        this.r = room;

    }

    public static Fragment_Shutter newInstance(Room room) {
        return new Fragment_Shutter(room);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View shutter = inflater.inflate(R.layout.fragment_shutter_bath, container, false);


        if (r.getName().equals(Util.BATH) )
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_bath, container, false);
            position = r.getShutters().get(0).getPosition();
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_kitchen, container, false);

            positionlist[0] = r.getShutters().get(0).getPosition();
            positionlist[1] = r.getShutters().get(1).getPosition();
        }

        else if (r.getName().equals(Util.LIVING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_living, container, false);
            position = r.getShutters().get(0).getPosition();
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_sleeping, container, false);
            position = r.getShutters().get(0).getPosition();
        }

        return shutter;




    }
}
