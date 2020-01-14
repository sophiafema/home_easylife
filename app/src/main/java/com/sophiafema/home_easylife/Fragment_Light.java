package com.sophiafema.home_easylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;

public class Fragment_Light extends Fragment implements View.OnClickListener
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    Room r;
    double brightness;
    boolean on;
    DatabaseAdapter db;

    ImageView iVFLightBath;
    ImageView iVFLightLiving;
    ImageView iVFLightSleeping;
    ImageView iVFLightHallway;
    ImageView iVFLightKitchen1;
    ImageView iVFLightKitchen2;
    ImageView iVFLightKitchen3;

    Switch sFLightBath;
    Switch sFLightLiving;
    Switch sFLightSleeping;
    Switch sFLightHallway;
    Switch sFLightKitchen;


    public Fragment_Light(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Light newInstance(Room room) { return new Fragment_Light(room);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View light = inflater.inflate(R.layout.fragment_light_bath, container, false);


       if (r.getName().equals(Util.BATH)) {
           light = inflater.inflate(R.layout.fragment_light_bath, container, false);

           sFLightBath = light.findViewById(R.id.sFLightBath);
           iVFLightBath = light.findViewById(R.id.iVFLightBath);

           brightness = r.getLights().get(0).getBrightness();
           on = r.getLights().get(0).isOn();
           sFLightBath.setChecked(on);
           setImageBath();

           iVFLightBath.setOnClickListener(this);
           sFLightBath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    r.getLights().get(0).setOn(on);
                   //TODO datenbank NullPointer warum???
                    //db.setLightPower(r.getName(),r.getLights().get(0).getName(), on);
                    setImageBath();
               }
           });
       }

        else if (r.getName().equals(Util.HALLWAY))
        {
            light = inflater.inflate(R.layout.fragment_light_hallway, container, false);

            iVFLightHallway = light.findViewById(R.id.iVFLightHallway1);
            sFLightHallway =  light.findViewById(R.id.sFLightHallway);

            brightness = r.getLights().get(0).getBrightness();
            on = r.getLights().get(0).isOn();
            sFLightHallway.setChecked(on);
            setImageHallway();

           iVFLightHallway.setOnClickListener(this);
           sFLightHallway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    r.getLights().get(0).setOn(on);
                    Log.e("Problemfinden", "getName"+r.getName());
                    Log.e("Problemfinden", "getNamegetLights"+r.getLights().get(0).getName());
                    Log.e("Problemfinden", "on"+on);
                    //db.setLightPower(r.getName(), r.getLights().get(0).getName(),on);
                    setImageHallway();
                }
            });

        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);

            sFLightKitchen = light.findViewById(R.id.sFLightKitchen);
            iVFLightKitchen1 = light.findViewById(R.id.iVFLightKitchen1);
            iVFLightKitchen2 = light.findViewById(R.id.iVFLightKitchen2);
            iVFLightKitchen3 = light.findViewById(R.id.iVFLightKitchen3);

            if(!(r.getLights().get(0).isOn() && r.getLights().get(1).isOn() && r.getLights().get(2).isOn()))
            {
                on = false;
            }
            else
            {
                on = true;
            }

            sFLightKitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                }
            });


            iVFLightKitchen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(0).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });

            iVFLightKitchen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(1).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });

            iVFLightKitchen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brightness = r.getLights().get(2).getBrightness();
                    Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
                    intent.putExtra(LIGHT_BRIGHTNESS, brightness);
                    startActivity(intent);
                }
            });
        }

        else if (r.getName().equals(Util.LIVING))
        {
            light = inflater.inflate(R.layout.fragment_light_living, container, false);

            iVFLightLiving = light.findViewById(R.id.iVFLightLiving1);
            sFLightLiving = light.findViewById(R.id.sFLightLiving);

            brightness = r.getLights().get(0).getBrightness();
            on = r.getLights().get(0).isOn();
            sFLightLiving.setChecked(on);
            setImageLiving();

            iVFLightLiving.setOnClickListener(this);
            sFLightLiving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    r.getLights().get(0).setOn(on);
                    //db.setLightPower(r.getName(), r.getLights().get(0).getName(),on);
                    setImageLiving();
                }
            });
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            light = inflater.inflate(R.layout.fragment_light_sleeping, container, false);

            iVFLightSleeping = light.findViewById(R.id.iVFLightSleeping1);
            sFLightSleeping = light.findViewById((R.id.sFLightSleeping));

            brightness = r.getLights().get(0).getBrightness();
            on = r.getLights().get(0).isOn();
            sFLightSleeping.setChecked(on);
            setImageSleeping();

            iVFLightSleeping.setOnClickListener(this);
            sFLightSleeping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    r.getLights().get(0).setOn(on);
                    //db.setLightPower(r.getName(), r.getLights().get(0).getName(),on);
                    setImageSleeping();
                }
            });
        }

       return light;

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
        intent.putExtra(LIGHT_BRIGHTNESS, brightness);
        //LightSettingsActivity.newInstance(r);
        startActivity(intent);

    }

    public void switchSettings (boolean isChecked){
        if(isChecked)
        {
            on = true;
        }
        else
        {
            on = false;
        }
    }

    public void setImageBath()
    {
        if(on)
        {
            iVFLightBath.setImageResource(R.drawable.image_test_2);
        }
        else
        {
            iVFLightBath.setImageResource(R.drawable.iv_plainroom_bath);
        }
    }

    public void setImageLiving()
    {
        if(on)
        {
            iVFLightLiving.setImageResource(R.drawable.image_test_2);
        }
        else
        {
            iVFLightLiving.setImageResource(R.drawable.iv_plainroom_bath);
        }
    }

    public void setImageSleeping()
    {
        if(on)
        {
            iVFLightSleeping.setImageResource(R.drawable.image_test_2);
        }
        else
        {
            iVFLightSleeping.setImageResource(R.drawable.iv_plainroom_bath);
        }
    }

    public void setImageHallway()
    {
        if(on)
        {
            iVFLightHallway.setImageResource(R.drawable.image_test_2);
        }
        else
        {
            iVFLightHallway.setImageResource(R.drawable.iv_plainroom_bath);
        }
    }


}
