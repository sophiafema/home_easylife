package com.sophiafema.home_easylife.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Thermostat {

    public static String NAME = "name";
    public static String ID = "id";
    public static String POWER = "power";
    public static String TEMPERATURE = "temperature";

    int id;
    String name;
    double temperature;
    boolean power;

    public Thermostat(int id, String name, double temperature, boolean power) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.power = power;
    }

    public Thermostat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

}
