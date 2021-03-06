package com.sophiafema.home_easylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Room;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import me.relex.circleindicator.CircleIndicator;

public class PlainRoom extends AppCompatActivity implements View.OnClickListener {

    //Instanzvariablen
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
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain_room);
        r = null;
        db = new DatabaseAdapter();

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        //current Room wird aus Home über den Intent übergeben!
        //Raumabfrage für Hintergrund
        Intent intent = getIntent();
        currentRoom = intent.getStringExtra(Util.ROOM);
        if(currentRoom == null) {
            currentRoom = Util.LIVING;
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (this) {
                    r = db.getRoom(currentRoom);
                    notify();
                }

            }

        };
        t.start();

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

        iVPlainRoomBackground = (ImageView) findViewById(R.id.iVPlainRoomBackground);

        /*Setzt über die übergebene Room-ID den Hintergrund des aktuellen Raumes, färbt den Button des aktuellen Raumes in der Raumleiste grau
         ein und setzt den Text des Zurück-Buttons */
        switch(currentRoom)
        {
            case Util.LIVING:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_wohnzimmer_raum_v2);
                setButtonColor(currentRoom);
                tVPlainRoomMenue.setText(R.string.living);
                break;
            case Util.BATH:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_badezimmer_raum_v2);
                setButtonColor(currentRoom);
                tVPlainRoomMenue.setText(R.string.bath);
                break;
            case Util.HALLWAY:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_flur_raum_v2);
                setButtonColor(currentRoom);
                tVPlainRoomMenue.setText(R.string.hallway);
                break;
            case Util.KITCHEN:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_kueche_raum);
                setButtonColor(currentRoom);
                tVPlainRoomMenue.setText(R.string.kitchen);
                break;
            case Util.SLEEPING:
                iVPlainRoomBackground.setImageResource(R.drawable.ic_schlafzimmer_raum_v2);
                setButtonColor(currentRoom);
                tVPlainRoomMenue.setText(R.string.sleeping);
                break;
        }

        synchronized (t) {
            try {
                t.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // View pager zum swipen
        //ruft Klasse ViwePagerAdapter auf
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        //Einbindung der ViewIndicatorDots/CircleIndicator
        indicator = (CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        //optional
        //new PagerAdapter(getSupportFragmentManager()).registerDataSetObserver(indicator.getDataSetObserver());

    }

    @Override
    public void onStart() {
        super.onStart();
        spinner.setVisibility(View.GONE);
    }

    //Startet entweder das Menü oder einen neuen Raum, der in Plain Room eingebunden ist
    @Override
    public void onClick(View view) {

        if((view.getId() == R.id.iVPlainRoomMenue) || (view.getId() == R.id.tVPlainRoomMenue))
        {
            Intent intent1 = new Intent(this, Home.class);
            startActivity(intent1);
        }

        else
        {
            spinner.setVisibility(View.VISIBLE);
             Intent intent2 = new Intent(this, PlainRoom.class);

            //Raum übergeben --> mit statischen variablen in Util
            if(view.getId() == R.id.iVPlainRoomHallway)
            {
                intent2.putExtra(Util.ROOM, Util.HALLWAY);
            }

            else if (view.getId() == R.id.iVPlainRoomBath)
            {
                intent2.putExtra(Util.ROOM, Util.BATH);
            }

            else if (view.getId() == R.id.iVPlainRoomKitchen)
            {
                intent2.putExtra(Util.ROOM, Util.KITCHEN);
            }

            else if (view.getId() == R.id.iVPlainRoomLiving)
            {
                intent2.putExtra(Util.ROOM, Util.LIVING);
            }
            else if (view.getId() == R.id.iVPlainRoomSleeping)
            {
                intent2.putExtra(Util.ROOM, Util.SLEEPING);
            }

            startActivity(intent2);
        }
    }

    //setzt die Farbe der Raumleiste abhängig vom aktuellen Raum
    public void setButtonColor (String currentRoom) {
        switch (currentRoom) {
            case Util.LIVING:
                iVPlainRoomLiving.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVPlainRoomHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.BATH:
                iVPlainRoomLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomBath.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                break;
            case Util.HALLWAY:
                iVPlainRoomLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomHallway.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVPlainRoomSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.KITCHEN:
                iVPlainRoomLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomSleeping.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomKitchen.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVPlainRoomBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case Util.SLEEPING:
                iVPlainRoomLiving.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomHallway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomSleeping.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                iVPlainRoomKitchen.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                iVPlainRoomBath.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

    }

    //PagerAdapter fürs swipen
    //weist zu, an welcher Stelle welches Fragment aufgerufen wird
    private class PagerAdapter extends FragmentPagerAdapter
    {
        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override @NonNull
        public Fragment getItem(int position)
        {
            if (r.getName().equals(Util.HALLWAY))
            {
                switch (position)
                {
                    case 0: return Fragment_Light.newInstance(r);
                    case 1: return Fragment_Thermostat.newInstance(r);
                }
                indicator.createIndicators(2,0);
                return Fragment_Light.newInstance(r);
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
                return Fragment_Light.newInstance(r);
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
