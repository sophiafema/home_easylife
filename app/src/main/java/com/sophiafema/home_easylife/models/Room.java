package com.sophiafema.home_easylife.models;

import java.util.ArrayList;

public class Room extends SimpleRoom {
    private ArrayList<Light> lights;
    private Thermostat thermo;
    private ArrayList<Shutter> shutters;
    private Music music;


    public Room() {
        super();
    }

    public Room(String name, int id, ArrayList<Light> lights, Thermostat thermo, ArrayList<Shutter> shutters, Music music) {
        super(name, id);
        this.lights = lights;
        this.thermo = thermo;
        this.shutters = shutters;
        this.music = music;
    }

    public Room(String name, int id, Thermostat thermo, Music music) {
        super(name, id);
        this.lights = new ArrayList<>();
        this.thermo = thermo;
        this.shutters = new ArrayList<>();
        this.music = music;
    }

    public boolean hasLights() {
        boolean hasLights = false;
        if(lights != null) {
            if(lights.size() > 0)
                hasLights = true;
        }
        return hasLights;
    }
    public boolean hasLouvres() {
        boolean hasLouvres = false;
        if(shutters != null) {
            if(shutters.size() > 0)
                hasLouvres = true;
        }
        return hasLouvres;
    }
    public boolean hasThermostat() {
        return thermo != null;
    }
    public boolean hasMusic() {
        return music != null;
    }


    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    public Thermostat getThermo() {
        return thermo;
    }

    public void setThermo(Thermostat thermo) {
        this.thermo = thermo;
    }

    public ArrayList<Shutter> getShutters() {
        return shutters;
    }

    public void setShutters(ArrayList<Shutter> shutters) {
        this.shutters = shutters;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
