package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.view.BrightnessPicker;
import com.sophiafema.home_easylife.view.Picker;

public class LightSettingsActivity extends AppCompatActivity {

    BrightnessPicker picker;
    Room r;
    double brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);

        Intent intent = getIntent();
        brightness = intent.getDoubleExtra(Fragment_Light.LIGHT_BRIGHTNESS, 0);

        picker = findViewById(R.id.brightness_picker);
        Log.e("pBright", " "+brightness);
        picker.setValueToPercent((float) brightness);
        picker.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = light
                brightness = color;
            }
        });

        findViewById(R.id.layout_light_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Fragment_Light.LIGHT_BRIGHTNESS, brightness);
                //resultIntent.putExtra(Fragment_Light., nnnn);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}
