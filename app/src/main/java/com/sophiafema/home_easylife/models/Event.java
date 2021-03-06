package com.sophiafema.home_easylife.models;

import com.sophiafema.home_easylife.Util;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    private int pictureID;
    private String name;
    private String id;
    private ArrayList<EventsRoom> rooms;

    private boolean isActivated;
//    private String day;
//    private int time;

    public Event() {

    }

    public Event(int pictureID, String name, String id, ArrayList<EventsRoom> rooms) {
        this.pictureID = pictureID;
        this.name = name;
        this.id = id;
        this.rooms = rooms;
        this.isActivated = false;
//        this.day = day;
//        this.time = time;
    }

    public Event(int pictureID, String name, String id) {
        this.pictureID = pictureID;
        this.name = name;
        this.id = id;
        fillRooms();
        this.isActivated = false;
    }

    public void fillRooms() {
        this.rooms = new ArrayList<>();
        rooms.add(new EventsRoom(Util.LIVING, 0));
        rooms.add(new EventsRoom(Util.BATH, 1));
        rooms.add(new EventsRoom(Util.KITCHEN, 2));
        rooms.add(new EventsRoom(Util.SLEEPING, 3));
        rooms.add(new EventsRoom(Util.HALLWAY, 4));
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<EventsRoom> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<EventsRoom> rooms) {
        this.rooms = rooms;
    }

    public int numberOfRoomsWithFunction() {
        int number=0;
        for(EventsRoom room : rooms) {
            if(room.hasFunctions()) {
                number++;
            }
        }
        return number;
    }

    public EventsRoom getRoomByName(String name) {
        EventsRoom e;
        for(EventsRoom room : rooms) {
            if(room.getName().equals(name)) {
                e = room;
                return e;
            }
        }
        return null;
    }

    public void setRoomByName(String name, EventsRoom room) {
        for(int i = 0; i<rooms.size(); i++) {
            if(rooms.get(i).getName().equals(name)) {
                rooms.set(i, room);
            }
        }
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }


//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
}
