package com.sophiafema.home_easylife;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Event_Fragment_Light extends AppCompatActivity implements View.OnClickListener
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    public static final String LIGHT_POWER = "LIGHT_POWER";
    public static final String LIGHT_ROOM = "LIGHT_ROOM";
    public static final String LIGHT_APOSITION = "LIGHT_APOSITION";
    static final int REQUEST_CODE = 1; // The request code.
    EventsRoom r;
    ArrayList<Light> lights;
    static double brightness;
    static boolean on;
    static int aPosition;
    static boolean powerManual = false;

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

    TextView save;
    TextView cancel;
    DatabaseAdapter dba;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent mI = getIntent();
        /*Bundle bundle = this.getIntent().getExtras();
        System.out.println(bundle);
        Light light = null;
        if (bundle != null) {
            light = (Light) bundle.getSerializable(Util.EVENTSROOM);
            System.out.println(bundle.getSerializable(Util.EVENTSROOM));
        }
        //Light light = mI.getParcelableExtra(Util.EVENTSROOM);
        System.out.println("light " + light);*/


        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            r = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
        }

        System.out.println(r.getName());


        if(r == null) {
            r = new EventsRoom(Util.LIVING, 0);
        }

        if(r.getLights().size() < 1) {
            dba = new DatabaseAdapter();
            lights = dba.getLights(r.getName());
            r.setLights(lights);
        }


        if (r.getName().equals(Util.BATH)) {
                setContentView(R.layout.activity_events_light_bath);
                setBackground();

                sFLightBath = findViewById(R.id.sFLightBath);
                iVFLightBath = findViewById(R.id.iVFLightBath);
                save = findViewById(R.id.tVEventsLight_SaveBath);
                cancel = findViewById(R.id.tVEventsLight_CancelBath);

                boolean []lightOn = {r.getLights().get(0).isOn()};

                setSwitchPosition(lightOn);
                setImages(lightOn);

                iVFLightBath.setOnClickListener(this);
                sFLightBath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);

                        boolean [] lightOn = {isChecked};

                        savePower(lightOn, 0);
                        setImages(lightOn);
                    }
                });
            }
        else if (r.getName().equals(Util.HALLWAY)) {
                setContentView(R.layout.activity_events_light_hallway);
                setBackground();
                iVFLightHallway = findViewById(R.id.iVFLightHallway1);
                sFLightHallway = findViewById(R.id.sFLightHallway);
                save = findViewById(R.id.tVEventsLight_SaveHallway);
                cancel = findViewById(R.id.tVEventsLight_CancelHallway);

                boolean []lightOn = {r.getLights().get(0).isOn()};

                setSwitchPosition(lightOn);
                setImages(lightOn);

                iVFLightHallway.setOnClickListener(this);
                sFLightHallway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);

                        boolean [] lightOn = {isChecked};

                        savePower(lightOn, 0);
                        setImages(lightOn);
                    }
                });

            }
        else if (r.getName().equals(Util.LIVING)) {
                setContentView(R.layout.activity_events_light_living);
                setBackground();
                iVFLightLiving = findViewById(R.id.iVFLightLiving1);
                sFLightLiving = findViewById(R.id.sFLightLiving);
                save = findViewById(R.id.tVEventsLight_SaveLiving);
                cancel = findViewById(R.id.tVEventsLight_CancelLiving);

                boolean []lightOn = {r.getLights().get(0).isOn()};

                setSwitchPosition(lightOn);
                setImages(lightOn);

                iVFLightLiving.setOnClickListener(this);
                sFLightLiving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);

                        boolean [] lightOn = {isChecked};

                        savePower(lightOn, 0);
                        setImages(lightOn);
                    }
                });
            }
        else if (r.getName().equals(Util.SLEEPING)) {
                setContentView(R.layout.activity_events_light_sleeping);
                setBackground();
                iVFLightSleeping = findViewById(R.id.iVFLightSleeping1);
                sFLightSleeping = findViewById((R.id.sFLightSleeping));
                save = findViewById(R.id.tVEventsLight_SaveSleeping);
                cancel = findViewById(R.id.tVEventsLight_CancelSleeping);

                boolean []lightOn = {r.getLights().get(0).isOn()};

                setSwitchPosition(lightOn);
                setImages(lightOn);

                iVFLightSleeping.setOnClickListener(this);
                sFLightSleeping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);

                        boolean [] lightOn = {isChecked};

                        savePower(lightOn, 0);
                        setImages(lightOn);
                    }
                });
            }
        else if (r.getName().equals(Util.KITCHEN)) {
                setContentView(R.layout.activity_events_light_kitchen);
                save = findViewById(R.id.tVEventsLight_SaveKitchen);
                cancel = findViewById(R.id.tVEventsLight_CancelKitchen);
                setBackground();
                sFLightKitchen = findViewById(R.id.sFLightKitchen);
                iVFLightKitchen1 = findViewById(R.id.iVFLightKitchen1); //Array Platz 0
                iVFLightKitchen2 = findViewById(R.id.iVFLightKitchen2); //Array Platz 1
                iVFLightKitchen3 = findViewById(R.id.iVFLightKitchen3); //Array Platz 2

                boolean []lightOn = {r.getLights().get(0).isOn(), r.getLights().get(1).isOn(), r.getLights().get(2).isOn()};

                boolean allLightOn = getIsArrayOn();
                setSwitchPosition(lightOn);
                setImages(lightOn);

                iVFLightKitchen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double lightBrightness = r.getLights().get(0).getBrightness();
                        boolean []lightOnOne = {r.getLights().get(0).isOn()};
                        powerManual = true;
                        onClickHelper(lightBrightness, Array.getBoolean(lightOnOne, 0),0);

                    }
                });

                iVFLightKitchen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double lightBrightness = r.getLights().get(1).getBrightness();
                        boolean []lightOnTwo = {r.getLights().get(1).isOn()};
                        powerManual = true;
                        onClickHelper(lightBrightness, Array.getBoolean(lightOnTwo, 0),1);

                    }
                });

                iVFLightKitchen3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double lightBrightness = r.getLights().get(2).getBrightness();
                        boolean []lightOnThree = {r.getLights().get(2).isOn()};
                        powerManual = true;
                        onClickHelper(lightBrightness, Array.getBoolean(lightOnThree, 0),2);

                    }
                });


                sFLightKitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        boolean [] lightOn = {isChecked};

                        if (isChecked && !powerManual) {

                            Array.setBoolean(lightOn, 0, true);
                            for (int i = 0; i < 3; i++) {
                                r.getLights().get(i).setOn(true);
                                savePower(lightOn, i);
                            }
                            System.out.println("schleife 1");
                        } else if(!isChecked){
                            Array.setBoolean(lightOn, 0, false);
                            for (int i = 0; i < 3; i++) {
                                r.getLights().get(i).setOn(false);
                                savePower(lightOn, i);
                            }

                        }
                        setImages(lightOn);
                    }
                });
            }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Util.EVENTSROOM, r);
                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        }

        @Override
        public void onClick (View view){

            double lightBrightness = r.getLights().get(0).getBrightness();
            boolean []lightOn = {r.getLights().get(0).isOn()};
            Log.e("Power", ""+Array.getBoolean(lightOn, 0));
            onClickHelper(lightBrightness, Array.getBoolean(lightOn, 0),0);
        }

        public void onClickHelper (double lightBrightness, boolean lightOn, int aPosition) {
            Intent intent = new Intent(this, LightSettingsActivity.class);
            intent.putExtra(LIGHT_BRIGHTNESS, lightBrightness);
            intent.putExtra(LIGHT_POWER, lightOn);
            intent.putExtra(LIGHT_ROOM, r.getName());
            intent.putExtra(LIGHT_APOSITION, aPosition);
            startActivityForResult(intent, REQUEST_CODE);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case(REQUEST_CODE):{
                    if(resultCode == RESULT_OK){
                        double lightBrightness = data.getDoubleExtra(LIGHT_BRIGHTNESS, brightness);
                        boolean [] lightOn = {data.getBooleanExtra(LIGHT_POWER, on)};
                        int resultAPosition = data.getIntExtra(LIGHT_APOSITION, aPosition);

                        if(lightBrightness == 0.0)
                        {
                            Array.setBoolean(lightOn, 0, false);
                        }

                        if(resultAPosition==2)
                        {
                            saveBrightness(lightBrightness, 2);
                            savePower(lightOn, 2);
                            Log.e("Schleife1", ""+resultAPosition);
                        }
                        else if (resultAPosition==1)
                        {
                            saveBrightness(lightBrightness, 1);
                            savePower(lightOn, 1);
                            Log.e("Schleife2", ""+resultAPosition);
                        }
                        else
                        {
                            saveBrightness(lightBrightness, 0);
                            savePower(lightOn, 0);
                            Log.e("Schleife3", ""+resultAPosition);
                            Log.e("Power", ""+Array.getBoolean(lightOn, 0));
                        }

                        setImages(lightOn);
                        setSwitchPosition(lightOn);
                        powerManual = false;
                    }
                    break;
                }
            }
        }

        public void saveBrightness ( double brightness, int arrayposition)
        {
            r.getLights().get(arrayposition).setBrightness(brightness);

        }
        public void savePower(boolean [] lightOn, int arrayposition)
        {
            r.getLights().get(arrayposition).setOn(Array.getBoolean(lightOn, 0));
        }

    public boolean switchSettings (boolean isChecked) {
        boolean lightOn;
        if (isChecked) {
            lightOn = true;
        } else {
            lightOn = false;
        }
        return lightOn;
    }

        public void setSwitchPosition(boolean []lightOn)
        {
            if (r.getName().equals(Util.BATH))
            {
                sFLightBath.setChecked(Array.getBoolean(lightOn, 0));
            }

            else if (r.getName().equals(Util.LIVING))
            {
                sFLightLiving.setChecked(Array.getBoolean(lightOn, 0));
            }

            else if (r.getName().equals(Util.SLEEPING))
            {
                sFLightSleeping.setChecked(Array.getBoolean(lightOn, 0));
            }

            else if (r.getName().equals(Util.HALLWAY))
            {
                sFLightHallway.setChecked(Array.getBoolean(lightOn, 0));
            }

            else if (r.getName().equals(Util.KITCHEN))
            {
                boolean allLightOn = getIsArrayOn();
                sFLightKitchen.setChecked(allLightOn);
            }
        }

        public void setImages (boolean [] lightOn){

            if (r.getName().equals(Util.BATH))
            {
                if(Array.getBoolean(lightOn, 0)){iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
                else { iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
            }

            else if (r.getName().equals(Util.LIVING))
            {
                if(Array.getBoolean(lightOn, 0)){iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
                else { iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
            }

            else if (r.getName().equals(Util.SLEEPING))
            {
                if(Array.getBoolean(lightOn, 0)){iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
                else { iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_aus_raum);}
            }

            else if (r.getName().equals(Util.HALLWAY))
            {
                if(Array.getBoolean(lightOn, 0)){iVFLightHallway.setImageResource(R.drawable.ic_gluehbirne_an_raum);}
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

        public boolean getIsArrayOn()
        {
            boolean allLightOn;

            if (!r.getLights().get(0).isOn() && !r.getLights().get(1).isOn() && !r.getLights().get(2).isOn()) {
                allLightOn = false;
            } else {
                allLightOn = true;
            }
            return allLightOn;
        }


    public void setBackground()
    {
        ImageView iVLightSettingBackground = (ImageView) findViewById(R.id.iVLightSettingBackground);

        if(r.getName().equals(Util.LIVING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);}
        else if(r.getName().equals(Util.BATH)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);}
        else if(r.getName().equals(Util.HALLWAY)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_flur_raum_v2);}
        else if(r.getName().equals(Util.KITCHEN)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_kueche_raum);}
        else if(r.getName().equals(Util.SLEEPING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);}
    }


}
