package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView tVHomeLiving;
    TextView tVHomeSleeping;
    TextView tVHomeHallway;
    TextView tVHomeKitchen;
    TextView tVHomeBath;


    ImageView iVHomeLiving;
    ImageView iVHomeSleeping;
    ImageView iVHomeHallway;
    ImageView iVHomeKitchen;
    ImageView iVHomeBath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tVHomeLiving = (TextView) findViewById(R.id.tVHomeLiving);
        tVHomeSleeping = (TextView) findViewById(R.id.tVHomeSleeping);
        tVHomeHallway = (TextView) findViewById(R.id.tVHomeHallway);
        tVHomeKitchen = (TextView) findViewById(R.id.tVHomeKitchen);
        tVHomeBath = (TextView) findViewById(R.id.tVHomeBath);

        iVHomeLiving = (ImageView) findViewById(R.id.iVHomeLiving);
        iVHomeSleeping = (ImageView) findViewById(R.id.iVHomeSleeping);
        iVHomeHallway = (ImageView) findViewById(R.id.iVHomeHallway);
        iVHomeKitchen = (ImageView) findViewById(R.id.iVHomeKitchen);
        iVHomeBath = (ImageView) findViewById(R.id.iVHomeBath);





    }


}
