package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sophiafema.home_easylife.models.Event;

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

    ImageView iVEvents_AddLight;
    ImageView iVEvents_AddTemperature;
    ImageView iVEvents_AddShutters;
    ImageView iVEvents_AddMusic;

    TextView tVEvents_AddLight;
    TextView tVEvents_AddTemperature;
    TextView tVEvents_AddShutters;
    TextView tVEvents_AddMusic;

    // Bottom sheet
    BottomSheetDialog bottomSheetDialog;

    public static final String EVENTS_PICTURES = "EVENTS_PICTURES";

    static final int REQUEST_CODE = 2;



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
        iVEvents_AddLiving = (ImageView) findViewById(R.id.iVEvents_AddLiving);
        iVEvents_AddBath = (ImageView) findViewById(R.id.iVEvents_AddBath);
        iVEvents_AddSleeping = (ImageView) findViewById(R.id.iVEvents_AddSleeping);
        iVEvents_AddKitchen = (ImageView) findViewById(R.id.iVEvents_AddKitchen);


        iVEvents_AddLight = (ImageView) findViewById(R.id.iVEvents_AddLight);
        iVEvents_AddTemperature = (ImageView) findViewById(R.id.iVEvents_AddTemperature);
        iVEvents_AddShutters = (ImageView) findViewById(R.id.iVEvents_AddShutters);
        iVEvents_AddMusic = (ImageView) findViewById(R.id.iVEvents_AddMusic);


        tVEvents_AddLight = (TextView) findViewById(R.id.tVEvents_AddLight);
        tVEvents_AddTemperature = (TextView) findViewById(R.id.tVEvents_AddTemperature);
        tVEvents_AddShutters = (TextView) findViewById(R.id.tVEvents_AddShutters);
        tVEvents_AddMusic = (TextView) findViewById(R.id.tVEvents_AddMusic);


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
