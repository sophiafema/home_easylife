package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.sophiafema.home_easylife.models.Room;

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


    Room r;
    DatabaseAdapter db;
    String currentRoom;


    // Bottom sheet
    BottomSheetDialog bottomSheetDialog;

    public static final String EVENTS_PICTURES = "EVENTS_PICTURES";

    static final int REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__add);



        //Intent intent = getIntent();
        //currentRoom = intent.getStringExtra(Util.ROOM);
        if(currentRoom == null) {
            currentRoom = Util.LIVING;
        }
        Log.e("current room", ""+currentRoom);



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


        findViewById(R.id.FRFEventAddFunction);

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

    public void setFunctionOverview(String currentRoom) {


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
                setButtonColor(currentRoom);
                break;

            case R.id.iVEvents_AddLiving:
                setButtonColor(currentRoom);
                break;

            case R.id.iVEvents_AddSleeping:
                setButtonColor(currentRoom);
                break;

            case R.id.iVEvents_AddKitchen:
                setButtonColor(currentRoom);
                break;

            case R.id.iVEvents_AddBath:
                setButtonColor(currentRoom);
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
        switch (requestCode){
            case(REQUEST_CODE):{
                if(resultCode == RESULT_OK){
                    System.out.println("drinnen");
                    resourceId = data.getIntExtra(EVENTS_PICTURES, 0);
                    iVEvents_AddPicture.setImageResource(resourceId);

                }
                break;
            }
        }
    }
}
