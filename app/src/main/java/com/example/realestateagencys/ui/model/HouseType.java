package com.example.realestateagencys.ui.model;

import java.io.Serializable;

public class HouseType implements Serializable {

    private String name;
    private String url;

    public HouseType(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HouseType() {

    }



    @Override
    public String toString() {
        return name;
    }
}
