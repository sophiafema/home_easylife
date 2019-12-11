package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class General extends AppCompatActivity implements View.OnClickListener{

    ImageView iVGeneralMenue;
    TextView tVGeneralMenue;

    TextView tVGeneralHeading;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        iVGeneralMenue = (ImageView) findViewById(R.id.iVGeneralMenue);
        tVGeneralMenue = (TextView) findViewById(R.id.tVGeneralMenue);
        tVGeneralMenue.setOnClickListener(this);

        tVGeneralHeading = (TextView) findViewById(R.id.tVGeneralHeading);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
