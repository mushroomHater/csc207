package com.example.uoftlife.gamework;

/**
 * The class represents dropping items.
 */
class DropItems {

    /**
     * The type of drop items.
     */
    private String type;

    /**
     * The x coordinate of location.
     */
    private int locationX;

    /**
     * The y coordinate of location.
     */
    private int locationY;

    /**
     * The dropping item speed.
     */
    private double speed;

    /**
     * Instantiates a new Drop items.
     */
    DropItems(String type, int locationX, double speed) {
        this.type = type;
        this.locationX = locationX;
        locationY = 0;
        this.speed = speed;

    }

    /**
     * Gets type.
     */
    String getType() {
        return type;
    }

    /**
     * Gets location x.
     */
    int getLocationX() {
        return locationX;
    }

    /**
     * Drop.
     */
    void drop(int time) {
        locationY += time * speed;

    }

    /**
     * Gets location y.
    */
    int getLocationY() {
        return locationY;
    }
}
