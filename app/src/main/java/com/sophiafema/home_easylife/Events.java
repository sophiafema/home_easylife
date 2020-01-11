package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

//import com.sophiafema.home_easylife.models.Event;

public class Events extends AppCompatActivity implements View.OnClickListener {

//    RecyclerView recyclerView;
//    recyclerView.setHasFixedSize(true);
//    LinearLayoutManager llm = new LinearLayoutManager(context);
//    recyclerView.setLayoutManager(llm);

    ImageView iVEventsMenue;
    TextView tVEventsHeading;
    ImageView iVEventsAdd;

    ImageView iVEventsBackground;
    TextView tVEventsName;
    TextView tVEventsTime;
    TextView tVEventsRepetition;
    Switch swEvents;
    ImageView iVEventsPicture;

//    Event event;
//    String name [];
//    int images [] = {R.drawable.common_google_signin_btn_icon_light, R.drawable.common_full_open_on_phone,
//            R.drawable.common_google_signin_btn_text_dark_focused, R.drawable.common_google_signin_btn_icon_light_normal_background,
//            R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_text_disabled};

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
        tVEventsName = (TextView) findViewById(R.id.tVEventsName);
        tVEventsTime = (TextView) findViewById(R.id.tVEventsTime);
        tVEventsRepetition = (TextView) findViewById(R.id.tVEventsRepetition);
        swEvents = (Switch) findViewById(R.id.swEvents);
        iVEventsPicture = (ImageView) findViewById(R.id.iVEventsPicture);

//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

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
