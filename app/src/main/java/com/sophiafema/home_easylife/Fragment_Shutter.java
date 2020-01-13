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
import com.sophiafema.home_easylife.view.Shutters;

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
        db = new DatabaseAdapter();

        if (r.getName().equals(Util.BATH) )
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_bath, container, false);
            position = r.getShutters().get(0).getPosition();

            shutterFBath = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFBath);
            shutterFBath.setPosition((float) position);

            shutterFBath.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    //Übergabe Position in Raum und Datenbank
                    r.getShutters().get(0).setPosition(value);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), value);
                    shutterFBath.setPosition(value);
                }
            });

            iVFBathShuttersUp = (ImageView) shutter.findViewById(R.id.iVFBathShuttersUp);
            iVFBathShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(0);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 0);
                    shutterFBath.setPosition(0);
                }
            });
            iVFKitchenShuttersDown = (ImageView) shutter.findViewById(R.id.iVFBathShuttersDown);
            iVFKitchenShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(1);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 1);
                    shutterFBath.setPosition(1);
                }
            });
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            //TODO Kitchen beide Shutters
            shutter = inflater.inflate(R.layout.fragment_shutter_kitchen, container, false);

            positionlist[0] = r.getShutters().get(0).getPosition();
            positionlist[1] = r.getShutters().get(1).getPosition();

            shutterFKitchen1 = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFKitchen1);
            shutterFKitchen2 = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFKitchen2);
            shutterFKitchen1.setPosition((float) positionlist[0]);
            shutterFKitchen2.setPosition((float) positionlist[1]);

            shutterFKitchen1.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    //Übergabe Position in Raum und Datenbank
                    r.getShutters().get(0).setPosition(value);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), value);
                    shutterFKitchen1.setPosition(value);
                }
            });

            shutterFKitchen2.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    //Übergabe Position in Raum und Datenbank
                    r.getShutters().get(1).setPosition(value);
                    db.setPosition(r.getName(), r.getShutters().get(1).getName(), value);
                    shutterFKitchen2.setPosition(value);
                }
            });

            iVFKitchenShuttersUp = (ImageView) shutter.findViewById(R.id. iVFKitchenShuttersUp);
            iVFKitchenShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(0);
                    r.getShutters().get(1).setPosition(0);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 0);
                    db.setPosition(r.getName(), r.getShutters().get(1).getName(), 0);
                    shutterFKitchen1.setPosition(0);
                    shutterFKitchen2.setPosition(0);
                }
            });
            iVFKitchenShuttersDown = (ImageView) shutter.findViewById(R.id.iVFKitchenShuttersDown);
            iVFKitchenShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(1);
                    r.getShutters().get(1).setPosition(1);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 1);
                    db.setPosition(r.getName(), r.getShutters().get(1).getName(), 1);
                    shutterFKitchen1.setPosition(1);
                    shutterFKitchen2.setPosition(1);
                }
            });

        }

        else if (r.getName().equals(Util.LIVING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_living, container, false);
            position = r.getShutters().get(0).getPosition();

            shutterFLiving = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFLiving);
            shutterFLiving.setPosition((float) position);

            shutterFLiving.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    //Übergabe Position in Raum und Datenbank
                    r.getShutters().get(0).setPosition(value);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), value);
                    shutterFLiving.setPosition(value);
                }
            });

            iVFLivingShuttersUp = (ImageView) shutter.findViewById(R.id. iVFLivingShuttersUp);
            iVFLivingShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(0);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 0);
                    shutterFLiving.setPosition(0);
                }
            });
            iVFLivingShuttersDown = (ImageView) shutter.findViewById(R.id.iVFLivingShuttersDown);
            iVFLivingShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(1);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 1);
                    shutterFLiving.setPosition(1);
                }
            });
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_sleeping, container, false);
            position = r.getShutters().get(0).getPosition();

            shutterFSleeping = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFSleeping);
            shutterFSleeping.setPosition((float) position);

            shutterFSleeping.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    r.getShutters().get(0).setPosition(value);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), value);
                    shutterFSleeping.setPosition(value);
                }
            });

            iVFSleepingShuttersUp = (ImageView) shutter.findViewById(R.id. iVFSleepingShuttersUp);
            iVFSleepingShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(0);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 0);
                    shutterFSleeping.setPosition(0);
                }
            });
            iVFSleepingShuttersDown = (ImageView) shutter.findViewById(R.id.iVFSleepingShuttersDown);
            iVFSleepingShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    r.getShutters().get(0).setPosition(1);
                    db.setPosition(r.getName(), r.getShutters().get(0).getName(), 1);
                    shutterFSleeping.setPosition(1);
                }
            });
        }

        return shutter;



    }

}
