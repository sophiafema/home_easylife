package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class Events extends AppCompatActivity implements View.OnClickListener {

    ImageView iVEventsMenue;
    TextView tVEventsHeading;
    ImageView iVEventsAdd;


    ImageView iVEventsBackground;
    TextView tVEventsRoom;
    TextView tVEventsName;
    TextView tVEventsTime;
    TextView tVEventsRepetition;
    Switch swEvents;
    ImageView iVEventsPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        iVEventsMenue = (ImageView) findViewById(R.id.iVEventsMenue);
        tVEventsHeading = (TextView) findViewById(R.id.tVEventsHeading);
        tVEventsHeading.setOnClickListener(this);
        iVEventsAdd = (ImageView) findViewById(R.id.iVEventsAdd);
        iVEventsAdd.setOnClickListener(this);

        iVEventsBackground = (ImageView) findViewById(R.id.iVEventsBackground);
        tVEventsRoom = (TextView) findViewById(R.id.tVEventsRoom);
        tVEventsName = (TextView) findViewById(R.id.tVEventsName);
        tVEventsTime = (TextView) findViewById(R.id.tVEventsTime);
        tVEventsRepetition = (TextView) findViewById(R.id.tVEventsRepetition);
        swEvents = (Switch) findViewById(R.id.swEvents);
        iVEventsPicture = (ImageView) findViewById(R.id.iVEventsPicture);


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
