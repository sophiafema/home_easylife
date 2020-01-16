package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.Room;

import java.util.ArrayList;

public class Events_Add extends AppCompatActivity implements View.OnClickListener{

    TextView tVEvents_AddCancel;
    TextView tVEvents_AddSave;

    ImageView iVEvents_AddPicture;
    EditText eTEvents_AddName;

    ImageView iVEvents_AddHallway;
    ImageView iVEvents_AddLiving;
    ImageView iVEvents_AddBath;
    ImageView iVEvents_AddSleeping;
    ImageView iVEvents_AddKitchen;

    ConstraintLayout iVEvents_AddLight;
    ConstraintLayout iVEvents_AddTemperature;
    ConstraintLayout iVEvents_AddShutters;
    ConstraintLayout iVEvents_AddMusic;

    TextView tVEvents_AddLight;
    TextView tVEvents_AddTemperature;
    TextView tVEvents_AddShutters;
    TextView tVEvents_AddMusic;

    ImageView iVAddLight;
    ImageView iVAddTemperature;
    ImageView iVAddShutters;
    ImageView iVAddMusic;


    Room r;
    DatabaseAdapter db;
    String currentRoom;
    Event event;


    // Bottom sheet
    BottomSheetDialog bottomSheetDialog;

    public static final String EVENTS_PICTURES = "EVENTS_PICTURES";

