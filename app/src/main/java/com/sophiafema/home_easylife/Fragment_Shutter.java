package com.sophiafema.home_easylife;

import android.graphics.Color;
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

    Room r;
    double position;
    double [] positionlist = new double [2];
    DatabaseAdapter db;


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
            iVFBathShuttersUp = (ImageView) shutter.findViewById(R.id.iVFBathShuttersUp);
            iVFBathShuttersDown = (ImageView) shutter.findViewById(R.id.iVFBathShuttersDown);

            shutterFBath.setPosition((float) position);

            shutterFBath.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    setShutterPosition(0,value);
                    shutterFBath.setPosition(value);
                    iVFBathShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFBathShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFBathShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,0);
                    shutterFBath.setPosition(0);
                    iVFBathShuttersUp.setColorFilter(getResources().getColor(R.color.colorAccent));
                    iVFBathShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFBathShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,1);
                    shutterFBath.setPosition(1);
                    iVFBathShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFBathShuttersDown.setColorFilter(getResources().getColor(R.color.colorAccent));
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
                    setShutterPosition(0,value);
                    shutterFKitchen1.setPosition(value);
                    iVFKitchenShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFKitchenShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            shutterFKitchen2.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    //Übergabe Position in Raum und Datenbank
                    setShutterPosition(1,value);
                    shutterFKitchen2.setPosition(value);
                    iVFKitchenShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFKitchenShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFKitchenShuttersUp = (ImageView) shutter.findViewById(R.id. iVFKitchenShuttersUp);
            iVFKitchenShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,0);
                    setShutterPosition(1,0);
                    shutterFKitchen1.setPosition(0);
                    shutterFKitchen2.setPosition(0);
                    iVFKitchenShuttersUp.setColorFilter(getResources().getColor(R.color.colorAccent));
                    iVFKitchenShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });
            iVFKitchenShuttersDown = (ImageView) shutter.findViewById(R.id.iVFKitchenShuttersDown);
            iVFKitchenShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,1);
                    setShutterPosition(1,1);
                    shutterFKitchen1.setPosition(1);
                    shutterFKitchen2.setPosition(1);
                    iVFKitchenShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFKitchenShuttersDown.setColorFilter(getResources().getColor(R.color.colorAccent));

                }
            });
        }

        else if (r.getName().equals(Util.LIVING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_living, container, false);
            position = r.getShutters().get(0).getPosition();

            shutterFLiving = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFLiving);
            iVFLivingShuttersUp = (ImageView) shutter.findViewById(R.id. iVFLivingShuttersUp);
            iVFLivingShuttersDown = (ImageView) shutter.findViewById(R.id.iVFLivingShuttersDown);

            shutterFLiving.setPosition((float) position);

            shutterFLiving.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    setShutterPosition(0,value);
                    shutterFLiving.setPosition(value);
                    iVFLivingShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFLivingShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFLivingShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,0);
                    shutterFLiving.setPosition(0);
                    iVFLivingShuttersUp.setColorFilter(getResources().getColor(R.color.colorAccent));
                    iVFLivingShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFLivingShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,1);
                    shutterFLiving.setPosition(1);
                    iVFLivingShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFLivingShuttersDown.setColorFilter(getResources().getColor(R.color.colorAccent));
                }
            });
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            shutter = inflater.inflate(R.layout.fragment_shutter_sleeping, container, false);
            position = r.getShutters().get(0).getPosition();

            shutterFSleeping = (com.sophiafema.home_easylife.view.Shutters) shutter.findViewById(R.id.shutterFSleeping);
            iVFSleepingShuttersUp = (ImageView) shutter.findViewById(R.id. iVFSleepingShuttersUp);
            iVFSleepingShuttersDown = (ImageView) shutter.findViewById(R.id.iVFSleepingShuttersDown);

            shutterFSleeping.setPosition((float) position);

            shutterFSleeping.setOnPositionChangedListener(new Shutters.OnPositionChangedListener() {
                @Override
                public void onColorChanged(float value) {
                    setShutterPosition(0,value);
                    shutterFSleeping.setPosition(value);
                    iVFSleepingShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFSleepingShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFSleepingShuttersUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,0);
                    shutterFSleeping.setPosition(0);
                    iVFSleepingShuttersUp.setColorFilter(getResources().getColor(R.color.colorAccent));
                    iVFSleepingShuttersDown.setColorFilter(Color.TRANSPARENT);
                }
            });

            iVFSleepingShuttersDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setShutterPosition(0,1);
                    shutterFSleeping.setPosition(1);
                    iVFSleepingShuttersUp.setColorFilter(Color.TRANSPARENT);
                    iVFSleepingShuttersDown.setColorFilter(getResources().getColor(R.color.colorAccent));
                }
            });
        }

        return shutter;

    }

    //Position der Jalousie in Room und Database speichern
    public void setShutterPosition(int arrayposition, float value)
    {
        r.getShutters().get(arrayposition).setPosition(value);
        db.setPosition(r.getName(), r.getShutters().get(arrayposition).getName(), value);
    }


}
