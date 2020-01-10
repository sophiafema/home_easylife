package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sophiafema.home_easylife.view.BrightnessPicker;

public class LightSettingsActivity extends AppCompatActivity {

    BrightnessPicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);

        Intent intent = getIntent();
        double brightness = intent.getDoubleExtra(Fragment_Light.LIGHT_BRIGHTNESS, 0);

        picker = findViewById(R.id.brightness_picker);
        picker.setValueToPercent((float) brightness);


        findViewById(R.id.layout_light_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
