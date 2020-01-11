package com.sophiafema.home_easylife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;


public class Fragment_Music extends Fragment implements View.OnClickListener
{
    Room r;
    float volume;
    boolean play;
    boolean power;
    DatabaseAdapter db;
    int musiccounter = 0;

    com.sophiafema.home_easylife.view.VolumePicker pFMusik;
    ImageView iVFMusicFF;
    ImageView iVFMusicBack;
    ImageView iVFMusicPlayPause;
    TextView tVFMusicTitel;

    public Fragment_Music(Room room) {
        // Required empty public constructor
        this.r = room;
    }

    public static Fragment_Music newInstance(Room room) {
        return new Fragment_Music(room);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstantState)
    {
        View music = inflater.inflate(R.layout.fragment_music, container, false);
        volume = r.getMusic().getVolume();
        play = r.getMusic().isPlay();
        power = r.getMusic().isPower();

        final String musiclist [] = {"Mamma Mia - ABBA", "Better - Nico Santos", "Let It Be - Beatles", "Sixteen - Ellie Goulding", "SOS - Avicii" };

        tVFMusicTitel = (TextView) music.findViewById(R.id.tVFMusicTitel);
        iVFMusicPlayPause = (ImageView) music.findViewById(R.id.iVFMusicPlayPause);
        if (play)
        {
            iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            play = false;
        }
        else
        {
            iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_play);
            play = true;
        }

        //TODO WIe sonst?
        pFMusik = (com.sophiafema.home_easylife.view.VolumePicker) music.findViewById(R.id.pFMusic);
        pFMusik.setOnClickListener(this);

        iVFMusicFF = (ImageView) music.findViewById(R.id.iVFMusicFF);
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

        iVFMusicBack = (ImageView) music.findViewById(R.id.iVFMusicBack);
        iVFMusicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iVFMusicPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play == true)
                {
                    iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                    play = false;
                }
                else if (play == false)
                {
                    iVFMusicPlayPause.setImageResource(android.R.drawable.ic_media_play);
                    play = true;
                }
            }
        });


        return music;
    }

    //TODO Wo bekomme ich die Rückgabewerte vom Picker her?
    @Override
    public void onClick(View v) {

    }
}
