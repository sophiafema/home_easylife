package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener {

    TextView tVHomeLiving;
    TextView tVHomeSleeping;
    TextView tVHomeHallway;
    TextView tVHomeKitchen;
    TextView tVHomeBath;
    TextView tVHomeGeneral;
    TextView tVHomeEvents;


    ImageView iVHomeLiving;
    ImageView iVHomeSleeping;
    ImageView iVHomeHallway;
    ImageView iVHomeKitchen;
    ImageView iVHomeBath;
    ImageView iVHomeGeneral;
    ImageView iVHomeEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tVHomeLiving = (TextView) findViewById(R.id.tVHomeLiving);
        tVHomeSleeping = (TextView) findViewById(R.id.tVHomeSleeping);
        tVHomeHallway = (TextView) findViewById(R.id.tVHomeHallway);
        tVHomeKitchen = (TextView) findViewById(R.id.tVHomeKitchen);
        tVHomeBath = (TextView) findViewById(R.id.tVHomeBath);
        tVHomeGeneral = (TextView) findViewById(R.id.tVHomeGeneral);
        tVHomeEvents = (TextView) findViewById(R.id.tVHomeEvents);

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
    public void onClick(View view) {
        System.out.println(view.getId());
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

        else
        {
            Intent intent3 = new Intent(this, PlainRoom.class);
            startActivity(intent3);
        }
    }



}
