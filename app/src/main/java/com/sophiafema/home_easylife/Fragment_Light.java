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

import static android.app.Activity.RESULT_OK;

public class Fragment_Light extends Fragment implements View.OnClickListener
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    public static final String LIGHT_POWER = "LIGHT_POWER";
    static final int REQUEST_CODE = 1; // The request code.
    Room r;
    DatabaseAdapter db;
    static double brightness;
    static boolean on;
    static int arrayposition;

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
        db = new DatabaseAdapter();
        View light = inflater.inflate(R.layout.fragment_light_bath, container, false);


       if (r.getName().equals(Util.BATH)) {
           light = inflater.inflate(R.layout.fragment_light_bath, container, false);

           sFLightBath = light.findViewById(R.id.sFLightBath);
           iVFLightBath = light.findViewById(R.id.iVFLightBath);

           doAtStart();

           iVFLightBath.setOnClickListener(this);
           sFLightBath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    savePower();
                    setImages();
               }
           });
       }

        else if (r.getName().equals(Util.HALLWAY))
        {
            light = inflater.inflate(R.layout.fragment_light_hallway, container, false);

            iVFLightHallway = light.findViewById(R.id.iVFLightHallway1);
            sFLightHallway =  light.findViewById(R.id.sFLightHallway);

            doAtStart();

           iVFLightHallway.setOnClickListener(this);
           sFLightHallway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    savePower();
                    setImages();
                }
            });

        }

        else if (r.getName().equals(Util.LIVING))
        {
            light = inflater.inflate(R.layout.fragment_light_living, container, false);

            iVFLightLiving = light.findViewById(R.id.iVFLightLiving1);
            sFLightLiving = light.findViewById(R.id.sFLightLiving);

            doAtStart();

            iVFLightLiving.setOnClickListener(this);
            sFLightLiving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    savePower();
                    setImages();
                }
            });
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            light = inflater.inflate(R.layout.fragment_light_sleeping, container, false);

            iVFLightSleeping = light.findViewById(R.id.iVFLightSleeping1);
            sFLightSleeping = light.findViewById((R.id.sFLightSleeping));

            doAtStart();

            iVFLightSleeping.setOnClickListener(this);
            sFLightSleeping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchSettings(isChecked);
                    savePower();
                    setImages();
                }
            });
        }

       else if (r.getName().equals(Util.KITCHEN))
       {
           light = inflater.inflate(R.layout.fragment_light_kitchen, container, false);

           sFLightKitchen = light.findViewById(R.id.sFLightKitchen);
           iVFLightKitchen1 = light.findViewById(R.id.iVFLightKitchen1); //Array Platz 0
           iVFLightKitchen2 = light.findViewById(R.id.iVFLightKitchen2); //Array Platz 1
           iVFLightKitchen3 = light.findViewById(R.id.iVFLightKitchen3); //Array Platz 2

           getIsArrayOn();
           setSwitchPosition();
           setImages();

           iVFLightKitchen1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   arrayposition=0;
                   onClickHelper();
               }
           });

           iVFLightKitchen2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   arrayposition=1;
                   onClickHelper();
               }
           });

           iVFLightKitchen3.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   arrayposition=2;
                   onClickHelper();
               }
           });

           sFLightKitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                   if(isChecked)
                   {
                       on = true;
                       for (int i = 0; i< 3; i++)
                       {
                           r.getLights().get(i).setOn(true);
                           savePower();
                           Log.e("isCheckedIF", ""+r.getLights().get(i).isOn());
                       }
                   }

                   else
                   {
                       on = false;
                       for (int i = 0; i< 3; i++)
                       {
                           r.getLights().get(i).setOn(false);
                           savePower();
                           Log.e("isCheckedELSE", ""+r.getLights().get(i).isOn());
                       }
                   }

                   setImages();
               }
           });
       }

       return light;

    }

    @Override
    public void onClick(View view) {
        onClickHelper();
    }

    public void onClickHelper(){
        brightness = r.getLights().get(arrayposition).getBrightness();
        on = r.getLights().get(arrayposition).isOn();
        Intent intent = new Intent(getActivity(), LightSettingsActivity.class);
        intent.putExtra(LIGHT_BRIGHTNESS, brightness);
        intent.putExtra(LIGHT_POWER, on);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case(REQUEST_CODE):{
            if(resultCode == RESULT_OK){
                brightness = data.getDoubleExtra(LIGHT_BRIGHTNESS, brightness);
                on = data.getBooleanExtra(LIGHT_POWER, on);
                saveBrightness();
                savePower();
                setImages();
                setSwitchPosition();
            }
            break;
            }
        }
    }

    public void doAtStart()
    {
        arrayposition=0;
        brightness = r.getLights().get(0).getBrightness();
        on = r.getLights().get(0).isOn();
        setSwitchPosition();
        setImages();
    }

    public void saveBrightness()
    {
        r.getLights().get(arrayposition).setBrightness(brightness);
        db.setBrightness(r.getName(), r.getLights().get(arrayposition).getName(), brightness);

    }
    public void savePower()
    {
        r.getLights().get(arrayposition).setOn(on);
        db.setLightPower(r.getName(), r.getLights().get(arrayposition).getName(), on);
    }

    public void switchSettings (boolean isChecked){
        if(isChecked) {on = true;}
        else { on = false;}
    }

    public void setSwitchPosition()
    {
        if (r.getName().equals(Util.BATH))
        {
            sFLightBath.setChecked(on);
        }

        else if (r.getName().equals(Util.LIVING))
        {
            sFLightLiving.setChecked(on);
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            sFLightSleeping.setChecked(on);
        }

        else if (r.getName().equals(Util.HALLWAY))
        {
            sFLightHallway.setChecked(on);
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            getIsArrayOn();
            sFLightKitchen.setChecked(on);

        }
    }

    public void setImages()
    {

        if (r.getName().equals(Util.BATH))
        {
            if(on){iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            else { iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
        }

        else if (r.getName().equals(Util.LIVING))
        {
            if(on){iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            else { iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
        }

        else if (r.getName().equals(Util.SLEEPING))
        {
            if(on){iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            else { iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
        }

        else if (r.getName().equals(Util.HALLWAY))
        {
            if(on){iVFLightHallway.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            else { iVFLightHallway.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
        }

        else if (r.getName().equals(Util.KITCHEN))
        {
            if(r.getLights().get(0).isOn()){iVFLightKitchen1.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            if(r.getLights().get(1).isOn()){iVFLightKitchen2.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            if(r.getLights().get(2).isOn()){iVFLightKitchen3.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
            if(!r.getLights().get(0).isOn()){iVFLightKitchen1.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
            if(!r.getLights().get(1).isOn()){iVFLightKitchen2.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
            if(!r.getLights().get(2).isOn()){iVFLightKitchen3.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
        }

    }

    public void getIsArrayOn()
    {
        boolean light1 = r.getLights().get(0).isOn();
        boolean light2 = r.getLights().get(1).isOn();
        boolean light3 = r.getLights().get(2).isOn();

        if (!light1 && !light2 && !light3) {
            on = false;
        } else {
            on = true;
        }
    }


}
