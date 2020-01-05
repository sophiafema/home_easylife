package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Events extends AppCompatActivity implements View.OnClickListener {

    ImageView iVEventsMenue;
    TextView tVEventsHeading;
    ImageView iVEventsAdd;

    TextView tVEventsAll;
    ImageView iVEventsAll;

    TextView tVEventsKitchen;
    ImageView iVEventsKitchen;

    TextView tVEventsLiving;
    ImageView iVEventsLiving;

    TextView tVEventsSleeping;
    ImageView iVEventsSleeping;

    TextView tVEventsBath;
    ImageView iVEventsBath;

    TextView tVEventsHallway;
    ImageView iVEventsHallway;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        iVEventsMenue = (ImageView) findViewById(R.id.iVEventsMenue);
        tVEventsHeading = (TextView) findViewById(R.id.tVEventsHeading);
        tVEventsHeading.setOnClickListener(this);
        iVEventsAdd = (ImageView) findViewById(R.id.iVEventsAdd);
        iVEventsAdd.setOnClickListener(this);


        tVEventsAll = (TextView) findViewById(R.id.tVEventsAll);
        iVEventsAll = (ImageView) findViewById(R.id.iVEventsAll);
        tVEventsKitchen = (TextView) findViewById(R.id.tVEventsKitchen);
        iVEventsKitchen = (ImageView) findViewById(R.id.iVEventsKitchen);
        tVEventsLiving = (TextView) findViewById(R.id.tVEventsLiving);
        iVEventsLiving = (ImageView) findViewById(R.id.iVEventsLiving);
        tVEventsSleeping = (TextView) findViewById(R.id.tVEventsSleeping);
        iVEventsSleeping = (ImageView) findViewById(R.id.iVEventsSleeping);
        tVEventsBath = (TextView) findViewById(R.id.tVEventsBath);
        iVEventsBath = (ImageView) findViewById(R.id.iVEventsBath);
        tVEventsHallway = (TextView) findViewById(R.id.tVEventsHallway);
        iVEventsHallway = (ImageView) findViewById(R.id.iVEventsHallway);


    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
             if(view.getId() == R.id.tVEventsHeading)
             {
                 this.finish();
             }
             else if(view.getId() == R.id.iVEventsAdd)
             {
                 Intent intent1 = new Intent(this, Events_Add.class);
                 startActivity(intent1);
             }
    }


}
