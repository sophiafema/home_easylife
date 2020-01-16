package com.sophiafema.home_easylife.models;

import java.io.Serializable;

public class Shutter implements Serializable {

    public static String NAME = "name";
    public static String ID = "id";
    public static String POSITION = "position";

    int id;
    String name;
    float position;

    public Shutter() {
    }

    public Shutter(int id, String name, float position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
