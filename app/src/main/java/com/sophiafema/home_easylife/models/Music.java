package com.sophiafema.home_easylife.models;

import java.io.Serializable;

public class Music implements Serializable {

    public static String NAME = "name";
    public static String ID = "id";
    public static String POWER = "power";
    public static String VOLUME = "volume";
    public static String PLAY = "play";

    int id;
    String name;
    private float volume;
    private boolean power;
    private boolean play;

    public Music() {
    }

    public Music(int id, String name, float volume, boolean power, boolean play) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.power = power;
        this.play = play;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
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

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}
