package com.sophiafema.home_easylife.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Light implements Serializable {

    public static String BRIGHTNESS = "brightness";
    public static String COLOR = "color";
    public static String POWER = "on";
    public static String NAME = "name";
    public static String ID = "id";

    int id;
    private String name;
    private double brightness;
    private int color;
    private boolean on;

    public Light() {
        //needed for firebase
    }

    public Light(int id, String name, int brightness, int color, boolean on) {
        this.id = id;
        this.name = name;
        this.brightness = brightness;
        this.color = color;
        this.on = on;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }


}
