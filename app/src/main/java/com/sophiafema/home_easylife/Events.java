package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Events extends AppCompatActivity implements View.OnClickListener {

    ImageView iVEventsMenue;
    TextView tVEventsMenue;

    TextView tVEventsHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        iVEventsMenue = (ImageView) findViewById(R.id.iVEventsMenue);
        tVEventsMenue = (TextView) findViewById(R.id.tVEventsMenue);
        tVEventsMenue.setOnClickListener(this);

        tVEventsHeading = (TextView) findViewById(R.id.tVEventsHeading);


    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}