package com.optimus.se.lab;

import java.util.ArrayList;

public class TimeTable {
    private String id = "default";
    private ArrayList<String> items;

    public TimeTable(String id, ArrayList<String> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
}
