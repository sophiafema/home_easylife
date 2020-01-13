package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

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
    DatabaseAdapter db;
    Room r;
    double  brightnessChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);

        Intent intent = getIntent();
        final double brightness = intent.getDoubleExtra(Fragment_Light.LIGHT_BRIGHTNESS, 0);
        brightnessChange = brightness;
        db = new DatabaseAdapter();

        picker = findViewById(R.id.brightness_picker);
        picker.setValueToPercent((float) brightness);
        picker.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = light

                //Übergabe Helligkeit in Raum und Datenbank
                //r.getLights().get(0).setBrightness(color);
                //db.setBrightness(r.getName(),r.getLights().get(0).getName(), color);
            }
        });

        findViewById(R.id.layout_light_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
