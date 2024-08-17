package com.hsbc.bookit.domain;


public class Rooms {
    private int id;
    private String name;
    private int seatingCapacity;

    public Rooms(String name, int seatingCapacity) {
        this.name = name;
        this.seatingCapacity = seatingCapacity;
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

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return "Room{id=" + id + ", name='" + name + '\'' + ", seatingCapacity=" + seatingCapacity + '}';
    }
}

