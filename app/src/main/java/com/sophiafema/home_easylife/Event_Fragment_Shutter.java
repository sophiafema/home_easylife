package com.sophiafema.home_easylife;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.view.Shutters;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Event_Fragment_Shutter extends AppCompatActivity {

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

    EventsRoom r;
    double position;
    double [] positionlist = new double [2];

    TextView save;
    TextView cancel;

    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shutter_bath);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            r = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
        }

        System.out.println(r.getName());


        if(r == null) {
            r = new EventsRoom(Util.LIVING, 0);
        }

        ArrayList<Shutter> shutters = new ArrayList<>();
        if(r.getShutters().size() < 1) {
            db = new DatabaseAdapter();
            shutters = db.getShutters(r.getName());
            r.setShutters(shutters);
        }

        if (r.getName().equals(Util.BATH) )
        {
            setContentView(R.layout.activity_events_shutter_bath);
            position = r.getShutters().get(0).getPosition();

            shutterFBath = (com.sophiafema.home_easylife.view.Shutters) findViewById(R.id.shutterFBath);
            iVFBathShuttersUp = (ImageView) findViewById(R.id.iVFBathShuttersUp);
            iVFBathShuttersDown = (ImageView) findViewById(R.id.iVFBathShuttersDown);

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
            setContentView(R.layout.activity_events_shutter_kitchen);

            positionlist[0] = r.getShutters().get(0).getPosition();
            positionlist[1] = r.getShutters().get(1).getPosition();

            shutterFKitchen1 = (com.sophiafema.home_easylife.view.Shutters) findViewById(R.id.shutterFKitchen1);
            shutterFKitchen2 = (com.sophiafema.home_easylife.view.Shutters) findViewById(R.id.shutterFKitchen2);
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

            iVFKitchenShuttersUp = (ImageView) findViewById(R.id. iVFKitchenShuttersUp);
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
            iVFKitchenShuttersDown = (ImageView) findViewById(R.id.iVFKitchenShuttersDown);
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
            setContentView(R.layout.activity_events_shutter_living);

            position = r.getShutters().get(0).getPosition();

            shutterFLiving = (com.sophiafema.home_easylife.view.Shutters) findViewById(R.id.shutterFLiving);
            iVFLivingShuttersUp = (ImageView) findViewById(R.id. iVFLivingShuttersUp);
            iVFLivingShuttersDown = (ImageView) findViewById(R.id.iVFLivingShuttersDown);

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
            setContentView(R.layout.activity_events_shutter_sleeping);
            position = r.getShutters().get(0).getPosition();

            shutterFSleeping = (com.sophiafema.home_easylife.view.Shutters) findViewById(R.id.shutterFSleeping);
            iVFSleepingShuttersUp = (ImageView) findViewById(R.id. iVFSleepingShuttersUp);
            iVFSleepingShuttersDown = (ImageView) findViewById(R.id.iVFSleepingShuttersDown);

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

        setBackground();
        save = findViewById(R.id.tVEventsShutters_Save);
        cancel = findViewById(R.id.tVEventsShutters_Cancel);

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

    //Position der Jalousie in Room und Database speichern
    public void setShutterPosition(int arrayposition, float value)
    {
        r.getShutters().get(arrayposition).setPosition(value);
    }

    public void setBackground()
    {
        ImageView iVLightSettingBackground = (ImageView) findViewById(R.id.iVShutterSettingBackground);

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