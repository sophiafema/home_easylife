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
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Room;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Event_Fragment_Light extends AppCompatActivity implements View.OnClickListener
{
    public static final String LIGHT_BRIGHTNESS = "LIGHT_BRIGHTNESS";
    public static final String LIGHT_POWER = "LIGHT_POWER";
    public static final String LIGHT_ROOM = "LIGHT_ROOM";
    static final int REQUEST_CODE = 1; // The request code.
    EventsRoom r;
    ArrayList<Light> lights;
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

    TextView save;
    TextView cancel;
    DatabaseAdapter dba;

    boolean clickedOnSwitch=true;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            r = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
        }


        if(r == null) {
            r = new EventsRoom(Util.LIVING, 0);
        }
        /*ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "esstisch", 3, 4, false));
        l.add(new Light(1, "sofa", 3, 4, false));
        l.add(new Light(2, "general", 3, 4, false));
        r.setLights(l);*/

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
                doAtStart();

                iVFLightBath.setOnClickListener(this);
                sFLightBath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);
                        savePower(isChecked, arrayposition);
                        setImages(isChecked);
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

                doAtStart();

                iVFLightHallway.setOnClickListener(this);
                sFLightHallway.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);
                        savePower(isChecked, arrayposition);
                        setImages(isChecked);
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

                doAtStart();

                iVFLightLiving.setOnClickListener(this);
                sFLightLiving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);
                        savePower(isChecked, arrayposition);
                        setImages(isChecked);
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

                doAtStart();

                iVFLightSleeping.setOnClickListener(this);
                sFLightSleeping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        switchSettings(isChecked);
                        savePower(isChecked, arrayposition);
                        setImages(isChecked);
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

                getIsArrayOn();
                setSwitchPosition(on);
                setImages(on);

                iVFLightKitchen1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayposition = 0;
                        onClickHelper();
                    }
                });

                iVFLightKitchen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayposition = 1;
                        onClickHelper();
                    }
                });

                iVFLightKitchen3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayposition = 2;
                        onClickHelper();
                    }
                });


                sFLightKitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        boolean light1 = r.getLights().get(0).isOn();
                        boolean light2 = r.getLights().get(1).isOn();
                        boolean light3 = r.getLights().get(2).isOn();

                        if (isChecked && clickedOnSwitch) {

                            on = true;
                            for (int i = 0; i < 3; i++) {
                                r.getLights().get(i).setOn(true);
                                savePower(on, i);
                            }
                            System.out.println("schleife 1");
                        } else if (clickedOnSwitch) {
                            System.out.println("schleife 0");
                            allLightsOff();
                        }

                        setImages(on);
                        clickedOnSwitch = true;
                    }
                });
            }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Util.EVENTSROOM, r);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Util.EVENTSROOM, bundle);
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
            onClickHelper();
        }

        public void onClickHelper () {
            brightness = r.getLights().get(arrayposition).getBrightness();
            on = r.getLights().get(arrayposition).isOn();
            Intent intent = new Intent(this, LightSettingsActivity.class);
            intent.putExtra(LIGHT_BRIGHTNESS, brightness);
            intent.putExtra(LIGHT_POWER, on);
            intent.putExtra(LIGHT_ROOM, r.getName());
            startActivityForResult(intent, REQUEST_CODE);
        }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case (REQUEST_CODE): {
                    if (resultCode == RESULT_OK) {
                        brightness = data.getDoubleExtra(LIGHT_BRIGHTNESS, brightness);
                        on = data.getBooleanExtra(LIGHT_POWER, on);
                        System.out.println(on);
                        System.out.println(brightness);
                        saveBrightness(brightness, arrayposition);
                        savePower(on, arrayposition);
                        setImages(on);
                        clickedOnSwitch = false;
                        setSwitchPosition(on);
                    }
                    break;
                }
            }
        }

        public void doAtStart ()
        {
            arrayposition = 0;
            brightness = r.getLights().get(0).getBrightness();
            on = r.getLights().get(0).isOn();
            setSwitchPosition(on);
            setImages(on);
        }

        public void saveBrightness ( double brightness, int arrayposition)
        {
            r.getLights().get(arrayposition).setBrightness(brightness);

        }
        public void savePower ( boolean on, int arrayposition)
        {
            r.getLights().get(arrayposition).setOn(on);
        }

        public void switchSettings ( boolean isChecked){
            if (isChecked) {
                on = true;
            } else {
                on = false;
            }
        }

        public void setSwitchPosition ( boolean on)
        {
            if (r.getName().equals(Util.BATH)) {
                sFLightBath.setChecked(on);
            } else if (r.getName().equals(Util.LIVING)) {
                sFLightLiving.setChecked(on);
            } else if (r.getName().equals(Util.SLEEPING)) {
                sFLightSleeping.setChecked(on);
            } else if (r.getName().equals(Util.HALLWAY)) {
                sFLightHallway.setChecked(on);
            } else if (r.getName().equals(Util.KITCHEN)) {
                getIsArrayOn();
                sFLightKitchen.setChecked(on);
            }
        }

        public void setImages ( boolean on)
        {

            if (r.getName().equals(Util.BATH)) {
                if (on) {
                    iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                } else {
                    iVFLightBath.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
            } else if (r.getName().equals(Util.LIVING)) {
                if (on) {
                    iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                } else {
                    iVFLightLiving.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
            } else if (r.getName().equals(Util.SLEEPING)) {
                if (on) {
                    iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                } else {
                    iVFLightSleeping.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
            } else if (r.getName().equals(Util.HALLWAY)) {
                if (on) {
                    iVFLightHallway.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                } else {
                    iVFLightHallway.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
            } else if (r.getName().equals(Util.KITCHEN)) {
                if (r.getLights().get(0).isOn()) {
                    iVFLightKitchen1.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                }
                if (r.getLights().get(1).isOn()) {
                    iVFLightKitchen2.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                }
                if (r.getLights().get(2).isOn()) {
                    iVFLightKitchen3.setImageResource(R.drawable.ic_gluehbirne_an_raum);
                }
                if (!r.getLights().get(0).isOn()) {
                    iVFLightKitchen1.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
                if (!r.getLights().get(1).isOn()) {
                    iVFLightKitchen2.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
                if (!r.getLights().get(2).isOn()) {
                    iVFLightKitchen3.setImageResource(R.drawable.ic_gluehbirne_aus_raum);
                }
            }

        }

        public void getIsArrayOn ()
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

        public void allLightsOff ()
        {
            on = false;
            for (int i = 0; i < 3; i++) {
                r.getLights().get(i).setOn(false);
                savePower(false, i);
            }
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
