package com.example.realestateagencys.ui.model;

import java.io.Serializable;

public class HouseArrangment implements Serializable {

        private String id;
        private String user;
        private Areah area;
        private String arrangmentDate;
        private House house;
        private String type;


        private boolean isComplete;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public HouseArrangment() {

        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }

        public boolean isComplete() {
            return isComplete;
        }


        public HouseArrangment(String id, String user, House house,  String arrangmentDate,boolean isComplete) {
            this.user = user;
           // this.area = area;
            this.arrangmentDate = arrangmentDate;
            this.house = house;
            //this.type = type;


            this.isComplete = isComplete;
            this.id = id;
        }

        public House getHouse() {
            return house;
        }

        public String getUser() {
            return user;
        }

        public Areah getArea() {
            return area;
        }

        public String getArrangmentDate() {
            return arrangmentDate;
        }

        public String getType() {
        return type;
    }


    }
