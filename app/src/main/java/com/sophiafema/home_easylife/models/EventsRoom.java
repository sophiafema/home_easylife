package com.sophiafema.home_easylife.models;

import java.util.ArrayList;

public class EventsRoom extends Room {

    public EventsRoom(String name, int id) {
       super(name, id, new ArrayList<Light>(), null, new ArrayList<Shutter>(), null);
    }

    public EventsRoom(String name, int id, ArrayList<Light> lights, Thermostat thermo, ArrayList<Shutter> shutters, Music music) {
        super(name, id, lights, thermo, shutters, music);
    }

    public EventsRoom(String name, int id, Thermostat thermo, Music music) {
        super(name, id, new ArrayList<Light>(), thermo, new ArrayList<Shutter>(), music);
    }


    public boolean hasFunctions() {
        return hasLights() || hasThermostat() || hasMusic() || hasShutters();

    }
    public boolean hasLights() {
        if(getLights() != null) {
            return getLights().size() > 0;
        }
        return false;
    }

    public boolean hasThermostat() {
        return getThermo() != null;
    }
    public boolean hasMusic() {
        return getMusic() != null;
    }
    public boolean hasShutters() {
        if(getShutters() != null) {
            return getShutters().size() > 0;
        }
        return false;
    }

}

