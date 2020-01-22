package com.sophiafema.home_easylife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.view.Picker;

public class Fragment_Music extends Fragment
{
    //Initialisierung aller benötigten Variablen

    Room r;
    float volume;
    boolean play;
    boolean power;
    DatabaseAdapter db;
    int musiccounter = 1;
    final String musiclist [] = {"Mamma Mia - ABBA", "Better - Nico Santos", "Let It Be - Beatles", "Sixteen - Ellie Goulding", "SOS - Avicii" };

    com.sophiafema.home_easylife.view.VolumePicker pFMusik;
    ImageView iVFMusicFF;
    ImageView iVFMusicBack;
    ImageView iVFMusicPlayPause;
    TextView tVFMusicTitel;
    Switch sFMusic;

    //Konstruktor
    public Fragment_Music(Room room) {
        // Required empty public constructor
        this.r = room;
    }
    public Fragment_Music() {
        // Required empty public constructor
    }

    public static Fragment_Music newInstance(Room room) {
        return new Fragment_Music(room);
    }

    /*
        - View fürs swipen
        - Holt sich Volume, Play, und Power aus der Datenbank
        - setzt Volume, den ToggleButton, PlayPause Button,
        - speichert neuen Volume-Wert
        - Kontrolliert ToggleButton
        - Steuert die Anzeige/den Durchlauf der Musiktitel
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View music = inflater.inflate(R.layout.fragment_music, container, false);
        volume = r.getMusic().getVolume();
        play = r.getMusic().isPlay();
        power = r.getMusic().isPower();
        db = new DatabaseAdapter();

        tVFMusicTitel = (TextView) music.findViewById(R.id.tVFMusicTitel);
        iVFMusicPlayPause = (ImageView) music.findViewById(R.id.iVFMusicPlayPause);
        pFMusik = (com.sophiafema.home_easylife.view.VolumePicker) music.findViewById(R.id.pFMusic);
        sFMusic = (Switch) music.findViewById(R.id.sFMusic1);
        iVFMusicFF = (ImageView) music.findViewById(R.id.iVFMusicFF);
        iVFMusicBack = (ImageView) music.findViewById(R.id.iVFMusicBack);

        pFMusik.setValueToPercent(volume);
        sFMusic.setChecked(power);
        tVFMusicTitel.setText(musiclist[0]);

        if(power)
        {
            setPlayPauseButton();
        }
        else{
            iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }

        pFMusik.setOnColorSelectedListener(new Picker.OnColorSelectedListener() {
            @Override
            public void onColorSelected(float color) {
                //color = music

                //Übergabe Lautstärke in Raum und Datenbank
                r.getMusic().setVolume(color);
                db.setMusicVolume(r.getName(),color);
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
                db.setMusicPower(r.getName(),power);
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
                db.setMusicPlay(r.getName(), play);
            }
        });

        return music;
    }

    //steuert die ImageRessource des PlayPause Buttons
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
}
