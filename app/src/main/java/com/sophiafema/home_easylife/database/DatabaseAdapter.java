package com.sophiafema.home_easylife.database;

import com.sophiafema.home_easylife.LogInActivity;
import com.sophiafema.home_easylife.models.Event;
import com.sophiafema.home_easylife.models.Light;
import com.sophiafema.home_easylife.models.Music;
import com.sophiafema.home_easylife.models.Room;
import com.sophiafema.home_easylife.models.Shutter;
import com.sophiafema.home_easylife.models.SimpleRoom;
import com.sophiafema.home_easylife.models.Thermostat;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * easier for getting data from database
 * -> use databaseadapter for recieving data
 * returns correct objects
 */
public class DatabaseAdapter {
    private Database db;
    private String userId;

    public DatabaseAdapter() {
        this.db = new Database();
        userId = LogInActivity.USER_UID;
    }


    public void setLight(String room, Light l) {
        db.setFunction(userId, room, Database.LIGHT, l,  l.getName());
    }
    public void setMusic(String room, Music m) {
        db.setFunction(userId, room, Database.MUSIC, m,  Database.MUSIC);
    }
    public void setShutter(String room, Shutter l) {
        db.setFunction(userId, room, Database.SHUTTER, l,  l.getName());
    }
    public void setThermostat(String room, Thermostat t) {
        db.setFunction(userId, room, Database.THERMOSTAT, t,  Database.THERMOSTAT);
    }
    public void setRoom(String room, Room r) {
        db.setRoom(userId, new SimpleRoom(r.getName(), r.getId()));

        ArrayList<Light> lights = r.getLights();
        for(Light light: lights) {
            setLight(r.getName(), light);
        }

        if(r.getShutters() != null) {
            ArrayList<Shutter> shutters = r.getShutters();
            for (Shutter shutter : shutters) {
                setShutter(r.getName(), shutter);
            }
        }
        if(r.getMusic() != null)
            setMusic(r.getName(), r.getMusic());
        setThermostat(r.getName(), r.getThermo());
    }

    public void setEvent(Event e) {
        db.setEvent(userId, e, e.getId());
    }


    public void setLightPower(String room, String lightId, boolean value) {
        db.updateSpecificFunction(userId, room, Database.LIGHT, lightId, Light.POWER, value);
    }
    public void setBrightness(String room, String lightId, double value) {
        db.updateSpecificFunction(userId, room, Database.LIGHT, lightId, Light.BRIGHTNESS, value);
    }
    public void setThermostatPower(String room, boolean value) {
        db.updateSpecificFunction(userId, room, Database.THERMOSTAT, Database.THERMOSTAT, Thermostat.POWER, value);
    }
    public void setTemperature(String room, double value) {
        db.updateSpecificFunction(userId, room, Database.THERMOSTAT, Database.THERMOSTAT, Thermostat.TEMPERATURE, value);
    }
    public void setMusicPower(String room, boolean value) {
        db.updateSpecificFunction(userId, room, Database.MUSIC, Database.MUSIC, Music.POWER, value);
    }
    public void setMusicVolume(String room, double value) {
        db.updateSpecificFunction(userId, room, Database.MUSIC, Database.MUSIC, Music.VOLUME, value);
    }
    public void setMusicPlay(String room, boolean value) {
        db.updateSpecificFunction(userId, room, Database.MUSIC, Database.MUSIC, Music.PLAY, value);
    }
    public void setPosition(String room, String louvreId, double value) {
        db.updateSpecificFunction(userId, room, Database.SHUTTER, louvreId, Shutter.POSITION, value);
    }


