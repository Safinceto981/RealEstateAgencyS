package com.example.realestateagencys.ui.model;


import java.io.Serializable;


public class House implements Serializable {

    private final String id;
    private final String name;
    private final HouseType type;
    private final Areah area;


    public House() {
        this.id = "";
        this.name = "";
        this.type = null;
        this.area = null;

    }

    public House( String id, String name, HouseType type, Areah area) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.area = area;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HouseType getType() {
        return type;
    }



    public Areah getArea() {
        return area;
    }




}