    static final int REQUEST_CODE = 2;
    static final int REQUEST_CODE_LIGHT = 3;
    static final int REQUEST_CODE_MUSIC = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__add);

        tVEvents_AddCancel = (TextView) findViewById(R.id.tVEvents_AddCancel);
        tVEvents_AddCancel.setOnClickListener(this);

        tVEvents_AddSave = (TextView) findViewById(R.id.tVEvents_AddSave);

        iVEvents_AddPicture = (ImageView) findViewById(R.id.iVEvents_AddPicture);
        iVEvents_AddPicture.setOnClickListener(this);
        eTEvents_AddName = (EditText) findViewById(R.id.eTEvents_AddName);

        iVEvents_AddHallway = (ImageView) findViewById(R.id.iVEvents_AddHallway);
        iVEvents_AddHallway.setOnClickListener(this);
        iVEvents_AddLiving = (ImageView) findViewById(R.id.iVEvents_AddLiving);
        iVEvents_AddLiving.setOnClickListener(this);
        iVEvents_AddBath = (ImageView) findViewById(R.id.iVEvents_AddBath);
        iVEvents_AddBath.setOnClickListener(this);
        iVEvents_AddSleeping = (ImageView) findViewById(R.id.iVEvents_AddSleeping);
        iVEvents_AddSleeping.setOnClickListener(this);
        iVEvents_AddKitchen = (ImageView) findViewById(R.id.iVEvents_AddKitchen);
        iVEvents_AddKitchen.setOnClickListener(this);

        iVEvents_AddLight = findViewById(R.id.iVEvents_AddLight);
        iVEvents_AddTemperature = findViewById(R.id.iVEvents_AddTemperature);
        iVEvents_AddShutters = findViewById(R.id.iVEvents_AddShutters);
        iVEvents_AddMusic = findViewById(R.id.iVEvents_AddMusic);


        tVEvents_AddLight = (TextView) findViewById(R.id.tVEvents_AddLight);
        tVEvents_AddTemperature = (TextView) findViewById(R.id.tVEvents_AddTemperature);
        tVEvents_AddShutters = (TextView) findViewById(R.id.tVEvents_AddShutters);
        tVEvents_AddMusic = (TextView) findViewById(R.id.tVEvents_AddMusic);

        iVAddLight = findViewById(R.id.iVEventsLight);
        iVAddTemperature = findViewById(R.id.iVEventsAddTemperature);
        iVAddShutters = findViewById(R.id.iVEventsAddShutter);
        iVAddMusic = findViewById(R.id.iVEventsAddMusic);



        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra(Util.EVENT);

        //currentRoom = intent.getStringExtra(Util.ROOM);
        if(event == null) {
            currentRoom = Util.LIVING;
            event = new Event(0, "default", 0);

            //event.getRoomByName(currentRoom).setMusic(new Music());
        }
        else {
            if(event.getRooms() != null) {
                if(event.getRooms().size()>0)
                    currentRoom = event.getRooms().get(0).getName();
                else {
                    event.fillRooms();
                }
            }
            else {
                event.fillRooms();
            }
        }

        Log.e("current room", ""+currentRoom);
        changeRoom(currentRoom);


        // Bottom sheet
        bottomSheetDialog = new BottomSheetDialog(Events_Add.this);

        View view = getLayoutInflater().inflate(R.layout.events_bottomsheet, null);
        bottomSheetDialog.setContentView(view);

        View  take_photoView = view.findViewById(R.id.take_photo);
        View  choose_galeryView = view.findViewById(R.id.choose_galery);
        View  choose_iconView = view.findViewById(R.id.choose_icon);

        take_photoView.setOnClickListener(this);
        choose_galeryView.setOnClickListener(this);
        choose_iconView.setOnClickListener(this);

    }

    public EventsRoom eroom;
    public void setFunctionOverview(EventsRoom room) {
        eroom = room;
        if(room.hasLights()) {
            iVAddLight.setImageResource(R.drawable.ic_cancel_black);
        }
        else {
            iVAddLight.setImageResource(R.drawable.ic_add_circle_outline_black_24dp);
            iVAddLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Util.EVENTSROOM, eroom);
                    Intent i = new Intent(getBaseContext(), Event_Fragment_Light.class);
                    i.putExtra(Util.EVENTSROOM, bundle);
                    startActivityForResult(i, REQUEST_CODE_LIGHT);
                }
            });
        }
        if(room.hasThermostat())  {
            iVAddTemperature.setImageResource(R.drawable.ic_cancel_black);
        }
        else {
            iVAddTemperature.setImageResource(R.drawable.ic_add_circle_outline_black_24dp);
        }
        if(room.hasMusic())  {
            iVAddMusic.setImageResource(R.drawable.ic_cancel_black);
        }
        else {
            iVAddMusic.setImageResource(R.drawable.ic_add_circle_outline_black_24dp);
        }
        if(room.hasShutters())  {
            iVAddShutters.setImageResource(R.drawable.ic_cancel_black);

        }
        else {
            iVAddShutters.setImageResource(R.drawable.ic_add_circle_outline_black_24dp);
        }
    }

    public void changeRoom(String currentRoom) {
        setButtonColor(currentRoom);
        EventsRoom room = event.getRoomByName(currentRoom);
        setFunctionOverview(room);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id)
        {
            case R.id.tVEvents_AddCancel:
                this.finish();
                break;

            case R.id.iVEvents_AddPicture:
                bottomSheetDialog.show();
                break;

            case R.id.take_photo:
                Toast.makeText(this, "Foto", Toast.LENGTH_SHORT).show();
                break;

            case R.id.choose_galery:
                Toast.makeText(this, "Galerie", Toast.LENGTH_SHORT).show();
                break;

            case R.id.choose_icon:
                Intent intent1 = new Intent(this, Events_Pictures.class);
                startActivityForResult(intent1, REQUEST_CODE);
                bottomSheetDialog.cancel();
                break;

            case R.id.iVEvents_AddHallway:
                currentRoom = Util.HALLWAY;
                changeRoom(currentRoom);
                break;

            case R.id.iVEvents_AddLiving:
                currentRoom = Util.LIVING;
                changeRoom(currentRoom);
                break;

            case R.id.iVEvents_AddSleeping:
                currentRoom = Util.SLEEPING;
                changeRoom(currentRoom);
                break;

            case R.id.iVEvents_AddKitchen:
                currentRoom = Util.KITCHEN;
                changeRoom(currentRoom);
                break;

            case R.id.iVEvents_AddBath:
                currentRoom = Util.BATH;
                changeRoom(currentRoom);
                break;
        }


    }


    public void setButtonColor (String currentRoom) {
        switch (currentRoom) {
            case Util.LIVING:
                iVEvents_AddLiving.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVEvents_AddHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.BATH:
                iVEvents_AddLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddBath.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                break;
            case Util.HALLWAY:
                iVEvents_AddLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddHallway.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVEvents_AddSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.KITCHEN:
                iVEvents_AddLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddKitchen.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVEvents_AddBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.SLEEPING:
                iVEvents_AddLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddSleeping.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVEvents_AddKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVEvents_AddBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        int resourceId;
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case(REQUEST_CODE):
                    resourceId = data.getIntExtra(EVENTS_PICTURES, 0);
                    iVEvents_AddPicture.setImageResource(resourceId);
                    break;
                case REQUEST_CODE_LIGHT:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        //EventsRoom eventsRoom = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
                        //event.setRoomByName(eventsRoom.getName(), eventsRoom);
                        //setFunctionOverview(eventsRoom);
                        //System.out.println(eventsRoom.getName());
                    }
                    break;


            }
        }
    }


}