    public Light getLight(String room, String id) {
        Light l = null;
        try {
            l = (Light) new Database().execute(new Object[] {"function", userId, room, Database.LIGHT, id}).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
        //Light l = (Light) db.getFunction(userId, room, Database.LIGHT, id);
        return l;
    }
    public Shutter getShutter(String room, String id) {
        //Shutter l = (Shutter) db.getFunction(userId, room, Database.SHUTTER, id);
        Shutter l = null;
        try {
            l = (Shutter) new Database().execute(new Object[] {"function", userId, room, Database.SHUTTER, id}).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        ;
        return l;
    }
    public Music getMusic(String room) {
        //Music m = (Music) db.getFunction(userId, room, Database.MUSIC, Database.MUSIC);
        Music m = null;
        try {
            m = (Music) new Database().execute(new Object[] {"function", userId, room, Database.MUSIC, Database.MUSIC}).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        };

        return m;
    }
    public Thermostat getThermostat(String room) {
        //Thermostat t = (Thermostat) db.getFunction(userId, room, Database.THERMOSTAT, Database.THERMOSTAT);
        Thermostat t = null;
        try {
            t = (Thermostat) new Database().execute(new Object[] {"function", userId, room, Database.THERMOSTAT, Database.THERMOSTAT}).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return t;
    }

    public Room getSRoom(String room) {

        Room r = null;
        try {
            r = (Room) new Database().execute(new Object[] {"room", userId, room}).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r;
    }

    public Room getRoom(String room) {

        SimpleRoom d = null;
        try {
            d = (SimpleRoom) db.getRoom(userId, room);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //SimpleRoom d = (SimpleRoom) db.getRoom(userId, room);
        Room r = new Room();
        r.setId(d.getId());
        r.setName(d.getName());
        r.setLights(getLights(d.getName()));
        r.setShutters(getShutters(d.getName()));
        r.setThermo(getThermostat(d.getName()));
        r.setMusic(getMusic(d.getName()));
        return r;
    }

    public Event getEvent(String event) {
        Event e = null;
        try {
            e = (Event) new Database().execute(new Object[] {"event", userId, event}).get();
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }

        return e;
    }

    public ArrayList<Light> getLights(String room) {
        //SimpleRoom d = (SimpleRoom) new Database().execute(new Object[] {"room", userId, room}).get();
        ArrayList<Object> o = new ArrayList<>();
        ArrayList<Light> l = new ArrayList<>();
        try {
            Object obj = new Database().execute(new Object[] {"functions", userId, room, Database.LIGHT}).get();
            if (obj instanceof ArrayList<?>) {
                // Get the List.
                ArrayList<?> al = (ArrayList<?>) obj;
                if (al.size() > 0) {
                    // Iterate.
                    for (int i = 0; i < al.size(); i++) {
                        // Still not enough for a type.
                        Object object = al.get(i);
                        if (object instanceof Light) {
                            Light v = (Light) object;
                            l.add(v);
                        }
                    }
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return l;
    }
    public ArrayList<Shutter> getShutters(String room) {
        ArrayList<Object> o = new ArrayList<>();
        ArrayList<Shutter> l = new ArrayList<>();
        try {
            Object obj = new Database().execute(new Object[] {"functions", userId, room, Database.SHUTTER}).get();
            //System.out.println("Object " +obj);
            if (obj instanceof ArrayList<?>) {
                // Get the List.
                ArrayList<?> al = (ArrayList<?>) obj;
                if (al.size() > 0) {
                    // Iterate.
                    for (int i = 0; i < al.size(); i++) {
                        // Still not enough for a type.
                        Object object = al.get(i);
                        if (object instanceof Shutter) {
                            // Here we go!
                            Shutter v = (Shutter) object;
                            l.add(v);
                            // use v.
                        }
                    }
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return l;
    }
    public ArrayList<Room> getRooms() {
        //ArrayList<String> roomIds = db.getAllRoomDocId(userId);
        ArrayList<String> roomIds = getRoomIds();
        ArrayList<Room> rooms = new ArrayList<>();
        for(String roomId : roomIds) {
            rooms.add(getRoom(roomId));
        }
        return rooms;
    }

    private ArrayList<String> getRoomIds() {
        ArrayList<String> roomIds = new ArrayList<>();
        try {
            Object obj = new Database().execute(new Object[] {"roomDoc", userId}).get();

            if (obj instanceof ArrayList<?>) {
                // Get the List.
                ArrayList<?> al = (ArrayList<?>) obj;
                if (al.size() > 0) {
                    // Iterate.
                    for (int i = 0; i < al.size(); i++) {
                        // Still not enough for a type.
                        Object object = al.get(i);
                        if (object instanceof String) {
                            // Here we go!
                            String v = (String) object;
                            roomIds.add(v);
                        }
                    }
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return roomIds;
    }


    private ArrayList<String> getFunctionIds(String roomId, String function) {
        ArrayList<String> functionIds = new ArrayList<>();
        try {
            Object obj = new Database().execute(new Object[] {"functionDoc", userId, roomId, function}).get();
            if (obj instanceof ArrayList<?>) {
                // Get the List.
                ArrayList<?> al = (ArrayList<?>) obj;
                if (al.size() > 0) {
                    // Iterate.
                    for (int i = 0; i < al.size(); i++) {
                        // Still not enough for a type.
                        Object object = al.get(i);
                        if (object instanceof String) {
                            // Here we go!
                            String v = (String) object;
                            functionIds.add(v);
                        }
                    }
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return functionIds;
    }

    public ArrayList<Event> getEvents() {
        //return db.getAllEvents(userId);
        ArrayList<Event> events = db.getAllEvents(userId);
        return events;
    }

    //ZENTRALSTEUERUNG
    public void setAllLightsPower(boolean value) {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            setAllLightsInOneRoomPower(roomId, value);
        }
    }
    public void setAllLightsInOneRoomPower(String roomId, boolean value) {
        ArrayList<String> lightIds = getFunctionIds(roomId, Database.LIGHT);
        for(String lightId : lightIds) {
            setLightPower(roomId, lightId, value);
        }
    }

    public void setAllThermoPower(boolean value) {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            setThermostatPower(roomId, value);
        }
    }

    public void setAllMusicPower(boolean value) {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            setMusicPower(roomId, value);
        }
    }

    public void setAllShutterPosition(double value) {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            setAllShutterPositionInOneRoom(roomId, value);
        }
    }
    public void setAllShutterPositionInOneRoom(String roomId, double value) {
        ArrayList<String> louvreIds = getFunctionIds(roomId, Database.SHUTTER);
        for(String louvreId : louvreIds) {
            setPosition(roomId, louvreId, value);
        }
    }

    public boolean getAllLightsPower() {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            if(getAllLightsInOneRoomPower(roomId))
                return true;
        }
        return false;
    }
    public boolean getAllLightsInOneRoomPower(String roomId) {
        ArrayList<String> lightIds = getFunctionIds(roomId, Database.LIGHT);
        System.out.println(lightIds);
        for(String lightId : lightIds) {
            Light l = getLight(roomId, lightId);
            //System.out.println(lightId);
            if(l.isOn()) {
                return true;
            }
        }
        return false;
    }

    public boolean getAllThermostatPower() {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            Thermostat t = getThermostat(roomId);
            if(t.isPower())
                return true;
        }
        return false;
    }

    public boolean getAllMusicPower() {
        ArrayList<String> roomIds = getRoomIds();
        for(String roomId : roomIds) {
            Music m = getMusic(roomId);
            if(m != null) {
                if (m.isPower())
                    return true;
            }
        }
        return false;
    }

    public double getAllShutterPosition() {
        ArrayList<String> roomIds = getRoomIds();
        double position = 0;
        for(String roomId : roomIds) {
            double pos = getAllShutterPositionInOneRoom(roomId);
            if(pos > -1)
                position += pos;
        }
        position /= roomIds.size();
        return position;
    }
    public double getAllShutterPositionInOneRoom(String roomId) {
        ArrayList<String> shutterIds = getFunctionIds(roomId, Database.SHUTTER);
        double position = 0;
        if(shutterIds != null) {
            for(String shutterId : shutterIds) {
                Shutter l = getShutter(roomId, shutterId);
                if(l != null)
                    position += l.getPosition();
            }
            position /= shutterIds.size();
            if(shutterIds.size() < 1)
                position = -1;
        }
        else
            position = -1;

        return position;
    }

}
