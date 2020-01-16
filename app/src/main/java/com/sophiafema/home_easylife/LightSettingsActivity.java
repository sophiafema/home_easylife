package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.view.BrightnessPicker;
import com.sophiafema.home_easylife.view.Picker;

public class LightSettingsActivity extends AppCompatActivity {

    ImageView iVLightSettingBackground;

    BrightnessPicker picker;
    Room r;
    DatabaseAdapter db;
    double brightness;
    String room;
    boolean power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);


        Intent intent = getIntent();
        brightness = intent.getDoubleExtra(Fragment_Light.LIGHT_BRIGHTNESS, 0);
        power = intent.getBooleanExtra(Fragment_Light.LIGHT_POWER, false);
        room = intent.getStringExtra(Fragment_Light.LIGHT_ROOM);

        setBackground();


        picker = findViewById(R.id.brightness_picker);
        picker.setValueToPercent((float) brightness);
        picker.setWheelIsEnabled(power);
        picker.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = light
                brightness = color;
                System.out.println("selected" + brightness);
                Log.e("Color", ""+color);
            }
        });
        picker.setOnClickIntoCenter(new BrightnessPicker.OnClickInCenter() {
            @Override
            public void onClick(boolean isEnabled) {
                power = isEnabled;
            }
        });

        picker.getValuePercent();

        findViewById(R.id.layout_light_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Fragment_Light.LIGHT_BRIGHTNESS, brightness);
                System.out.println("in intent" + brightness);

                resultIntent.putExtra(Fragment_Light.LIGHT_POWER, power);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    public void setBackground()
    {
        iVLightSettingBackground = (ImageView) findViewById(R.id.iVLightSettingBackground);

           if(room.equals(Util.LIVING)) {
                iVLightSettingBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);}
           else if(room.equals(Util.BATH)) {
               iVLightSettingBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);}
           else if(room.equals(Util.HALLWAY)) {
               iVLightSettingBackground.setImageResource(R.drawable.ic_flur_raum_v2);}
           else if(room.equals(Util.KITCHEN)) {
                iVLightSettingBackground.setImageResource(R.drawable.ic_kueche_raum);}
           else if(room.equals(Util.SLEEPING)) {
                iVLightSettingBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);}

    }


}
