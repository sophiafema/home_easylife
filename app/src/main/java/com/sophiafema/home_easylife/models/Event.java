package com.sophiafema.home_easylife.models;

import java.util.ArrayList;

public class Event {
    private String name;
    private int id;
    private ArrayList<Room> rooms;
    private String day;
    private int time;

    public Event() {

    }

    public Event(String name, int id, ArrayList<Room> rooms, String day, int time) {
        this.name = name;
        this.id = id;
        this.rooms = rooms;
        this.day = day;
        this.time = time;
    }

    public boolean inMultipleRooms() {
        return rooms.size()>1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
