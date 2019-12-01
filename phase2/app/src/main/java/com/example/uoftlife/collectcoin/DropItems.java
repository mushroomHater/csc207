package com.example.uoftlife.collectcoin;

 class DropItems {
    private String type;
    private int locationX;
    private int locationY;
    private double speed;

     DropItems(String type, int locationX) {
        this.type = type;
        this.locationX = locationX;
        locationY = 0;
        speed = 0.7;

    }

     String getType() {
        return type;
    }

     int getLocationX() {
        return locationX;
    }

     void drop(int time) {
        locationY += time * speed;

    }
     int getLocationY() {
        return locationY;
    }
}
