package com.sophiafema.home_easylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sophiafema.home_easylife.database.DatabaseAdapter;
import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.EventsRoom;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.models.Thermostat;

import java.util.ArrayList;

//import com.sophiafema.home_easylife.models.Event;

public class Events extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_CODE_EVENT = 7;

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

    EventsAdapter adapter;
    EventsAdapter adapterLiving;
    EventsAdapter adapterBath;
    EventsAdapter adapterKitchen;
    EventsAdapter adapterSleeping;
    EventsAdapter adapterHallway;

    DatabaseAdapter dba;


    int images [] = {R.drawable.common_google_signin_btn_icon_light, R.drawable.common_full_open_on_phone,
            R.drawable.common_google_signin_btn_text_dark_focused, R.drawable.common_google_signin_btn_icon_light_normal_background,
            R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_text_disabled};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        dba = new DatabaseAdapter();
        events = dba.getEvents();

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


        eventsAll = new ArrayList<Event>();
        eventsLiving = new ArrayList<Event>();
        eventsBath = new ArrayList<Event>();
        eventsKitchen = new ArrayList<Event>();
        eventsHallway = new ArrayList<Event>();
        eventsSleeping = new ArrayList<Event>();





        // Lookup the recyclerview in activity layout
        recyclerViewAll = (RecyclerView) findViewById(R.id.recyclerViewAll);
        recyclerViewLiving = (RecyclerView) findViewById(R.id.recyclerViewLiving);
        recyclerViewBath = (RecyclerView) findViewById(R.id.recyclerViewBath);
        recyclerViewSleeping = (RecyclerView) findViewById(R.id.recyclerViewSleeping);
        recyclerViewKitchen = (RecyclerView) findViewById(R.id.recyclerViewKitchen);
        recyclerViewHallway = (RecyclerView) findViewById(R.id.recyclerViewHallway);

        setRoomLists(events);

        adapter = new EventsAdapter(eventsAll);
        recyclerViewAll.setAdapter(adapter);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(this));

        adapterLiving = new EventsAdapter(eventsLiving);
        recyclerViewLiving.setAdapter(adapterLiving);
        recyclerViewLiving.setLayoutManager(new LinearLayoutManager(this));

        adapterBath = new EventsAdapter(eventsBath);
        recyclerViewBath.setAdapter(adapterBath);
        recyclerViewBath.setLayoutManager(new LinearLayoutManager(this));

        adapterSleeping = new EventsAdapter(eventsSleeping);
        recyclerViewSleeping.setAdapter(adapterSleeping);
        recyclerViewSleeping.setLayoutManager(new LinearLayoutManager(this));

        adapterKitchen = new EventsAdapter(eventsKitchen);
        recyclerViewKitchen.setAdapter(adapterKitchen);
        recyclerViewKitchen.setLayoutManager(new LinearLayoutManager(this));

        adapterHallway = new EventsAdapter(eventsHallway);
        recyclerViewHallway.setAdapter(adapterHallway);
        recyclerViewHallway.setLayoutManager(new LinearLayoutManager(this));

    }

    private void updateRecyclerViews(String room) {
        if(room.equals("all")) {
            //to all
            adapter.notifyDataSetChanged();
        }
        else if(room.equals(Util.LIVING)) {
            //living room
            adapterLiving.notifyDataSetChanged();
        }
        else if(room.equals(Util.BATH)) {
            //bath room
            adapterBath.notifyDataSetChanged();
        }
        else if(room.equals(Util.KITCHEN)) {
            //kitchen room
            adapterKitchen.notifyDataSetChanged();
        }
        else if(room.equals(Util.HALLWAY)) {
            //hallway room
            adapterHallway.notifyDataSetChanged();
        }
        else if(room.equals(Util.SLEEPING)) {
            //sleeping room
            adapterSleeping.notifyDataSetChanged();
        }
    }

    public void setRoomLists(ArrayList<Event> events) {
        for(Event e : events) {
            addRoom(e);
        }
    }
    public String addRoom(Event e) {
        String room = "";

        if(e.numberOfRoomsWithFunction() > 1) {
            //to all
            eventsAll.add(e);
            room = "all";
        }
        else if(e.getRoomByName(Util.LIVING).hasFunctions()) {
            //living room
            eventsLiving.add(e);
            room = Util.LIVING;
        }
        else if(e.getRoomByName(Util.BATH).hasFunctions()) {
            //bath room
            eventsBath.add(e);
            room = Util.BATH;
        }
        else if(e.getRoomByName(Util.KITCHEN).hasFunctions()) {
            //kitchen room
            eventsKitchen.add(e);
            room = Util.KITCHEN;
        }
        else if(e.getRoomByName(Util.HALLWAY).hasFunctions()) {
            //hallway room
            eventsHallway.add(e);
            room = Util.HALLWAY;
        }
        else if(e.getRoomByName(Util.SLEEPING).hasFunctions()) {
            //sleeping room
            eventsSleeping.add(e);
            room = Util.SLEEPING;
        }
        return room;
    }

    public void removeRoom(String room, int index) {
        if(room.equals("all")) {
            //to all
            eventsAll.remove(index);
        }
        else if(room.equals(Util.LIVING)) {
            //living room
            eventsLiving.remove(index);
        }
        else if(room.equals(Util.BATH)) {
            //bath room
            eventsBath.remove(index);
        }
        else if(room.equals(Util.KITCHEN)) {
            //kitchen room
            eventsKitchen.remove(index);
        }
        else if(room.equals(Util.HALLWAY)) {
            //hallway room
            eventsHallway.remove(index);
        }
        else if(room.equals(Util.SLEEPING)) {
            //sleeping room
            eventsSleeping.remove(index);
        }
    }

    public String getRoom(Event e) {
        String room = "";

        if(e.numberOfRoomsWithFunction() > 1) {
            //to all
            room = "all";
        }
        else if(e.getRoomByName(Util.LIVING).hasFunctions()) {
            //living room
            room = Util.LIVING;
        }
        else if(e.getRoomByName(Util.BATH).hasFunctions()) {
            //bath room
            room = Util.BATH;
        }
        else if(e.getRoomByName(Util.KITCHEN).hasFunctions()) {
            //kitchen room
            room = Util.KITCHEN;
        }
        else if(e.getRoomByName(Util.HALLWAY).hasFunctions()) {
            //hallway room
            room = Util.HALLWAY;
        }
        else if(e.getRoomByName(Util.SLEEPING).hasFunctions()) {
            //sleeping room
            room = Util.SLEEPING;
        }
        return room;
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
                 startActivityForResult(intent1, REQUEST_CODE_EVENT);
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
            final Event contact = mEvents.get(position);
            final int pos = position;
            final String arrayname = getRoom(contact);


            // Set item views based on your views and data model
            TextView textView = holder.nameTextView;
            textView.setText(contact.getName());
            ImageView image = holder.img;
            image.setImageResource(contact.getPictureID());
            ImageView background = holder.iVEvents;
            Switch sw = holder.swEvents;
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        activateEvent(contact);
                    }
                }
            });

            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getApplicationContext(), Events_Add.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Util.EVENT, contact);
                    intent1.putExtras(bundle);
                    intent1.putExtra(Util.EVENT_INDEX, pos);
                    intent1.putExtra(Util.EVENT_ARRAY_ROOM, arrayname);
                    startActivityForResult(intent1, REQUEST_CODE_EVENT);
                }
            });
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
            public ImageView iVEvents;
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
                iVEvents = (ImageView) itemView.findViewById(R.id.iVEventsBackground);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case(REQUEST_CODE_EVENT):
                    Bundle bundle = data.getExtras();
                    if(data.hasExtra(Util.EVENT_ARRAY_ROOM) && data.hasExtra(Util.EVENT_INDEX)) {
                        removeRoom(data.getStringExtra(Util.EVENT_ARRAY_ROOM), data.getIntExtra(Util.EVENT_INDEX, 0));
                    }
                    if (bundle != null) {
                        Event event = (Event) bundle.getSerializable(Util.EVENT);
                        events.add(event);
                        dba.setEvent(event);
                        String room = addRoom(event);
                        updateRecyclerViews(room);
                    }
                    break;
            }
        }
    }

    public void activateEvent(Event event) {
        System.out.println(event);
        for(EventsRoom room : event.getRooms()) {
            if(room.hasLights()) {
                for(Light l : room.getLights()) {
                    dba.setLight(room.getName(), l);
                }
            }
            if(room.hasShutters()) {
                for(Shutter s : room.getShutters()) {
                    dba.setShutter(room.getName(), s);
                }
            }
            if(room.hasThermostat()) {
                dba.setThermostat(room.getName(), room.getThermo());
            }
            if(room.hasMusic()) {
                dba.setMusic(room.getName(), room.getMusic());
            }
        }
    }
}
