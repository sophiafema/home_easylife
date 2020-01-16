package com.sophiafema.home_easylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.view.Picker;

import androidx.appcompat.app.AppCompatActivity;

public class Event_Fragment_Music extends AppCompatActivity {


    EventsRoom r;
    float volume;
    boolean play;
    boolean power;
    int musiccounter = 1;
    final String musiclist [] = {"Mamma Mia - ABBA", "Better - Nico Santos", "Let It Be - Beatles", "Sixteen - Ellie Goulding", "SOS - Avicii" };
    DatabaseAdapter db;

    com.sophiafema.home_easylife.view.VolumePicker pFMusik;
    ImageView iVFMusicFF;
    ImageView iVFMusicBack;
    ImageView iVFMusicPlayPause;
    TextView tVFMusicTitel;
    Switch sFMusic;

    TextView save;
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_music);


        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            r = (EventsRoom) bundle.getSerializable(Util.EVENTSROOM);
        }



        if(r == null) {
            r = new EventsRoom(Util.LIVING, 0);
        }

        System.out.println(r.getName());

        if(r.getMusic() == null) {
            db = new DatabaseAdapter();
            r.setMusic(db.getMusic(r.getName()));
        }

        setBackground();

        volume = r.getMusic().getVolume();
        play = r.getMusic().isPlay();
        power = r.getMusic().isPower();
        db = new DatabaseAdapter();

        tVFMusicTitel = (TextView) findViewById(R.id.tVFMusicTitel);
        iVFMusicPlayPause = (ImageView) findViewById(R.id.iVFMusicPlayPause);
        pFMusik = (com.sophiafema.home_easylife.view.VolumePicker) findViewById(R.id.pFMusic);
        sFMusic = (Switch) findViewById(R.id.sFMusic1);
        iVFMusicFF = (ImageView) findViewById(R.id.iVFMusicFF);
        iVFMusicBack = (ImageView) findViewById(R.id.iVFMusicBack);

        save = findViewById(R.id.tVEventsMusic_Save);
        cancel = findViewById(R.id.tVEventsMusic_Cancel);

        pFMusik.setValueToPercent(volume);
        sFMusic.setChecked(power);
        tVFMusicTitel.setText(musiclist[0]);

        if(power)
        {
            setPlayPauseButton();
        }

        pFMusik.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = music

                //Übergabe Lautstärke in Raum und Datenbank
                r.getMusic().setVolume(color);
            }
        });

        sFMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    power = true;
                    iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                }
                else
                {
                    power = false;
                    iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_play);
                }
                //Übergabe Power in Raum und Datenbank
                r.getMusic().setPower(power);
            }
        });

        iVFMusicFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musiccounter = musiccounter+1;
                if (musiccounter <= 4) {
                    switch (musiccounter) {
                        case 1: tVFMusicTitel.setText(musiclist[0]);break;
                        case 2: tVFMusicTitel.setText(musiclist[1]);break;
                        case 3: tVFMusicTitel.setText(musiclist[2]);break;
                        case 4: tVFMusicTitel.setText(musiclist[3]);break;
                    }
                }
                else if (musiccounter ==5)
                {
                    musiccounter = 0;
                    tVFMusicTitel.setText(musiclist[4]);
                }
            }
        });

        iVFMusicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musiccounter = musiccounter-1;
                if (musiccounter == 0 )
                {
                    tVFMusicTitel.setText(musiclist[4]);
                    musiccounter=5;
                }
                if (musiccounter <= 4) {
                    switch (musiccounter) {
                        case 1: tVFMusicTitel.setText(musiclist[0]);break;
                        case 2: tVFMusicTitel.setText(musiclist[1]);break;
                        case 3: tVFMusicTitel.setText(musiclist[2]);break;
                        case 4: tVFMusicTitel.setText(musiclist[3]);break;
                    }
                }
            }
        });

        iVFMusicPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPlayPauseButton();
                r.getMusic().setPlay(play);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Util.EVENTSROOM, r);
                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public void setPlayPauseButton ()
    {
        if (play)
        {
            iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            play = false;
            sFMusic.setChecked(true);
        }
        else
        {
            iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_play);
            play = true;
        }
    }

    public void setBackground()
    {
        ImageView iVLightSettingBackground = (ImageView) findViewById(R.id.iVMusicSettingBackground);

        if(r.getName().equals(Util.LIVING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);}
        else if(r.getName().equals(Util.BATH)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);}
        else if(r.getName().equals(Util.HALLWAY)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_flur_raum_v2);}
        else if(r.getName().equals(Util.KITCHEN)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_kueche_raum);}
        else if(r.getName().equals(Util.SLEEPING)) {
            iVLightSettingBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);}
    }
}
