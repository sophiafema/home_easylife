package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.sophiafema.home_easylife.database.Database;
import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.House;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.models.Thermostat;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener {

    TextView tVHomeLiving;
    TextView tVHomeSleeping;
    TextView tVHomeHallway;
    TextView tVHomeKitchen;
    TextView tVHomeBath;
    TextView tVHomeGeneral;
    TextView tVHomeEvents;


    ImageView iVHomeSettings;
    ImageView iVHomeLiving;
    ImageView iVHomeSleeping;
    ImageView iVHomeHallway;
    ImageView iVHomeKitchen;
    ImageView iVHomeBath;
    ImageView iVHomeGeneral;
    ImageView iVHomeEvents;


    private ProgressBar spinner;
    Room r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        tVHomeLiving = (TextView) findViewById(R.id.tVHomeLiving);
        tVHomeSleeping = (TextView) findViewById(R.id.tVHomeSleeping);
        tVHomeHallway = (TextView) findViewById(R.id.tVHomeHallway);
        tVHomeKitchen = (TextView) findViewById(R.id.tVHomeKitchen);
        tVHomeBath = (TextView) findViewById(R.id.tVHomeBath);
        tVHomeGeneral = (TextView) findViewById(R.id.tVHomeGeneral);
        tVHomeEvents = (TextView) findViewById(R.id.tVHomeEvents);

        iVHomeSettings = (ImageView) findViewById(R.id.iVHomeSettings);
        iVHomeSettings .setOnClickListener(this);
        iVHomeLiving = (ImageView) findViewById(R.id.iVHomeLiving);
        iVHomeLiving .setOnClickListener(this);
        iVHomeSleeping = (ImageView) findViewById(R.id.iVHomeSleeping);
        iVHomeSleeping .setOnClickListener(this);
        iVHomeHallway = (ImageView) findViewById(R.id.iVHomeHallway);
        iVHomeHallway .setOnClickListener(this);
        iVHomeKitchen = (ImageView) findViewById(R.id.iVHomeKitchen);
        iVHomeKitchen .setOnClickListener(this);
        iVHomeBath = (ImageView) findViewById(R.id.iVHomeBath);
        iVHomeBath .setOnClickListener(this);
        iVHomeGeneral = (ImageView) findViewById(R.id.iVHomeGeneral);
        iVHomeGeneral .setOnClickListener(this);
        iVHomeEvents = (ImageView) findViewById(R.id.iVHomeEvents);
        iVHomeEvents .setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        spinner.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        spinner.setVisibility(View.VISIBLE);
        if(view.getId() == R.id.iVHomeGeneral)
        {
            Intent intent1 = new Intent(this, General.class);
            startActivity(intent1);
        }
        else if(view.getId() == R.id.iVHomeEvents)
        {
            Intent intent2 = new Intent(this, Events.class);
            startActivity(intent2);
        }
        else if(view.getId() == R.id.iVHomeSettings)
        {
            Intent intent2 = new Intent(this, Settings.class);
            startActivity(intent2);
        }
        else
        {
            Intent intent3 = new Intent(this, PlainRoom.class);

            if(view.getId() == R.id.iVHomeHallway)
            {
                intent3.putExtra(Util.ROOM, Util.HALLWAY);
            }
            else if (view.getId() == R.id.iVHomeBath)
            {
                intent3.putExtra(Util.ROOM, Util.BATH);
            }
            else if (view.getId() == R.id.iVHomeKitchen)
            {
                intent3.putExtra(Util.ROOM, Util.KITCHEN);
            }
            else if (view.getId() == R.id.iVHomeLiving)
            {
                intent3.putExtra(Util.ROOM, Util.LIVING);
            }
            else if (view.getId() == R.id.iVHomeSleeping)
            {
                intent3.putExtra(Util.ROOM, Util.SLEEPING);
            }
            startActivity(intent3);
        }
    }



}
