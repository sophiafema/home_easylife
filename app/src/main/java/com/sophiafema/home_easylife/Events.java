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

import java.util.ArrayList;

//import com.sophiafema.home_easylife.models.Event;

public class Events extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;

    ImageView iVEventsMenue;
    TextView tVEventsHeading;
    ImageView iVEventsAdd;

    ImageView iVEventsBackground;
    TextView tVEventsName;
    TextView tVEventsTime;
    TextView tVEventsRepetition;
    Switch swEvents;
    ImageView iVEventsPicture;


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



        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Initialize contacts
        //events = Contact.createContactsList(20);
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event(0, "hallo", 2, null));
        events.add(new Event(1, "hallo1", 2, null));
        events.add(new Event(4, "hallo2", 2, null));
        events.add(new Event(5, "hallo3", 2, null));
        // Create adapter passing in the sample user data
        EventsAdapter adapter = new EventsAdapter(events);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

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
