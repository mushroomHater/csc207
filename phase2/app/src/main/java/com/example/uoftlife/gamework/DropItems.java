package com.example.uoftlife.gamework;

 class DropItems {
    private String type;
    private int locationX;
    private int locationY;
    private double speed;

     DropItems(String type, int locationX, double speed) {
        this.type = type;
        this.locationX = locationX;
        locationY = 0;
        this.speed = speed;

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
