package com.sophiafema.home_easylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.models.Thermostat;

import java.util.ArrayList;

//import com.sophiafema.home_easylife.models.Event;

public class Events extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewAll, recyclerViewLiving, recyclerViewBath, recyclerViewKitchen, recyclerViewSleeping, recyclerViewHallway;

    ImageView iVEventsMenue;
    TextView tVEventsHeading;
    ImageView iVEventsAdd;

    ImageView iVEventsBackground;
    TextView tVEventsName;
    TextView tVEventsTime;
    TextView tVEventsRepetition;
    Switch swEvents;
    ImageView iVEventsPicture;

    ArrayList<Event> events;
    ArrayList<Event> eventsAll;
    ArrayList<Event> eventsLiving;
    ArrayList<Event> eventsBath;
    ArrayList<Event> eventsKitchen;
    ArrayList<Event> eventsHallway;
    ArrayList<Event> eventsSleeping;


    int images [] = {R.drawable.common_google_signin_btn_icon_light, R.drawable.common_full_open_on_phone,
            R.drawable.common_google_signin_btn_text_dark_focused, R.drawable.common_google_signin_btn_icon_light_normal_background,
            R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_text_disabled};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        iVEventsMenue = (ImageView) findViewById(R.id.iVEventsMenue);
        tVEventsHeading = (TextView) findViewById(R.id.tVEventsHeading);
        tVEventsHeading.setOnClickListener(this);
        iVEventsAdd = (ImageView) findViewById(R.id.iVEventsAdd);
        iVEventsAdd.setOnClickListener(this);

        iVEventsBackground = (ImageView) findViewById(R.id.iVEventsBackground);
        tVEventsName = (TextView) findViewById(R.id.tVEventsName);
        tVEventsTime = (TextView) findViewById(R.id.tVEventsTime);
        tVEventsRepetition = (TextView) findViewById(R.id.tVEventsRepetition);
        swEvents = (Switch) findViewById(R.id.swEvents);
        iVEventsPicture = (ImageView) findViewById(R.id.iVEventsPicture);


        events = new ArrayList<Event>();
        eventsAll = new ArrayList<Event>();
        eventsLiving = new ArrayList<Event>();
        eventsBath = new ArrayList<Event>();
        eventsKitchen = new ArrayList<Event>();
        eventsHallway = new ArrayList<Event>();
        eventsSleeping = new ArrayList<Event>();


        ArrayList<Room> roomAll = new ArrayList<>();

        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "esstisch", 3, 4, false));
        l.add(new Light(1, "sofa", 3, 4, false));
        l.add(new Light(2, "general", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo = new ArrayList<>();
        lo.add(new Shutter(0, "east", 40));
        Music m = new Music(0, "music", 4, true, false);
        Room living = new Room(Util.LIVING, 0, l, t, lo,m);

        ArrayList<Light> l1 = new ArrayList<>();
        l1.add(new Light(0, "general", 3, 4, false));
        Thermostat t1 = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo1 = new ArrayList<>();
        lo1.add(new Shutter(0, "east", 0));
        Music m1 = new Music(0, "music", 4, true, false);
        Room bath = new Room(Util.BATH, 0, l1, t1, lo1,m1);

        roomAll.add(living);
        roomAll.add(bath);

        ArrayList<Room> room= new ArrayList<>();
        room.add(living);
        ArrayList<Room> room1= new ArrayList<>();
        room1.add(bath);


        events.add(new Event(0, "hallo", 2, roomAll));
        events.add(new Event(0, "hallok", 2, roomAll));
        events.add(new Event(0, "hallob", 2, roomAll));
        events.add(new Event(0, "hallol", 2, roomAll));
        events.add(new Event(0, "hallo1", 2, room));
        events.add(new Event(0, "hallo2", 2, room1));

        setRoomLists(events);


        // Lookup the recyclerview in activity layout
        recyclerViewAll = (RecyclerView) findViewById(R.id.recyclerViewAll);
        recyclerViewLiving = (RecyclerView) findViewById(R.id.recyclerViewLiving);
        recyclerViewBath = (RecyclerView) findViewById(R.id.recyclerViewBath);
        recyclerViewSleeping = (RecyclerView) findViewById(R.id.recyclerViewSleeping);
        recyclerViewKitchen = (RecyclerView) findViewById(R.id.recyclerViewKitchen);
        recyclerViewHallway = (RecyclerView) findViewById(R.id.recyclerViewHallway);



        EventsAdapter adapter = new EventsAdapter(eventsAll);
        recyclerViewAll.setAdapter(adapter);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(this));

        EventsAdapter adapterLiving = new EventsAdapter(eventsLiving);
        recyclerViewLiving.setAdapter(adapterLiving);
        recyclerViewLiving.setLayoutManager(new LinearLayoutManager(this));

        EventsAdapter adapterBath = new EventsAdapter(eventsBath);
        recyclerViewBath.setAdapter(adapterBath);
        recyclerViewBath.setLayoutManager(new LinearLayoutManager(this));

        EventsAdapter adapterSleeping = new EventsAdapter(eventsSleeping);
        recyclerViewSleeping.setAdapter(adapterSleeping);
        recyclerViewSleeping.setLayoutManager(new LinearLayoutManager(this));

        EventsAdapter adapterKitchen = new EventsAdapter(eventsKitchen);
        recyclerViewKitchen.setAdapter(adapterKitchen);
        recyclerViewKitchen.setLayoutManager(new LinearLayoutManager(this));

        EventsAdapter adapterHallway = new EventsAdapter(eventsHallway);
        recyclerViewHallway.setAdapter(adapterHallway);
        recyclerViewHallway.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setRoomLists(ArrayList<Event> events) {
        for(Event e : events) {
            if(e.getRooms().size()>1) {
                //to all
                eventsAll.add(e);
            }
            else if(e.getRooms().get(0).getName().equals(Util.LIVING)) {
                //living room
                eventsLiving.add(e);
            }
            else if(e.getRooms().get(0).getName().equals(Util.BATH)) {
                //bath room
                eventsBath.add(e);
            }
            else if(e.getRooms().get(0).getName().equals(Util.KITCHEN)) {
                //kitchen room
                eventsKitchen.add(e);
            }
            else if(e.getRooms().get(0).getName().equals(Util.HALLWAY)) {
                //hallway room
                eventsHallway.add(e);
            }
            else if(e.getRooms().get(0).getName().equals(Util.SLEEPING)) {
                //sleeping room
                eventsSleeping.add(e);
            }
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
             if(view.getId() == R.id.tVEventsHeading)
             {
                 this.finish();
             }
             else if(view.getId() == R.id.iVEventsAdd)
             {
                 Intent intent1 = new Intent(this, Events_Add.class);
                 startActivity(intent1);
             }
    }

    public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

        private ArrayList<Event> mEvents;

        public EventsAdapter(ArrayList<Event> events) {
            mEvents = events;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item_event, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // Get the data model based on position
            Event contact = mEvents.get(position);

            // Set item views based on your views and data model
            TextView textView = holder.nameTextView;
            textView.setText(contact.getName());
            ImageView image = holder.img;
            image.setImageResource(images[contact.getPictureID()]);

        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }


        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView nameTextView;
            public Switch swEvents;
            public ImageView img;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                nameTextView = (TextView) itemView.findViewById(R.id.tVEventsName);
                swEvents = (Switch) itemView.findViewById(R.id.swEvents);
                img = (ImageView) itemView.findViewById(R.id.iVEventsPicture);
            }
        }
    }
}
