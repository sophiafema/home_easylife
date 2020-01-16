package com.sophiafema.home_easylife.models;

import java.io.Serializable;

public class SimpleRoom implements Serializable {

    private String name;
    private int id;


    public SimpleRoom() {}


    public SimpleRoom(String name, int id) {
        this.name = name;
        this.id = id;

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

}
