package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Events_Pictures extends AppCompatActivity implements View.OnClickListener{


    ImageView iVEvents_PictureMenue;

    ImageView iVEvents_PictureP1;
    ImageView iVEvents_PictureP2;
    ImageView iVEvents_PictureP3;
    ImageView iVEvents_PictureP4;
    ImageView iVEvents_PictureP5;
    ImageView iVEvents_PictureP6;
    ImageView iVEvents_PictureP7;
    ImageView iVEvents_PictureP8;
    ImageView iVEvents_PictureP9;
    ImageView iVEvents_PictureP10;
    ImageView iVEvents_PictureP11;
    ImageView iVEvents_PictureP12;
    ImageView iVEvents_PictureP13;
    ImageView iVEvents_PictureP14;
    ImageView iVEvents_PictureP15;


    ImageView iVEvents_AddPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__pictures);

        iVEvents_PictureMenue = (ImageView) findViewById(R.id.iVEvents_PictureMenue);
        iVEvents_PictureMenue.setOnClickListener(this);
        iVEvents_PictureP1 = (ImageView) findViewById(R.id.iVEvents_PictureP1);
        iVEvents_PictureP1.setOnClickListener(this);
        iVEvents_PictureP2 = (ImageView) findViewById(R.id.iVEvents_PictureP2);
        iVEvents_PictureP2.setOnClickListener(this);
        iVEvents_PictureP3 = (ImageView) findViewById(R.id.iVEvents_PictureP3);
        iVEvents_PictureP3.setOnClickListener(this);
        iVEvents_PictureP4 = (ImageView) findViewById(R.id.iVEvents_PictureP4);
        iVEvents_PictureP4.setOnClickListener(this);
        iVEvents_PictureP5 = (ImageView) findViewById(R.id.iVEvents_PictureP5);
        iVEvents_PictureP5.setOnClickListener(this);
        iVEvents_PictureP6 = (ImageView) findViewById(R.id.iVEvents_PictureP6);
        iVEvents_PictureP6.setOnClickListener(this);
        iVEvents_PictureP7 = (ImageView) findViewById(R.id.iVEvents_PictureP7);
        iVEvents_PictureP7.setOnClickListener(this);
        iVEvents_PictureP8 = (ImageView) findViewById(R.id.iVEvents_PictureP8);
        iVEvents_PictureP8.setOnClickListener(this);
        iVEvents_PictureP9 = (ImageView) findViewById(R.id.iVEvents_PictureP9);
        iVEvents_PictureP9.setOnClickListener(this);
        iVEvents_PictureP10 = (ImageView) findViewById(R.id.iVEvents_PictureP10);
        iVEvents_PictureP10.setOnClickListener(this);
        iVEvents_PictureP11 = (ImageView) findViewById(R.id.iVEvents_PictureP11);
        iVEvents_PictureP11.setOnClickListener(this);
        iVEvents_PictureP12 = (ImageView) findViewById(R.id.iVEvents_PictureP12);
        iVEvents_PictureP12.setOnClickListener(this);
        iVEvents_PictureP13 = (ImageView) findViewById(R.id.iVEvents_PictureP13);
        iVEvents_PictureP13.setOnClickListener(this);
        iVEvents_PictureP14 = (ImageView) findViewById(R.id.iVEvents_PictureP14);
        iVEvents_PictureP14.setOnClickListener(this);
        iVEvents_PictureP15 = (ImageView) findViewById(R.id.iVEvents_PictureP15);
        iVEvents_PictureP15.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
    System.out.println(view.getId());
        if (view.getId() == R.id.iVEvents_PictureMenue) {
            this.finish();
        }

    }
}
