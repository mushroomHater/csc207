package com.example.uoftlife;

import android.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameSleepModel {
    /**
     * The target number of click to pass the game level.
     */
    private static int targetClick = 45;

    /**
     * The tag of the game level.
     */
    private static final String TAG = "LevelOne";

    /**
     * The number of clicks entered by the user.
     */
    private int clickAmount = 0;

    /**
     * The x coordinate of the alarm button.
     */
    private float dx;

    /**
     * The y coordinate of the alarm button.
     */
    private float dy;

    /**
     * The score the user gets in this game level
     */
    private int score;


    /**
     * Creates a LevelOne instance.
     */
    GameSleepModel(float dx, float dy) {
     this.dx = dx;
     this.dy = dy;
    }

    /**
     * @return the target number of clicks in this game level.
     */
    static int getTargetClick() {
        return targetClick;
    }

    /**
     * Sets the target number of clicks in this game level.
     */
    static void setTargetClick(int t) {
        GameSleepModel.targetClick = t;
    }

    /**
     * @return the number of clicks a user has entered.
     */
    int getClickAmount() {
        return clickAmount;
    }

    /**
     * Adds the number of clicks.
     */
    void addClickAmount() {
        clickAmount++;
    }

    /**
     * @return the score of the game level.
     */
    int getScore() {
        return score;
    }

    /**
     * Sets the score of the game level.
     */
    void setScore(int score) {
        this.score = score;
    }

    /**
     * @return random location for the alarm button.
     */
    List<Float> generateRandomloc(){
        List<Float> result = new ArrayList<>();
        Random R = new Random();
        final float dx = R.nextFloat() * this.dx;
        final float dy = R.nextFloat() * this.dy;
        result.add(dx);
        result.add(dy);
        return result;
    }
}


