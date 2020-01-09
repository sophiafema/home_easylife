package com.sophiafema.home_easylife.models;

import com.sophiafema.home_easylife.Util;
import com.sophiafema.home_easylife.database.DatabaseAdapter;

import java.util.ArrayList;

public class House {
    private Room bath;
    private Room living;
    private Room kitch;
    private Room hallway;
    private Room sleeping;

    public House() {
        setLivingDefault();
        setKitchenDefault();
        setBathDefault();
        setHallwayDefault();
        setSleepingDefault();
    }

    public void setHouseInDatebase() {
        DatabaseAdapter db = new DatabaseAdapter();
        db.setRoom(living.getName(), living);
        db.setRoom(kitch.getName(), kitch);
        db.setRoom(bath.getName(), bath);
        db.setRoom(hallway.getName(), hallway);
        db.setRoom(sleeping.getName(), sleeping);
    }

    public void setLivingDefault() {
        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "esstisch", 3, 4, false));
        l.add(new Light(1, "sofa", 3, 4, false));
        l.add(new Light(2, "general", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo = new ArrayList<>();
        lo.add(new Shutter(0, "east", 40));
        Music m = new Music(0, "music", 4, true, false);
        living = new Room(Util.LIVING, 0, l, t, lo,m);
    }

    public void setBathDefault() {
        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "general", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo = new ArrayList<>();
        lo.add(new Shutter(0, "east", 0));
        Music m = new Music(0, "music", 4, true, false);
        bath = new Room(Util.BATH, 0, l, t, lo,m);
    }
    public void setHallwayDefault() {
        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(1, "general", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        hallway = new Room(Util.HALLWAY, 0, l, t, null, null);
    }
    public void setSleepingDefault() {
        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "esstisch", 3, 4, true));
        l.add(new Light(1, "sofa", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo = new ArrayList<>();
        lo.add(new Shutter(0, "east", 40));
        Music m = new Music(0, "music", 4, true, false);
        sleeping = new Room(Util.SLEEPING, 0, l, t, lo,m);
    }
    public void setKitchenDefault() {
        ArrayList<Light> l = new ArrayList<>();
        l.add(new Light(0, "esstisch", 3, 4, true));
        l.add(new Light(1, "sofa", 3, 4, false));
        l.add(new Light(2, "general", 3, 4, false));
        Thermostat t = new Thermostat(0, "thermo", 30, true);
        ArrayList<Shutter> lo = new ArrayList<>();
        lo.add(new Shutter(0, "east", 40));
        lo.add(new Shutter(0, "west", 50));
        Music m = new Music(0, "music", 4, true, false);
        kitch = new Room(Util.KITCHEN, 0, l, t, lo,m);
    }
}
