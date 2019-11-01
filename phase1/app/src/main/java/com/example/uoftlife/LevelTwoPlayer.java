package com.example.uoftlife;

public abstract class LevelTwoPlayer {

    /**
     * name of this player
     */
    private String name;

    /**
     * current step of this player;
     */
    private int curr_step;

    public LevelTwoPlayer(){

    }
    abstract void move(int min_step, int max_step, int curr_step);
}
