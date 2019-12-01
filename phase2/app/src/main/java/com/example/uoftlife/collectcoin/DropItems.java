package com.example.uoftlife.collectcoin;

public class DropItems {
    String type;
    int locationX;
    int locationY;

    public DropItems(String type, int locationX) {
        this.type = type;
        this.locationX = locationX;
        locationY = 0;
    }

    public String getType() {
        return type;
    }

    public int getLocationX() {
        return locationX;
    }

    public void drop(int time) {
        locationY += time * (0.7);

    }
    public int getLocationY() {
        return locationY;
    }
}
