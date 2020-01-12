package com.sophiafema.home_easylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;

import me.relex.circleindicator.CircleIndicator;

public class PlainRoom extends AppCompatActivity implements View.OnClickListener {

    ImageView iVPlainRoomMenue;
    TextView tVPlainRoomMenue;

    ImageView iVPlainRoomHallway;
    ImageView iVPlainRoomLiving;
    ImageView iVPlainRoomKitchen;
    ImageView iVPlainRoomSleeping;
    ImageView iVPlainRoomBath;
    ImageView iVPlainRoomBackground;

    ViewPager viewPager;
    CircleIndicator indicator;

    Room r;
    DatabaseAdapter db;
    String currentRoom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain_room);
        db = new DatabaseAdapter();

        //current Room wird aus Home über den Intent übergeben!
        //Raumabfrage für Hintergrund
        Intent intent = getIntent();
        currentRoom = intent.getStringExtra(Util.ROOM);
        Log.e("Utli.room", Util.ROOM);
        Log.e("currentroom", currentRoom);
        if(currentRoom == null) {
            currentRoom = Util.LIVING;
        }

        iVPlainRoomBackground = (ImageView) findViewById(R.id.iVPlainRoomBackground);
        switch(currentRoom)
        {
            case Util.LIVING:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);
                break;
            case Util.BATH:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);
                break;
            case Util.HALLWAY:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_flur_raum_v2);
                break;
            case Util.KITCHEN:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_kueche_raum);
                break;
            case Util.SLEEPING:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);
                break;
        }

        //Room mit Wert aus Datenbank befüllen
        r = db.getRoom(currentRoom);
        //Log.e("Lights", r.getLights().get(0).getName());


        iVPlainRoomMenue = (ImageView) findViewById(R.id.iVPlainRoomMenue);
        tVPlainRoomMenue = (TextView) findViewById(R.id.tVPlainRoomMenue);
        iVPlainRoomMenue.setOnClickListener(this);
        tVPlainRoomMenue.setOnClickListener(this);

        iVPlainRoomHallway = (ImageView) findViewById(R.id.iVPlainRoomHallway);
        iVPlainRoomHallway.setOnClickListener(this);
        iVPlainRoomLiving = (ImageView) findViewById(R.id.iVPlainRoomLiving);
        iVPlainRoomLiving.setOnClickListener(this);
        iVPlainRoomKitchen = (ImageView) findViewById(R.id.iVPlainRoomKitchen);
        iVPlainRoomKitchen.setOnClickListener(this);
        iVPlainRoomSleeping = (ImageView) findViewById(R.id.iVPlainRoomSleeping);
        iVPlainRoomSleeping.setOnClickListener(this);
        iVPlainRoomBath = (ImageView) findViewById(R.id.iVPlainRoomBath);
        iVPlainRoomBath.setOnClickListener(this);

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        indicator = (CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        //optional
        //new PagerAdapter(getSupportFragmentManager()).registerDataSetObserver(indicator.getDataSetObserver());

    }

    //TODO Hintergrünbilder einfügen
    @Override
    public void onClick(View view) {

        if((view.getId() == R.id.iVPlainRoomMenue) || (view.getId() == R.id.tVPlainRoomMenue))
        {
            Intent intent1 = new Intent(this, Home.class);
            startActivity(intent1);
        }

        else
        {
//            Intent intent2 = new Intent(this, PlainRoom.class);

            //Raum übergeben --> mit statischen variablen in Util


            if(view.getId() == R.id.iVPlainRoomHallway)
            {
                //intent2.putExtra(Util.ROOM, Util.HALLWAY);
                iVPlainRoomBackground.setImageResource(R.drawable.ic_flur_raum_v2);
            }

            else if (view.getId() == R.id.iVPlainRoomBath)
            {
                //intent2.putExtra(Util.ROOM, Util.BATH);
                iVPlainRoomBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);
            }

            else if (view.getId() == R.id.iVPlainRoomKitchen)
            {
                //intent2.putExtra(Util.ROOM, Util.KITCHEN);
                iVPlainRoomBackground.setImageResource(R.drawable.ic_kueche_raum);
            }

            else if (view.getId() == R.id.iVPlainRoomLiving)
            {
                //intent2.putExtra(Util.ROOM, Util.LIVING);
                iVPlainRoomBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);
            }
            else if (view.getId() == R.id.iVPlainRoomSleeping)
            {
                //intent2.putExtra(Util.ROOM, Util.SLEEPING);
                iVPlainRoomBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);
            }

//            startActivity(intent2);
        }
    }

    private class PagerAdapter extends FragmentPagerAdapter
    {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            //TODO an die fragments die Werte vom Room übergeben


            if (r.getName().equals(Util.HALLWAY))
            {
                switch (position)
                {
                    case 0: return Fragment_Light.newInstance(r);
                    case 1: return Fragment_Thermostat.newInstance(r);
                }
                indicator.createIndicators(2,0);
                return null;
            }
            else
            {
                switch(position)
                {
                    case 0: return Fragment_Light.newInstance(r);
                    case 1: return Fragment_Thermostat.newInstance(r);
                    case 2: return Fragment_Shutter.newInstance(r);
                    case 3: return Fragment_Music.newInstance(r);

                }
                indicator.createIndicators(4,0);
                return null;
            }
        }

        @Override
        public int getCount() {
            int count;
            if (r.getName().equals(Util.HALLWAY)) {count = 2;}
            else {count = 4;}
            return count;}

    }
}
