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
        if(currentRoom == null) {
            currentRoom = Util.LIVING;
        }

        //Log.e("room:",currentRoom);
        switch(currentRoom)
        {
            case Util.LIVING:
               //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
                break;
            case Util.BATH:
              //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
                break;
            case Util.HALLWAY:
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
                break;
            case Util.KITCHEN:
               //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
                break;
            case Util.SLEEPING:
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
                break;
             default:
        }

        //Room mit Wert aus Datenbank befüllen
        r = db.getRoom(currentRoom);


        iVPlainRoomMenue = (ImageView) findViewById(R.id.iVPlainRoomMenue);
        tVPlainRoomMenue = (TextView) findViewById(R.id.tVPlainRoomMenue);
        tVPlainRoomMenue.setOnClickListener(this);

        //TODO Onclicklistener hinzufügen: Raumvariable aktualisieren, Hintergrund aktualisieren

        iVPlainRoomHallway = (ImageView) findViewById(R.id.iVPlainRoomHallway);
        iVPlainRoomLiving = (ImageView) findViewById(R.id.iVPlainRoomLiving);
        iVPlainRoomKitchen = (ImageView) findViewById(R.id.iVPlainRoomKitchen);
        iVPlainRoomSleeping = (ImageView) findViewById(R.id.iVPlainRoomSleeping);
        iVPlainRoomBath = (ImageView) findViewById(R.id.iVPlainRoomBath);
        iVPlainRoomBackground = (ImageView) findViewById(R.id.iVPlainRoomBackground);

        //TODO verstehe nicht ganz was du mit r.setName machen möchtest
        //TODO onclicklistener vermutlich besser wenn wie in Home gelöst -> über ids, da duplizierter code vermieden werden kann
        iVPlainRoomHallway.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
             r.setName(Util.HALLWAY);
             //iVPlainRoomBackground.setImageResource(R.drawable.new_background);

             }
        });

        iVPlainRoomLiving.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                r.setName(Util.LIVING);
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
            }
        });

        iVPlainRoomKitchen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                r.setName(Util.KITCHEN);
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
            }
        });

        iVPlainRoomSleeping.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                r.setName(Util.SLEEPING);
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
            }
        });

        iVPlainRoomBath.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                r.setName(Util.BATH);
                //iVPlainRoomBackground.setImageResource(R.drawable.new_background);
            }
        });

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        indicator = (CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        indicator.createIndicators(5,0);
        //optional
       //new PagerAdapter(getSupportFragmentManager()).registerDataSetObserver(indicator.getDataSetObserver());

    }

    //TODO wofür braauchst du das? hab s nicht gefunden
    @Override
    public void onClick(View view) {
        this.finish();
    }

    private class PagerAdapter extends FragmentPagerAdapter
    {
        public PagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            //TODO in switch position abfrage in welchem raum man ist und je nachdem fragment starten --> in Fragment Klassen (Fragment_Light / Fragment_Jalousie)
            //TODO am sinnvollsten für licht und jalousien für jeden raum ein eigenes fragment
            //TODO an die fragments die Werte vom Room übergeben

            //TODO Activity zur Einstellung von Einzelnen Lampen und Jalousien

            switch(position)
            {
                //TODO auch hier nach Schema von case 0 abändern
                case 0: return Fragment_Light.newInstance(r);
                case 1: return new Fragment_Thermostat();
                case 2: return new Fragment_Jalousie();
                case 3: return new Fragment_Music();
                case 4: return new Fragment_Events();
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return 5;
        }
    }



}
