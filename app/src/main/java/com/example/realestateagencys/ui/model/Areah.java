package com.example.realestateagencys.ui.model;

import java.io.Serializable;

 public class Areah implements Serializable {
    private String id;
    private String address;
    private String city;
    private String state;
    private String distance;

    public Areah() {

    }


    public Areah(String id, String address, String city, String state, String distance) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDistance() {
        return distance;
    }
}
